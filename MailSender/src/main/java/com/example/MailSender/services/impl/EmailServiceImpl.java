package com.example.MailSender.services.impl;

import com.example.MailSender.dto.EmailDetails;
import com.example.MailSender.dto.MerchantDto;
import com.example.MailSender.dto.ProductDto;
import com.example.MailSender.feign.ProductService;
import com.example.MailSender.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    ProductService productService;

    @Value("${spring.mail.username}")
    private String sender;

    public String sendSimpleMail(String email, String productId, String merchantId) {
        try {


            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            Optional<MerchantDto> merchantDto = productService.findByProductIdAndMerchantId(merchantId, productId);
            MerchantDto merchantDto1 = new MerchantDto();
            if (merchantDto.isPresent()) {
                merchantDto1 = merchantDto.get();
            }

            ProductDto productDetails = productService.findByProductId(productId);

            mailMessage.setFrom(sender);
            mailMessage.setTo(email);
            mailMessage.setText("Thank you for chossing At the door " + "\n\n" +
                    "Product Name : " + productDetails.getProductName() + "\n\n" +
                    "Product Price : " + merchantDto1.getPrice() + "\n\n" +
                    "Product Description :" + productDetails.getProductDescription() + "\n\n" +
                    "Product Category :" + productDetails.getProductCategory() + "\n\n" +
                    "Merchant Name :" + merchantDto1.getMerchantName()+ "\n\n" +
                    productDetails.getImageUrl().get(0)


            );
            mailMessage.setSubject("Ordered By At The Door");


            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }


//    public String sendMailWithAttachment(EmailDetails details) {
//
//        MimeMessage mimeMessage
//                = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper;
//
//        try {
//
//
//            mimeMessageHelper
//                    = new MimeMessageHelper(mimeMessage, true);
//            mimeMessageHelper.setFrom(sender);
//            mimeMessageHelper.setTo(details.getRecipient());
//            mimeMessageHelper.setText(details.getMsgBody());
//            mimeMessageHelper.setSubject(
//                    details.getSubject());
//
//
//            FileSystemResource file
//                    = new FileSystemResource(
//                    new File(details.getAttachment()));
//
//            mimeMessageHelper.addAttachment(
//                    file.getFilename(), file);
//
//
//
//
//            javaMailSender.send(mimeMessage);
//            return "Mail sent Successfully";
//        } catch (MessagingException e) {
//
//
//            return "Error while sending mail!!!";
//        }
//    }




}