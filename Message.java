package UnSocialNetwork;

public class Message {
	
	String subject, body;
	User sender, recepient;
	boolean read;
	
	public Message(String subject, String body) {
		this.subject = subject;
		this.body = body;
	}
	
	
	public boolean  isRead() {
		return read;
	}
	
	public void read() {
		read = true;
	}

	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public User getSender() {
		return sender;
	}


	public void setSender(User sender) {
		this.sender = sender;
	}


	public User getRecepient() {
		return recepient;
	}


	public void setRecepient(User recepient) {
		this.recepient = recepient;
	}
	
	
	
}
