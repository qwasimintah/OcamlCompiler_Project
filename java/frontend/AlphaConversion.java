import java.util.HashSet;

public class AlphaConversion implements ObjVisitor<Integer>{
  /**
  set of variables
  */
  private static HashSet<String> epsilon = new HashSet<String> ();

  public Integer visit(Add e){
    alpha(e);
    return 0;
  }

  public Integer visit(Sub e){
    alpha(e);
    return 0;
  }

  public Integer visit(Let e){
    alpha(e);
    return 0;
  }

  public Integer visit(Var e){
    alpha(e);
    return 0;
  }

  public Integer visit(Int e){
    return 0;
  }

  public Integer visit(Unit e){
    return 0;
  }

  public Integer visit(Bool e){
    return 0;
  }

  public Integer visit(Float e){
    return 0;
  }

  public Integer visit(Not e){
    return 0;
  }

  public Integer visit(Neg e){
    return 0;
  }

  public Integer visit(FNeg e){
    return 0;
  }

  public Integer visit(FAdd e){
    return 0;
  }

  public Integer visit(FSub e){
    return 0;
  }

  public Integer visit(FMul e){
    return 0;
  }

  public Integer visit(FDiv e){
    return 0;
  }

  public Integer visit(Eq e){
    return 0;
  }

  public Integer visit(LE e){
    return 0;
  }

  public Integer visit(If e){
    return 0;
  }

  public Integer visit(LetRec e){
    return 0;
  }

  public Integer visit(App e){
    return 0;
  }

  public Integer visit(Tuple e){
    return 0;
  }

  public Integer visit(LetTuple e){
    return 0;
  }

  public Integer visit(Array e){
    return 0;
  }

  public Integer visit(Get e){
    return 0;
  }

  public Integer visit(Put e){
    return 0;
  }

  /**
  function doing the alpha-conversion
  */
  public void alpha(Exp e){
    if (e instanceof Var){
      if (!epsilon.add(e.id.toString())){
        e.id = e.id.gen();
        epsilon.add(e.id.toString());
      }
    } else if (e instanceof Add || e instanceof Sub){
      alpha(e.e1);
      alpha(e.e2);
    } else if (e instanceof Let){
        e.id = e.id.gen();
        alpha(e.e1);
        epsilon.add(e.id.toString());
        alpha(e.e2);
    }
  }
}
