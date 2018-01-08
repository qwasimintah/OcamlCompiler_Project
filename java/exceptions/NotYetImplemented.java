package exceptions;

import java.io.*;

public class NotYetImplemented extends Exception {
public NotYetImplemented() {
}

public String getMessage() {
  return "This feature is not implemented yet";
}
}
