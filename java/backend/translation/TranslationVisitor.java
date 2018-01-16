package backend.translation;

import java.io.*;
import java.util.*;
import backend.registers.*;
import backend.exceptions.*;
import backend.functions.*;
import backend.instructions.*;
import backend.variables.*;
import backend.booleans.*;
import backend.tuples.*;
import exp.*;
import ast.*;
import ast.type.*;

public class TranslationVisitor {

private static Integer tmpId = 0;
private static Integer tmpExpId = 0;
private static Integer label = 0;

private HashMap<String, String> labels = new HashMap<String, String>();

public String getTempVarName() {
        tmpId++;
        return "tmpVar" + tmpId.toString();
}

public String getTempBoolExpName() {
        tmpExpId++;
        return "tmpBoolExp" + tmpExpId.toString();
}

public String getNewLabel() {
        label++;
        return "label" + label.toString();
}

public String getLabel(String var) {
        if (var.equals("print_int")) {
                return "print_int";
        }
        for (Map.Entry<String, String> entry : labels.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key == var) {
                        return value;
                }
        }
        String newLabel = getNewLabel();
        labels.put(var, newLabel);
        return newLabel;
}

public Object visit(Exp e, Function func) {
        if (e instanceof Add) {
                return (InstructionADD)visit((Add)e, func);
        }
        else if (e instanceof Sub) {
                return (InstructionSUB) visit((Sub)e, func);
        }
        else if (e instanceof Let) {
                return (Instruction)visit((Let)e, func);
        }
        else if (e instanceof LetRec) {
                visit((LetRec)e, func);
        }
        else if (e instanceof LetTuple) {
                visit((LetTuple)e, func);
        }
        else if (e instanceof Int) {
                return (Integer) visit((Int)e, func);
        }
        else if (e instanceof Bool) {
                return (boolean) visit((Bool)e, func);
        }
        else if (e instanceof Not) {
                return (boolean) visit((Bool)e, func);
        }
        else if (e instanceof Var) {
                return (Variable) visit((Var)e, func);
        }
        else if (e instanceof App) {
                return (InstructionCALL) visit((App)e, func);
        }
        else if (e instanceof Neg) {
                return (Integer) visit((Neg)e, func);
        }
        else if (e instanceof If) {
                return (InstructionIF) visit((If)e, func);
        }
        else if (e instanceof Eq) {
                return (BooleanEQ)visit((Eq)e, func);
        }
        else if (e instanceof LE) {
                return (BooleanLE)visit((LE)e, func);
        }
        else if (e instanceof Tuple) {
                return (TupleJerry)visit((Tuple)e, func);
        }
        return null;
}

public InstructionADD visit(Add e, Function func) {
        // System.out.println("ADD");
        ArrayList<Variable> vars = new ArrayList<Variable>();

        String var1 = ((Var)e.e1).id.id;
        String var2 = ((Var)e.e2).id.id;

        for (Variable var : func.getVariables()) {
                if (var1 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                for (Variable var : func.getArguments()) {
                        if (var1 == var.getName()) {
                                vars.add(var);
                        }
                }
        }
        if (vars.size() == 0) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        for (Variable var : func.getArguments()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                vars.add(tmpY);
        }


        try {
                InstructionADD inst = new InstructionADD(func, vars.get(0), vars.get(1));
                func.addInstruction(inst);
                return inst;
        } catch (IndexOutOfBoundsException exception) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                func.getVariables().add(tmpX);
                func.getVariables().add(tmpY);
                InstructionADD inst = new InstructionADD(func, tmpX, tmpY);
                func.addInstruction(inst);
                return inst;
        }
}

public InstructionSUB visit(Sub e, Function func) {
        // System.out.println("SUB");
        ArrayList<Variable> vars = new ArrayList<Variable>();

        String var1 = ((Var)e.e1).id.id;
        String var2 = ((Var)e.e2).id.id;

        for (Variable var : func.getVariables()) {
                if (var1 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                vars.add(tmpY);
        }

        try {
                InstructionSUB inst = new InstructionSUB(func, vars.get(0), vars.get(1));
                func.addInstruction(inst);
                return inst;
        } catch (IndexOutOfBoundsException exception) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                func.getVariables().add(tmpX);
                func.getVariables().add(tmpY);
                InstructionSUB inst = new InstructionSUB(func, tmpX, tmpY);
                func.addInstruction(inst);
                return inst;
        }
}

