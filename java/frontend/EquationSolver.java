package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

public class EquationSolver{
  public EquationSolver(){}
  // int i, j = 0;
  public void transform(GenEquation eq){
    for (int i = 0; i < eq.eqt_list.size(); i++){
      Equation e = eq.eqt_list.get(i);
      // System.out.println(i);
      // System.out.println(eq.eqt_list);
      if (e.t1 instanceof TFun && e.t2 instanceof TFun){
        TFun f1 = (TFun)e.t1, f2 = (TFun)e.t2;
        eq.eqt_list.remove(e);
        if (f1.nbArgs != f2.nbArgs){
          System.out.println("Those functions have not the same domain dimension.\n" + f1 + f2);
          System.exit(1);
        } else if (f1.nbArgs == 1){
          eq.eqt_list.add(0, new Equation(f1.dom, f2.dom));
        } else {
          // System.out.println("caca" + f1.nbArgs);
          for (int j = 0; j < f1.nbArgs; j++){
            eq.eqt_list.add(0, new Equation(f1.domlist.get(j), f2.domlist.get(j)));
          }
        }
        eq.eqt_list.add(0, new Equation(f1.codom, f2.codom));
      } else if (e.t1 instanceof TFun || e.t2 instanceof TFun){
        if (e.t1 instanceof TFun && e.t2 instanceof TVar){
          Type f_save = e.t2;
          for (Equation ee : eq.eqt_list){
              // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
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
              // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
              if (f_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t2;
              }
              if (f_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t2;
              }
          }
        }
        i = 0;
      } else if (e.t1 instanceof TArray){
          Type ty = ((TArray)e.t1).t;
          // System.out.println("i = " + i + " e.t2 = " + e.t2);
          for (Equation ee : eq.eqt_list){
              // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
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
          // System.out.println("i = " + i + " e.t2 = " + e.t2);
          for (Equation ee : eq.eqt_list){
              // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
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
    // for (Equation e : eq.eqt_list)
    this.transform(eq);
    System.out.println("transformed eq list : " + eq.eqt_list);
    int i = 0;
    while (! eq.eqt_list.isEmpty()){
        if (i == eq.eqt_list.size()){
          i = 0;
          continue;
        }
        Equation e = eq.eqt_list.get(i);
        if (e.t1.toString().equals(e.t2.toString())){
          // System.out.println("ehouaisnegro");
          eq.eqt_list.remove(e);
          // System.out.println(eq.eqt_list);
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
          // System.out.println(eq.eqt_list);
          // try
          // {
          //     Thread.sleep(1000);
          // }
          // catch(InterruptedException ex)
          // {
          //     Thread.currentThread().interrupt();
          // }
          // System.out.println(i);
          i++;
          continue;
        } else if (e.t1 instanceof TVar) {
            TVar e_save = (TVar)e.t1;
            // System.out.println("i = " + i + " e.t2 = " + e.t2);
            for (Equation ee : eq.eqt_list){
                // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
                if (e_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t2;
                }
                if (e_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t2;
                }
                // j++;
            }
            i = 0;
        } else if (e.t2 instanceof TVar){
            TVar e_save = (TVar)e.t2;
            // System.out.println("i = " + i + " e.t2 = " + e.t2);
            for (Equation ee : eq.eqt_list){
                // System.out.println("j = " + j + " ee.t2 = " + ee.t2);
                if (e_save.toString() == ee.t1.toString()){
                  ee.t1 = e.t1;
                }
                if (e_save.toString() == ee.t2.toString()){
                  ee.t2 = e.t1;
                }
                // j++;
              }
              i = 0;
        } else if (!e.t1.getClass().equals(e.t2.getClass())){
          System.out.println(e.t1 + " and " + e.t2 + "are not from the same type.");
          System.exit(1);
        }
        // System.out.println(eq.eqt_list);
        // System.out.println(eq.eqt_list);
        // try
        // {
        //     Thread.sleep(1000);
        // }
        // catch(InterruptedException ex)
        // {
        //     Thread.currentThread().interrupt();
        // }
        // System.out.println(i);

    // i++;
    }
    // System.out.println("hey");
    return eq.eqt_list.isEmpty();
  }

  public Boolean solve(GenEquation eq){
    for (Equation e : eq.eqt_list){
      if (!e.t1.getClass().equals(e.t2.getClass())){
        return false;
      }
    }
    return true;
  }
}
