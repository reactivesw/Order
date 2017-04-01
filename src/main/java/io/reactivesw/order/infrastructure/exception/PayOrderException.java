package io.reactivesw.order.infrastructure.exception;

/**
 * Pay order.
 */
public class PayOrderException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public PayOrderException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public PayOrderException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public PayOrderException(String msg, Throwable source) {
    super(msg, source);
  }

}
