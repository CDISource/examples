package org.cdisource.springapp;


public class TaskValidator {
	
	public void validate(Task task) {
		if (task.getTitle().equals("do nothing and party")) {
			throw new IllegalStateException("You can't do nothing and party you laszy bastard");
		}
	}

}
