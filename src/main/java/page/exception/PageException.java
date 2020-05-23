package page.exception;

public class PageException extends RuntimeException {
  public PageException(String errorMsg){
    super(errorMsg);
  }
}
