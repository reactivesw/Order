package io.reactivesw.order.infrastructure.exception;

/**
 * get product failed exception.
 */
public class GetProductException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public GetProductException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public GetProductException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public GetProductException(String msg, Throwable source) {
    super(msg, source);
  }

}
