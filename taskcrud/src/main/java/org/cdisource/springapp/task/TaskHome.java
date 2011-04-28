package org.cdisource.springapp.task;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SuppressWarnings("serial")
@Named("taskHome")
@ConversationScoped
public class TaskHome implements Serializable {

	private Long id;
	private Task task;
	
	@Inject
	private Conversation conversation;

	@Inject
	private TaskRepository taskRepository;

	public boolean isIdAvailable() {
		return id != null;
	}

	public Task getTask() {
		if (isIdAvailable()) {
			editTask();
		}
		return task == null ? task = new Task() : task;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Task createTask() {
		return new Task();
	}

	public void updateTask() {
		task = this.taskRepository.merge(task);
	}

	public void init() {
		if (conversation.isTransient()) {
			conversation.begin();
		}		
	}
	public void editTask() {
		task = taskRepository.findTask(this.id);
	}

	public void newTask() {
		task = new Task();
	}

	public List<Task> getTasks() {
		return taskRepository.findAllTasks();
	}
	
}
