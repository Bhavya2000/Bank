
package Withdraw;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class WithdrawMail{
    
    public static void sendMail(String recepient,String amt,String bal) throws MessagingException
    {
        System.out.println("Preparing to send Email.");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail = "aryan.mansani.btech2019@sitpune.edu.in";
        String password = "Yoyo@1234";
        
        Session session = Session.getInstance(properties,new Authenticator(){
        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(myAccountEmail,password);
            
        }
        });
        Message message = prepareMessage(session, myAccountEmail, recepient,amt,bal);
        
        Transport.send(message);
        System.out.println("Message Sent Successfully");
        
        
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient , String amt,String bal){
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Amount Debited");
            message.setText("₹ "+amt+" apke khatey se nikaal liye gye he\n ab ₹ "+bal+" inte bache he");
            return message;
        }catch(Exception ex)
        {
            System.out.println("Theres been a error");
            return null;
            
            
       
    }
        
        
    }
    
}
