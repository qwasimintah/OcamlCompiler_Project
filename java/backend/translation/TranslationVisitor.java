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

private static Object lastResult;

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
        // System.out.print(e.getClass());
        // System.out.println("\twith lastResult = " + lastResult);
        if (e instanceof Add) {
                lastResult = (InstructionADD)visit((Add)e, func);
                return lastResult;
        }
        else if (e instanceof Sub) {
                lastResult = (InstructionSUB)visit((Sub)e, func);
                return lastResult;
        }
        else if (e instanceof Let) {
                lastResult = (Instruction)visit((Let)e, func);
                return lastResult;
        }
        else if (e instanceof LetRec) {
                visit((LetRec)e, func);
        }
        else if (e instanceof LetTuple) {
                visit((LetTuple)e, func);
        }
        else if (e instanceof Int) {
                lastResult = (Integer) visit((Int)e, func);
                return lastResult;
        }
        else if (e instanceof Bool) {
                lastResult = (boolean) visit((Bool)e, func);
                return lastResult;
        }
        else if (e instanceof Not) {
                lastResult = (boolean) visit((Bool)e, func);
                return lastResult;
        }
        else if (e instanceof Var) {
                lastResult = (Variable) visit((Var)e, func);
                return lastResult;
        }
        else if (e instanceof App) {
                lastResult = (InstructionCALL) visit((App)e, func);
                return lastResult;
        }
        else if (e instanceof Neg) {
                lastResult = (InstructionMULT) visit((Neg)e, func);
                return lastResult;
        }
        else if (e instanceof If) {
                lastResult = (InstructionIF) visit((If)e, func);
                return lastResult;
        }
        else if (e instanceof Eq) {
                lastResult = (BooleanEQ)visit((Eq)e, func);
                return lastResult;
        }
        else if (e instanceof LE) {
                lastResult = (BooleanLE)visit((LE)e, func);
                return lastResult;
        }
        else if (e instanceof Tuple) {
                lastResult = (TupleJerry)visit((Tuple)e, func);
                return lastResult;
        }
        return null;
}

