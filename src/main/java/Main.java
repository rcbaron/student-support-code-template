import ast.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {
  public static void main(String[] args) {
    String input =
        """
        a     := 0
            if    10 < 1
               do
        a    :=     42
        else do
                a :=      7
          end
        """;

    // Lexer & Parser
    MyLanguageLexer lexer = new MyLanguageLexer(CharStreams.fromString(input));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    MyLanguageParser parser = new MyLanguageParser(tokens);

    // Parse tree
    ParseTree tree = parser.programm();

    // Pretty printer
    PrettyPrinter printer = new PrettyPrinter();
    String formatted = printer.visit(tree);

    // Output
    System.out.println(" --- Original ---");
    System.out.println(input);

    System.out.println("\n --- Formatted ---");
    System.out.println(formatted);

    // ASTPrettyPrinter
      System.out.println("\n --- AST Print ---");
    AST ast = new ASTBuilder().visit(tree);
    new ASTPrettyPrinter().print(ast);
  }
}
