import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import backend.*;
import backend.functions.*;
import backend.translation.*;
import exp.*;
import ast.*;
import frontend.*;
import backend.variables.*;
import backend.registers.*;

public class Main {
static public void main(String argv[]) {
        // À mettre dans une IHM
        Ihm ihm = new Ihm(argv);
        try {
              Parser p = new Parser(new Lexer(new FileReader(argv[0])));
              Exp expression = (Exp) p.parse().value;
              assert (expression != null);

              if (ihm.given_output){
              new Outgesture(ihm.output_file);
              }

              int height = Height.computeHeight(expression);

              if (ihm.ast || ihm.parse_only) {
                  System.out.println("------ AST ------");
                  expression.accept(new PrintVisitor());
                  System.out.println();

                  System.out.println("------ Height of the AST ------");
                  System.out.println("using Height.computeHeight: " + height);
                  ObjVisitor<Integer> v = new HeightVisitor();
                  height = expression.accept(v);
                  System.out.println("using HeightVisitor: " + height);
                  if (ihm.parse_only) {
                      System.exit(0);
                  }
              }


              /* For evaluation :
                 System.out.println("------ Evaluation ------");
                 System.out.println("Ceci est le résultat : " + expression.accept(new EvaluationVisitor()));
               */

              // For KNormalization :
              else if (ihm.knorm) {
                      Exp expression_normalized = expression.accept(new KNormalization());
                      System.out.println("------ K-Normalization ------");
                      expression_normalized.accept(new PrintVisitor());
                      System.out.println("");
              }

              //For AlphaConversion :
              else if (ihm.alpha_conversion) {
                      Exp expression_converted = expression.accept(new AlphaConversion());
                      System.out.println("------ AlphaConversion ------");
                      //Exp expression_normalized = expression.accept(new KNormalization());
                      //Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                      expression_converted.accept(new PrintVisitor());
                      System.out.println("");
              }

              // For Reduction of Nested Let-Expressions
              else if (ihm.reduction) {
                      Exp expression_reducted = expression.accept(new ReductionNestedExpression());
                      System.out.println("------ Reduction of Nested Let-Expressions ------");
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

              else if (ihm.arm){
                Exp expression_reducted = expression.accept(new ReductionNestedExpression());
                System.out.println("@------ ARM ------");
                expression_reducted.accept(new PrintVisitor());
                System.out.println("");
              }

              else{
                Exp expression_normalized = expression.accept(new KNormalization());
                Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());
                HashMap<Register, Variable> registers = new HashMap<Register, Variable>(9);
                HashMap<Register, Variable> parametersRegisters = new HashMap<Register, Variable>(4);
                RegisterUtils.initRegisters(registers, parametersRegisters);


                Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters);
                TranslationVisitor tv = new TranslationVisitor();
                tv.visit(expression_reducted, func);
                System.out.println("------ Translation to Jerry ------");
                func.show();
                System.out.println("");



                RegisterAllocation regalloc = new RegisterAllocation();
                regalloc.VBA(func);
                System.out.println("------ Register Allocation ------");
                func.showVariablesState();
                System.out.println("");

                System.out.println("------ ARM code generation ------");
                List<Function> flist = new ArrayList<Function>();
                flist.add(func);
                ArmGenerator arm = new ArmGenerator();
                arm.generate_code(flist);
                StringBuilder text = arm.textSection.text;
                System.out.println(text);

                try (FileOutputStream oS = new FileOutputStream(new File("../ARM/output.s"))) {
                 oS.write(text.toString().getBytes());
               } catch (IOException e) {
                  e.printStackTrace();
                }
              }

        } catch (Exception e) {
                e.printStackTrace();
        }
}
}
