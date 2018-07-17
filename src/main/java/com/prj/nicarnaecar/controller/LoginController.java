package com.prj.nicarnaecar.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.prj.nicarnaecar.service.LoginService;
import com.prj.nicarnaecar.vo.LoginVO;
import com.prj.nicarnaecar.vo.MemberVO;


@SessionAttributes({"login","find"})
@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	@Qualifier("loginServiceImplXML")
	LoginService loginService;
	
/*	@RequestMapping(value = "/login")
	public String login(Model model, HttpSession session, SessionStatus sessionStatus) {	
	
		if(session.getAttribute("login") != null) {
			return "redirect:/"; 
		}
		model.addAttribute("login", new LoginVO());
		model.addAttribute("find", new MemberVO());
		return "login/login";	
	}
	
// 로그인 처리 부분
	@RequestMapping("/memLoginOK")
 	public String memLoginOK(@Valid @ModelAttribute("login") LoginVO login, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "login/login";
		}else { 
			MemberVO memberVO = loginService.getMember(login);
			if(memberVO != null) {
				model.addAttribute("login", memberVO);
				return "redirect:/";
			} else {
				return "login/login";
			}
		}
	}*/
	
	 // Security 용 로그인, 로그아웃
	@RequestMapping("/login")
	public String securityLogin(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(!auth.getPrincipal().equals("anonymousUser")) {
			System.out.println("인증" + auth.getPrincipal());
			return "redirect:/";
		}
		model.addAttribute("find",new MemberVO());
		return "login/login";
		
	}
	
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET )
		public String logoutPage(SessionStatus sessionStatus,HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request,response,auth);
		}
		return "redirect:/";
	}
	
}
