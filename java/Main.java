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

/**
 * Implementation of a type-checker in java to translate Ocaml to ARM (normal)
 *
 *@author les_cons-pileurs
 *
 */

public class Main {
static public void main(String argv[]) {
        Ihm ihm = new Ihm(argv);
        try {
                Parser p = new Parser(new Lexer(new FileReader(ihm.input_file)));
                Exp expression = (Exp) p.parse().value;

                if (ihm.given_output) {
                        new Outgesture(ihm.output_file);
                }

                if (ihm.ast || ihm.parse_only) {
                        System.out.println("------ AST ------");
                        expression.accept(new PrintVisitor());
                        System.out.println("");
                        if (ihm.parse_only) {
                                System.exit(0);
                        }
                }

                // For TypeChecking :

                else if (ihm.typecheck_only) {
                        Env predef = new Env();
                        predef = new Env(predef.gen_predef());
                        GenEquation expression_typechecked = new GenEquation();
                        expression_typechecked.generate(predef, expression, new TUnit());
                        // System.out.println("initial eqt list : " + expression_typechecked.eqt_list);
                        EquationSolver solved = new EquationSolver();
                        solved.reduce(expression_typechecked);
                        // System.out.println("transformed eq list : " + expression_typechecked.eqt_list);
                        solved.solve(expression_typechecked);
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
                        Exp expression_free = expression.accept(new FreeVariables());
                        Exp expression_converted = expression_free.accept(new ClosureConversion());
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
                        ArrayList<Register> registers = new ArrayList<Register>();
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>();
                        RegisterUtils.initRegisters(registers, parametersRegisters);

                        ArrayList<Function> flist = new ArrayList<Function>();
                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters, flist);
                        flist.add(func);
                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);

                        System.out.println("------ Register Allocation ------");
                        RegisterAllocation regalloc = new RegisterAllocation();
                        for (Function f : flist) {
                                regalloc.LinearScan(f);
                                f.showVariablesState();
                        }
                        System.out.println("");

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
                        ArrayList<Register> registers = new ArrayList<Register>();
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>();
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
                        Exp expression_normalized = expression.accept(new KNormalization());
                        Exp expression_converted = expression_normalized.accept(new AlphaConversion());
                        Exp expression_reducted = expression_converted.accept(new ReductionNestedExpression());
                        // System.out.println("------ Reduction of Nested Let-Expressions ------");
                        // expression_reducted.accept(new PrintVisitor());
                        // System.out.println("");
                        ArrayList<Register> registers = new ArrayList<Register>();
                        ArrayList<Register> parametersRegisters = new ArrayList<Register>();
                        RegisterUtils.initRegisters(registers, parametersRegisters);

                        ArrayList<Function> flist = new ArrayList<Function>();
                        Function func = new Function("main", new ArrayList(), new ArrayList(), registers, parametersRegisters, flist);
                        flist.add(func);

                        TranslationVisitor tv = new TranslationVisitor();
                        tv.visit(expression_reducted, func);
                        // for (Function f : flist) {
                        //         f.show();
                        // }

                        RegisterAllocation regalloc = new RegisterAllocation();
                        for (Function f : flist) {
                                regalloc.LinearScan(f);
                                // regalloc.VBA(f);
                                // f.showVariablesState();
                        }

                        System.out.println("@------ ARM code generation ------");
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
