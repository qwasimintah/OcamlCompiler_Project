package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

/**
  * Implementation of the Generation of Equation Class.
  * Class generating Equations in eqt_list
  *
  *@author Quentin Delfosse
  *
  */

public class GenEquation{
  public List<Equation> eqt_list;
  public int app_id;
  public GenEquation(){
      // Load of the environement
      this.eqt_list = new ArrayList();
  }

  /**
    * Generate all the equations in eqt_list
    *
    *@param env of type Env the current environment where we create the equation
    *@param exp of type Exp the current evaluated expression
    *@param exp_type of type Type its expected type
    *@return nothing
  */

  public void generate(Env env, Exp exp, Type exp_type){
      // Basic case handling
      if (exp instanceof Unit) {
              eqt_list.add(new Equation(new TUnit(), exp_type));
      } else if (exp instanceof Bool) {
              eqt_list.add(new Equation(new TBool(), exp_type));
      } else if (exp instanceof Int) {
        eqt_list.add(new Equation(new TInt(), exp_type));
      } else if (exp instanceof exp.Float) {
        eqt_list.add(new Equation(new TFloat(), exp_type));

        // Bool stuff
      } else if (exp instanceof Not) {
          eqt_list.add(new Equation(new TBool(), exp_type));
          generate(env, ((Not)exp).e, new TBool());

          // Int stuff
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

      // Float stuff
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

          // Comparaison stuff
      } else if (exp instanceof LE) {
          eqt_list.add(new Equation(new TBool(), exp_type));
          Type t = Type.gen();
          generate(env, ((LE)exp).e1, t);
          generate(env, ((LE)exp).e2, t);
      } else if (exp instanceof Eq) {
          eqt_list.add(new Equation(new TBool(), exp_type));
          Type t = Type.gen();
          generate(env, ((Eq)exp).e1, t);
          generate(env, ((Eq)exp).e2, t);


          // Declaration stuff
      } else if (exp instanceof Let) {
          Env env1 = env.hadd(new EnvElem(((Let)exp).id, ((Let)exp).t));
          generate(env1, ((Let)exp).e2, exp_type);
          generate(env, ((Let)exp).e1, ((Let)exp).t);
        } else if (exp instanceof LetRec) {
          // System.out.println("in letrec");
          FunDef f = (FunDef)(((LetRec)exp).fd);
          Exp expr1 = f.e; //Peut être dans l'autre sens.
          Exp expr2 = (Exp)(((LetRec)exp).e);
          Env env1, env2;
          Type returnType = Type.gen();
          // Case on the size of the domain of the function
          if (f.args.size() == 0){
            env1 = env.hadd(new EnvElem(f.id, new TFun(returnType)));
            env2 = env.hadd(new EnvElem(f.id, new TFun(returnType)));
          } else if (f.args.size() == 1){
            Type t1 = Type.gen();
            env1 = env.hadd(new EnvElem(f.id, new TFun(t1, returnType)));
            env2 = env.hadd(new EnvElem(f.args.get(0), t1)).hadd(new EnvElem(f.id, new TFun(t1, returnType)));
          } else {
            List<Type> argsList = new ArrayList();
            env2 = env;
            for (int i=0; i < f.args.size(); i++){ //change the env and add an eq for every args
              Type ty = Type.gen();
              argsList.add(ty);
              env2 = env2.hadd(new EnvElem(f.args.get(i), ty));
            }
            env2 = env2.hadd(new EnvElem(f.id, new TFun(argsList, returnType)));
            env1 = env.hadd(new EnvElem(f.id, new TFun(argsList, returnType)));
          }
          generate(env1, expr2, exp_type);
          generate(env2, expr1, returnType);


          // Var stuff
      } else if (exp instanceof Var) {
          Type t1;
          EnvElem node = env.head;
          Boolean inside = false;
          // Checking if the variable is inside.
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
            System.out.println(env);
            System.out.println(((Var)exp).id);
            System.out.println(exp);
            System.exit(1);
          }

          // Call stuff
      } else if (exp instanceof App) {
          Type t1;
          // Case on the number of parameters
          if (((App)exp).es.size() == 0) {
            generate(env, ((App)exp).e, new TFun(exp_type));
          } else if (((App)exp).es.size() == 1){
            t1 = Type.gen();
            generate(env, ((App)exp).e, new TFun(t1, exp_type));
            generate(env, ((App)exp).es.get(0), t1);
          } else {
            List<Type> t = new ArrayList();
            for (int i = 0; i < ((App)exp).es.size(); i++){
              t1 = Type.gen();
              t.add(t1);
              generate(env, ((App)exp).es.get(i), t1);
            }
            generate(env, ((App)exp).e, new TFun(t, exp_type));
          }

          // Conditionnal stuff
      } else if (exp instanceof If) {
          generate(env, ((If)exp).e1, new TBool());
          generate(env, ((If)exp).e2, exp_type);
          generate(env, ((If)exp).e3, exp_type);

          // Tupple stuff
      } else if (exp instanceof Tuple) {
          eqt_list.add(new Equation(new TTuple(), exp_type));
          for (int i = 0; i < ((Tuple)exp).es.size(); i++){
            generate(env, ((Tuple)exp).es.get(i), Type.gen());
          }
      } else if (exp instanceof LetTuple) {
        Env env1 = env;
        for (int i = 0; i < ((LetTuple)exp).ids.size(); i++){ //change the env and add an eq for every id
            env1 = env1.hadd(new EnvElem(((LetTuple)exp).ids.get(i), Type.gen()));
            generate(env, ((LetTuple)exp).e1, Type.gen());
          }
        generate(env1, ((LetTuple)exp).e2, exp_type);


        // Array stuff
      } else if (exp instanceof Array) {
          Type t = Type.gen();
          eqt_list.add(new Equation(new TArray(t), exp_type));
          generate(env, ((Array)exp).e1, new TInt());
          generate(env, ((Array)exp).e2, t);
      } else if (exp instanceof Get) {
          generate(env, ((Get)exp).e1, new TArray(exp_type));
          generate(env, ((Get)exp).e2, new TInt());
      } else if (exp instanceof Put) {
        Type t_array = Type.gen();
        generate(env, ((Put)exp).e1, new TArray(t_array));
        eqt_list.add(new Equation(new TUnit(), exp_type));
        generate(env, ((Put)exp).e2, new TInt());
        generate(env, ((Put)exp).e3, t_array);
      } else {
        System.out.println(exp);
              // shouldn't happen
              assert (false);
      }
  }

}
