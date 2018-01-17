package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

public class EquationSolver{
  // Class solving Equations passed by parameters to transform and solve
  public EquationSolver(){}
  // int i, j = 0;
  public void transform(GenEquation eq){
    // Transforms equations so they are easier to solve, for exemple, seperate functions equations
    // to equations between their parameters and their results.
    for (int i = 0; i < eq.eqt_list.size(); i++){
      Equation e = eq.eqt_list.get(i);
      if (e.t1 instanceof TFun && e.t2 instanceof TFun){
        // If there are two functions to compare
        TFun f1 = (TFun)e.t1, f2 = (TFun)e.t2;
        eq.eqt_list.remove(e);
        if (f1.nbArgs != f2.nbArgs){ // They must have the same number of arguments
          System.out.println("Those functions have not the same domain dimension.\n" + f1 + f2);
          System.exit(1);
        } else if (f1.nbArgs == 1){
          eq.eqt_list.add(0, new Equation(f1.dom, f2.dom));
        } else {
          for (int j = 0; j < f1.nbArgs; j++){ // For each parameter :
            eq.eqt_list.add(0, new Equation(f1.domlist.get(j), f2.domlist.get(j)));
          }
        }
        eq.eqt_list.add(0, new Equation(f1.codom, f2.codom)); // for the result
      } else if (e.t1 instanceof TFun || e.t2 instanceof TFun){ // Otherwise, with just one function
        // For the functions passed in parameters.
        if (e.t1 instanceof TFun && e.t2 instanceof TVar){
          Type f_save = e.t2;
          for (Equation ee : eq.eqt_list){
              if (f_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t1;
              }
              if (f_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t1;
              }
          }
        } else if (e.t1 instanceof TVar && e.t2 instanceof TFun){
          Type f_save = e.t1;
          for (Equation ee : eq.eqt_list){
              if (f_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t2;
              }
              if (f_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t2;
              }
          }
        }
        i = -1; // to prevent skipping an equation
      } else if (e.t1 instanceof TArray){
          if (e.t2 instanceof TArray){
            if (((TArray)e.t1).t.toString().equals(((TArray)e.t2).t.toString())){
              eq.eqt_list.remove(e);
              eq.eqt_list.add(new Equation(((TArray)e.t1).t, ((TArray)e.t2).t));
              i--; // to prevent skipping an equation
            } else {
              System.out.println("Arrays " + e.t1 + "and " + e.t2 + "are incompatible.");
            }
          }
          Type ty = ((TArray)e.t1).t;
          for (Equation ee : eq.eqt_list){
              if (ty.toString() == ee.t1.toString()){
                if (!(ee.t2 instanceof TVar)){
                  ((TArray)e.t1).t = ee.t2;
                }
              }
              if (ty.toString() == ee.t2.toString()){
                if (!(ee.t1 instanceof TVar)){
                  ((TArray)e.t1).t = ee.t1;
                }
              }
          }
      } else if(e.t2 instanceof TArray) {
          Type ty = ((TArray)e.t2).t;
          for (Equation ee : eq.eqt_list){
              if (ty.toString() == ee.t1.toString()){
                if (!(ee.t2 instanceof TVar)){
                  ((TArray)e.t2).t = ee.t2;
                }
              }
              if (ty.toString() == ee.t2.toString()){
                if (!(ee.t1 instanceof TVar)){
                  ((TArray)e.t2).t = ee.t1;
                }
              }
          }
      }
    }
  }

  public Boolean reduce(GenEquation eq){
    // reduce and simplify the eqation list by removing evident equations after transformations
    this.transform(eq);
    int i = 0, j = 0;
    while (! eq.eqt_list.isEmpty()){
        j++;
        if(j > 9999) { // Looping so unable to loop out, for weird function, not to loop infinitly
          return false;
        }
        if (i == eq.eqt_list.size()){
          i = 0;
          continue;
        }
        Equation e = eq.eqt_list.get(i);
        if (e.t1.toString().equals(e.t2.toString())){
          eq.eqt_list.remove(e);
        } else if (e.t1 instanceof TFun && e.t2 instanceof TFun){
          i++;
          continue;
        }
        if (!(e.t1 instanceof TVar || e.t2 instanceof TVar)){
          if(e.t1.toString().equals(e.t2.toString())){
              eq.eqt_list.remove(e);
              i = 0;
              continue;
          } else {
            System.out.println("You try to convert " + e.t1 + " in " + e.t2);
            System.exit(1);
          }

        } else if (e.t1 instanceof TVar && e.t2 instanceof TVar){
          i++;
          continue;
        } else if (e.t1 instanceof TVar) {
            TVar e_save = (TVar)e.t1;
            for (Equation ee : eq.eqt_list){
                if (e_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t2;
                }
                if (e_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t2;
                }
            }
            i = 0;
        } else if (e.t2 instanceof TVar){
            TVar e_save = (TVar)e.t2;
            for (Equation ee : eq.eqt_list){
                if (e_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t1;
                }
                if (e_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t1;
                }
              }
              i = 0;
        } else if (!e.t1.getClass().equals(e.t2.getClass())){
          System.out.println(e.t1 + " and " + e.t2 + "are not from the same type.");
          System.exit(1);
        }
    }
    return eq.eqt_list.isEmpty();
  }

  public Boolean solve(GenEquation eq){
    // solve the equation, for now, if the left eqations are just variables, it consider it as true
    Boolean all_variables = true;
    for (Equation e : eq.eqt_list){ //checking if all equation is just composed of variables, to check
      if (!(e.t1 instanceof TVar && e.t2 instanceof TVar)) {
        all_variables = false;
        break;
      }
    } if (!all_variables){ //if not, might try to reduce it.
        for (Equation e : eq.eqt_list){
          if (!e.t1.getClass().equals(e.t2.getClass())){
            return false;
          }
        }
    }
    return true;
  }
}
