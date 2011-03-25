package org.cdisource.web.demoapp.demoapp;

import java.beans.FeatureDescriptor;
import java.util.Iterator;

import javax.el.ELContext;
import javax.el.ELResolver;

import org.cdisource.beancontainer.BeanContainer;
import org.cdisource.beancontainer.BeanContainerManager;
import org.cdisource.beancontainer.BeanNotFoundException;

public class CDIExpressionResolver extends ELResolver {

	@Override
	public Object getValue(ELContext context, Object base, Object property) {
		Object result = null;
		if (base == null && property != null) {
			BeanContainer container = BeanContainerManager.getInstance();
			try {
				String stringProperty = property.toString();
				result = container.getBeanByName(stringProperty);
				context.setPropertyResolved(result != null);
			} catch (BeanNotFoundException e) {
				// ignore exception, if we can't find the bean, the default
				// result is null  
			}
		}
		return result;
	}

	@Override
	public Class<?> getType(ELContext context, Object base, Object property) {
		return null;
	}

	@Override
	public void setValue(ELContext context, Object base, Object property,
			Object value) {

	}

	@Override
	public boolean isReadOnly(ELContext context, Object base, Object property) {
		return false;
	}

	@Override
	public Iterator<FeatureDescriptor> getFeatureDescriptors(ELContext context,
			Object base) {
		return null;
	}

	@Override
	public Class<?> getCommonPropertyType(ELContext context, Object base) {
		return null;
	}

}
