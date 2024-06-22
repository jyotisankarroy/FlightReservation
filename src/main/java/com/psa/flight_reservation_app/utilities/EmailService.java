package com.psa.flight_reservation_app.utilities;

public interface EmailService {
	public void sendEmail(String to, String subject, String message);
	public void sendEmailWithAttachment(String to, String subject, String message, String filePath);
}
