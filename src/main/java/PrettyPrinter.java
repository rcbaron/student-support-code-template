public class PrettyPrinter extends MyLanguageBaseVisitor<String> {
  private final StringBuilder sb = new StringBuilder();
  private int indent = 0;

  private void newline() {
    sb.append("\n").append(" ".repeat(indent * 4)); // 4 spaces per level
  }

  private void write(String text) {
    sb.append(text);
  }

  @Override
  public String visitProgramm(MyLanguageParser.ProgrammContext ctx) {
    for (int i = 0; i < ctx.statement().size(); i++) {
      visit(ctx.statement(i));
      if (i < ctx.statement().size() - 1) newline();
    }
    return sb.toString();
  }

  @Override
  public String visitAssignment(MyLanguageParser.AssignmentContext ctx) {
    write(ctx.ID().getText() + " := " + visit(ctx.expr()));
    return null;
  }

  @Override
  public String visitIfStatement(MyLanguageParser.IfStatementContext ctx) {
    write("if " + visit(ctx.condition()) + " do");
    indent++;

    // Anzahl THEN-Statements bis zum ELSE
    int thenCount = ctx.ELSE() == null ? ctx.statement().size() : ctx.statement().size() / 2;

    // THEN-Block
    for (int i = 0; i < thenCount; i++) {
      newline();
      visit(ctx.statement(i));
    }

    indent--;

    // ELSE-Block falls vorhanden
    if (ctx.ELSE() != null) {
      newline();
      write("else do");
      indent++;

      for (int i = thenCount; i < ctx.statement().size(); i++) {
        newline();
        visit(ctx.statement(i));
      }

      indent--;
      newline();
    }

    write("end");
    return null;
  }

  @Override
  public String visitWhileStatement(MyLanguageParser.WhileStatementContext ctx) {
    write("while " + visit(ctx.condition()) + " do");
    indent++;
    for (var st : ctx.statement()) {
      newline();
      visit(st);
    }
    indent--;
    newline();
    write("end");
    return null;
  }

  @Override
  public String visitCondition(MyLanguageParser.ConditionContext ctx) {
    return visit(ctx.expr(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expr(1));
  }

  @Override
  public String visitExpr(MyLanguageParser.ExprContext ctx) {
    if (ctx.INT() != null) return ctx.INT().getText();
    if (ctx.ID() != null) return ctx.ID().getText();
    if (ctx.STRING() != null) return ctx.STRING().getText();

    if (ctx.expr().size() == 2) {
      return visit(ctx.expr(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expr(1));
    }
    return "(" + visit(ctx.expr(0)) + ")";
  }
}
