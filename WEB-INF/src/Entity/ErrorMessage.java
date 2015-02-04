package Entity;

public class ErrorMessage {

	private String message = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEmpty() {
		if(this.message == null){
			return true;
		}else{
			return false;
		}
	}
}
