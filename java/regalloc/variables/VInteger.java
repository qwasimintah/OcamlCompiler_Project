package variables;

import java.io.*;
import java.util.Hashtable;
import java.util.*;

public class VInteger extends Variable {
private Integer value;

public VInteger(String name, Integer value, HashMap registers) {
        super(name, registers);
        this.value = value;
}

public Integer getValue() {
        return value;
}
}
