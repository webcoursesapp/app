package com.webCourses.app.model.statistic;

public class StatisticWrapper {
	
	private int courses;
	private int materials;
	private int messages;
	private int users;
	private int tests;
	private int homeworks;
	private int members;
	
	public int getCourses() {
		return courses;
	}
	public void setCourses(int courses) {
		this.courses = courses;
	}
	public int getMaterials() {
		return materials;
	}
	public void setMaterials(int materials) {
		this.materials = materials;
	}
	public int getMessages() {
		return messages;
	}
	public void setMessages(int messages) {
		this.messages = messages;
	}
	public int getUsers() {
		return users;
	}
	public void setUsers(int users) {
		this.users = users;
	}
	public int getTests() {
		return tests;
	}
	public void setTests(int tests) {
		this.tests = tests;
	}
	public int getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(int homeworks) {
		this.homeworks = homeworks;
	}
	
	public void setMembers(int members) {
		this.members = members;
	}
	
	public int getMembers(){
		return this.members;
	}
}
