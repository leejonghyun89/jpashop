package jpabook.jpashop.exception;

/**
 * Created by JongHyun Lee on 2020-09-21
 */
public class NotEnoughStockException extends RuntimeException {

  public NotEnoughStockException() {
    super();
  }

  public NotEnoughStockException(String message) {
    super(message);
  }

  public NotEnoughStockException(String message,
                                 Throwable cause) {
    super(message, cause);
  }

  public NotEnoughStockException(Throwable cause) {
    super(cause);
  }

}
