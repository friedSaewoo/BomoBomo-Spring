package com.example.bomobomo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final UserService userService;
    private final JavaMailSender javaMailSender;
//    private static final String senderEmail= "gohwangbong@gmail.com";
    private static final String senderEmail= "test@naver.com";
    private static int number;
//    private static String rePassword;

    public static void createNumber(){
        System.out.println("여기도 도는지 확인");
       number = (int)(Math.random() * (90000)) + 100000;// (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail){
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("bomobomo에서 인증번호 발송드립니다.");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail){
        System.out.println("메일 서비스 오는지 확인");
        MimeMessage message = CreateMail(mail);

        javaMailSender.send(message);

        return number;
    }








public static String tempPassword(int leng){
   	int index = 0;
   	char[] charSet = new char[] {
   			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
   			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
   			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '='
   	};	//배열안의 문자 숫자는 원하는대로

   	StringBuffer password = new StringBuffer();
   	Random random = new Random();

   	for (int i = 0; i < leng ; i++) {
   		double rd = random.nextDouble();
   		index = (int) (charSet.length * rd);

   		password.append(charSet[index]);

   		System.out.println("index::" + index + "	charSet::"+ charSet[index]);
   	}

   	return password.toString();
       //StringBuffer를 String으로 변환해서 return 하려면 toString()을 사용하면 된다.
   }



    public MimeMessage pwMail(String mail, String userId){

        String rePassword = tempPassword(10);
        System.out.println("유우저어 : " + userId);
        System.out.println("임시비밀번호 :"+ rePassword);

        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("bomobomo 패스워드 재발송입니다.");
            String body = "";
            body += "<h3>" + "요청하신 임시 패스워드입니다." + "</h3>";
            body += "<h1>" + rePassword + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        userService.rePassword(rePassword, userId);

        return message;
    }

    public int reworkPwMail(String mail, String userId){
        System.out.println("메일 서비스 오는지 확인");
        MimeMessage message = pwMail(mail, userId);

        javaMailSender.send(message);

        return number;
    }

}