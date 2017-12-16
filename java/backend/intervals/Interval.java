package intervals;

import variables.*;

public class Interval {
private Integer start;
private Integer end;
private Variable variable;

public Interval(Variable variable, Integer start) {
        this.variable = variable;
        this.start = start;
}

public Interval(Variable variable) {
        this.variable = variable;
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
        return "(" + start + ", " + end + ")";
}
}
