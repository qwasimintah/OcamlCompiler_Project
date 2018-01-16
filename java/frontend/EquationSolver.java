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
          System.out.println("Functions " + f1.id + " and " + f2.id + " aren't from same type.\n" + f1 + f2);
          System.exit(1);
        } else if (f1.nbArgs == 1){
          eq.eqt_list.add(0, new Equation(f1.dom, f2.dom));
        } else {
          System.out.println("caca" + f1.nbArgs);
          for (int j = 0; j < f1.nbArgs; j++){
            eq.eqt_list.add(0, new Equation(f1.domlist.get(j), f2.domlist.get(j)));
          }
        }
        eq.eqt_list.add(0, new Equation(f1.codom, f2.codom));
      } else if (e.t1 instanceof TFun || e.t2 instanceof TFun){
        System.out.println("Function associated with a non function");
        System.exit(1);
      }
      // System.out.println(i);
    }
  }

  public Boolean reduce(GenEquation eq){
    // for (Equation e : eq.eqt_list)
    this.transform(eq);
    System.out.println("transformed eq list : " + eq.eqt_list);
    int i = 0;
    while (! eq.eqt_list.isEmpty()){
        Equation e = eq.eqt_list.get(i);
        if (!(e.t1 instanceof TVar || e.t2 instanceof TVar)){
          if(e.t1.toString().equals(e.t2.toString())){
              eq.eqt_list.remove(e);
              i = 0;
              continue;
          } else {
            return false;
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
          // System.out.println("In NOTIN");
          return false;
        }
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

    // i++;
    }
    return eq.eqt_list.isEmpty();
  }

  public Boolean solve(GenEquation eq){
    // int i = 1;
    for (Equation e : eq.eqt_list){
      if (!e.t1.getClass().equals(e.t2.getClass())){
        System.out.println(e.t2.getClass());
        return false;
      }
      // i++;
    }
    return true;
  }
}
