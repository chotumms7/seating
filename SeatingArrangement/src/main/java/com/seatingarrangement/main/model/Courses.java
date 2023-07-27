package com.seatingarrangement.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Courses {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
    private String course;
	
	private int no_of_seats_available;
	
	private int no_of_seats_alloted;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public int getNo_of_seats_available() {
		return no_of_seats_available;
	}

	public void setNo_of_seats_available(int no_of_seats_available) {
		this.no_of_seats_available = no_of_seats_available;
	}

	public int getNo_of_seats_alloted() {
		return no_of_seats_alloted;
	}

	public void setNo_of_seats_alloted(int no_of_seats_alloted) {
		this.no_of_seats_alloted = no_of_seats_alloted;
	}


}
