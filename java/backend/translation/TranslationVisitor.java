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
                visit((Add)e, func);
        }
        else if (e instanceof Sub) {
                visit((Sub)e, func);
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
        return null;
}

public void visit(Add e, Function func) {
        System.out.println("ADD");
        ArrayList<Variable> vars = new ArrayList<Variable>();
        for (Variable var : func.getVariables()) {
                if (var.getName() == ((Var)e.e1).id.id) {
                        vars.add(var);
                } else if (var.getName() == ((Var)e.e2).id.id) {
                        vars.add(var);
                }
        }
        InstructionADD inst = new InstructionADD(func, vars.get(0), vars.get(1));
        func.addInstruction(inst);
}

public void visit(Sub e, Function func){
        System.out.println("SUB");
        ArrayList<Variable> vars = new ArrayList<Variable>();
        for (Variable var : func.getVariables()) {
                if (var.getName() == ((Var)e.e1).id.id) {
                        vars.add(var);
                } else if (var.getName() == ((Var)e.e2).id.id) {
                        vars.add(var);
                }
        }
        InstructionADD inst = new InstructionSUB(func, vars.get(0), vars.get(1));
        func.addInstruction(inst);
}

public void visit(Let e, Function func){
        System.out.println("LET");
        if (e.t instanceof TInt) {
                Integer value = (Integer) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, value, func.registers, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, value);
                func.getVariables().add(var);
                func.addInstruction(inst);
        }
        // if (visit(e.e1, func) instanceof Integer) {
        //   System.out.println("int");
        //   Integer op1 = (Integer) visit(e.e1, func);
        //   VInteger tmpX = new VInteger(getTempVarName(), op1, func.registers, func);
        //   InstructionASSIGN assX = new InstructionASSIGN(func, tmpX, op1);
        //   func.addInstruction(assX);
        // }
        // visit(e.e1, func);
        visit(e.e2, func);
}

public Variable visit(Var e, Function func){
        return new Variable(e.id.id + "bis", func.registers, func);
}

public Integer visit(Int e, Function func){
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

public Instruction visit(Neg e, Function func){
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
        List<Object> arguments = new ArrayList<Object>();
        for (Exp o : e.es) {
                arguments.add((Object)visit(o, func));
        }
        InstructionCALL inst = new InstructionCALL(arguments, ((Var)e.e).id.toString());
        func.addInstruction(inst);
        // return inst;
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
