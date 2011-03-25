package org.cdisource.web.demoapp.demoapp;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.inject.Named;

import org.cdisource.beancontainer.BeanContainer;
import org.cdisource.beancontainer.BeanContainerManager;

@Named("someBean")
public class BackingBean {

	@Inject
	private BeanManager beanManager;
	
	@Inject
	private MessageProvider provider;
	
	public String getMessageThroughInjection() {
		return provider == null ? "NuLL Provider" : provider.getContent() + "(INJECTED)"; 		
	}
	
	public String getMessageFromManualLookup() {
		MessageProvider manual = BeanContainerManager.getInstance().getBeanByName(MessageProvider.class,"mp");
		return manual == null ? "Null manual Provider" : manual.getContent()+ "(MANUAL)";		
	}

	public BeanManager getBeanManager() {
		return beanManager;
	}
}
