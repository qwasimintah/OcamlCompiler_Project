package frontend;

import ast.*;
import ast.type.*;
import java.util.*;

@SuppressWarnings("serial")
public class Env{
  public EnvElem head;
  public int size;

  public Env(){
      head = null;
      size = 0;
  }

  public Env hadd(EnvElem elem){

    Env newenv = new Env();
    newenv.size = this.size + 1;
    elem.next = this.head;
    newenv.head = elem;
    // System.out.println(newenv.head);
    return newenv;
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


// newenv = env.hadd(elem)
