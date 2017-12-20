package translation;

import java.io.*;
import java.util.Hashtable;
import java.util.*;
import registers.*;
import exceptions.*;
import functions.*;
import instructions.*;
// import exp.*;
import variables.*;

public class TranslationVisitor implements ObjVisitor<Exp> {

public InstructionADD visit(Add e) {
        Exp x = e.e1.accept(this);
        Exp y = e.e2.accept(this);
}

public Exp visit(Sub e){
        return e;
}

public Exp visit(Let e){
        return e;
}

public Exp visit(Var e){
        return e;
}

public Exp visit(Int e){
        return e;
}

public Exp visit(Unit e){
        return e;
}

public Exp visit(Bool e){
        return e;
}

public Exp visit(Float e){
        return e;
}

public Exp visit(Not e){
        return e;
}

public Exp visit(Neg e){
        return e;
}

public Exp visit(FNeg e){
        return e;
}

public Exp visit(FAdd e){
        return e;
}

public Exp visit(FSub e){
        return e;
}

public Exp visit(FMul e){
        return e;
}

public Exp visit(FDiv e){
        return e;
}

public Exp visit(Eq e){
        return e;
}

public Exp visit(LE e){
        return e;
}

public Exp visit(If e){
        return e;
}

public Exp visit(LetRec e){
        return e;
}

public Exp visit(App e){
        return e;
}

public Exp visit(Tuple e){
        return e;
}

public Exp visit(LetTuple e){
        return e;
}

public Exp visit(Array e){
        return e;
}

public Exp visit(Get e){
        return e;
}

public Exp visit(Put e){
        return e;
}

// public static void main(String[] args) {
//   System.out.println("caca");
// }
}
