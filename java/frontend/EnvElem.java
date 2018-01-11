package frontend;

import ast.*;
import ast.type.*;
import java.util.*;

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
