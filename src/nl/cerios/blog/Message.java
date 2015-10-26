package nl.cerios.blog;
import java.sql.Date;

public class Message {
	private String 	title;
	private String 	body;
	private Date 	date;
	private int 	userID;
	private enum MessageStatus{
		SHOW, HIDE, DELETED, ADDETED, BLOCKED, FLAGGED, BANNED
	}
	MessageStatus messageSatus;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public MessageStatus getMessageSatus() {
		return messageSatus;
	}
	public void setMessageSatus(MessageStatus messageSatus) {
		this.messageSatus = messageSatus;
	}
}
