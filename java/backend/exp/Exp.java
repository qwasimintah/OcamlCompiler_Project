package exp;

import translation.*;

import java.util.*;
import translation.*;

public abstract class Exp {
    public abstract void accept(Visitor v);

    public abstract <E> E accept(ObjVisitor<E> v);
}
