package com.zuri.circle.manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailBuilder {
	private TemplateEngine templateEngine;

	@Autowired
	    public MailBuilder(TemplateEngine templateEngine) {
	        this.templateEngine = templateEngine;
	    }

	public String build(String firstName,String message) {
		Context context = new Context();
		 message  +=  "\n Please click on the link to confirm availability\n ";
		context.setVariable("message", message);
		context.setVariable("salutation", "Hi"+firstName);
		context.setVariable("thankText", "Thanks \n"+"Zuri Team");
		return templateEngine.process("mailTemplate", context);
	}

}

