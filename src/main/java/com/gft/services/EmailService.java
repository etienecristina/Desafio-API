package com.gft.services;

import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

@Service
public class EmailService {

    public void mandarEmail() throws MessagingException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("criadoparatestefinsdeestudos@gmail.com", "rbdlboygndkiuptl");
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("criadoparatestefinsdeestudos@gmail.com"));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("criadoparatestefinsdeestudos@gmail.com"));
        message.setSubject("Login Efetuado");

        String msg = "Login efetuado na API de controle de cadastro de Categorias e Starters";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);

    }

}