public Instruction visit(Let e, Function func){
        InstructionASSIGN inst = new InstructionASSIGN(func, null, null);

        if (e.e1 instanceof Int) {
                Integer value = (Integer) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, value, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Neg) {
                InstructionMULT value = (InstructionMULT) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof App) {
                InstructionCALL value = (InstructionCALL) visit(e.e1, func);
                if (func.getInstructions().contains(value)) {
                        func.getInstructions().remove(value);
                }
                Variable var = new Variable(e.id.id, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Add) {
                InstructionADD value = (InstructionADD) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Sub) {
                InstructionSUB value = (InstructionSUB) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Var) {
                Variable var = new Variable(e.id.id, func);
                Variable var2 = (Variable)visit(e.e1, func);
                inst = new InstructionASSIGN(func, var, var2);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof If) {
                InstructionIF instif = (InstructionIF) visit(e.e1, func);
                VInteger var = new VInteger(e.id.id, 0, func);
                inst = new InstructionASSIGN(func, var, instif);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Let) {
                Variable var = new Variable(e.id.id, func);
                InstructionASSIGN assignment = (InstructionASSIGN) visit(e.e1, func);
                Object value = lastResult;
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else if (e.e1 instanceof Tuple) {
                TupleJerry value = (TupleJerry) visit(e.e1, func);
                VTuple var = new VTuple(e.id.id, value, func);
                inst = new InstructionASSIGN(func, var, value);
                func.addVariable(var);
                func.addInstruction(inst);
                visit(e.e2, func);
                return inst;
        }
        else {
                visit(e.e1, func);
                visit(e.e2, func);
                return inst;
        }
}

public InstructionADD visit(Add e, Function func) {
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
        if (vars.size() == 1) {
                for (Variable var : func.getArguments()) {
                        if (var2 == var.getName()) {
                                vars.add(var);
                        }
                }
        }
        if (vars.size() == 1) {
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
                func.addVariable(tmpX);
                func.addVariable(tmpY);
                InstructionADD inst = new InstructionADD(func, tmpX, tmpY);
                func.addInstruction(inst);
                return inst;
        }
}

public InstructionSUB visit(Sub e, Function func) {
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
        if (vars.size() == 1) {
                for (Variable var : func.getArguments()) {
                        if (var2 == var.getName()) {
                                vars.add(var);
                        }
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
                func.addVariable(tmpX);
                func.addVariable(tmpY);
                InstructionSUB inst = new InstructionSUB(func, tmpX, tmpY);
                func.addInstruction(inst);
                return inst;
        }
}

public Variable visit(Var e, Function func){
        String varName = ((Var)e).id.id;
        if (varName.equals("print_int")) {
                Variable var = new Variable(varName, func);
                InstructionNOTHING inst = new InstructionNOTHING(var);
                func.addInstruction(inst);
                return null;
        }
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
        VInteger i = new VInteger(getTempVarName(), e.i, func);
        InstructionNOTHING inst = new InstructionNOTHING(i);
        func.addInstruction(inst);
        return e.i;
}

public InstructionMULT visit(Neg e, Function func) {
        if (e.e instanceof Int) {
                Integer i = (Integer) visit(e.e, func);
                VInteger minusOne = new VInteger(getTempVarName(), -1, func);
                InstructionASSIGN ass = new InstructionASSIGN(func, minusOne, -1);
                InstructionMULT inst = new InstructionMULT(func, i, minusOne);
                func.addVariable(minusOne);
                func.addInstruction(ass);
                func.addInstruction(inst);
                return inst;
        }
        else if (e.e instanceof Var) {
                Variable var = (Variable)visit(e.e, func);
                VInteger minusOne = new VInteger(getTempVarName(), -1, func);
                InstructionASSIGN ass = new InstructionASSIGN(func, minusOne, -1);
                InstructionMULT inst = new InstructionMULT(func, var, minusOne);
                func.addVariable(minusOne);
                func.addInstruction(ass);
                func.addInstruction(inst);
                return inst;
        }
        System.out.println("Problem here ! " + e.e.getClass());
        InstructionMULT inst = new InstructionMULT(func, null, null);
        func.addInstruction(inst);
        return inst;


}

public InstructionCALL visit(App e, Function func){
        ArrayList<Object> vars = new ArrayList<Object>();

        for (Exp e1 : e.es) {
                Object var = (Object) visit(e1, func);
                if (var instanceof Integer) {
                        var = new VInteger(getTempVarName(), (Integer)var, func);
                        // func.addVariable((VInteger)var);
                        InstructionASSIGN inst = new InstructionASSIGN(func, var, ((VInteger)var).getValue());
                        func.addInstruction(inst);
                }
                else if (var instanceof Boolean) {
                        var = new VBoolean(getTempVarName(), (boolean)var, func);
                        // func.addVariable((VInteger)var);
                        InstructionASSIGN inst = new InstructionASSIGN(func, var, ((VBoolean)var).getExp());
                        func.addInstruction(inst);
                }
                vars.add(var);
        }

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
        return exp;
}

public InstructionIF visit(If e, Function func) {
        VBoolean cond = new VBoolean(getTempVarName(), (BooleanExpression)visit(e.e1, func), func);
        Function branch_then = new Function(getNewLabel(), func.getArguments(), new ArrayList<Instruction>(), func.registers, func.parametersRegisters, func.getVariables(), func.flist);
        visit(e.e2, branch_then);
        Function branch_else = new Function(getNewLabel(), func.getArguments(), new ArrayList<Instruction>(), func.registers, func.parametersRegisters, func.getVariables(), func.flist);
        visit(e.e3, branch_else);
        InstructionIF inst = new InstructionIF(cond, branch_then, branch_else);
        func.addInstruction(inst);
        return inst;
}

public void visit(LetRec e, Function func){
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
        ArrayList<Object> vars = new ArrayList<Object>();
        for (Exp e1 : e.es) {
                Object value = (Object)visit(e1, func);
                Variable var = new Variable(getTempVarName(), func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, value);
                func.addInstruction(inst);
                vars.add(var);
                var.spill(func);
                // System.out.println(var.getOffset());
        }
        TupleJerry tuple = new TupleJerry(func, vars);

        tuple.show();
        return tuple;
}

public void visit(LetTuple e, Function func){
        // System.out.println(e.e1.getClass());
        // System.out.println(e.e2.getClass());
        TupleJerry values;
        try {
                values = (TupleJerry)visit(e.e1, func);
        }
        catch (Exception exc) {
                values = ((VTuple)visit(e.e1, func)).getValue();
        }
        for (Integer i = 0; i < e.ids.size(); i++) {
                // Object value = visit(e.e2, func);
                // if (value instanceof TupleJerry) {
                //         value = (TupleJerry)value;
                //         System.out.println(value);
                // }
                Variable var = new Variable(e.ids.get(i).id, func);
                InstructionASSIGN inst = new InstructionASSIGN(func, var, values.getElements().get(i));
                func.addInstruction(inst);
                func.addVariable(var);
        }
        visit(e.e2, func);
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
