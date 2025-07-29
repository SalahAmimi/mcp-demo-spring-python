package test.demo.mcpserver.models;

import java.time.LocalDate;

public record Stock(String companyName, LocalDate date, double stock) {
}
