package org.cdisource.springapp;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;

import org.springframework.beans.factory.FactoryBean;
import org.cdisource.beancontainer.BeanContainer;
import org.cdisource.beancontainer.BeanContainerImpl;
import org.cdisource.beancontainer.BeanContainerInitializationException;

public class TaskRepositoryFactoryBean implements FactoryBean<TaskRepository> {

	private BeanContainer beanContainer = null;
	private final String BEAN_MANAGER_LOCATION = "java:comp/BeanManager";

	
	@Override
	public TaskRepository getObject() throws Exception {

		if (beanContainer==null) {
			InitialContext ic = new InitialContext();
			Object bean = null;
			try {
				bean = ic.lookup(BEAN_MANAGER_LOCATION);
			} catch (Exception e) {
				throw new BeanContainerInitializationException("Unable to lookup BeanManager instance in JNDI", e);
			}
			if (bean == null) {
				throw new BeanContainerInitializationException(
						"Null value returned when looking up the BeanManager from JNDI");
			}
			if (bean instanceof BeanManager) {
				beanContainer = new BeanContainerImpl((BeanManager)bean);
			} else {
				String msg = "Looked up JNDI Bean is not a BeanManager instance, bean type is " + bean.getClass().getName();
				throw new BeanContainerInitializationException(msg);
			}
		}
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
