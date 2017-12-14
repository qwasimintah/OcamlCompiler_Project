import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import frontend.*;

public class Main {
  static public void main(String argv[]) {
    try {
      Parser p = new Parser(new Lexer(new FileReader(argv[0])));
      Exp expression = (Exp) p.parse().value;
      assert (expression != null);

      System.out.println("------ AST ------");
      expression.accept(new PrintVisitor());
      System.out.println();

      System.out.println("------ Height of the AST ------");
      int height = Height.computeHeight(expression);
      System.out.println("using Height.computeHeight: " + height);

      ObjVisitor<Integer> v = new HeightVisitor();
      height = expression.accept(v);
      System.out.println("using HeightVisitor: " + height);

      /* For evaluation :
      System.out.println("------ Evaluation ------");
      System.out.println("Ceci est le résultat : " + expression.accept(new EvaluationVisitor()));
      */

      /* For KNormalization :
      System.out.println("------ K-Normalization ------");
      Exp expression_normalized = expression.accept(new KNormalization());
      System.out.println("Ceci est le résultat : ");
      expression_normalized.accept(new PrintVisitor());
      */

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
