import {Component, ElementRef, ViewChild } from '@angular/core';
import {FormsModule} from '@angular/forms';
import { CommonModule } from '@angular/common';
import {HttpClient, HttpDownloadProgressEvent, HttpEventType} from '@angular/common/http';
import {MarkdownComponent} from 'ngx-markdown';

@Component({
  selector: 'app-chat',
  imports: [
    FormsModule,
    MarkdownComponent,
    CommonModule
  ],
  templateUrl: './chat.component.html',
  standalone: true,
  styleUrl: './chat.component.css'
})
export class ChatComponent {
  query: String ="";
  response: any;
  progress: boolean = false;
  messages : any[] = [];



  constructor(private http: HttpClient) {
  }

  askAgent() {
    const userQuery = this.query;
    if (!userQuery || this.progress) return;
    this.response = "";
    this.progress = true;
    this.messages.push({ role: 'user', content: userQuery });
    this.http.get("http://localhost:8866/test/chat?query="+this.query,
      {responseType: 'text'})
      .subscribe({
        next : resp =>{
          this.response =  resp;
          console.log("---------------- ", resp);
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          this.progress = false;
          // Add assistant message
          this.messages.push({ role: 'assistant', content: this.response });
          this.query = "";
        }
      })
  }

  askAgentStream() {
    const userQuery = this.query;
    if (!userQuery || this.progress) return;
    this.response = "";
    this.progress = true;
    // Add user message
    this.messages.push({ role: 'user', content: userQuery });
    this.http.get("http://localhost:8866/agent/askAgent/stream?query=" + userQuery,
      { responseType: 'text', observe: "events", reportProgress: true })
      .subscribe({
        next: evt => {
          if (evt.type === HttpEventType.DownloadProgress) {
            this.response = (evt as HttpDownloadProgressEvent).partialText;
            console.log("Progress--------: ", this.response);
          }
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          this.progress = false;
          // Add assistant message
          this.messages.push({ role: 'assistant', content: this.response });
          this.query = "";
        }
      });
  }
}
