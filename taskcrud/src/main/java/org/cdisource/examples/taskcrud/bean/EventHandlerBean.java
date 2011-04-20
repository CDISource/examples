package org.cdisource.examples.taskcrud.bean;

import javax.enterprise.event.Observes;

import org.cdisource.examples.taskcrud.event.StudentCoursePayload;
import org.cdisource.examples.taskcrud.model.Course;
import org.cdisource.examples.taskcrud.model.Student;
import org.cdisource.examples.taskcrud.qualifier.StudentAdded;
import org.cdisource.examples.taskcrud.qualifier.StudentRemoved;

/**
 * This bean contans the event handlers for application.
 * 
 * @author Andy Gibson
 * 
 */
public class EventHandlerBean {

	/**
	 * This method handles events when students are added to courses as defined
	 * by the {@link StudentAdded} qualifier annotation. It pretends to send an
	 * email to the student by logging it in the console.
	 * 
	 * @param payload
	 *            {@link StudentCoursePayload} instance passed into the event
	 */
	public void onAddStudentToCourse(
			@Observes @StudentAdded StudentCoursePayload payload) {
		String msg = "Sending email to %s that they have been signed up for to %s - %s";
		Student student = payload.getStudent();
		Course course = payload.getCourse();
		// just show message indicating an email was sent
		System.out.println(String.format(msg, student.getName(), course
				.getCode(), course.getTitle()));
	}

	/**
	 * This method handles events when students are removed from courses as
	 * defined by the {@link StudentRemoved} qualifier annotation.
	 * 
	 * @param payload
	 *            {@link StudentCoursePayload} instance passed into the event
	 */
	public void onRemoveStudentFromCourse(
			@Observes @StudentRemoved StudentCoursePayload payload) {
		String msg = "Sending email to %s that they are no longer signed up for %s - %s";
		Student student = payload.getStudent();
		Course course = payload.getCourse();
		// just show message indicating an email was sent
		System.out.println(String.format(msg, student.getName(), course
				.getCode(), course.getTitle()));
	}

}