public Instruction visit(Let e, Function func){
        // System.out.println("LET");
        // System.out.println(e.e1.getClass());
        InstructionASSIGN inst = new InstructionASSIGN(func, null, null);
        if (e.e1 instanceof Int) {
                Integer value = (Integer) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, value, func);
                inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Neg) {
                Integer value = (Integer) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, value, func);
                inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof App) {
                InstructionCALL value = (InstructionCALL) visit(e.e1, func);
                if (func.getInstructions().contains(value)) {
                        // System.out.println("REMOVED");
                        func.getInstructions().remove(value);
                }
                Variable var = new Variable(e.id.id, func);
                inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Add) {
                InstructionADD instadd = (InstructionADD) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, instadd);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Sub) {
                InstructionSUB instsub = (InstructionSUB) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, instsub);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Var) {
                Variable var = new Variable(e.id.id, func);
                Variable var2 = (Variable)visit(e.e1, func);
                // System.out.print("var2 : ");
                // System.out.println(var2);
                inst = new InstructionASSIGN(func, var, var2);
                func.getVariables().add(var);
                func.addInstruction(inst);
                //func.showVariables();
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof If) {
                InstructionIF instif = (InstructionIF) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, instif);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Let) {
                InstructionASSIGN value = (InstructionASSIGN) visit(e.e1, func);
                Variable var = new Variable(e.id.id, func);
                inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        // else {
        //         visit(e.e1, func);
        //         return inst;
        // }
        return inst;
}

public Variable visit(Var e, Function func){
        // System.out.println("VAR");
        String varName = ((Var)e).id.id;
        // System.out.println(varName);

        for (Variable var : func.getVariables()) {
                if (varName == var.getName()) {
                        InstructionNOTHING inst = new InstructionNOTHING(var);
                        func.addInstruction(inst);
                        return var;
                }
        }
        for (Variable var : func.getArguments()) {
                if (varName == var.getName()) {
                        InstructionNOTHING inst = new InstructionNOTHING(var);
                        func.addInstruction(inst);
                        return var;
                }
        }
        InstructionNOTHING inst = new InstructionNOTHING(null);
        func.addInstruction(inst);
        return null;
}

public Integer visit(Int e, Function func){
        // System.out.println("INT");
        VInteger i = new VInteger(getTempVarName(), e.i, func);
        InstructionNOTHING inst = new InstructionNOTHING(i);
        func.addInstruction(inst);
        return e.i;
}

public Integer visit(Neg e, Function func){
        //System.out.println("NEG");
        Integer i = (Integer) visit(e.e, func);
        return -i;
}

public InstructionCALL visit(App e, Function func){
        // System.out.println("APP");
        ArrayList<Object> vars = new ArrayList<Object>();

        // if (!(e.es.get(0) instanceof Let)) {
        for (Exp e1 : e.es) {
                Object var = (Object) visit(e1, func);
                if (var instanceof Integer) {
                        var = new VInteger(getTempVarName(), (Integer)var, func);
                        // func.getVariables().add((VInteger)var);
                        InstructionASSIGN inst = new InstructionASSIGN(func, var, ((VInteger)var).getValue());
                        func.addInstruction(inst);
                }
                else if (var instanceof Boolean) {
                        var = new VBoolean(getTempVarName(), (boolean)var, func);
                        // func.getVariables().add((VInteger)var);
                        InstructionASSIGN inst = new InstructionASSIGN(func, var, ((VBoolean)var).getExp());
                        func.addInstruction(inst);
                }
                vars.add(var);
        }

        // func.getParameters().add(vars);
        for (Object o : vars) {
                if (o instanceof Variable) {
                        ((Variable)o).allocParametersRegister();
                }
        }
        for (Object o : vars) {
                if (o instanceof Variable) {
                        ((Variable)o).killParameter();
                }
        }
        InstructionCALL inst = new InstructionCALL(vars, getLabel(((Var)e.e).id.id));
        func.addInstruction(inst);
        return inst;
}

public boolean visit(exp.Bool e, Function func){
        boolean b = e.b;
        return b;
}

public boolean visit(Not e, Function func){
        boolean b = (boolean) visit(e.e, func);
        return !b;
}

