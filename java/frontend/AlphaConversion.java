package frontend;

import java.util.*;
import exp.*;
import ast.*;

/**
  * Implementation of the alpha conversion of an expression
  *
  *@author Florian Groguelin
  *
  */

public class AlphaConversion implements ObjVisitor<Exp>{
  private HashMap<String, Stack> epsilon = new HashMap<String, Stack> ();
  //  Set which associates a variable to all the others current names of this variable

  private boolean sec_exp_let = false;
  // To know if we are after a Let or LetRec

  private HashMap<String, Stack> used_in_let = new HashMap<String, Stack> ();
  // Mapping of a variable (Let or LetRec) to all the variables used inside

  private HashSet<String> functions = new HashSet();
  // Set of functions (LetRec)

  /**
    *Visit the Add in the AST
    *
    *@param e an Add

    *@return an alpha converted Add
  */
  public Exp visit(Add e){
    Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
    return new_add;
  }

  /**
    *Visit the Sub in the AST
    *
    *@param e a Sub

    *@return an alpha converted Sub
  */
  public Exp visit(Sub e){
    Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
    return new_sub;
  }

  /**
    *Visit the Let in the AST
    *
    *@param e a Let

    *@return an alpha converted Let
  */
  public Exp visit(Let e){
    Var new_var = new Var(e.id.gen());
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
      epsilon.put(e.id.toString(), stack);
    }
    stack.push(new_var.id.toString());
    Exp new_e1 = e.e1.accept(this);
    sec_exp_let = true;
    Exp new_e2 = e.e2.accept(this);
    Let new_let = new Let(new_var.id, e.t, new_e1, new_e2);
    HashSet set = new HashSet();
    Stack used_vars = used_in_let.get(e.id.toString());
    if (used_vars == null){
      used_vars = new Stack();
      used_in_let.put(e.id.toString(), used_vars);
    }
    while (!used_vars.empty()){
      // Some variables are used in this Let
      String key = (String) used_vars.pop();
      if ((stack.size() > 1 && functions.contains(key)) || !functions.contains(key)){
        // Either the variable has a function name and a variable name
        // Or the variable is not a function
        Stack tmp_stack = epsilon.get(key);
        if (!tmp_stack.empty() && !set.contains(key)){
          // The most recent new name of the variable is remove (only one time)
          tmp_stack.pop();
          set.add(key);
        }
      }
    }
    return new_let;
  }

  /**
    *Visit the Var in the AST
    *
    *@param e a Var

    *@return an alpha converted Var
  */
  public Exp visit(Var e){
    Stack stack = epsilon.get(e.id.toString());
    if (stack == null) {
      stack = new Stack();
      epsilon.put(e.id.toString(), stack);
    }
    if (!stack.empty()){
      // The variable has no others names
      if (!sec_exp_let){
        if (functions.contains(e.id.toString())){
          // The variable is a funtion
          Var new_var = new Var(new Id((String) stack.peek()));
          return new_var;
        } else {
          // The variable isn't a funtion
          Var new_var = new Var(e.id.gen());
          stack.push(new_var.id.toString());
          return new_var;
        }
      } else {
        // The variable is used in this Let
        Stack used_vars = used_in_let.get(e.id.toString());
        if (used_vars == null){
          used_vars = new Stack();
          used_in_let.put(e.id.toString(), used_vars);
        }
        used_vars.push(e.id.toString());
        Var new_var = new Var(new Id((String) stack.peek()));
        return new_var;
      }
    } else {
      return e;
    }
  }

  /**
    *Visit the Int in the AST
    *
    *@param e an Int

    *@return the Int e
  */
  public Exp visit(Int e){
    return e;
  }

  /**
    *Visit the Unit in the AST
    *
    *@param e an Unit

    *@return the Unit e
  */
  public Exp visit(Unit e){
    return e;
  }

  /**
    *Visit the Bool in the AST
    *
    *@param e a Bool

    *@return the Bool e
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
    *Visit the Not in the AST
    *
    *@param not a Not

    *@return an alpha converted Not
  */
  public Exp visit(Not not){
    Not new_not = new Not(not.e.accept(this));
    return new_not;
  }

  /**
    *Visit the Neg in the AST
    *
    *@param neg an Neg

    *@return an alpha converted Neg
  */
  public Exp visit(Neg neg){
    Neg new_neg = new Neg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FNeg in the AST
    *
    *@param neg a FNeg

    *@return an alpha converted FNeg
  */
  public Exp visit(FNeg neg){
    FNeg new_neg = new FNeg(neg.e.accept(this));
    return new_neg;
  }

  /**
    *Visit the FAdd in the AST
    *
    *@param e a FAdd

    *@return an alpha converted FAdd
  */
  public Exp visit(FAdd e){
    FAdd new_fadd = new FAdd(e.e1.accept(this), e.e2.accept(this));
    return new_fadd;
  }

  /**
    *Visit the FSub in the AST
    *
    *@param e a FSub

    *@return an alpha converted FSub
  */
  public Exp visit(FSub e){
    FSub new_fsub = new FSub(e.e1.accept(this), e.e2.accept(this));
    return new_fsub;
  }

  /**
    *Visit the FMul in the AST
    *
    *@param e a FMul

    *@return an alpha converted FMul
  */
  public Exp visit(FMul e){
    FMul new_fmult = new FMul(e.e1.accept(this), e.e2.accept(this));
    return new_fmult;
  }

  /**
    *Visit the FDiv in the AST
    *
    *@param e a FDiv

    *@return an alpha converted FDiv
  */
  public Exp visit(FDiv e){
    FDiv new_fdiv = new FDiv(e.e1.accept(this), e.e2.accept(this));
    return new_fdiv;
  }

  /**
    *Visit the Eq in the AST
    *
    *@param e an Eq

    *@return an alpha converted Eq
  */
  public Exp visit(Eq e){
    Eq new_eq = new Eq(e.e1.accept(this), e.e2.accept(this));
    return new_eq;
  }

  /**
    *Visit the LE in the AST
    *
    *@param e a LE

    *@return an alpha converted LE
  */
  public Exp visit(LE e){
    LE new_le = new LE(e.e1.accept(this), e.e2.accept(this));
    return new_le;
  }

  /**
    *Visit the If in the AST
    *
    *@param e an If

    *@return an alpha converted If
  */
  public Exp visit(If e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    If new_if = new If(new_e1, new_e2, new_e3);
    return new_if;
  }

  /**
    *Visit the App in the AST
    *
    *@param app an App

    *@return an alpha converted App
  */
  public Exp visit(App app){
    Exp new_exp = app.e.accept(this);
    List<Exp> new_list = new LinkedList<Exp>();
    for (Exp exp: app.es){
      Exp exp_list = exp.accept(this);
      new_list.add(exp_list);
    }
    App new_app = new App(new_exp, new_list);
    return new_app;
  }

  /**
    *Visit the Tuple in the AST
    *
    *@param e a Tuple

    *@return an alpha converted Tuple
  */
  public Exp visit(Tuple e){
    List<Exp> new_list = new LinkedList<Exp>();
    sec_exp_let = true;
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

    *@return an alpha converted LetTuple
  */
  public Exp visit(LetTuple e){
    List<Id> new_ids = new LinkedList<Id>();
    for (Id id: e.ids){
      Id new_id = id.gen();
      Stack stack = epsilon.get(id.toString());
      if (stack == null){
        stack = new Stack();
        epsilon.put(id.toString(), stack);
      }
      stack.push(new_id.toString());
      new_ids.add(new_id);
    }
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    LetTuple new_e = new LetTuple(new_ids, e.ts, new_e1, new_e2);
    return new_e;
  }

  /**
    *Visit the Array in the AST
    *
    *@param e an Array

    *@return an alpha converted Array
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

    *@return an alpha converted Get
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

    *@return an alpha converted Put
  */
  public Exp visit(Put e){
    Exp new_e1 = e.e1.accept(this);
    Exp new_e2 = e.e2.accept(this);
    Exp new_e3 = e.e3.accept(this);
    Put new_e = new Put(new_e1, new_e2, new_e3);
    return new_e;
  }

  /**
    *Visit the LetRec in the AST
    *
    *@param let_rec a LetRec

    *@return an alpha converted LetRec
  */
  public Exp visit(LetRec let_rec){
    functions.add(let_rec.fd.id.toString());
    Id new_id = let_rec.fd.id.gen();
    Stack stack = epsilon.get(let_rec.fd.id.toString());
    if (stack == null) {
      stack = new Stack();
      epsilon.put(let_rec.fd.id.toString(), stack);
    }
    stack.push(new_id.toString());
    List<Id> new_args = new LinkedList();
    for (Id arg: let_rec.fd.args){
      Id new_arg = arg.gen();
      Stack arg_stack = epsilon.get(arg.toString());
      if (arg_stack == null){
        arg_stack = new Stack();
        epsilon.put(arg.toString(), arg_stack);
      }
      arg_stack.push(new_arg.toString());
      new_args.add(new_arg);
    }
    sec_exp_let = true;
    Exp new_exp = let_rec.fd.e.accept(this);
    FunDef new_fd = new FunDef(new_id, let_rec.fd.type, new_args, new_exp);
    for (Id arg: let_rec.fd.args){
      Stack tmp_arg_stack = epsilon.get(arg.toString());
      if (!tmp_arg_stack.empty()) {
        tmp_arg_stack.pop();
      }
    }
    sec_exp_let = true;
    Exp let_rec_e = let_rec.e.accept(this);
    sec_exp_let = false;
    LetRec new_let_rec = new LetRec(new_fd, let_rec_e);
    return new_let_rec;
  }
}
