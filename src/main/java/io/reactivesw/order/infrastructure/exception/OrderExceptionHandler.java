package io.reactivesw.order.infrastructure.exception;

import io.reactivesw.exception.handler.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * exception handler.
 */
@Component
public class OrderExceptionHandler extends ExceptionHandler implements HandlerExceptionResolver {

  /**
   * exception LOCAL_EXCEPTION_MAP.
   */
  private static final Map<Class<?>, HttpStatus> LOCAL_EXCEPTION_MAP = new
      ConcurrentHashMap<Class<?>, HttpStatus>();


  /**
   * constructor.
   */
  public OrderExceptionHandler() {
    super();
    LOCAL_EXCEPTION_MAP.put(PlaceOrderFailedException.class, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  /**
   * resolve exception.
   */
  @Override
  public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                       Object handler, Exception ex) {
    setResponse(request, response, handler, ex);
    setLocalResponse(request, response, handler, ex);
    return new ModelAndView();
  }

  /**
   * set local exception response.
   */
  public Exception setLocalResponse(HttpServletRequest request, HttpServletResponse response, Object
      obj, Exception ex) {

    // get the status
    HttpStatus status = LOCAL_EXCEPTION_MAP.get(ex.getClass());
    if (status == null) {
      //if this is an unexpected exception, set the code to internal server error.
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    response.setStatus(status.value());
    response.addHeader("ErrorMessage", ex.getMessage());
    //use the application_json_utf8_value
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    return null;
  }
}
