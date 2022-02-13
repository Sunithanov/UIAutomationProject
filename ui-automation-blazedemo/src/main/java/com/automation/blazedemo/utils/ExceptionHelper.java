package com.automation.blazedemo.utils;

/**
 * Exception class to create the custom exception.
 * 
 * 
 */
public final class ExceptionHelper extends RuntimeException {

  /**
   * Constructs a checked standard Java exception with the specified cause and a detail message.
   * @author Sunitha
   * @param message -> The detail message (which is saved for later retrieval by the
   *          Throwable.getMessage() method).
   */
  public ExceptionHelper(final String message) {
	  super(message);
  }

  /**
   * Constructs checked standard Java exception with the specified cause and detail message.
   * @author Sunitha
   * @param message -> The detail message (which is saved for later retrieval by the
   *          Throwable.getMessage() method).
   * @param cause -> The cause (which is saved for later retrieval by the
   *          Throwable.getCause() method)
   */
  public ExceptionHelper(final String message, final Throwable cause) {
	  super(message, cause);
  }

  /**
   * Constructs checked standard Java exception with the specified cause and a detail message.
   * @author Sunitha
   * @param cause -> The cause (which is saved for later retrieval by the
   *          Throwable.getCause() method)
   */
  public ExceptionHelper(final Throwable cause) {
    super(cause);
  }
}
