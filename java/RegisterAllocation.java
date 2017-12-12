import java.io.*;
import java.util.Hashtable;
import java.util.*;

public class RegisterAllocation {

  public static void main(String[] args) {

    HashMap registers = new HashMap(12);
    for (Integer i = 4; i < 13; i++) {
      Register reg = new Register(i);
      // System.out.println(reg.getIndex());
      registers.put(reg, null);
    }

    Set set = registers.entrySet();

    Iterator i = set.iterator();

    while(i.hasNext()) {
       Map.Entry me = (Map.Entry)i.next();
       Register reg = (Register)me.getKey();
       System.out.print("r" + reg.getIndex() + ": ");
       System.out.println(me.getValue());
    }
    System.out.println();

    VInteger x = new VInteger("x", 1);
    System.out.println(x.getValue());
  }
}
