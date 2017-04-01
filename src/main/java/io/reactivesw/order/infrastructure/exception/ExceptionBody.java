package io.reactivesw.order.infrastructure.exception;

/**
 * exception body.
 * return customized code and message to the client.
 */
public class ExceptionBody {

  /**
   * order build failed code.
   */
  public static final int BUILD_ORDER_FAILED_CODE = 60001;
  /**
   * order build failed message.
   */
  public static final String BUILD_ORDER_FAILED_MESSAGE = "build order failed.";

  /**
   * order reduce inventory failed code.
   */
  public static final int RESERVE_INVENTORY_FAILED_CODE = 60002;
  /**
   * order reduce inventory failed message.
   */
  public static final String RESERVE_INVENTORY_FAILED_MESSAGE = "reserve inventory failed.";


  /**
   * pay order failed code.
   */
  public static final int PAY_FAILED_CODE = 60003;
  /**
   * pay order failed message.
   */
  public static final String PAY_FAILED_MESSAGE = "pay order failed.";

  /**
   * CODE.
   */
  private int code;

  /**
   * Message
   */
  private String message;

  /**
   * build.
   *
   * @param code
   * @param message
   * @return
   */
  public static ExceptionBody build(int code, String message) {
    ExceptionBody body = new ExceptionBody();
    body.code = code;
    body.message = message;
    return body;
  }

  /**
   * get code.
   *
   * @return
   */
  public int getCode() {
    return code;
  }

  /**
   * set code.
   *
   * @param code
   */
  public void setCode(int code) {
    this.code = code;
  }

  /**
   * get message.
   *
   * @return
   */
  public String getMessage() {
    return message;
  }

  /**
   * set message.
   *
   * @param message
   */
  public void setMessage(String message) {
    this.message = message;
  }
}
