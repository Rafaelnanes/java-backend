package rbn.edu.exceptions;

public class BusinessException extends Throwable {

	private static final long serialVersionUID = -6845610310422875419L;

	public BusinessException() {
	}

	public BusinessException(String message) {
		super(message);
	}

}
