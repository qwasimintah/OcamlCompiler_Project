package frontend;

import ast.*;
import ast.type.*;
import java.util.*;

/**
  * Implementation of the Environement as a chained list of EnvElem.
  *
  *
  *@author Quentin Delfosse
  *
  */


@SuppressWarnings("serial")
public class Env{
  public EnvElem head;
  public int size;

  /**
    * Generate all the equations in eqt_list
    *
    *@param _ no parameters
    *@return nothing
  */
  public Env(){
      head = null;
      size = 0;
  }

  /**
    * Generate all the equations in eqt_list
    *
    *@param predef the linked list of element necessary to construct our cells
    *@return nothing
  */

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
  /**
    * Adds an element to the head. Does not modify the env but returned the modified one
    *
    *@param elem of type EnvElem elem to the head
    *@return an new Environment (in order to keep the previous one clean)
  */
  public Env hadd(EnvElem elem){
    Env newenv = new Env();
    newenv.size = this.size + 1;
    elem.next = this.head;
    newenv.head = elem;
    return newenv;
  }
  /**
    * Generate the predefined environment in a linked list. Have to give it to the constructor to get the
    * predefined environment with type of this class
    *@param _ None
    *@return a linked list of what should contained the basic Environment.
  */
  public LinkedList<EnvElem> gen_predef(){
    LinkedList<EnvElem> env = new LinkedList<EnvElem>();
    env.add(new EnvElem(new Id("print_string"), new TFun(new TString(), new TUnit())));
    env.add(new EnvElem(new Id("print_int"), new TFun(new TInt(), new TUnit())));
    env.add(new EnvElem(new Id("int_of_float"), new TFun(new TFloat(), new TInt())));
    env.add(new EnvElem(new Id("float_of_int"), new TFun(new TInt(), new TFloat())));
    env.add(new EnvElem(new Id("sin"), new TFun(new TFloat(), new TFloat())));
    env.add(new EnvElem(new Id("cos"), new TFun(new TFloat(), new TFloat())));
    env.add(new EnvElem(new Id("sqrt"), new TFun(new TFloat(), new TFloat())));
    env.add(new EnvElem(new Id("abs_float"), new TFun(new TFloat(), new TFloat())));
    env.add(new EnvElem(new Id("truncate"), new TFun(new TFloat(), new TInt())));
    env.add(new EnvElem(new Id("print_newline"), new TFun(new TUnit(), new TUnit())));
    return env;
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
