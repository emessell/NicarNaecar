package com.prj.nicarnaecar.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.prj.nicarnaecar.service.ServiceService;
import com.prj.nicarnaecar.vo.FaqVO;
import com.prj.nicarnaecar.vo.NoticeVO;
import com.prj.nicarnaecar.vo.OneonOneVO;


@Controller
@RequestMapping("/service")
public class ServiceController {
   
   @Autowired
   @Qualifier("serviceServiceImplXML")
   ServiceService serviceService;
   
   @RequestMapping(value = "/notice")
   public String notice(HttpServletRequest request) {
      serviceService.noticeList(request);
      return "/service/notice";
   }
   
   @RequestMapping(value="/noticeView")
   public void noticeView(@RequestParam int nnumber, Model model) {
      model.addAttribute("view",serviceService.noticeView(nnumber));
   }
   
   @RequestMapping(value="/noticeWrite")   // get방식일 경우 return으로 경로 설정 안해줘도 RequestMapping으로 알아서감~
   public void noticeWrite(Model model) {
      model.addAttribute("notice", new NoticeVO());
   }
   
   @RequestMapping(value="/noticeWrite", method=POST) 
   public String noticeWriteOK(NoticeVO noticeVO) {
      serviceService.noticeInsert(noticeVO);
      return "redirect:/service/notice";
   }
   
   @RequestMapping(value="/noticeModifyOK", method=POST)
   public String noticeModifyOK(NoticeVO noticeVO) {
      serviceService.noticeModify(noticeVO);
      return "redirect:/service/noticeView?nnumber="+noticeVO.getNnumber();
   }
   @RequestMapping(value="/noticeDelete")
   public String noticeDelete(int nnumber) {
      serviceService.noticeDelete(nnumber);
      return "redirect:/service/notice";
   }
   
   
   
   @RequestMapping(value = "/oneonone")
   public String oneonone() {
      return "/service/oneonone";
   }
   
   //자바 메일 설정
      @RequestMapping(value="/sendMailOK")
      public String mailSender(HttpServletRequest request, ModelMap mo,OneonOneVO one) throws AddressException, MessagingException{
         
         //구글일 경우
         String host = "smtp.gmail.com";
         
         final String username = "nicarnaecar"; //google id
         final String password = "suzyzzang"; //password
         int port = 465;
         
         //mail content
         String recipient = "nicarnaecar@gmail.com"; //받는사람 email
         String name = one.getName();
         String sendEmail = one.getSendEmail();
         String subject = one.getSubject(); // 메일 제목
         String body = one.getBody(); //메일내용
         
         Properties props = System.getProperties();
         
         // smtp 서버 정보 설정
         props.put("mail.smtp.host", host);
         props.put("mail.smtp.port", port);
         props.put("mail.smtp.auth", "true");
         props.put("mail.smtp.ssl.enable", "true");
         props.put("mail.smtp.trust", host);
         
         //Session 생성
         Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un = username;
            String pw = password;
            protected javax.mail.PasswordAuthentication getPasswordAuthentication(){
               return new javax.mail.PasswordAuthentication(un, pw);
            }
         });
         session.setDebug(true);
         
         Message mimeMessage = new MimeMessage(session); //MimeMessage 생성
         mimeMessage.setFrom(new InternetAddress("sendEmail@gmail")); //보내는 사람 이메일
         
         mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
         
         mimeMessage.setSubject(subject);
         mimeMessage.setText("고객 이름:\t"+name+"\n\n고객 e-mail:\t"+sendEmail+",\n\n문의 내용:\t"+body);
         Transport.send(mimeMessage);
         return "redirect:/";
      }
   
   
   
   
   @RequestMapping(value = "/faq")
   public void faq(HttpServletRequest request) {
      serviceService.faqList(request);
   }
   
   @RequestMapping(value = "/faqWrite")
   public void faqWrite(Model model) {
      model.addAttribute("faq",new FaqVO());
   }
   
   @RequestMapping(value = "/faqWrite", method=POST)
   public String faqWriteOK(FaqVO faqVO) {
      serviceService.faqInsert(faqVO);
      return "redirect:/service/faq";
   }
   
   @RequestMapping(value = "/faqModify/{fnumber}")
   public String faqModify(@PathVariable int fnumber, Model model) {
      FaqVO faqVO = new FaqVO();
      faqVO = serviceService.faqView(fnumber);
      
      model.addAttribute("faq", faqVO);
      return "/service/faqModify";
   }
   
   @RequestMapping(value = "/faqModifyOK", method=POST)
   public String faqModifyOK(FaqVO faqVO) {
      System.out.println(faqVO.toString());
      serviceService.faqModify(faqVO);
      return "redirect:/service/faq";
   }
   
   @RequestMapping(value = "/faqDelete")
   public String faqDelete(int fnumber) {
      serviceService.faqDelete(fnumber);
      return "redirect:/service/faq";
   }
   
   
}
