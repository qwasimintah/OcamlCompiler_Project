import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import frontend.*;

public class Main {
  static public void main(String argv[]) {
    // À mettre dans une IHM
    Boolean knorm_bool = false, ast_bool = false, reduction_bool = false;
    for (String arg : argv){
        switch(arg){
          case "--ast":
            ast_bool = true;
            break;
          case "--knorm":
            knorm_bool = true;
            break;
          case "--reduction":
            reduction_bool = true;
          default:
            break;
        }
    }
    try {
      Parser p = new Parser(new Lexer(new FileReader(argv[0])));
      Exp expression = (Exp) p.parse().value;
      assert (expression != null);

      if (ast_bool){
          System.out.println("------ AST ------");
          expression.accept(new PrintVisitor());
          System.out.println();

          System.out.println("------ Height of the AST ------");
          int height = Height.computeHeight(expression);
          System.out.println("using Height.computeHeight: " + height);

          ObjVisitor<Integer> v = new HeightVisitor();
          height = expression.accept(v);
          System.out.println("using HeightVisitor: " + height);
      }
      /* For evaluation :
      System.out.println("------ Evaluation ------");
      System.out.println("Ceci est le résultat : " + expression.accept(new EvaluationVisitor()));
      */

      // For KNormalization :
      if (knorm_bool){
        System.out.println("------ K-Normalization ------");
        Exp expression_normalized = expression.accept(new KNormalization());
        expression_normalized.accept(new PrintVisitor());
        System.out.println("");
      }

      /*//For AlphaConversion :
      System.out.println("------ AlphaConversion ------");
      Exp expression_converted = expression.accept(new AlphaConversion());
      System.out.println("Ceci est le résultat : ");
      expression_converted.accept(new PrintVisitor());
      System.out.print("\n");*/

      /*//for Reduction of Nested Let-Expressions
      if (reduction_bool){
        System.out.println("------ Reduction of Nested Let-Expressions ------");
        Exp expression_reducted = expression.accept(new ReductionNestedExpression());
        expression_reducted.accept(new PrintVisitor());
        System.out.println("");
      }*/
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
