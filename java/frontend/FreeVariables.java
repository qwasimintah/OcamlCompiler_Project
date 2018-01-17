package frontend;

import exp.*;
import ast.*;
import java.util.*;

/**
  * Implementation of the detection of free variables
  *
  *@author Florian Groguelin
  *
  */
public class FreeVariables implements ObjVisitor<Exp>{
  private HashMap<String, HashSet<String> > current_variables =  new HashMap<String, HashSet<String> > ();
  // Mapping of variables (Let or LetRec) to the alive variables in a certain context

  private static HashMap<String, HashSet<String> > free_variables = new HashMap<String, HashSet<String> > ();
  // List of free variables associate to a function

  private Stack current_functions = new Stack();
  // List of current functions

  private static HashSet<String> set_of_functions = new HashSet<String> ();
  // Set of functions (LetRec)

  private HashSet<String> external_functions = new HashSet<String> ();
  {{
    external_functions.add("print_int");
    external_functions.add("print_newline");
    external_functions.add("truncate");
    external_functions.add("sin");
    external_functions.add("cos");
    external_functions.add("sqrt");
    external_functions.add("abs_float");
    external_functions.add("int_of_float");
    external_functions.add("float_of_int");
  }}
  // Set of external functions

  public static HashMap<String, HashSet<String> > getFree_variables(){
    return free_variables;
  }

  public static HashSet<String> getSet_of_functions(){
    return set_of_functions;
  }

  /**
    *Visit the Add in the AST
    *
    *@param e an Add

    *@return an Add
  */
  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  /**
    *Visit the Sub in the AST
    *
    *@param e a Sub

    *@return a Sub
  */
  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  /**
    *Visit the Let in the AST
    *
    *@param e a Let

    *@return a Let
  */
  public Exp visit(Let e){
    if (!current_functions.empty()){
      // We are in an function
      LetRec function = (LetRec) current_functions.peek();
      String current_function = function.fd.id.toString();
      HashSet set = current_variables.get(current_function);
      if (set == null){
        set = new HashSet();
        current_variables.put(current_function, set);
      }
      set.add(e.id.toString());
    }
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(e.id, e.t, new_e1, new_e2);
    return new_let;
  }

  /**
    *Visit the Var in the AST
    *
    *@param e a Var

    *@return a Var
  */
  public Exp visit(Var e){
    if (!current_functions.empty()){
      // We are in a function
      LetRec function = (LetRec) current_functions.peek();
      String current_function = function.fd.id.toString();
      HashSet set = current_variables.get(current_function);
      boolean in_set = set.contains(e.id.toString());
      if (!set_of_functions.contains(e.id.toString()) && !external_functions.contains(e.id.toString())){
        // The variable is not a funtion and not an external funtion
        for (int i = 0; i < current_functions.size(); i++){
          // For all the current functions
          LetRec tmp_func = (LetRec) current_functions.get(i);
          HashSet set_free_variables = free_variables.get(tmp_func.fd.id.toString());
          if (set_free_variables == null){
            set_free_variables = new HashSet();
            free_variables.put(tmp_func.fd.id.toString(), set_free_variables);
          }
          HashSet tmp_set = current_variables.get(tmp_func.fd.id.toString());
          if (tmp_set == null){
            tmp_set = new HashSet();
            current_variables.put(tmp_func.fd.id.toString(), tmp_set);
          }
          if (!tmp_set.contains(e.id.toString()) && !in_set){
            // We add the free variable only one time
            set_free_variables.add(e.id.toString());
          }
        }
      }
    }
    return e;
  }

  /**
    *Visit the Int in the AST
    *
    *@param e an Int

    *@return the same Int e
  */
  public Exp visit(Int e){
    return e;
  }

  /**
    *Visit the Unit in the AST
    *
    *@param e an Unit

    *@return the same Unit e
  */
  public Exp visit(Unit e){
    return e;
  }

  /**
    *Visit the Bool in the AST
    *
    *@param e a Bool

    *@return the same Bool e
  */
  public Exp visit(exp.Bool e){
    return e;
  }

  /**
    *Visit the Float in the AST
    *
    *@param e a Float

    *@return the Float e
  */
  public Exp visit(exp.Float e){
    return e;
  }

  /**
    *Visit the NOT in the AST
    *
    *@param not a Not

    *@return a Not
  */
  public Exp visit(Not not){
    Not new_not = new Not(not.e.accept(this));
    return new_not;
  }

