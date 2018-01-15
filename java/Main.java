import java_cup.runtime.*;
import java.io.*;
import java.util.*;
import backend.*;
import backend.functions.*;
import backend.translation.*;
import exp.*;
import ast.*;
import ast.type.*;
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
                // System.out.println(ihm.typecheck_only);
                // if (ihm.typecheck_only) {
                //         throw new NotYetImplemented();
                // }
                Parser p = new Parser(new Lexer(new FileReader(ihm.input_file)));
                Exp expression = (Exp) p.parse().value;
                //System.out.println(expression);
                // assert (expression != null);

                if (ihm.given_output) {
                        new Outgesture(ihm.output_file);
                }

                if (ihm.ast || ihm.parse_only) {
                        System.out.println("------ AST ------");
                        expression.accept(new PrintVisitor());
                        System.out.println();
                        if (ihm.parse_only) {
                                System.exit(0);
                        }
                }


                /* For evaluation :
                   System.out.println("------ Evaluation ------");
                   System.out.println("Ceci est le résultat : " + expression.accept(new EvaluationVisitor()));
                 */

                // For TypeChecking :

                 else if (ihm.typecheck_only) {
                      LinkedList<EnvElem> env = new LinkedList<EnvElem>();
                      env.add(new EnvElem(new Id("print_string"), new TFun(new TString(), new TUnit())));
                      env.add(new EnvElem(new Id("print_int"), new TFun(new TInt(), new TUnit())));
                      Env predef = new Env(env);
                      System.out.println(predef);
                      GenEquation expression_typechecked = new GenEquation();
                      expression_typechecked.generate(predef, expression, new TInt()); // A CHANGER
                      System.out.println("initial eqt list : " + expression_typechecked.eqt_list);
                      EquationSolver solved = new EquationSolver();
                      System.out.println(solved.reduce(expression_typechecked));
                      System.out.println(expression_typechecked.eqt_list);
                      System.out.println(solved.solve(expression_typechecked));
                 }

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

                //For ClosureConversion :
                else if (ihm.closure_conversion) {
                        Exp expression_converted = expression.accept(new ClosureConversion());
                        System.out.println("------ ClosureConversion ------");
                        expression_converted.accept(new PrintVisitor());
                        System.out.println("");
                }

                else if (ihm.arm) {

                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());


                        // LinkedHashMap<Register, Variable> registers = new LinkedHashMap<Register, Variable>(9);
                        // LinkedHashMap<Register, Variable> parametersRegisters = new LinkedHashMap<Register, Variable>(4);
                        ArrayList<Register> registers = new ArrayList<Register>(9);
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>(2);
                        RegisterUtils.initRegisters(registers, parametersRegisters);

                        ArrayList<Function> flist = new ArrayList<Function>();
                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters, flist);
                        flist.add(func);
                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);

                        RegisterAllocation regalloc = new RegisterAllocation();
                        regalloc.LinearScan(func);
                        //System.out.println("------ Register Allocation ------");
                        //func.showVariablesState();
                        //System.out.println("");

                        //RegisterAllocation regalloc = new RegisterAllocation();
                        //regalloc.VBA(func);


                        System.out.println("@------ ARM------");
                        ArmGenerator arm = new ArmGenerator();
                        arm.generate_code(flist);
                        StringBuilder text = arm.textSection.text;
                        System.out.println(text);

                }

                else if (ihm.output_asml) {
                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());


                        // LinkedHashMap<Register, Variable> registers = new LinkedHashMap<Register, Variable>();
                        // LinkedHashMap<Register, Variable> parametersRegisters = new LinkedHashMap<Register, Variable>();
                        ArrayList<Register> registers = new ArrayList<Register>(9);
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>(2);
                        RegisterUtils.initRegisters(registers, parametersRegisters);

                        ArrayList<Function> flist = new ArrayList<Function>();
                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters, flist);
                        flist.add(func);
                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);
                        AsmlConverter asml = new AsmlConverter();
                        StringBuilder text1 = asml.convert(flist);
                        System.out.println(text1);


                }

                else{
                        System.out.println("------ AST ------");
                        expression.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ K-Normalization ------");
                        Exp expression_normalized = expression.accept(new KNormalization());
                        expression_normalized.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ AlphaConversion ------");
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        expression_converted.accept(new PrintVisitor());
                        System.out.println("");

                        System.out.println("------ Reduction of Nested Let-Expressions ------");
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());
                        expression_reducted.accept(new PrintVisitor());
                        System.out.println("");

                        // LinkedHashMap<Register, Variable> registers = new LinkedHashMap<Register, Variable>(9);
                        // LinkedHashMap<Register, Variable> parametersRegisters = new LinkedHashMap<Register, Variable>(4);
                        ArrayList<Register> registers = new ArrayList<Register>(9);
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>(2);
                        RegisterUtils.initRegisters(registers, parametersRegisters);
                        // RegisterUtils.showRegisters(registers);

                        ArrayList<Function> flist = new ArrayList<Function>();
                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters, flist);
                        flist.add(func);
                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);
                        System.out.println("------ Translation to Jerry ------");
                        func.show();
                        System.out.println("");

                        RegisterAllocation regalloc = new RegisterAllocation();
                        for (Function f : flist) {
                          f.show();
                          regalloc.LinearScan(f);
                        }
                        System.out.println("------ Register Allocation ------");
                        func.showVariablesState();
                        System.out.println("");

                        System.out.println("------ ARM code generation ------");
                        ArmGenerator arm = new ArmGenerator();

                        arm.generate_code(flist);
                        StringBuilder text = arm.textSection.text;
                        System.out.println(text);


                }

        } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
        System.exit(0);
}
}
