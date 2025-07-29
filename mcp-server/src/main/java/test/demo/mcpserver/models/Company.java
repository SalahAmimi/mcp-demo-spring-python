package test.demo.mcpserver.models;


import org.springframework.context.annotation.Description;

public record Company(String name, String activity,
                      @Description("The turnover in billions of EURO ")
                      double turnover,
                      int employeesCount,
                      String country) {
}
