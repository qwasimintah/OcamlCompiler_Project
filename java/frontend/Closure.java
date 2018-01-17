package frontend;

import exp.*;
import ast.*;
import java.util.*;

/**
  * Implementation of a closure class
  *
  *@author Florian Groguelin
  *
  */
public class Closure {
  private String label;
  // Label of the function

  private List<String> free_list = new LinkedList();
  // Free variable list of the function

  private List<String> args = new LinkedList();
  // Argument list of the function

  private Exp code;
  // Code of the function

  /**
    *Closure constructor
    *
    *@param label label of the function

    *@param free_list free variable list of the function

    *@param args argument list of the function

    *@param code code of the function (Exp)

  */
  public Closure(String label, List<String> free_list, List<String> args, Exp code){
    this.label = label;

    this.free_list = free_list;

    this.args = args;

    this.code = code;
  }

  public String getLabel(){
    return label;
  }

  public List<String> getFree_list(){
    return free_list;
  }

  public List<String> getArgs(){
    return args;
  }

  public Exp getCode(){
    return code;
  }
}
