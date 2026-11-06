package org.sistema.triagem.Exception;

public class NotAuthorized extends RuntimeException {
  public NotAuthorized(String message) {
    super(message);
  }
}
