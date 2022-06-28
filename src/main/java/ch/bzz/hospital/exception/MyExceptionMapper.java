package ch.bzz.hospital.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <h1>MyExceptionMapper</h1>
 *
 * @author Andras Tarlos
 * @since 2022.06.28
 * @version 0.1
 *
 * An exception mapper
 */
@Provider
public class MyExceptionMapper
               implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public Response toResponse(final ConstraintViolationException exception) {
      return Response.status(Response.Status.BAD_REQUEST)
                     .entity(prepareMessage(exception))
                     .type("text/plain")
                     .build();
  }

    /**
     * Prepares a message for the exception
     * @param exception ConstraintViolationException
     * @return String
     */
  private String prepareMessage(ConstraintViolationException exception) {
      String msg = "";
      for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
          msg+=cv.getPropertyPath()+" "+cv.getMessage()+"\n";
      }
      return msg;
  }
}