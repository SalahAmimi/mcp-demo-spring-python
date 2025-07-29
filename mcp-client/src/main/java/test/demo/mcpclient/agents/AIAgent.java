package test.demo.mcpclient.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.execution.ToolExecutionException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

@Service
public class AIAgent {

    private ChatClient chatClient;

    public AIAgent (ChatClient.Builder chatClient, ToolCallbackProvider toolCallbackProvider) {
        this.chatClient = chatClient
                .defaultToolCallbacks(toolCallbackProvider)
                .defaultSystem("Answer the user question using provided tools")
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                        MessageWindowChatMemory.builder().maxMessages(20).build()
                ).build())
                .build();
    }

//    public String askLLM(String query) {
//        return chatClient.prompt()
//                .user(query)
//                .call()
//                .content();
//    }


    public String askLLM(String query) {
        try {
            return chatClient.prompt()
                    .user(query)
                    .call()
                    .content();
        } catch (ToolExecutionException e) {
            return "Erreur lors de l'exécution de l'outil : " + e.getMessage();
        } catch (Exception e) {
            return "Une erreur s’est produite : " + e.getMessage();
        }
    }



}
