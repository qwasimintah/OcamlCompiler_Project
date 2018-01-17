package frontend;

import ast.*;
import ast.type.*;
import java.util.*;

/**
  * Implementation of EnvElem, a cell of the complete environment, with several way to construct it.
  *
  *
  *@author Quentin Delfosse
  *
  */

@SuppressWarnings("serial")
public class EnvElem{

  public Id id;
  public Type t;
  public EnvElem next;

  public EnvElem(Id i, Type type){
      id = i;
      t = type;
      next = null;
  }

  public EnvElem(Id i, Type type, EnvElem suiv){
      id = i;
      t = type;
      next = suiv;
  }

  public String toString(){
    String result = new String();
    result += this.id;
    result += " : ";
    result += this.t;
    return result;
  }

}
