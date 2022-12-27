package com.fis.sdc.checkfile.CONTROLLER.REST;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.MODEL.File;
import com.fis.sdc.checkfile.MODEL.SendMail;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/sendmail")
public class SendMailRestController {
    @Autowired
    AccountService accdao;
    @Autowired
    FileService service;
    @Autowired
    JavaMailSender mailer;
    @PostMapping()
    public String index(@RequestBody SendMail sendMail){
        sendmail(sendMail);
        return "Gửi thành công";
    }

    @PostMapping("thongbao")
    public String thongbaochiase(@RequestBody SendMail sendMail){
        thongbao(sendMail);
        return "Gửi thông báo thành công";
    }
    public void sendmail(SendMail sendMail){
        try {
            MimeMessage mail = mailer.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
            helper.setFrom("sprologfile@gmail.com", "sprologfile@gmail.com");
            helper.setTo(sendMail.getTo());
            helper.setReplyTo("sprologfile@gmail.com", "sprologfile@gmail.com");
            helper.setSubject(sendMail.getSubject());
            helper.setCc(sendMail.getCc());
            helper.setText("XIn chào bạn",true);
            Multipart multipart = new MimeMultipart();
            List<File> file = sendMail.getIdfile();
            for(File attach : file){
                java.io.File fi = new java.io.File("C:\\FPT\\JAVA6\\spro-logfile\\checkfile\\src\\main\\resources\\static\\SaveFile\\" + attach.getName());
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.attachFile(fi);
                multipart.addBodyPart(mimeBodyPart);
            }

            mail.setContent(multipart);
            mailer.send(mail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void thongbao(SendMail sendMail){
        try {
            MimeMessage mail = mailer.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
            helper.setFrom("sprologfile@gmail.com", "sprologfile@gmail.com");
            helper.setTo(sendMail.getTo());
            helper.setReplyTo("sprologfile@gmail.com", "sprologfile@gmail.com");
            helper.setSubject(sendMail.getSubject());
            helper.setCc(sendMail.getCc());
            helper.setText(sendMail.getContent(),true);
            mailer.send(mail);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
