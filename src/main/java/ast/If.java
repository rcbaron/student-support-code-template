package ast;

import java.util.List;

public record If(String condition, List<AST> thenBlock, List<AST> elseBlock) implements AST {}
