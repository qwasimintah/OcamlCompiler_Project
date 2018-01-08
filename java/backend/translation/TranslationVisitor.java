package backend.translation;

import java.io.*;
import java.util.*;
import backend.registers.*;
import backend.exceptions.*;
import backend.functions.*;
import backend.instructions.*;
import backend.variables.*;
import exp.*;
import ast.*;
import ast.type.*;

public class TranslationVisitor {

private static Integer tmp_id = 0;

public String getTempVarName() {
        tmp_id++;
        return "tmpVar" + tmp_id.toString();
}

public Object visit(Exp e, Function func) {
        if (e instanceof Add) {
                return (InstructionADD)visit((Add)e, func);
        }
        else if (e instanceof Sub) {
                return (InstructionSUB) visit((Sub)e, func);
        }
        else if (e instanceof Let) {
                visit((Let)e, func);
        }
        else if (e instanceof Int) {
                return (Integer) visit((Int)e, func);
        }
        else if (e instanceof Var) {
                return (Variable) visit((Var)e, func);
        }
        else if (e instanceof App) {
                visit((App)e, func);
        }
        else if (e instanceof Neg) {
                return (Integer) visit((Neg)e, func);
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
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func.registers, func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func.registers, func);
                vars.add(tmpY);
        }


        try {
                InstructionADD inst = new InstructionADD(func, vars.get(0), vars.get(1));
                func.addInstruction(inst);
                return inst;
        } catch (IndexOutOfBoundsException exception) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func.registers, func);
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func.registers, func);
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
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func.registers, func);
                vars.add(tmpX);
        }

        for (Variable var : func.getVariables()) {
                if (var2 == var.getName()) {
                        vars.add(var);
                }
        }
        if (vars.size() == 0) {
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func.registers, func);
                vars.add(tmpY);
        }

        try {
                InstructionSUB inst = new InstructionSUB(func, vars.get(0), vars.get(1));
                func.addInstruction(inst);
                return inst;
        } catch (IndexOutOfBoundsException exception) {
                VInteger tmpX = new VInteger(getTempVarName(), (Integer)visit(e.e1, func), func.registers, func);
                VInteger tmpY = new VInteger(getTempVarName(), (Integer)visit(e.e2, func), func.registers, func);
                func.getVariables().add(tmpX);
                func.getVariables().add(tmpY);
                InstructionSUB inst = new InstructionSUB(func, tmpX, tmpY);
                func.addInstruction(inst);
                return inst;
        }
}

public void visit(Let e, Function func){
        // System.out.println("LET");
        // System.out.println(e.e1.getClass());
        if (e.e1 instanceof Int) {
                Integer value = (Integer) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, value, func.registers, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
        }
        else if (e.e1 instanceof App) {
                visit(e.e1, func);
        }
        else if (e.e1 instanceof Add) {
                InstructionADD instadd = (InstructionADD) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func.registers, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, instadd);
                func.getVariables().add(var);
                func.addInstruction(inst);
        }
        else if (e.e1 instanceof Sub) {
                InstructionSUB instadd = (InstructionSUB) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func.registers, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, instadd);
                func.getVariables().add(var);
                func.addInstruction(inst);
        }
        else if (e.e1 instanceof Var) {
                Variable var = new Variable(e.id.id, func.registers, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, (Variable)visit(e.e1, func));
                func.getVariables().add(var);
                func.addInstruction(inst);
                func.showVariables();
        }
        else {
                visit(e.e1, func);
        }
        visit(e.e2, func);
}

public Variable visit(Var e, Function func){
        // System.out.println("VAR");
        String varName = ((Var)e).id.id;

        for (Variable var : func.getVariables()) {
                if (varName == var.getName()) {
                        return var;
                }
        }
        return null;
}

public Integer visit(Int e, Function func){
        // System.out.println("INT");
        return e.i;
}

public Instruction visit(Unit e, Function func){
        return null;
}

public Instruction visit(exp.Bool e, Function func){
        return null;
}

public Instruction visit(exp.Float e, Function func){
        return null;
}

public Instruction visit(Not e, Function func){
        return null;
}

public Integer visit(Neg e, Function func){
        //System.out.println("NEG");
        Integer i = (Integer) visit(e.e, func);
        return -i;
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

public Instruction visit(Eq e, Function func){
        return null;
}

public Instruction visit(LE e, Function func){
        return null;
}

public Instruction visit(If e, Function func){
        return null;
}

public Instruction visit(LetRec e, Function func){
        return null;
}

public void visit(App e, Function func){
        // System.out.println("APP");
        ArrayList<Object> vars = new ArrayList<Object>();
        try {
                for (Variable var : func.getVariables()) {
                        for (Exp o : e.es) {
                                if (var.getName() == ((Var)o).id.id) {
                                        vars.add(var);
                                }
                        }
                }
        } catch (ClassCastException exc) {
        }
        for (Exp e1 : e.es) {
                Object var = (Object) visit(e1, func);
                // func.getVariables().add(var);
                // vars.add(var);
        }
        InstructionCALL inst = new InstructionCALL(vars, ((Var)e.e).id.toString());
        func.addInstruction(inst);
}

public Instruction visit(Tuple e, Function func){
        return null;
}

public Instruction visit(LetTuple e, Function func){
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

// public static void main(String[] args) {
//         Function fun = new Function("main", new ArrayList(), new ArrayList());
//         Int x = new Int(1);
//         Int y = new Int(2);
//         Add add = new Add(x, y);
//         Sub sub = new Sub(x, y);
//         Var print = new Var(new Id("print_int"));
//         List params = new ArrayList();
//         params.add(x);
//         App call = new App(print, params);
//         Let let1 = new Let(new Id("id1"), new TInt(), sub, call);
//         Let let2 = new Let(new Id("id2"), new TInt(), add, let1);
//         visit(let2, fun);
//         fun.show();
// }
}
