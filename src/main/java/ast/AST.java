package ast;

public sealed interface AST permits Program, Assign, If, While {}
