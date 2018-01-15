package frontend;

import exp.*;
import ast.*;
import java.util.*;

public class Closure {
  private String label;

  private List<String> free_list = new LinkedList();

  private List<String> args = new LinkedList();

  private Exp code;

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
