package org.cdisource.web.demoapp.demoapp;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("mp")
public class MessageProvider implements Serializable {

	public String getContent() {
		return "this message is from the CDI message Provider";
	}
}
