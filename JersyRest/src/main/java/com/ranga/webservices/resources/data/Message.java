package com.ranga.webservices.resources.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {
	int id;
	String message;
	
	public Message()
	{
	}
	
	public Message(int id, String message)
	{
		this.id = id;
		this.message = message;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}
	
	public String toString()
	{
		return "Message [id: " + id + ", Message: " + message +"]";
	}
}
