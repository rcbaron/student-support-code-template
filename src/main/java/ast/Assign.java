package ast;

public record Assign(String name, String expr) implements AST {}
