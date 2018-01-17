package frontend;

import java.util.*;
import ast.type.*;

/**
  * Implementation of the class Equation (to put in the list), the construction is always the same :
  * the two Types we want to compare.
  *
  *@author Quentin Delfosse
  *
  */

public class Equation{
  public Type t1;
  public Type t2;

  //Only one way to construct it.
  public Equation(Type type1, Type type2){
    t1 = type1;
    t2 = type2;
  }

  public String toString(){
      return "(" + this.t1 + ", " + this.t2 + ")";
  }
}
