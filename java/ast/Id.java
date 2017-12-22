package ast;

public class Id {

public String id;
static int x = -1;

public Id(String id) {
        this.id = id;
}

@Override
public String toString() {
        return id;
}

public Id gen() {
        x++;
        return new Id("?v" + x);
}
}
