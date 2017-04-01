package io.reactivesw.order.infrastructure.exception;

/**
 * build order exception.
 */
public class BuildOrderException extends RuntimeException {

  /**
   * auto generated serial version.
   */
  private static final long serialVersionUID = -3289418661184350533L;

  /**
   * checkout failed exception.
   */
  public BuildOrderException() {
    super();
  }

  /**
   * constructor with message.
   *
   * @param msg
   */
  public BuildOrderException(String msg) {
    super(msg);
  }

  /**
   * constructor with message and source throwable.
   *
   * @param msg
   * @param source
   */
  public BuildOrderException(String msg, Throwable source) {
    super(msg, source);
  }

}
