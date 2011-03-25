package org.cdisource.web.demoapp.demoapp;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.cdisource.beancontainer.BeanContainer;
import org.cdisource.beancontainer.BeanContainerManager;

/**
 * This class bootstraps the {@link BeanContainer} instance and keeps a hold of
 * it for static lookup through {@link CDIBootstrapListener#getBeanContainer()}.
 * This is a temporary solution for now.
 * 
 * I've made the init and shutdown methods thread safe, but I get a null pointer
 * exception on shutdown sometimes, I still need to look at it.
 * 
 * 
 * @author Andy Gibson
 * 
 */
public class CDIBootstrapListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		BeanContainerManager.initialize();		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		BeanContainerManager.shutdown();
	}
}
