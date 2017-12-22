import java.util.*;


public class KNormalization implements ObjVisitor<Exp> {
  public Exp visit(Int e) {
    return e;
  }

  public Exp visit(Var e) {
    return e;
  }

  public Exp visit(Add e) {
    Type t = new TInt();
    Var var1 = new Var(Id.gen());
    Var var2 = new Var(Id.gen());
    Add new_op = (new Add(var1, var2));
    Let new_let2 = (new Let(var2.id, t, e.e2.accept(this), new_op));
    Let new_let1 = (new Let(var1.id, t, e.e1.accept(this), new_let2));
    return new_let1;
  }

  public Exp visit(Sub e) {
    Type t = new TInt();
    Var var1 = new Var(Id.gen());
    Var var2 = new Var(Id.gen());
    Sub new_op = (new Sub(var1, var2));
    Let new_let2 = (new Let(var2.id, t, e.e2.accept(this), new_op));
    Let new_let1 = (new Let(var1.id, t, e.e1.accept(this), new_let2));
    return new_let1;
  }

  public Exp visit(Let e) {
    Let new_let = (new Let(e.id, e.t, e.e1.accept(this), e.e2.accept(this)));
    return new_let;
  }

  public Exp visit(App e) {
    List<Exp> new_es = new ArrayList<Exp>();
    for (int i = 0; i < e.es.size(); i++){
      Exp es_temp = e.es.get(i);
      new_es.add(es_temp.accept(this));
    }
    return (new App(e.e.accept(this), new_es));
  }

  public Exp visit(Unit e) {
    return e;
  }

  public Exp visit(Bool e) {
    return e;
  }

  public Exp visit(Float e) {
    return e;
  }

  public Exp visit(Not e) {
    return e;
  }

  public Exp visit(Neg e) {
    return e;
  }

  public Exp visit(FNeg e) {
    return e;
  }

  public Exp visit(FAdd e) {
    return e;
  }

  public Exp visit(FSub e) {
    return e;
  }

  public Exp visit(FMul e) {
    return e;
  }

  public Exp visit(FDiv e) {
    return e;
  }

  public Exp visit(Eq e) {
    return e;
  }

  public Exp visit(LE e) {
    return e;
  }

  public Exp visit(If e) {
    return e;
  }

  public Exp visit(LetRec e) {
    return e;
  }


  public Exp visit(Tuple e) {
    return e;
  }

  public Exp visit(LetTuple e) {
    return e;
  }

  public Exp visit(Array e) {
    return e;
  }

  public Exp visit(Get e) {
    return e;
  }

  public Exp visit(Put e) {
    return e;
  }
}
