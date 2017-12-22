import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import backend.*;
import backend.functions.*;
import backend.translation.*;
import exp.*;
import ast.*;
import frontend.*;

public class Main {
static public void main(String argv[]) {
        // À mettre dans une IHM
        Ihm ihm = new Ihm(argv);
        try {
                Parser p = new Parser(new Lexer(new FileReader(argv[0])));
                Exp expression = (Exp) p.parse().value;
                assert (expression != null);

                if (ihm.given_output) {
                        // System.out.println("hello;");
                        new Outgesture(ihm.output_file);
                }

                if (ihm.ast || ihm.parse_only) {
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

                if (ihm.parse_only) {
                        System.exit(0);
                }
                /* For evaluation :
                   System.out.println("------ Evaluation ------");
                   System.out.println("Ceci est le résultat : " + expression.accept(new EvaluationVisitor()));
                 */

                // For KNormalization :
                if (ihm.knorm) {
                        System.out.println("------ K-Normalization ------");
                        Exp expression_normalized = expression.accept(new KNormalization());
                        expression_normalized.accept(new PrintVisitor());
                        System.out.println("");
                }

                //For AlphaConversion :
                if (ihm.alpha_conversion) {
                        System.out.println("------ AlphaConversion ------");
                        //Exp expression_normalized = expression.accept(new KNormalization());
                        //Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_converted = expression.accept(new AlphaConversion());
                        expression_converted.accept(new PrintVisitor());
                        System.out.println("");
                }

                // For Reduction of Nested Let-Expressions
                if (ihm.reduction) {
                        System.out.println("------ Reduction of Nested Let-Expressions ------");
                        Exp expression_reducted = expression.accept(new ReductionNestedExpression());
                        expression_reducted.accept(new PrintVisitor());
                        System.out.println("");
                }

                // if (true) {
                //         System.out.println("------ Translation to Jerry ------");
                //         TranslationVisitor tv = new TranslationVisitor();
                //         Function func = new Function("main", new ArrayList(), new ArrayList());
                //         tv.visit((Exp) expression, func);
                //         System.out.println("");
                // }

                if (ihm.arm) {
                        System.out.println("@------ ARM ------");
                        Exp expression_reducted = expression.accept(new ReductionNestedExpression());
                        expression_reducted.accept(new PrintVisitor());
                        System.out.println("");
                }

                if (ihm.complete) {
                        System.out.println("------ Complete Compilation ------");
                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted  = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted   = expression_converted.accept(new ReductionNestedExpression());
                        TranslationVisitor tv = new TranslationVisitor();
                        Function func = new Function("main", new ArrayList(), new ArrayList());
                        tv.visit((Exp) expression, func);
                        RegisterAllocation allocator = new RegisterAllocation();
                        allocator.LinearScan(func);
                        // Insert ARM code generation here

                }

        } catch (Exception e) {
                e.printStackTrace();
        }
}
}
