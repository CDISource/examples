package org.cdisource.springapp;

import javax.enterprise.inject.spi.BeanManager;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.cdisource.beancontainer.BeanContainer;
import org.cdisource.beancontainer.BeanContainerImpl;

public class TaskRepositoryFactoryBean implements FactoryBean<TaskRepository>, InitializingBean {

	private BeanContainer beanContainer;
	private BeanManager beanManager;
	
	public void setBeanManager(BeanManager beanManager) {
		this.beanManager = beanManager;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		beanContainer = new BeanContainerImpl(beanManager);
	}

	@Override
	public TaskRepository getObject() throws Exception {
		return beanContainer.getBeanByType(TaskRepository.class);
	}

	@Override
	public Class<?> getObjectType() {
		return TaskRepository.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


}
