package backend.tuples;

import java.util.*;
import backend.functions.*;
import backend.variables.*;

public class TupleJerry {

private ArrayList<Object> elements;

public TupleJerry(Function func, ArrayList<Object> elems) {
        this.elements = elems;
}

public ArrayList<Object> getElements() {
        return this.elements;
}

public void show() {
        System.out.print("(");
        for (Object o : this.elements) {
                try {
                  System.out.print(((Variable)o).getName() + ", ");
                }
                catch (Exception e) {
                  System.out.print(o + ", ");
                }
        }
        System.out.println(")");
}
}
