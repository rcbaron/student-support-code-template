package ast;

import java.util.List;

public record While(String condition, List<AST> body) implements AST {}
