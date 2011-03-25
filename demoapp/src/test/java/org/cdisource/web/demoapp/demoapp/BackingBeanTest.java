package org.cdisource.web.demoapp.demoapp;

import javax.inject.Inject;

import org.cdisource.beancontainer.BeanContainerManager;
import org.cdisource.testing.junit.CdiTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.Assert;


@RunWith(CdiTestRunner.class)
public class BackingBeanTest {
	
	@Inject
	private BackingBean backingBean;
	
	@Before
	public void doBefore() {
		BeanContainerManager.initialize();
	}
	
	@After
	public void doAfter() {
		BeanContainerManager.shutdown();
	}
	
	@Test
	public void testBackingBeanInjectionAndSanity() {
		Assert.assertNotNull(backingBean);	
		Assert.assertNotNull(backingBean.getBeanManager());
		String result = "this message is from the CDI message Provider(INJECTED)";
		Assert.assertEquals(result,backingBean.getMessageThroughInjection());
	}

}
