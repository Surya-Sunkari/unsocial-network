package UnSocialNetwork;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main implements ActionListener {

	// https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java

	// https://www.javatpoint.com/how-to-change-titlebar-icon-in-java-awt-swing

	JFrame loginFrame, signUpFrame, mainFrame, addFriendFrame, friendRequestsFrame, myFriendsFrame, sendMessageFrame;
	JTextField userField, passField, fNameField, lNameField, friendNameField;
	JButton signUp, signIn, createAccount, sendMessage, readMessages, addFriend1, addFriend2, myFriends, friendRequests,
			menuFromFR, menuFromAF, menuFromMF, menuFromSM, acceptFriendReq, declineFriendReq;
	Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\100031985\\Desktop\\UnSocial_Network\\duck.png");
	JComboBox<String> friendRequestsCB, friendsCB;
	String selectedRequest;
	User currentUser;

	HashMap<String, User> users = new HashMap<>();
	List<User> totalUsers = new ArrayList<>();

	File file = new File("C:\\Users\\100031985\\Desktop\\UnSocial_Network\\data.txt");

	public static void main(String[] args) { // don't mess with this
		new Main();
	}

	public Main() {
		try {
			createUserData();
		} catch (Exception e) {
			System.out.println("creating user data failed");
			e.printStackTrace();
		}
		start();
		saveData();
	}

	private void addFriend() {
		addFriendFrame = new JFrame("Add Friend");
		friendNameField = new JTextField(20);
		addFriend2 = new JButton("Add Friend");
		menuFromAF = new JButton("Return");
		JLabel friendLabel = new JLabel("Input Username of Friend:");

		friendLabel.setBounds(15, 35, 285, 30);
		friendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addFriendFrame.add(friendLabel);
		friendNameField.setBounds(50, 70, 200, 30);
		addFriendFrame.add(friendNameField);
		friendNameField.addActionListener(this);

		menuFromAF.setBounds(10, 10, 75, 25);
		addFriendFrame.add(menuFromAF);
		menuFromAF.addActionListener(this);

		addFriend2.setBounds(75, 155, 150, 40);
		addFriendFrame.add(addFriend2);
		addFriend2.addActionListener(this);

		addFriendFrame.setIconImage(icon);
		addFriendFrame.setLayout(null);
		addFriendFrame.setVisible(true);
		addFriendFrame.getContentPane().setBackground(Color.CYAN);
		addFriendFrame.setSize(300, 300);
		addFriendFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		addFriendFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFriendFrame.setResizable(false);
	}

	private void mainPage() {
		// https://stackoverflow.com/questions/16372241/run-function-on-jframe-close
		// send message button, read message button, add friend button, friends list
		// (show bio with fname and lname if friend is clicked)
		// add friend request button
		mainFrame = new JFrame("Homescreen");
		sendMessage = new JButton("Send Message");
		readMessages = new JButton("Check Inbox");
		addFriend1 = new JButton("Add Friend");
		friendRequests = new JButton("Friend Requests");

		myFriends = new JButton("My Friends");
		JLabel messageLabel = new JLabel("Messages");
		JLabel friendLabel = new JLabel("Friends");

		messageLabel.setBounds(0, 90, 300, 50);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setVerticalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		mainFrame.add(messageLabel);

		friendLabel.setBounds(300, 90, 300, 50);
		friendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		friendLabel.setVerticalAlignment(SwingConstants.CENTER);
		friendLabel.setFont(new Font("Serif", Font.PLAIN, 30));
		mainFrame.add(friendLabel);

		readMessages.setBounds(75, 175, 150, 40);
		mainFrame.add(readMessages);
		readMessages.addActionListener(this);

		sendMessage.setBounds(75, 300, 150, 40);
		mainFrame.add(sendMessage);
		sendMessage.addActionListener(this);

		myFriends.setBounds(375, 175, 150, 40);
		mainFrame.add(myFriends);
		myFriends.addActionListener(this);

		addFriend1.setBounds(375, 300, 150, 40);
		mainFrame.add(addFriend1);
		addFriend1.addActionListener(this);

		friendRequests.setBounds(375, 425, 150, 40);
		mainFrame.add(friendRequests);
		friendRequests.addActionListener(this);

		mainFrame.setIconImage(icon);
		mainFrame.setLayout(null);
		mainFrame.setVisible(true);
		mainFrame.getContentPane().setBackground(Color.CYAN);
		mainFrame.setSize(600, 600);
		mainFrame.setLocation(1920 / 2 - 600 / 2, 1080 / 2 - 600 / 2);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.setResizable(false);

		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				saveData();
				closeFrame(mainFrame);
			}
		});
	}

	private void friendRequestsPage() {
		friendRequestsFrame = new JFrame("My Friend Requests");
		menuFromFR = new JButton("Return");
		acceptFriendReq = new JButton("Accept");
		declineFriendReq = new JButton("Decline");

		JLabel usernames = new JLabel("Current Friend Requests:");

		usernames.setBounds(10, 80, 264, 30);
		usernames.setHorizontalAlignment(SwingConstants.CENTER);
		friendRequestsFrame.add(usernames);

		menuFromFR.setBounds(10, 10, 75, 25);
		friendRequestsFrame.add(menuFromFR);
		menuFromFR.addActionListener(this);

		String[] friendRequestsArr = currentUser.friendReqNames;
		if (friendRequestsArr.length == 0) {

		} else {

			friendRequestsCB = new JComboBox<>(friendRequestsArr);
			friendRequestsCB.setSelectedIndex(0);
			selectedRequest = friendRequestsArr[0];

			friendRequestsCB.setBounds(10, 110, 264, 30);
			friendRequestsFrame.add(friendRequestsCB);
			friendRequestsCB.addActionListener(this);

			acceptFriendReq.setBounds(10, 160, 110, 40);
			friendRequestsFrame.add(acceptFriendReq);
			acceptFriendReq.addActionListener(this);

			declineFriendReq.setBounds(164, 160, 110, 40);
			friendRequestsFrame.add(declineFriendReq);
			declineFriendReq.addActionListener(this);
		}

		friendRequestsFrame.setIconImage(icon);
		friendRequestsFrame.setLayout(null);
		friendRequestsFrame.setVisible(true);
		friendRequestsFrame.getContentPane().setBackground(Color.CYAN);
		friendRequestsFrame.setSize(300, 300);
		friendRequestsFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		friendRequestsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		friendRequestsFrame.setResizable(false);
	}

	private void myFriendsPage() {
		myFriendsFrame = new JFrame("My Friends");
		menuFromMF = new JButton("Return");

		JLabel friendLabel = new JLabel("My Friends:");

		friendLabel.setBounds(10, 80, 264, 30);
		friendLabel.setHorizontalAlignment(SwingConstants.CENTER);
		myFriendsFrame.add(friendLabel);

		menuFromMF.setBounds(10, 10, 75, 25);
		myFriendsFrame.add(menuFromMF);
		menuFromMF.addActionListener(this);

		String[] friendsArr = currentUser.friendNames;
		if (friendsArr.length == 0) {
			JLabel noFriendsLabel = new JLabel("You have no friends :(");
			noFriendsLabel.setBounds(10, 130, 264, 30);
			noFriendsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			myFriendsFrame.add(noFriendsLabel);
		} else { // add a thing at the bottom that displays info of each friend when selected
					// (user, first name, last name, about page?)

			friendsCB = new JComboBox<>(friendsArr);
			friendsCB.setSelectedIndex(0);
			selectedRequest = friendsArr[0];

			friendsCB.setBounds(10, 110, 264, 30);
			myFriendsFrame.add(friendsCB);
			friendsCB.addActionListener(this);
		}

		myFriendsFrame.setIconImage(icon);
		myFriendsFrame.setLayout(null);
		myFriendsFrame.setVisible(true);
		myFriendsFrame.getContentPane().setBackground(Color.CYAN);
		myFriendsFrame.setSize(300, 300);
		myFriendsFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		myFriendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFriendsFrame.setResizable(false);
	}

	private void sendMessagePage() {
		sendMessageFrame = new JFrame("Send Message");
		menuFromSM = new JButton("Return");

		JLabel recepientLabel = new JLabel("Recepient:");

		recepientLabel.setBounds(10, 80, 264, 30);
		recepientLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sendMessageFrame.add(recepientLabel);

		menuFromSM.setBounds(10, 10, 75, 25);
		sendMessageFrame.add(menuFromSM);
		menuFromSM.addActionListener(this);

		String[] friendsArr = currentUser.friendNames;
		if (friendsArr.length == 0) {
			JLabel noFriendsLabel = new JLabel("You need to add friends in order to send messages to them!");
			noFriendsLabel.setBounds(10, 110, 264, 30);
			sendMessageFrame.add(noFriendsLabel);
		} else {
			friendsCB = new JComboBox<>(friendsArr);
			friendsCB.setSelectedIndex(0);
			selectedRequest = friendsArr[0];

			friendsCB.setBounds(10, 110, 264, 30);
			myFriendsFrame.add(friendsCB);
			friendsCB.addActionListener(this);
		}

		myFriendsFrame.setIconImage(icon);
		myFriendsFrame.setLayout(null);
		myFriendsFrame.setVisible(true);
		myFriendsFrame.getContentPane().setBackground(Color.CYAN);
		myFriendsFrame.setSize(300, 300);
		myFriendsFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		myFriendsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFriendsFrame.setResizable(false);
	}

	private void saveData() {

		file.delete();

		for (User user : totalUsers) {
			FileWriter w;
			try {
				w = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(w);
				bw.write(String.format("username=%s password=%s firstName=%s lastName=%s ", user.getUsername(),
						user.getPassword(), user.getFirstName(), user.getLastName()));
				bw.newLine();
				bw.write("friendReq=");
				for (User friendReq : user.getFriendReq()) {
					bw.write(friendReq.getUsername() + ",");
				}
				bw.newLine();
				bw.write("friends=");
				for (User friend : user.getFriends()) {
					bw.write(friend.getUsername() + ",");
				}
				bw.newLine();
				bw.close();
				w.close();
			} catch (Exception e1) {
				System.out.println("failure when adding " + user.getUsername() + " to file");
			}
		}
	}

	private void createUserData() throws IOException {
		FileReader r = new FileReader(file);
		BufferedReader br = new BufferedReader(r);
		String line = br.readLine();

		while (line != null && !line.isEmpty()) {
//			try {
			String u = searchAndReceive("username", line); // error with empty file
			String p = searchAndReceive("password", line);
			String f = searchAndReceive("firstName", line);
			String l = searchAndReceive("lastName", line);
			line = br.readLine();
			String friendReqNames = line.substring(line.indexOf("=") + 1);
			String[] friendReqNamesSplit = friendReqNames.isEmpty() ? new String[0] : friendReqNames.split(",");
			line = br.readLine();
			String friendNames = line.substring(8);
			String[] friendNamesSplit = friendNames.isEmpty() ? new String[0] : friendNames.split(",");
			line = br.readLine();
			User user = new User(u, p, f, l, friendReqNamesSplit, friendNamesSplit);
			users.put(u, user);
			totalUsers.add(user);
//			} catch (StringIndexOutOfBoundsException e) {
//				
//			}

		}

		for (User user : totalUsers) {
			for (String friendReqName : user.friendReqNames) {
				user.addUserToFriendReq(users.get(friendReqName));
			}
			for (String friendName : user.friendNames) {
				user.addFriend(users.get(friendName));
			}
		}

		r.close();
	}

	private String searchAndReceive(String search, String line) {
		String read = line.substring(line.indexOf(search));
		int first = read.indexOf("=") + 1;
		int last = read.substring(first + 1).indexOf(" ");
		return read.substring(first, first + last + 1);
	}

	private void start() {
		loginFrame = new JFrame("Log in / Sign up");
		userField = new JTextField(20);
		passField = new JTextField(20);
		signUp = new JButton("Sign Up");
		signIn = new JButton("Sign In");

		JLabel userlabel = new JLabel("Username:");
		JLabel passlabel = new JLabel("Password:");
		JLabel acclabel = new JLabel("Don't have an account?");

		userlabel.setBounds(15, 30, 100, 30);
		loginFrame.add(userlabel);
		userField.setBounds(85, 30, 195, 30);
		loginFrame.add(userField);
		userField.addActionListener(this);

		passlabel.setBounds(15, 80, 100, 30);
		loginFrame.add(passlabel);
		passField.setBounds(85, 80, 195, 30);
		loginFrame.add(passField);
		passField.addActionListener(this);

		signIn.setBounds(100, 140, 100, 30);
		loginFrame.add(signIn);
		signIn.addActionListener(this);

		acclabel.setBounds(85, 200, 200, 30);
		loginFrame.add(acclabel);
		signUp.setBounds(100, 230, 100, 30);
		loginFrame.add(signUp);
		signUp.addActionListener(this);

		loginFrame.setIconImage(icon);
		loginFrame.setLayout(null);
		loginFrame.setVisible(true);
		loginFrame.getContentPane().setBackground(Color.CYAN);
		loginFrame.setSize(300, 320);
		loginFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setResizable(false);
	}

	private void signUp() {
		signUpFrame = new JFrame("Sign Up");
		userField = new JTextField(20);
		passField = new JTextField(20);
		fNameField = new JTextField(20);
		lNameField = new JTextField(20);
		createAccount = new JButton("Create Account");

		JLabel userlabel = new JLabel("Username:");
		JLabel passlabel = new JLabel("Password:");
		JLabel firstNameLabel = new JLabel("First Name:");
		JLabel lastNameLabel = new JLabel("Last Name:");

		userlabel.setBounds(15, 30, 100, 30);
		signUpFrame.add(userlabel);
		userField.setBounds(85, 30, 195, 30);
		signUpFrame.add(userField);
		userField.addActionListener(this);

		passlabel.setBounds(15, 80, 100, 30);
		signUpFrame.add(passlabel);
		passField.setBounds(85, 80, 195, 30);
		signUpFrame.add(passField);
		passField.addActionListener(this);

		firstNameLabel.setBounds(15, 130, 100, 30);
		signUpFrame.add(firstNameLabel);
		fNameField.setBounds(85, 130, 195, 30);
		signUpFrame.add(fNameField);
		fNameField.addActionListener(this);

		lastNameLabel.setBounds(15, 180, 100, 30);
		signUpFrame.add(lastNameLabel);
		lNameField.setBounds(85, 180, 195, 30);
		signUpFrame.add(lNameField);
		lNameField.addActionListener(this);

		createAccount.setBounds(80, 230, 140, 30);
		signUpFrame.add(createAccount);
		createAccount.addActionListener(this);

		signUpFrame.setIconImage(icon);
		signUpFrame.setLayout(null);
		signUpFrame.setVisible(true);
		signUpFrame.getContentPane().setBackground(Color.CYAN);
		signUpFrame.setSize(300, 325);
		signUpFrame.setLocation(1920 / 2 - 300 / 2, 1080 / 2 - 300 / 2);
		signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUpFrame.setResizable(false);
	}

	private void closeFrame(JFrame frame) {
		frame.setVisible(false);
		frame.dispose();
	}

	private void resetData() {
		saveData();
		try {
			users.clear();
			totalUsers.clear();
			createUserData();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		mainPage();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == signUp) {
			closeFrame(loginFrame);
			userField.setText("");
			passField.setText("");
			signUp();
		}

		if (e.getSource() == createAccount) {

			String username = userField.getText();
			String pass = passField.getText();
			String fName = fNameField.getText();
			String lName = lNameField.getText();

			if (username.isEmpty() || pass.isEmpty() || fName.isEmpty() || lName.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please fill in all of the fields!", "Unfilled Fields",
						JOptionPane.ERROR_MESSAGE);
			} else if (users.containsKey(username)) {
				JOptionPane.showMessageDialog(null, "Username already in use! Try another one!", "Username Taken",
						JOptionPane.ERROR_MESSAGE);
			} else {
				User user = new User(username, pass, fName, lName, new String[0], new String[0]);
				users.put(userField.getText(), user);
				totalUsers.add(user);
				saveData();
				closeFrame(signUpFrame);
				start();
			}

		}

		if (e.getSource() == signIn) {
			// check if username and pass are correct
			String username = userField.getText();
			String pass = passField.getText();

			if (users.get(username) == null || !users.get(username).getPassword().equals(pass)) {
				JOptionPane.showMessageDialog(null, "Incorrect Username or Password. Try again or create new account.",
						"Wrong User/Pass", JOptionPane.ERROR_MESSAGE);
			} else {
				closeFrame(loginFrame);
				currentUser = users.get(username);
				mainPage();
			}
		}

		if (e.getSource() == addFriend1) {
			closeFrame(mainFrame);
			addFriend();
		}

		if (e.getSource() == myFriends) {
			closeFrame(mainFrame);
			myFriendsPage();
		}

		if (e.getSource() == addFriend2) {
			if (!users.containsKey(friendNameField.getText())) {
				JOptionPane.showMessageDialog(null, "Cannot find the user: " + friendNameField.getText(),
						"User not found", JOptionPane.ERROR_MESSAGE);
				friendNameField.setText("");
			} else if (friendNameField.getText().equals(currentUser.getUsername())) {
				JOptionPane.showMessageDialog(null, "You can't send a friend request to yourself!", "Nice Try",
						JOptionPane.WARNING_MESSAGE);
			} else if (currentUser.getFriendReq().contains(users.get(friendNameField.getText()))) {
				JOptionPane.showMessageDialog(null, "This user has already sent you a friend request!",
						"Duplicate Requests", JOptionPane.WARNING_MESSAGE);
			} else if (users.get(friendNameField.getText()).getFriendReq().contains(currentUser)) {
				JOptionPane.showMessageDialog(null, "Friend Request Already Sent", "Cannot Send Again",
						JOptionPane.WARNING_MESSAGE);
			} else if (currentUser.getFriends().contains(users.get(friendNameField.getText()))) {
				JOptionPane.showMessageDialog(null, "You are already friends with this user!", "Nice Try",
						JOptionPane.WARNING_MESSAGE);
			} else {
				users.get(friendNameField.getText()).addUserToFriendReq(currentUser);
				closeFrame(addFriendFrame);
				JOptionPane.showMessageDialog(null, "Friend Request Sent!", "Request Sent",
						JOptionPane.INFORMATION_MESSAGE);
				System.out.println(users.get(friendNameField.getText()).getFriendReq());
				resetData();
			}
		}

		if (e.getSource() == menuFromAF) {
			closeFrame(addFriendFrame);
			resetData();
		}

		if (e.getSource() == menuFromFR) {
			closeFrame(friendRequestsFrame);
			resetData();
		}

		if (e.getSource() == menuFromMF) {
			closeFrame(myFriendsFrame);
			resetData();
		}

		if (e.getSource() == menuFromSM) {
			closeFrame(sendMessageFrame);
			resetData();
		}

		if (e.getSource() == friendRequests) {
			closeFrame(mainFrame);
			friendRequestsPage();
		}

		if (e.getSource() == friendRequestsCB) {
			selectedRequest = (String) friendRequestsCB.getSelectedItem();
		}

		if (e.getSource() == acceptFriendReq) {
			currentUser.acceptFriendReq(users.get(selectedRequest));
			users.get(selectedRequest).addFriend(currentUser);

			JOptionPane.showMessageDialog(null, "Request from " + selectedRequest + " has been accepted! ",
					"Friend Acquired!", JOptionPane.INFORMATION_MESSAGE);
		}

		if (e.getSource() == declineFriendReq) {

		}
	}

}
