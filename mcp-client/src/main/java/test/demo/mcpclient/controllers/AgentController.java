package test.demo.mcpclient.controllers;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import test.demo.mcpclient.agents.AIAgent;
import test.demo.mcpclient.agents.AIAgentNormal;

import java.awt.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/agent")
public class AgentController {

    private AIAgentNormal agent;

    public AgentController(AIAgentNormal agent) {
        this.agent = agent;
    }

    @GetMapping(value = "/askAgent", produces = MediaType.TEXT_PLAIN_VALUE)
    public String askAgent(@RequestParam(defaultValue = "Hello") String query) {
        return agent.onQuery(query);
    }


    @GetMapping(value = "/askAgent/stream", produces = MediaType.TEXT_PLAIN_VALUE)
    public Flux<String> askAgentStreamMode(@RequestParam(defaultValue = "Hello") String query) {
        return agent.onQueryStream(query);
    }
}
