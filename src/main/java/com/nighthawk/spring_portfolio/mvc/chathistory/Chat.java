package com.nighthawk.spring_portfolio.mvc.chathistory;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {
	 // automatic unique identifier for Person record
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    public Long getId() {
		return id;
	}
	private String chatMessage;
    private String chatReponse;
    private Date timestamp;
    private Long personId;
    
	public Long getPersonId() {
		return personId;
	}
	public String getChatMessage() {
		return chatMessage;
	}
	public String getChatReponse() {
		return chatReponse;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public Chat(String chatMessage, String chatReponse, Date timestamp, Long personId) {
		super();
		this.chatMessage = chatMessage;
		this.chatReponse = chatReponse;
		this.timestamp = timestamp;
		this.personId = personId;
	}
    
    // todo add person id foreign key

}
