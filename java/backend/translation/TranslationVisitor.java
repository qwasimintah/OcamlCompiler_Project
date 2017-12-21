package translation;

import java.io.*;
import java.util.Hashtable;
import java.util.*;
import registers.*;
import exceptions.*;
import functions.*;
import instructions.*;
import variables.*;
import exp.*;

public class TranslationVisitor {

public Object visit(Exp e, Function func) {
        if (e instanceof Add) {
                return (InstructionADD) visit((Add)e, func);
        }
        else if (e instanceof Sub) {
                return (InstructionSUB) visit((Sub)e, func);
        }
        else if (e instanceof Let) {
                return (InstructionASSIGN) visit((Let)e, func);
        }
        else if (e instanceof Int) {
                return (Integer) visit((Int)e, func);
        }
        else if (e instanceof Var) {
                return (Variable) visit((Var)e, func);
        }
        return null;
}

public InstructionADD visit(Add e, Function func) {
        return new InstructionADD(func, visit(e.e1, func), visit(e.e2, func));
}

public InstructionSUB visit(Sub e, Function func){
        return new InstructionSUB(func, visit(e.e1, func), visit(e.e2, func));
}

public InstructionASSIGN visit(Let e, Function func){
        return new InstructionASSIGN(func, visit(e.e1, func), visit(e.e2, func));
}

public Variable visit(Var e, HashMap registers, Function func){
        return new Variable(e.id.toString(), registers, func);
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

public Instruction visit(App e, Function func){
        List<Parameter> arguments = new ArrayList<Parameter>();
        for (Exp o : e.es) {
                arguments.add((Parameter) visit(o, func));
        }
        return new InstructionCALL(arguments, e.e.toString());
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

public void main(String[] args) {
        Function fun = new Function("main", new ArrayList(), new ArrayList());
        Int x = new Int(1);
        Int y = new Int(2);
        Add add = new Add(x, y);
        InstructionADD Iadd = visit(add, fun);
        Iadd.show();
        System.out.println("caca");
}
}
