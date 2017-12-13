public class AlphaConversion implements ObjVisitor<void>{
  /**
  set of variables
  */
  private static HashSet<String> epsilon;
  /**
  number of variables
  */
  private static int number_of_variables = 0;

  public void visit(Add e){
    alpha(e);
  }

  public void visit(Sub e){
    alpha(e);
  }

  public void visit(Let e){
    alpha(e);
  }

  public void visit(Var e){
    alpha(e);
  }

  public void visit(Int e){
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

  public void alpha(Exp e){
    if (e instanceof Var){
      if (!epsilon.add(e.Id.toString())){
        int n = (int) (8*Math.random()) + 2;
        e.Id.id = random_name(n, e);
        number_of_variables++;
      }
    } else if (e instanceof Add || e instanceof Sub){
      alpha(e.e1);
      alpha(e.e2);
    } else if (e instanceof Let){
      
    }
  }

  final String letters = "abcdefghijklmnopqrstuvwxyz";

  /**
  function which generates a random name for a variable
  */
  String random_name(int n, Var e){
    char name[n] = new char[n];
    for (int i = 0; i < n; i++){
      name[i] = letters[(int) (26*Math.random())];
    }
    String new_name = name;
    if (!epsilon.add(new_name)){
      random_name(int n, Var e);
    } else {
      return new_name;
    }
  }
}
