package exceptions;

/**
 * @author Sergey Solovyov
 */
public class MyDOMException extends Exception{

    public MyDOMException(String message){
        super(message);
    }

    public MyDOMException(String message, Throwable cause) {
        		super(message, cause);
    }

}
