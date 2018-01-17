package frontend;

import exp.*;
import ast.*;
import ast.type.*;
import java.util.*;

/**
  * Implementation of the closure conversion
  *
  *@author Florian Groguelin
  *
  */

// Seems to work if there is no functions with functions in arguments

public class ClosureConversion implements ObjVisitor<Exp>{
  private List<Closure> func_list= new LinkedList<Closure> ();
  // List of closure classes (functions)

  private HashMap<String, HashSet<String> > free_variables = FreeVariables.getFree_variables();
  // Free variables associated to the functions

  private HashSet<String> set_of_functions = FreeVariables.getSet_of_functions();
  // Set of functions

  private Stack current_let_rec = new Stack();
  // Current functions

  private String current_app = null;
  // Current call

  private boolean in_app = false;
  // We are in call

  private boolean moshi_moshi = false;
  // We have just called

  private HashSet<String> var_call = new HashSet<String> ();
  // Set of called variables

  private HashMap<String, Boolean> closure_done = new HashMap<String, Boolean> ();
  // To know we have done the closure of a function

  private Let kyrie_irving = null;

  /**
    *Visit the Add in the AST
    *
    *@param e an Add

    *@return an modified App
  */
  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  /**
    *Visit the Sub in the AST
    *
    *@param e a Sub

    *@return a new Sub
  */
  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  /**
    *Visit the Let in the AST
    *
    *@param e a Let

    *@return a new Let
  */
  public Exp visit(Let e){
    Exp new_e1 = e.e1.accept(this);
    if (moshi_moshi){
      var_call.add(e.id.toString());
    }
    Exp new_e2 = e.e2.accept(this);
    Let new_e = new Let(e.id, e.t, new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Var in the AST
    *
    *@param e a Var

    *@return a new Var or a Let
  */
  public Exp visit(Var e){
    if (in_app){
      // We are in a call
      Var new_var = null;
      if (set_of_functions.contains(e.id.toString())){
        // The variable is a function
        current_app = "_" + e.id.toString();
        HashSet<String> set_free_variables = free_variables.get(e.id.toString());
        if (set_free_variables == null || set_free_variables.isEmpty()){
          // The function has not free variables
          new_var = new Var(new Id("apply_direct"));
        } else {
          if (!closure_done.get(e.id.toString())){
            // We haven't done the closure_done
            Id id = new Id("");
            id = id.gen();
            new_var = new Var(new Id("make_closure"));
            List<Exp> list = new LinkedList<Exp> ();
            list.add(new Var(new Id("_" + e.id.toString())));
            LetRec prev_fun = (LetRec) current_let_rec.peek();
            for (Id arg: prev_fun.fd.args){
              list.add(new Var(arg))  ;
            }
            kyrie_irving = new Let(id, prev_fun.fd.type, new App(new_var, list), new Var(id));
            closure_done.put(e.id.toString(), true);
            return kyrie_irving;
          } else {
            new_var = new Var(new Id("apply_closure"));
          }
        }
      } else {
        if (!moshi_moshi && var_call.contains(e.id.toString())){
          // We have just called and the variable is called
          current_app = e.id.toString();
          new_var = new Var(new Id("apply_closure"));
        } else {
          new_var = e;
        }
      }
      return new_var;
    } else {
      if (set_of_functions.contains(e.id.toString()) && current_let_rec.size() > 1){
        // The variable is a function and there is at least 2 current functions
        Id id = new Id("");
        id = id.gen();
        LetRec function = (LetRec) current_let_rec.peek();
        Var new_var = new Var(new Id("make_closure"));
        List<Exp> list = new LinkedList<Exp> ();
        list.add(new Var(new Id("_" + e.id.toString())));
        LetRec prev_fun = (LetRec) current_let_rec.get(current_let_rec.size() - 2);
        for (Id arg: prev_fun.fd.args){
          list.add(new Var(arg))  ;
        }
        Let new_let = new Let(id, function.fd.type, new App(new_var, list), new Var(id));
        closure_done.put(e.id.toString(), true);
        return new_let;
      }
      return e;
    }
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

    *@return the same Float e
  */
  public Exp visit(exp.Float e){
    return e;
  }

  /**
    *Visit the Not in the AST
    *
    *@param not a Not

    *@return a new Not
  */
  public Exp visit(Not not){
    Not new_not = new Not(not.e.accept(this));
    return new_not;
  }

  /**
    *Visit the Neg in the AST
    *
    *@param neg a Neg

    *@return a new Neg
  */
  public Exp visit(Neg neg){
    Neg new_neg = new Neg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FNeg in the AST
    *
    *@param neg a FNeg

    *@return a new FNeg
  */
  public Exp visit(FNeg neg){
    FNeg new_neg = new FNeg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FAdd in the AST
    *
    *@param e a FAdd

    *@return a new FAdd
  */
  public Exp visit(FAdd e){
    FAdd new_fadd = new FAdd(e.e1.accept(this), e.e2.accept(this));
    return new_fadd;
  }

  /**
    *Visit the FSub in the AST
    *
    *@param e a FSub

    *@return a new FSub
  */
  public Exp visit(FSub e){
    FSub new_fsub = new FSub(e.e1.accept(this), e.e2.accept(this));
    return new_fsub;
  }

  /**
    *Visit the FMul in the AST
    *
    *@param e a FMul

    *@return a new FMul
  */
  public Exp visit(FMul e){
    FMul new_fmult = new FMul(e.e1.accept(this), e.e2.accept(this));
    return new_fmult;
  }

  /**
    *Visit the FDiv in the AST
    *
    *@param e a FDiv

    *@return a new FDiv
  */
  public Exp visit(FDiv e){
    FDiv new_fdiv = new FDiv(e.e1.accept(this), e.e2.accept(this));
    return new_fdiv;
  }

  /**
    *Visit the Eq in the AST
    *
    *@param e an Eq

    *@return a new Eq
  */
  public Exp visit(Eq e){
    Eq new_eq = new Eq(e.e1.accept(this), e.e2.accept(this));
    return new_eq;
  }

  /**
    *Visit the LE in the AST
    *
    *@param e a LE

    *@return a new LE
  */
  public Exp visit(LE e){
    LE new_le = new LE(e.e1.accept(this), e.e2.accept(this));
    return new_le;
  }

  /**
    *Visit the If in the AST
    *
    *@param e an If

    *@return a new If
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

    *@return an Exp
  */
  public Exp visit(LetRec let_rec){
    current_let_rec.push(let_rec);
    closure_done.put(let_rec.fd.id.toString(), false);
    HashSet<String> set_free_variables = free_variables.get(let_rec.fd.id.toString());
    String label = "_" + let_rec.fd.id.toString();
    List<String> args = new LinkedList<String> ();
    for (Id id: let_rec.fd.args){
      args.add(id.toString());
    }
    Exp new_e = null;
    Closure closure = null;
    Exp new_fd_e = let_rec.fd.e.accept(this);
    if (set_free_variables == null || set_free_variables.isEmpty()){
      // The function has no free variables
      closure = new Closure(label, null, args, new_fd_e);
      current_let_rec.pop();
      new_e = let_rec.e.accept(this);
    } else {
      List<String> free_list = new LinkedList<String> ();
      free_list.addAll(set_free_variables);
      closure = new Closure(label, free_list, args, new_fd_e);
      new_e = let_rec.e.accept(this);
      current_let_rec.pop();
    }
    func_list.add(closure);
    /*System.out.println("Closure list: ");
    System.out.println("closure numbers: " + func_list.size());
    for (Closure clos: func_list){
      System.out.println("\tlabel: " + clos.getLabel());
      System.out.println("\tfree_list: " + clos.getFree_list());
      System.out.println("\targs: " + clos.getArgs());
      System.out.println("\tcode: ");
      clos.getCode().accept(new PrintVisitor());
      System.out.println("");
    }*/
    return new_e;
  }

  /**
    *Visit the App in the AST
    *
    *@param app an App

    *@return a new App or Let
  */
  public Exp visit(App app){
    in_app = true;
    moshi_moshi = false;
    Exp new_e = app.e.accept(this);
    moshi_moshi = true;
    List<Exp> new_list = new LinkedList<Exp> ();
    for (Exp exp: app.es){
      new_list.add(exp.accept(this));
    }
    if (kyrie_irving != null){
      new_list.add(0, new Var(kyrie_irving.id));
      App new_app = new App(new Var(new Id("apply_closure")), new_list);
      Let new_let = new Let(kyrie_irving.id, kyrie_irving.t, kyrie_irving.e1, new_app);
      kyrie_irving = null;
      return new_let;
    } else {
      if (current_app != null){
        new_list.add(0, new Var(new Id(current_app)));
        current_app = null;
      }
      App new_app = new App(new_e, new_list);
      in_app = false;
      return new_app;
    }
  }

  /**
    *Visit the Tuple in the AST
    *
    *@param e a Tuple

    *@return a new Tuple
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

    *@return a new LetTuple
  */
  public Exp visit(LetTuple e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(e.ids, e.ts, new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Array in the AST
    *
    *@param e a Array

    *@return a new Array
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

    *@return a new Get
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

    *@return a new Put
  */
  public Exp visit(Put e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    Put new_e = new Put(new_e1, new_e2, new_e3);
    return new_e;
  }
}
