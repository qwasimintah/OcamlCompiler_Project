package translation;

// import exp.*;

public interface ObjVisitor<E> {
    E visit(Unit e);
    E visit(Bool e);
    E visit(Int e);
    E visit(exp.Float e);
    E visit(Not e);
    E visit(Neg e);
    E visit(Add e);
    E visit(Sub e);
    E visit(FNeg e);
    E visit(FAdd e);
    E visit(FSub e);
    E visit(FMul e);
    E visit(FDiv e);
    E visit(Eq e);
    E visit(LE e);
    E visit(If e);
    E visit(Let e);
    E visit(Var e);
    E visit(LetRec e);
    E visit(App e); //It takes a function as the left child and its argument as the right child
    E visit(Tuple e);
    E visit(LetTuple e);
    E visit(Array e);
    E visit(Get e);
    E visit(Put e);
}
