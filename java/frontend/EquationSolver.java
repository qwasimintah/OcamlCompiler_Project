package frontend;

import java.util.*;
import exp.*;
import ast.*;
import ast.type.*;

public class EquationSolver{
  public EquationSolver(){}
  // int i, j = 0;
  public void reduce(GenEquation eq){
    for (Equation e : eq.eqt_list){
        // j = 0;
        if (e.t1 instanceof TVar) {
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
        } else if (e.t2 instanceof TVar) {
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
        }
    // i++;
    }
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
