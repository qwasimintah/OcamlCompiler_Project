package exceptions;

import java.io.*;

public class WrongTypeException extends Exception {
public WrongTypeException() {
}

    public String getMessage() {
      return "There is a wrong type exception";
    }
}
