package test.demo.mcpclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;
import test.demo.mcpclient.agents.AIAgent;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class AIRestController {

    private AIAgent agent;

    public AIRestController(AIAgent agent) {
        this.agent = agent;
    }



//    @Operation(summary = "Pose une question au LLM", description = "Utilise l'agent AI pour répondre à une requête texte.")
//    @GetMapping("/chat")
//    public String chat(@Parameter(description = "La requête utilisateur") @RequestParam String query) {
//        return agent.askLLM(query);
//    }

    @Operation(summary = "Pose une question au LLM", description = "Utilise l'agent AI pour répondre à une requête texte.")
    @GetMapping("/chat")
    public String chat(@RequestParam String query) {
        return agent.askLLM(query);
    }
}
