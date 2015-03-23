package com.webCourses.app.services;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.webCourses.app.model.user.User;
import com.webCourses.app.utils.ApplicationUtils;

public class EmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private MessageSource messageSource;

	public void sendRegistrationConfirmation(User user, Locale locale) {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("user", user);
		model.put("activationURL", ApplicationUtils.urlPath + "confirm?id=" + user.getUserId()+"&code=" + user.getConfirmationCode());
		sendEmail(model, user.getEmail(), locale,
				messageSource.getMessage("registration.confirm.email.subject", null, locale),
				"registration-confirmation.vm");
	}

	public void sendEmail(final Map<String, Object> model, final String sendTo, final Locale locale,
			final String subject, final String template) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				message.setTo(sendTo);
				message.setSubject(subject);
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"templates/" + locale.getLanguage() + "/" + template, "UTF-8", model);
				message.setText(text, true);
			}
		};
		this.mailSender.send(preparator);
	}
}
