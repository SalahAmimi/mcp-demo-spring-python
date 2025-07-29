package test.demo.mcpclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import test.demo.mcpclient.agents.AIAgent;

//@Controller
//@CrossOrigin("*")
public class ChatController {
    private final AIAgent agent;

    public ChatController(AIAgent agent) {
        this.agent = agent;
    }

    @PostMapping("/chat/send")
    @ResponseBody
    public  String sendMessage(String message) {
        return agent.askLLM(message);
    }




}
