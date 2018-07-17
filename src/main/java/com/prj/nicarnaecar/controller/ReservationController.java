package com.prj.nicarnaecar.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prj.nicarnaecar.service.BookingService;
import com.prj.nicarnaecar.service.MemberService;
import com.prj.nicarnaecar.service.SearchService;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.MemberVO;
import com.prj.nicarnaecar.vo.VehicleVO;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);
		
	@Autowired
	@Qualifier("searchServiceImplXML")
	SearchService searchService;
	
	// 예약 전 총 정리?
	@RequestMapping(value="/reservation/{vnumber}/{bin}/{bout}",method=GET)
	public String reservation(@PathVariable String vnumber, @PathVariable Date bin, @PathVariable Date bout, Model model) {
		VehicleVO vehicleVO  = searchService.reservationView(vnumber);
		model.addAttribute("vehicleVO", vehicleVO);
		BookingVO bookingVO = new BookingVO();
		bookingVO.setBin(bin);
		bookingVO.setBout(bout);
		model.addAttribute("bookingVO", bookingVO);
		return "/reservation/reservation";
	}
	
	
	@Autowired
	@Qualifier("bookingServiceImplXML")
	BookingService bookingService;
	
	
	
	@RequestMapping(value="/reservationCheck",method=GET)
	public String reservationCheck(Principal principal, Model model, HttpServletRequest request) {
		String cemail = principal.getName();
		bookingService.bookingView(cemail, request);
		return "/reservation/reservationCheck";
	}
	
	@RequestMapping(value="/reservationOK", method=POST)
	public String reservationOK(Principal principal, BookingVO bookingVO) {
		String cemail = principal.getName();
		bookingVO.setCemail(cemail);
		bookingService.bookingInsert(bookingVO);
		System.out.println(bookingVO.toString());
		return "redirect:/reservation/reservationCheck";
	}
	
	@RequestMapping(value="/cancel/{bnumber}")
	public String reservationCancel(@PathVariable int bnumber) {
		bookingService.bookingCancel(bnumber);
		return "redirect:/reservation/reservationCheck";
	}
	@RequestMapping(value="/extend/{bnumber}")
	public String reservationExtend(@PathVariable int bnumber, Model model) {
		BookingVO bookingVO = bookingService.getBookingInfo(bnumber);
		model.addAttribute("bookingVO",bookingVO);
		return "/reservation/extend";
	}
	@RequestMapping(value="/extendOK", method=POST)
	public String reservationExtendOK(BookingVO bookingVO) {
		System.out.println(bookingVO.toString());
		bookingService.bookingExtendOK(bookingVO);
		return "redirect:/reservation/reservationCheck";
	}
	
	
	
	
	
	
	@Autowired
	@Qualifier("memberServiceImplXML")
	MemberService memberService;
	
	// 내 정보 페이지 접근
	@RequestMapping(value = "/myPage")
	public String modify(Principal principal, Model model) {
		String cemail = principal.getName();
		model.addAttribute("memberVO",memberService.getMember(cemail));
		return "/reservation/myPage";
	}
	
   @RequestMapping(value="/modifyOK", method = RequestMethod.POST)
   public String memberModifyOK(@Valid MemberVO memberVO, BindingResult result) {
      if(result.hasErrors()) {
         return "/reservation/myPage";
      }else {
         memberService.memberUpdate(memberVO);
         return "redirect:/reservation/myPage";
      }
   }
}
