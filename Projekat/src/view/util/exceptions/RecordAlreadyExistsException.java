package view.util.exceptions;

@SuppressWarnings("serial")
public class RecordAlreadyExistsException extends Exception {

	public RecordAlreadyExistsException(String message) {
		super(message);
	}
}
