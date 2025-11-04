import ast.*;

public class ASTPrettyPrinter {
  private int indent = 0;

  private void out(String s) {
    System.out.println(" ".repeat(indent * 2) + s);
  }

  public void print(AST n) {
    switch (n) {
      case Program p -> {
        out("Program");
        indent++;
        p.statements().forEach(this::print);
        indent--;
      }
      case Assign a -> out("Assign " + a.name() + " = " + a.expr());
      case If i -> {
        out("If " + i.condition());
        indent++;
        out("Do:");
        indent++;
        i.thenBlock().forEach(this::print);
        indent--;
        if (!i.elseBlock().isEmpty()) {
          out("Else:");
          indent++;
          i.elseBlock().forEach(this::print);
          indent--;
        }
        indent--;
      }
      case While w -> {
        out("While " + w.condition());
        indent++;
        w.body().forEach(this::print);
        indent--;
      }
    }
  }
}
