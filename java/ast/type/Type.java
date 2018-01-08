package ast.type;

public class Type {
private static int x = 0;

public Type gen() {
        return new TVar("?" + x++);
}

}
