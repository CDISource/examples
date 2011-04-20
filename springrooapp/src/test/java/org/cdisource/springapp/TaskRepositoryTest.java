package org.cdisource.springapp;


import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

//import org.cdisource.testing.junit.CdiTestRunner;
//import org.junit.Test;
//import org.junit.runner.RunWith;


//@RunWith(CdiTestRunner.class)
//@RunWith(ResinBeanContainerRunner.class)
//@ResinBeanConfiguration(beansXml="testbeans.xml", persistenceXml="META-INF/persistence.xml")
public class TaskRepositoryTest {

	@Inject
	private TaskRepository taskRepository;

	//@Test
    public void testCrud() {
		Task task = new Task();
		task.setDone(true);
		task.setTitle("love rockets");
		taskRepository.persist(task);
		taskRepository.flush();
		List<Task> findAllTasks = taskRepository.findAllTasks();
		assertEquals(1, findAllTasks.size());
		taskRepository.remove(task);
		taskRepository.flush();
		List<Task> findAllTasks2 = taskRepository.findAllTasks();
		assertEquals(0, findAllTasks2.size());
    }

}