  /**
    *Visit the Neg in the AST
    *
    *@param neg a Neg

    *@return a Neg
  */
  public Exp visit(Neg neg){
    Neg new_neg = new Neg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FNeg in the AST
    *
    *@param neg a FNeg

    *@return a FNeg
  */
  public Exp visit(FNeg neg){
    FNeg new_neg = new FNeg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FAdd in the AST
    *
    *@param e a FAdd

    *@return a Fadd
  */
  public Exp visit(FAdd e){
    FAdd new_fadd = new FAdd(e.e1.accept(this), e.e2.accept(this));
    return new_fadd;
  }

  /**
    *Visit the FSub in the AST
    *
    *@param e a FSub

    *@return a FSub
  */
  public Exp visit(FSub e){
    FSub new_fsub = new FSub(e.e1.accept(this), e.e2.accept(this));
    return new_fsub;
  }

  /**
    *Visit the FMul in the AST
    *
    *@param e a FMul

    *@return a FMul
  */
  public Exp visit(FMul e){
    FMul new_fmult = new FMul(e.e1.accept(this), e.e2.accept(this));
    return new_fmult;
  }

  /**
    *Visit the FDiv in the AST
    *
    *@param e a FDiv

    *@return a FDiv
  */
  public Exp visit(FDiv e){
    FDiv new_fdiv = new FDiv(e.e1.accept(this), e.e2.accept(this));
    return new_fdiv;
  }

  /**
    *Visit the Eq in the AST
    *
    *@param e an Eq

    *@return an Eq
  */
  public Exp visit(Eq e){
    Eq new_eq = new Eq(e.e1.accept(this), e.e2.accept(this));
    return new_eq;
  }

  /**
    *Visit the LE in the AST
    *
    *@param e a LE

    *@return a LE
  */
  public Exp visit(LE e){
    LE new_le = new LE(e.e1.accept(this), e.e2.accept(this));
    return new_le;
  }

  /**
    *Visit the If in the AST
    *
    *@param e an If

    *@return an If
  */
  public Exp visit(If e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    If new_if = new If(new_e1, new_e2, new_e3);
    return new_if;
  }

  /**
    *Visit the LetRec in the AST
    *
    *@param let_rec a LetRec

    *@return a LetRec
  */
  public Exp visit(LetRec let_rec){
    set_of_functions.add(let_rec.fd.id.toString());
    current_functions.push(let_rec);
    HashSet set = current_variables.get(let_rec.fd.id.toString());
    if (set == null){
      set = new HashSet();
      current_variables.put(let_rec.fd.id.toString(), set);
    }
    for (Id arg: let_rec.fd.args){
      set.add(arg.toString());
      for (int i = 0; i < current_functions.size(); i++){
        // The function is a current variable of all the others current function
        LetRec tmp_func = (LetRec) current_functions.get(i);
        String tmp_id = tmp_func.fd.id.toString();
        HashSet tmp_set = current_variables.get(tmp_id);
        tmp_set.add(arg.toString());
      }
    }
    Exp new_exp_fd = let_rec.fd.e.accept(this);
    HashSet set_free_variables = free_variables.get(let_rec.fd.id.toString());
    if (set_free_variables == null){
      set_free_variables = new HashSet();
    }
    FunDef new_fd = new FunDef(let_rec.fd.id, let_rec.fd.type, let_rec.fd.args, new_exp_fd);
    current_functions.pop();
    Exp new_e = let_rec.e.accept(this);
    LetRec new_let_rec = new LetRec(new_fd, new_e);
    return new_let_rec;
  }

  /**
    *Visit the App in the AST
    *
    *@param app an App

    *@return an App
  */
  public Exp visit(App app){
    if (current_functions.size() > 1){
      // There is at least 2 current functions
      Exp new_e = app.e.accept(this);
      List<Exp> new_es = new LinkedList<Exp> ();
      for (Exp tmp_exp: app.es){
        new_es.add(tmp_exp.accept(this));
      }
      App new_app = new App(new_e, new_es);
      return new_app;
    } else {
      return app;
    }
  }

  /**
    *Visit the Tuple in the AST
    *
    *@param e a Tuple

    *@return a Tuple
  */
  public Exp visit(Tuple e){
    List<Exp> new_list = new LinkedList<Exp>();
    for (Exp exp: e.es){
      Exp new_exp = exp.accept(this);
      new_list.add(new_exp);
    }
    Tuple new_tuple = new Tuple(new_list);
    return new_tuple;
  }

  /**
    *Visit the LetTuple in the AST
    *
    *@param e a LetTuple

    *@return a LetTuple
  */
  public Exp visit(LetTuple e){
    if (!current_functions.empty()){
      // We are in a function
      for (Id exp: e.ids){
        LetRec function = (LetRec) current_functions.peek();
        String current_function = function.fd.id.toString();
        HashSet set = current_variables.get(current_function);
        if (set == null){
          set = new HashSet();
          current_variables.put(current_function, set);
        }
        set.add(exp.toString());
      }
    }
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(e.ids, e.ts, new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Array in the AST
    *
    *@param e an Array

    *@return an Array
  */
  public Exp visit(Array e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Array new_e = new Array(new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Get in the AST
    *
    *@param e a Get

    *@return a Get
  */
  public Exp visit(Get e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Get new_e = new Get(new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Put in the AST
    *
    *@param e a Put

    *@return a Put
  */
  public Exp visit(Put e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    Put new_e = new Put(new_e1, new_e2, new_e3);
    return new_e;
  }
}
