package kae.util;

/**
 * CheckResult
 *
 * @author Kapralov A.
 *         28.02.2014 13:30
 */
public class CheckResult {

  public final boolean success;
  public final String message;
  public final String description;

  public CheckResult(boolean success, String message, String description) {
    this.success = success;
    this.message = message;
    this.description = description;
  }

}
