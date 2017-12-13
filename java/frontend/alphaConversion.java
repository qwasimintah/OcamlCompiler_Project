public class AlphaConversion implements ObjVisitor<void>{
  private int epsilon;

  public void visit(Int e){
  }

  public void visit(Add e){
    alpha(epsilon, e);
  }

  public void visit(Sub e){
    alpha(epsilon, e);
  }

  public void visit(Let e){
    alpha(epsilon, e);
  }

  public void visit(Var e){
    alpha(epsilon, e);
  }

  public void visit(Unit e){
  }

  public void visit(Bool e){
  }

  public void visit(Float e){
  }

  public void visit(Not e){
  }

  public void visit(Neg e){
  }

  public void visit(FNeg e){
  }

  public void visit(FAdd e){
  }

  public void visit(FSub e){
  }

  public void visit(FMul e){
  }

  public void visit(FDiv e){
  }

  public void visit(Eq e){
  }

  public void visit(LE e){
  }

  public void visit(If e){
  }

  public void visit(LetRec e){
  }

  public void visit(App e){
  }

  public void visit(Tuple e){
  }

  public void visit(LetTuple e){
  }

  public void visit(Array e){
  }

  public void visit(Get e){
  }

  public void visit(Put e){
  }
}
