import ast.*;
import java.util.ArrayList;
import java.util.List;

public class ASTBuilder extends MyLanguageBaseVisitor<AST> {

  @Override
  public AST visitProgramm(MyLanguageParser.ProgrammContext ctx) {
    List<AST> stmts = new ArrayList<>();
    for (var st : ctx.statement()) stmts.add(visit(st));
    return new Program(stmts);
  }

  @Override
  public AST visitAssignment(MyLanguageParser.AssignmentContext ctx) {
    return new Assign(
        ctx.ID().getText(), ctx.expr().getText() // raw expr text
        );
  }

  @Override
  public AST visitIfStatement(MyLanguageParser.IfStatementContext ctx) {

    int thenSize = ctx.ELSE() == null ? ctx.statement().size() : ctx.statement().size() / 2;

    // then-block
    List<AST> thenBlock = new ArrayList<>();
    for (int i = 0; i < thenSize; i++) thenBlock.add(visit(ctx.statement(i)));

    // else-block
    List<AST> elseBlock = new ArrayList<>();
    if (ctx.ELSE() != null)
      for (int i = thenSize; i < ctx.statement().size(); i++)
        elseBlock.add(visit(ctx.statement(i)));

    return new If(
        ctx.condition().getText(), // condition as text
        thenBlock,
        elseBlock);
  }

  @Override
  public AST visitWhileStatement(MyLanguageParser.WhileStatementContext ctx) {
    List<AST> body = new ArrayList<>();
    for (var st : ctx.statement()) body.add(visit(st));

    return new While(ctx.condition().getText(), body);
  }
}
