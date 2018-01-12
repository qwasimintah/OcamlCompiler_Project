package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

public class GenEquation{
  public List<Equation> eqt_list;
  public GenEquation(){
      // Load of the environement
      this.eqt_list = new ArrayList();
      // System.out.println(envr);
  }
  public void generate(Env env, Exp exp, Type exp_type){
      if (exp instanceof Unit) {
              eqt_list.add(new Equation(new TUnit(), exp_type));
      } else if (exp instanceof Bool) {
              eqt_list.add(new Equation(new TBool(), exp_type));


      } else if (exp instanceof Int) {
        eqt_list.add(new Equation(new TInt(), exp_type));
      } else if (exp instanceof exp.Float) {
        eqt_list.add(new Equation(new TFloat(), exp_type));
      } else if (exp instanceof Not) {
          eqt_list.add(new Equation(new TBool(), exp_type));
          generate(env, ((Not)exp).e, new TBool());
      } else if (exp instanceof Neg) {
          eqt_list.add(new Equation(new TInt(), exp_type));
          generate(env, ((Neg)exp).e, new TInt());


      } else if (exp instanceof Add) {
          eqt_list.add(new Equation(new TInt(), exp_type));
          generate(env, ((Add)exp).e1, new TInt());
          generate(env, ((Add)exp).e2, new TInt());
      } else if (exp instanceof Sub) {
          eqt_list.add(new Equation(new TInt(), exp_type));
          generate(env, ((Sub)exp).e1, new TInt());
          generate(env, ((Sub)exp).e2, new TInt());


      } else if (exp instanceof FNeg) {
          eqt_list.add(new Equation(new TFloat(), exp_type));
          generate(env, ((FNeg)exp).e, new TFloat());
      } else if (exp instanceof FAdd) {
          eqt_list.add(new Equation(new TFloat(), exp_type));
          generate(env, ((FAdd)exp).e1, new TFloat());
          generate(env, ((FAdd)exp).e2, new TFloat());
      } else if (exp instanceof FSub) {
          eqt_list.add(new Equation(new TFloat(), exp_type));
          generate(env, ((FSub)exp).e1, new TFloat());
          generate(env, ((FSub)exp).e2, new TFloat());
      } else if (exp instanceof FMul) {
          eqt_list.add(new Equation(new TFloat(), exp_type));
          generate(env, ((FMul)exp).e1, new TFloat());
          generate(env, ((FMul)exp).e2, new TFloat());
      } else if (exp instanceof FDiv) {
          eqt_list.add(new Equation(new TFloat(), exp_type));
          generate(env, ((FDiv)exp).e1, new TFloat());
          generate(env, ((FDiv)exp).e2, new TFloat());

      } else if (exp instanceof LE) {
          eqt_list.add(new Equation(new TBool(), exp_type));
          Type t = Type.gen();
          generate(env, ((LE)exp).e1, t);
          generate(env, ((LE)exp).e2, t);
      // } else if (exp instanceof If) {
      //         If e = (If) exp;
      //         res = Math.max(computeHeight(e.e1), Math.max(computeHeight(e.e2), computeHeight(e.e3))) + 1;
      } else if (exp instanceof Let) {
          Env env1 = env.hadd(new EnvElem(((Let)exp).id, ((Let)exp).t));
          // System.out.println(env + " size : " + env.size);
          // System.out.println(env1 + " size : " + env1.size);
          generate(env1, ((Let)exp).e2, exp_type);
          generate(env, ((Let)exp).e1, ((Let)exp).t);
      } else if (exp instanceof Var) {
          Type t1;
          EnvElem node = env.head;
          Boolean inside = false;
          while(node != null){
            if(node.id.toString().equals(((Var)exp).id.toString())){
              t1 = node.t;
              inside = true;
              eqt_list.add(new Equation(t1, exp_type));
              break;
            }
            node = node.next;
          }
          if (!inside){
            System.out.println("Erreur de typage");
            System.exit(1);
          }

      }
      // } else if (exp instanceof LetRec) {
      //         LetRec e = (LetRec) exp;
      //         res = Math.max(computeHeight(e.e), computeHeight(e.fd.e)) + 1;
      // } else if (exp instanceof App) {
      //         App e = (App) exp;
      //         res = computeHeight(e.e);
      //         for (Exp e1 : e.es) {
      //                 res = Math.max(computeHeight(e1), res);
      //         }
      //         res++;
      // } else if (exp instanceof Tuple) {
      //         Tuple e = (Tuple) exp;
      //         res = 0;
      //         for (Exp e1 : e.es) {
      //                 res = Math.max(computeHeight(e1), res);
      //         }
      //         res++;
      // } else if (exp instanceof LetTuple) {
      //         LetTuple e = (LetTuple) exp;
      //         res = Math.max(computeHeight(e.e1), computeHeight(e.e2)) + 1;
      // } else if (exp instanceof Array) {
      //         Array e = (Array) exp;
      //         res = Math.max(computeHeight(e.e1), computeHeight(e.e2)) + 1;
      // } else if (exp instanceof Get) {
      //         Get e = (Get) exp;
      //         res = Math.max(computeHeight(e.e1), computeHeight(e.e2)) + 1;
      // }  else if (exp instanceof Put) {
      //         Put e = (Put) exp;
      //         res = Math.max(computeHeight(e.e1), Math.max(computeHeight(e.e2), computeHeight(e.e3))) + 1;
      // } else {
      //         // shouldn't happen
      //         assert (false);
      // }
      // return res;
  }

}
