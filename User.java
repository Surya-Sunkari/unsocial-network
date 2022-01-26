package UnSocialNetwork;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private List<User> friendRequests;
	private List<User> friendsList;
	public String[] friendReqNames;
	public String[] friendNames;
//	private List<Message> inbox;
	
	public User(String u, String p, String f, String l, String[] fRN, String[] fN) {
		username = u;
		password = p;
		firstName = f;
		lastName = l;
		friendReqNames = fRN;
		friendNames = fN;
		friendsList = new ArrayList<>();
		friendRequests = new ArrayList<>();
//		inbox = new ArrayList<>();
	}
	
//	public List<Message> getInbox() {
//		return inbox;
//	}
//	
//	public void addMessageToInbox(Message m) {
//		inbox.add(m);
//	}
//	
	public String toString() {
		return String.format("user=%s pass=%s firstName=%s lastName=%s", username, password, firstName, lastName);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public void addUserToFriendReq(User user) {
		friendRequests.add(user);
	}
	
	public void addFriend(User user) {
		friendsList.add(user);
	}
	
	public void acceptFriendReq(User user) {
		friendRequests.remove(user);
		friendsList.add(user);
	}
	
	public void declineFriendReq(User user) {
		friendRequests.remove(user);
	}
	
	public void removeFriend(User user) {
		friendsList.remove(user);
	}
	
	public List<User> getFriendReq() {
		return friendRequests;
	}
	
	public List<User> getFriends() {
		return friendsList;
	}
	
	
	
	
}
