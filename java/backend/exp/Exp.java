package exp;

import translation.*;

import java.util.*;
import translation.*;

public abstract class Exp {
    abstract void accept(Visitor v);

    abstract <E> E accept(ObjVisitor<E> v);
}