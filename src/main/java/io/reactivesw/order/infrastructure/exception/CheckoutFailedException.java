package io.reactivesw.order.infrastructure.exception;

/**
 * checkout failed exception.
 */
public class CheckoutFailedException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public CheckoutFailedException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public CheckoutFailedException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public CheckoutFailedException(String msg, Throwable source) {
    super(msg, source);
  }

}
