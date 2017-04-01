package io.reactivesw.order.infrastructure.exception;

/**
 * reserve inventory exception.
 */
public class ReserveInventoryException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public ReserveInventoryException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public ReserveInventoryException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public ReserveInventoryException(String msg, Throwable source) {
    super(msg, source);
  }

}
