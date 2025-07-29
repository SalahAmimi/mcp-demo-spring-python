package test.demo.mcpserver;

import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import test.demo.mcpserver.tools.StockTolls;

@SpringBootApplication
public class McpServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public MethodToolCallbackProvider getMethodToolCallbackProvider(StockTolls stockTolls) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(stockTolls)
                .build();
    }
}
