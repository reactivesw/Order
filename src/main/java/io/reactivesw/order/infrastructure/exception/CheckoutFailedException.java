package io.reactivesw.order.infrastructure.exception;

public class CheckoutFailedException extends RuntimeException {

  public CheckoutFailedException() {
    super();
  }

  public CheckoutFailedException(String msg) {
    super(msg);
  }

  public CheckoutFailedException(String msg, Throwable source) {
    super(msg, source);
  }

}
