package com.seatingarrangement.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Blueprint {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;
	
	private String floor;
	
	private int room;
	
	@ManyToOne
	private Courses courses;
	
	@OneToOne
	private StudentDetails studentDetails;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}

	public StudentDetails getStudentDetails() {
		return studentDetails;
	}

	public void setStudentDetails(StudentDetails studentDetails) {
		this.studentDetails = studentDetails;
	}

	@Override
	public String toString() {
		return "Blueprint [id=" + id + ", floor=" + floor + ", room=" + room + ", courses=" + courses
				+ ", studentDetails=" + studentDetails + "]";
	}

	
	
	
}
