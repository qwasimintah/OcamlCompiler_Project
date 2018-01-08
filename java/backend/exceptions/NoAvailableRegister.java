package backend.exceptions;

import java.io.*;

public class NoAvailableRegister extends Exception {
public NoAvailableRegister() {}

public String getMessage() {
  return "All registers are allocated";
}
}
