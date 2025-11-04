package ast;

import java.util.List;

public record Program(List<AST> statements) implements AST {}
