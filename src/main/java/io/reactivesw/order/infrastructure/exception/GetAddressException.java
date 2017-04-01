package io.reactivesw.order.infrastructure.exception;

/**
 * get address failed exception.
 */
public class GetAddressException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public GetAddressException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public GetAddressException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public GetAddressException(String msg, Throwable source) {
    super(msg, source);
  }

}
