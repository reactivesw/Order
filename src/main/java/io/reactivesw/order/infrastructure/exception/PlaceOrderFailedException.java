package io.reactivesw.order.infrastructure.exception;

/**
 * place order failed exception.
 */
public class PlaceOrderFailedException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public PlaceOrderFailedException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public PlaceOrderFailedException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public PlaceOrderFailedException(String msg, Throwable source) {
    super(msg, source);
  }

}
