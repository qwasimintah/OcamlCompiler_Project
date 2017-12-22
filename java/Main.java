import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import backend.functions.*;
import backend.translation.*;
import exp.*;
import ast.*;
import frontend.*;

public class Main {
  public static void main(String argv[]) {
    // temp :
    Integer translation = 1;
    Height height_computer = new Height();
    // À mettre dans une IHM
    Ihm ihm = new Ihm(argv);
    try {
      Parser p = new Parser(new Lexer(new FileReader(argv[0])));
      Exp expression = (Exp) p.parse().value;
      assert (expression != null);

      if (ihm.ast){
          System.out.println("------ AST ------");
          expression.accept(new PrintVisitor());
          System.out.println();

          System.out.println("------ Height of the AST ------");
          int height = height_computer.computeHeight(expression);
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
      if (ihm.knorm){
        System.out.println("------ K-Normalization ------");
        Exp expression_normalized = expression.accept(new KNormalization());
        expression_normalized.accept(new PrintVisitor());
        System.out.println("");
      }

      //For AlphaConversion :
      if (ihm.alpha_conversion){
        System.out.println("------ AlphaConversion ------");
        //Exp expression_normalized = expression.accept(new KNormalization());
        //Exp expression_converted = expression_normalized.accept(new AlphaConversion());
        Exp expression_converted = expression.accept(new AlphaConversion());
        expression_converted.accept(new PrintVisitor());
        System.out.println("");
      }

      //For Reduction of Nested Let-Expressions
      if (ihm.reduction){
        System.out.println("------ Reduction of Nested Let-Expressions ------");
        Exp expression_reducted = expression.accept(new ReductionNestedExpression());
        expression_reducted.accept(new PrintVisitor());
        System.out.println("");
      }

      //For translation to Jerry
      if (translation == 1) {
        System.out.println("------ Translation to Jerry ------");
        TranslationVisitor tv = new TranslationVisitor();
        Function func = new Function("main", new ArrayList(), new ArrayList());
        tv.visit((Exp) expression, func);
        System.out.println("");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
