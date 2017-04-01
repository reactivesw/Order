package io.reactivesw.order.infrastructure.exception;

import io.reactivesw.exception.handler.ExceptionHandler;
import io.reactivesw.order.infrastructure.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * exception handler.
 */
@Component
public class OrderExceptionHandler extends ExceptionHandler implements HandlerExceptionResolver {

  /**
   * LOG.
   */
  private static final Logger LOG = LoggerFactory.getLogger(OrderExceptionHandler.class);

  /**
   * resolve exception.
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                       Object handler, Exception ex) {
    setResponse(request, response, handler, ex);
    addExceptionBody(response, ex);
    return new ModelAndView();
  }


  /**
   * add customized message body to the response.
   *
   * @param response
   * @param ex
   */
  private void addExceptionBody(HttpServletResponse response, Exception ex) {
    try {
      ExceptionBody body = getBody(ex);
      response.getWriter().print(JsonUtils.serialize(body));
    } catch (IOException e) {
      LOG.error("failed to write response JSON", e);
      throw new IllegalStateException(e);
    }
  }

  /**
   * get customized message body by exception type.
   *
   * @param ex exception.
   * @return exception body.
   */
  private ExceptionBody getBody(Exception ex) {
    ExceptionBody body = null;
    if (ex instanceof BuildOrderException) {
      body = ExceptionBody.build(ExceptionBody.BUILD_ORDER_FAILED_CODE, ExceptionBody
          .BUILD_ORDER_FAILED_MESSAGE);
    }
    if (ex instanceof ReserveInventoryException) {
      body = ExceptionBody.build(ExceptionBody.RESERVE_INVENTORY_FAILED_CODE, ExceptionBody
          .RESERVE_INVENTORY_FAILED_MESSAGE);
    }
    if (ex instanceof PayOrderException) {
      body = ExceptionBody.build(ExceptionBody.PAY_FAILED_CODE, ExceptionBody
          .PAY_FAILED_MESSAGE);
    }
    return body;
  }
}
