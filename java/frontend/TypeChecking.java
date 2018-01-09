package frontend;

import java.util.*;
import exp.*;
import ast.*;
import exceptions.*;

public class TypeChecking implements ObjVisitor<Exp>{
  public Exp visit(Add e){
      System.out.println(e);
      System.out.println(e.e1);
      System.out.println(e.e2);
      try{

        if (e.e1 instanceof Int && e.e2 instanceof Int){
          return e;
        } else {
        throw new WrongTypeException();
        }
      } catch(Exception ex){
        ex.printStackTrace();
        System.exit(1);
      }
      return e;
  }

  private HashSet<String> known = new HashSet<String> ();

  private boolean test_app = false;

  private boolean in_known = false;

  private HashMap<String, Stack> current_variables =  new HashMap<String, Stack> ();

  private boolean free_variable = false;

  private Stack current_functions = new Stack();


  // TODO
  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  public Exp visit(Let e){
    String current_function = (String) current_functions.peek();
    Stack stack = current_variables.get(current_function);
    if (stack == null){
      stack = new Stack();
    }
    stack.push(e.id.toString());
    current_variables.put(current_function, stack);
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(e.id, e.t, new_e1, new_e2);
    return new_let;
  }

  public Exp visit(Var e){
    String current_function = (String) current_functions.peek();
    Stack stack = current_variables.get(current_function);
    int stack_position = stack.search(e.id.toString());
    if (stack_position == -1){
      free_variable = true;
    }
    return e;
  }

  public Exp visit(Int e){
    return e;
  }

  public Exp visit(Unit e){
    return e;
  }

  public Exp visit(exp.Bool e){
    return e;
  }

  public Exp visit(exp.Float e){
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

  public Exp visit(LetRec let_rec){
    if (test_app){
      in_known = known.contains(let_rec.fd.id.toString());
      return let_rec;
    } else {
      current_functions.push(let_rec.fd.id.toString());
      known.add(let_rec.fd.id.toString());
      Stack stack = current_variables.get(let_rec.fd.id.toString());
      if (stack == null){
        stack = new Stack();
      }
      for (Id arg: let_rec.fd.args){
        stack.push(arg.toString());
      }
      current_variables.put(let_rec.fd.id.toString(), stack);
      Exp new_exp_fd = let_rec.fd.e.accept(this);
      if (!free_variable){
        Exp new_e = let_rec.e.accept(this);
      } else {
        //Redo everything
      }
      return let_rec;
    }
  }

  public Exp visit(App app){
    test_app = true;
    Exp new_app = app.e.accept(this);
    test_app = false;
    if (in_known){
      return app;
    } else {
      //TO DO
      return app;
    }
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
