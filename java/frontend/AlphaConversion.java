import java.util.*;

public class AlphaConversion implements ObjVisitor<Exp>{
  private static HashMap<String, Stack> epsilon = new HashMap<String, Stack> ();

  private boolean sec_exp_let = false;

  private static Stack used_in_let = new Stack();

  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    Var new_var = new Var(e.id.gen());
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
    }
    stack.push(new_var.id.toString());
    epsilon.put(e.id.toString(), stack);
    Exp new_e1 = e.e1.accept(this);
    sec_exp_let = true;
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(new_var.id, e.t, new_e1, new_e2);
    while (!used_in_let.empty()) {
      String key = (String) used_in_let.pop();
      Stack tmp_stack = epsilon.get(key);
      if (!tmp_stack.empty()) {
        tmp_stack.pop();
      }
    }
    used_in_let = new Stack();
    sec_exp_let = false;
    return new_let;
  }

  public Exp visit(Var e){
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
    }
    if (!stack.empty()){
      if (!sec_exp_let){
        Var new_var = new Var(e.id.gen());
        stack.push(new_var.id.toString());
        epsilon.put(e.id.toString(), stack);
        return new_var;
      } else {
        used_in_let.push(e.id.toString());
        e.id.id = (String) stack.peek();
        return e;
      }
    } else {
      return e;
    }
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
    FAdd new_fadd = new FAdd(e.e1.accept(this), e.e2.accept(this));
    return new_fadd;
  }

  public Exp visit(FSub e){
    FSub new_fsub = new FSub(e.e1.accept(this), e.e2.accept(this));
    return new_fsub;
  }

  public Exp visit(FMul e){
    FMul new_fmult = new FMul(e.e1.accept(this), e.e2.accept(this));
    return new_fmult;
  }

  public Exp visit(FDiv e){
    FDiv new_fdiv = new FDiv(e.e1.accept(this), e.e2.accept(this));
    return new_fdiv;
  }

  public Exp visit(Eq e){
    Eq new_eq = new Eq(e.e1.accept(this), e.e2.accept(this));
    return new_eq;
  }

  public Exp visit(LE e){
    LE new_le = new LE(e.e1.accept(this), e.e2.accept(this));
    return new_le;
  }

  public Exp visit(If e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    If new_if = new If(new_e1, new_e2, new_e3);
    return new_if;
  }

  public Exp visit(LetRec e){
    Id new_id = e.id.gen();
    return e;
  }

  public Exp visit(App e){
    return e;
  }

  public Exp visit(Tuple e){
    List<Exp> list_exp = e.es;
    List<Exp> new_list = new LinkedList<Exp>();
    for (Exp exp: list_exp){
      Exp new_exp = exp.accept(this);
      new_list.add(new_exp);
    }
    Tuple new_tuple = new Tuple(new_list);
    return new_tuple;
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
