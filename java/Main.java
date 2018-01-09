import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import backend.*;
import backend.functions.*;
import backend.translation.*;
import exp.*;
import ast.*;
import exceptions.*;
import frontend.*;
import backend.variables.*;
import backend.registers.*;

public class Main {
static public void main(String argv[]) {
        Ihm ihm = new Ihm(argv);
        try {
                // System.out.println(ihm.output_file);
                // System.out.println(ihm.input_file);
                // System.out.println(ihm.output_asml);
                if (ihm.typecheck_only){
                  throw new NotYetImplemented();
                }
                Parser p = new Parser(new Lexer(new FileReader(ihm.input_file)));
                Exp expression = (Exp) p.parse().value;
                // assert (expression != null);
                int height = Height.computeHeight(expression);


                if (ihm.given_output) {
                        new Outgesture(ihm.output_file);
                }



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

                else if (ihm.arm) {

                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());


                        TreeMap<Register, Variable> registers = new TreeMap<Register, Variable>();
                        TreeMap<Register, Variable> parametersRegisters = new TreeMap<Register, Variable>();
                        RegisterUtils.initRegisters(registers, parametersRegisters);

                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters);
                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);

                        RegisterAllocation regalloc = new RegisterAllocation();
                        regalloc.VBA(func);


                        System.out.println("@------ ARM------");
                        List<Function> flist = new ArrayList<Function>();
                        flist.add(func);
                        ArmGenerator arm = new ArmGenerator();
                        arm.generate_code(flist);
                        StringBuilder text = arm.textSection.text;
                        System.out.println(text);

                } else if (ihm.output_asml){
                  Exp expression_normalized = expression.accept(new KNormalization());
                  Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                  Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());
                  System.out.println("------ AST ------");
                  expression.accept(new PrintVisitor());
                  System.out.println("");

                  System.out.println("------ K-Normalization ------");
                  expression_normalized.accept(new PrintVisitor());
                  System.out.println("");

                  System.out.println("------ AlphaConversion ------");
                  expression_converted.accept(new PrintVisitor());
                  System.out.println("");

                  System.out.println("------ Reduction of Nested Let-Expressions ------");
                  expression_reducted.accept(new PrintVisitor());
                  System.out.println("");

                  TreeMap<Register, Variable> registers = new TreeMap<Register, Variable>();
                  TreeMap<Register, Variable> parametersRegisters = new TreeMap<Register, Variable>();
                  RegisterUtils.initRegisters(registers, parametersRegisters);

                  Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters);
                  TranslationVisitor tv = new TranslationVisitor();
                  tv.visit(expression_reducted, func);
                  System.out.println("------ Translation to Jerry ------");
                  func.show();
                  System.out.println("");

                }

                else{
                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());
                        System.out.println("------ AST ------");
                        expression.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ K-Normalization ------");
                        expression_normalized.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ AlphaConversion ------");
                        expression_converted.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ Reduction of Nested Let-Expressions ------");
                        expression_reducted.accept(new PrintVisitor());
                        System.out.println("");

                        TreeMap<Register, Variable> registers = new TreeMap<Register, Variable>();
                        TreeMap<Register, Variable> parametersRegisters = new TreeMap<Register, Variable>();
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

                        try (FileOutputStream oS = new FileOutputStream(new File("ARM/output.s"))) {
                                oS.write(text.toString().getBytes());
                        } catch (IOException e) {
                                e.printStackTrace();
                        }
                }

        } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
      System.exit(0);
      }
}
