package backend.registers;

public class Register implements Comparable {
private Integer index;

public Register(Integer index) {
        this.index = index;
}

public Integer getIndex() {
        return index;
}

public String getName() {
        return "r" + index.toString();
}

@Override
public int compareTo(Object o) {
        try {
                return this.index.compareTo(((Register)o).index);
        } catch (Exception e) {
                System.out.println(e.getMessage());
                return 0;
        }
}
}
