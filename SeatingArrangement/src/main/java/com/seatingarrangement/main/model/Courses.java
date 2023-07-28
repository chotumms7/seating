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
    
    private int total_seats;
	
	private int no_of_seats_available;
	
	private int no_of_seats_alloted;
	
	private int min_marks;
	
	private int max_marks;
	
	public int getMin_marks() {
		return min_marks;
	}

	public void setMin_marks(int min_marks) {
		this.min_marks = min_marks;
	}

	public int getMax_marks() {
		return max_marks;
	}

	public void setMax_marks(int max_marks) {
		this.max_marks = max_marks;
	}

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

	public int getTotal_seats() {
		return total_seats;
	}

	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}


}