public BooleanEQ visit(Eq e, Function func){
        ArrayList<Variable> vars = new ArrayList<Variable>();

        String var1 = ((Var)e.e1).id.id;
        String var2 = ((Var)e.e2).id.id;

        for (Variable var : func.getVariables()) {
                if (var1 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                vars.add(tmpY);
        }

        BooleanEQ exp = new BooleanEQ(getTempBoolExpName(), func, vars.get(0), vars.get(1));
        // System.out.println("came here");
        return exp;
}

public BooleanLE visit(LE e, Function func){
        ArrayList<Variable> vars = new ArrayList<Variable>();

        String var1 = ((Var)e.e1).id.id;
        String var2 = ((Var)e.e2).id.id;

        for (Variable var : func.getVariables()) {
                if (var1 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func);
                vars.add(tmpY);
        }

        BooleanLE exp = new BooleanLE(getTempBoolExpName(), func, vars.get(0), vars.get(1));
        // System.out.println("came here1");
        return exp;
}

public InstructionIF visit(If e, Function func) {
        // System.out.println("IF");
        VBoolean cond = new VBoolean(getTempVarName(), (BooleanExpression)visit(e.e1, func), func);
        Function branch_then = new Function(getNewLabel(), func.getArguments(), new ArrayList<Instruction>(), func.registers, func.parametersRegisters, func.getVariables(), func.flist);
        visit(e.e2, branch_then);
        Function branch_else = new Function(getNewLabel(), func.getArguments(), new ArrayList<Instruction>(), func.registers, func.parametersRegisters, func.getVariables(), func.flist);
        visit(e.e3, branch_else);
        //System.out.println(cond.getExp());
        InstructionIF inst = new InstructionIF(cond, branch_then, branch_else);
        func.addInstruction(inst);
        return inst;
}

public void visit(LetRec e, Function func){
        // System.out.println("LETREC");

        ArrayList<Variable> args = new ArrayList<Variable>();
        ArrayList<Register> newRegisters = new ArrayList<Register>(9);
        ArrayList<Register> newParametersRegisters = new ArrayList<Register>(2);
        RegisterUtils.initRegisters(newRegisters, newParametersRegisters);

        Function newFunc = new Function(getLabel(e.fd.id.id), args, new ArrayList<Instruction>(), newRegisters, newParametersRegisters, func.flist);

        for (Id id : e.fd.args) {
                Variable arg = new Variable(id.id, newFunc);
                args.add(arg);
        }
        visit(e.fd.e, newFunc);
        func.flist.add(newFunc);
        visit(e.e, func);
}

public TupleJerry visit(Tuple e, Function func){
        // System.out.println("TUPLE");
        ArrayList<Object> obj = new ArrayList<Object>();
        for (Exp e1 : e.es) {
                Object o = (Object)visit(e1, func);
                obj.add(o);
        }
        TupleJerry tuple = new TupleJerry(func, obj);
        tuple.show();
        return tuple;
}

public void visit(LetTuple e, Function func){
        System.out.println("LETTUPLE");
        System.out.println(e.e1.getClass());
        System.out.println(e.e2.getClass());
        // if (e.e1 instanceof Var) {
        //   Variable
        // }
        for (Integer i = 0; i < e.ids.size(); i++) {
                Object value = visit(e.e2, func);
                if (value instanceof TupleJerry) {
                        value = (TupleJerry)value;
                        System.out.println(value);
                }
                // Variable var = new
        }
        // for (Id id : e.ids) {
        //         System.out.println(id.id);
        // }
        // Integer i = 0;
        // Integer size = e.ids.size();
        //
        // for (i = 0; i < size; i++) {
        //       Type t = e.ts.get(0);
        //       String name = e.ids.get(0).id;
        // }
}

public Instruction visit(Unit e, Function func){
        return null;
}

public Instruction visit(exp.Float e, Function func){
        return null;
}

public Instruction visit(FNeg e, Function func){
        return null;
}

public Instruction visit(FAdd e, Function func){
        return null;
}

public Instruction visit(FSub e, Function func){
        return null;
}

public Instruction visit(FMul e, Function func){
        return null;
}

public Instruction visit(FDiv e, Function func){
        return null;
}


public Instruction visit(Array e, Function func){
        return null;
}

public Instruction visit(Get e, Function func){
        return null;
}

public Instruction visit(Put e, Function func){
        return null;
}
}
