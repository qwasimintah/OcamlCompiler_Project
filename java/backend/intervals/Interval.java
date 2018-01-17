package backend.intervals;

import backend.variables.*;

public class Interval implements Comparable {
private Integer start;
private Integer end;
private Variable variable;

public Interval(Variable variable) {
        this.variable = variable;
}

public Interval(Variable variable, Integer start) {
        this.variable = variable;
        this.start = start;
}

public Variable getVariable() {
        return variable;
}

public Integer getStartingPoint() {
        return start;
}

public Integer getEndingPoint() {
        return end;
}

public void setStartingPoint(Integer start) {
        this.start = start;
}

public void setEndingPoint(Integer end) {
        this.end = end;
}

public String getDescription() {
        return variable.getName() + " (" + start + ", " + end + ")";
}

@Override
public int compareTo(Object other) {
        Interval otherInt = (Interval) other;
        return this.getStartingPoint().compareTo(otherInt.getStartingPoint());
}
}
