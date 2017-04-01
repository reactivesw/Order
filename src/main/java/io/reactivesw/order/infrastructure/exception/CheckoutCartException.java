package io.reactivesw.order.infrastructure.exception;

/**
 * checkout cart failed.
 */
public class CheckoutCartException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public CheckoutCartException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public CheckoutCartException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public CheckoutCartException(String msg, Throwable source) {
    super(msg, source);
  }

}
