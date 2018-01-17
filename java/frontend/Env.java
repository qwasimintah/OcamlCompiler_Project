package frontend;

import ast.*;
import ast.type.*;
import java.util.*;

@SuppressWarnings("serial")
public class Env{
  // Class of the environment chained list
  public EnvElem head;
  public int size;

  // CONSTRUCTORS
  public Env(){
      head = null;
      size = 0;
  }

  public Env(LinkedList<EnvElem> predef){
    head = null;
    size = 0;
    for (int i = 0; i < predef.size(); i++) {
      EnvElem elem = (EnvElem)(predef.get(i));
      elem.next = head;
      head = elem;
      size ++;
    }
  }

  public Env hadd(EnvElem elem){
    //to add elem to the head, does not modify the env but returned the modified one
    Env newenv = new Env();
    newenv.size = this.size + 1;
    elem.next = this.head;
    newenv.head = elem;
    return newenv;
  }

  public String toString(){
      String result = new String();
      EnvElem node = this.head;
      result = "[";
      int i = 0;
      while(node.next != null)
      {
        result += node;
        result += ", ";
        node = node.next;
      }
      result += node;
      result += "]";
      return result;
  }


}
