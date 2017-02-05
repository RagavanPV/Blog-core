package com.ragavan.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import com.ragavan.model.Comment;

public class MailUtil {
	private MailUtil() {

	}

	public static void sendSimpleMail(Comment comments) throws EmailException {
		Email email = new SimpleEmail();
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("ragavanpv007@gmail.com", "changedmypassword"));
		email.setDebug(false);
		email.setHostName("smtp.gmail.com");
		email.setSSLOnConnect(true);
		email.setFrom("ragavanpv007@gmail.com");// Admin email
		email.setSubject("Comment on your article");
		email.setMsg(comments.getUserId().getUserName() + "" + comments.getCommentText());
		email.addTo(comments.getArticleId().getUserId().getEmailId());
		email.setStartTLSEnabled(true);
		email.send();

	}

}
