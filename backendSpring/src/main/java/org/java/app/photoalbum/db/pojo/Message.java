package org.java.app.photoalbum.db.pojo;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false)
	@NotNull
	@Length(max = 255, message = "Email supera lunghezza consentita")
	private String email;
	
	@Column(length = 650, nullable = false)
	@Length(min = 3, max = 650, message = "Il messaggio deve essere lungo tra i 3 e 650 carrateri")
	private String text;

	public Message() { }
	public Message(String email, String text) {
		setEmail(email);
		setText(text);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
