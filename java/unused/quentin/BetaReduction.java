import java.util.HashMap;

class BeteReduction implements ObjVisitor<Exp>{
private static HashMap<String, String> associated = new HashMap<String, String> ();

public Exp visit(Add e){
        return e;
}

public Exp visit(Sub e){
        return e;
}

public Exp visit(Let e){
        Var new_var = new Var(e.id.gen());
        e.e1.accept(this);
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
}
