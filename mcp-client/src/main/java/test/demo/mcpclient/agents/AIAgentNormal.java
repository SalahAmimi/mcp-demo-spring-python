package test.demo.mcpclient.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AIAgentNormal {

    private ChatClient chatClient;


    public AIAgentNormal(ChatClient.Builder chatClient) {
        this.chatClient = chatClient
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(
                        MessageWindowChatMemory.builder().maxMessages(20).build()
                ).build())
                .build();
    }

    public Flux<String> onQueryStream(String query) {
                String response = chatClient.prompt()
                        .user(query)
                        .call()
                        .content();
                return Flux.just(response);

    }


    public String onQuery(String query) {
        return chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
