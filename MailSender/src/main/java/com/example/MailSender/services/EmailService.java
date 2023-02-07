package com.example.MailSender.services;

import com.example.MailSender.dto.EmailDetails;

public interface EmailService {

    // Method
    // To send a simple email
    String sendSimpleMail(String email,String productId,String merchantId);

    // Method
    // To send an email with attachment
//    String sendMailWithAttachment(String email,String productId,String merchantId);
}