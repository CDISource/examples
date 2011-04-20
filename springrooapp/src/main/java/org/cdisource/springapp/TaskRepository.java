package org.cdisource.springapp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TaskRepository {
	
    @PersistenceContext
    private EntityManager entityManager;

    
    public void persist(Task task) {
        this.entityManager.persist(task);
    }
    
    public void remove(Task task) {
        if (this.entityManager.contains(task)) {
            this.entityManager.remove(task);
        } else {
            Task attached = this.findTask(task.getId());
            this.entityManager.remove(attached);
        }
    }
    
    public void flush() {
        this.entityManager.flush();
    }
    
    public void clear() {
        this.entityManager.clear();
    }
    
    public Task merge(Task task) {
    	
        Task merged = this.entityManager.merge(task);
        this.entityManager.flush();
        return merged;
    }
        
    public  long countTasks() {
        return entityManager.createQuery("select count(o) from Task o", Long.class).getSingleResult();
    }
    
    public  List<Task> findAllTasks() {
        return entityManager.createQuery("select o from Task o", Task.class).getResultList();
    }
    
    public  Task findTask(Long id) {
        if (id == null) return null;
        return entityManager.find(Task.class, id);
    }
    
    public  List<Task> findTaskEntries(int firstResult, int maxResults) {
        return entityManager.createQuery("select o from Task o", Task.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

}
