import java.util.HashMap;
import java.util.ArrayList;

public class AlphaConversion implements ObjVisitor<Exp>{
private static HashMap<String, ArrayList<String> > epsilon = new HashMap<String, ArrayList<String> > ();

private boolean sec_exp_let = false;

private static ArrayList<String> used_in_let = new ArrayList<String> ();

public Exp visit(Add e){
        Add new_add = new Add(e.e1.accept(this), e.e2.accept(this));
        return new_add;
}

public Exp visit(Sub e){
        Sub new_sub = new Sub(e.e1.accept(this), e.e2.accept(this));
        return new_sub;
}

public Exp visit(Let e){
        Var new_var = new Var(e.id.gen());
        ArrayList<String> array = epsilon.get(e.id.toString());
        if (array == null) {
                array = new ArrayList<String> ();
        }
        array.add(new_var.id.toString());
        epsilon.put(e.id.toString(), array);
        Exp new_e1 = e.e1.accept(this);
        sec_exp_let = true;
        Exp new_e2 = e.e2.accept(this);
        Let new_let = new Let(new_var.id, e.t, new_e1, new_e2);
        while (used_in_let.size() > 0) {
                String key = used_in_let.remove(used_in_let.size()-1);
                ArrayList<String> tmp_array = epsilon.get(key);
                if (tmp_array.size() > 0) {
                        tmp_array.remove(tmp_array.size()-1);
                }
        }
        used_in_let = new ArrayList<String> ();
        sec_exp_let = false;
        return new_let;
}

public Exp visit(Var e){
        ArrayList<String> array = epsilon.get(e.id.toString());
        if (array.size() != 0) {
                if (!sec_exp_let) {
                        Var new_var = new Var(e.id.gen());
                        array.add(new_var.id.toString());
                        epsilon.put(e.id.toString(), array);
                        return new_var;
                } else {
                        used_in_let.add(e.id.toString());
                        e.id.id = array.get(array.size()-1);
                        return e;
                }
        } else {
                return e;
        }
}

public Exp visit(Int e){
        return e;
}

public Exp visit(Unit e){
        return e;
}

public Exp visit(Bool e){
        return e;
}

public Exp visit(Float e){
        return e;
}

public Exp visit(Not e){
        return e;
}

public Exp visit(Neg e){
        return e;
}

public Exp visit(FNeg e){
        return e;
}

public Exp visit(FAdd e){
        return e;
}

public Exp visit(FSub e){
        return e;
}

public Exp visit(FMul e){
        return e;
}

public Exp visit(FDiv e){
        return e;
}

public Exp visit(Eq e){
        return e;
}

public Exp visit(LE e){
        return e;
}

public Exp visit(If e){
        return e;
}

public Exp visit(LetRec e){
        return e;
}

public Exp visit(App e){
        return e;
}

public Exp visit(Tuple e){
        return e;
}

public Exp visit(LetTuple e){
        return e;
}

public Exp visit(Array e){
        return e;
}

public Exp visit(Get e){
        return e;
}

public Exp visit(Put e){
        return e;
}
}
