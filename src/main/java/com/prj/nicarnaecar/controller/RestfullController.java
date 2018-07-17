package com.prj.nicarnaecar.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prj.nicarnaecar.service.BookingService;
import com.prj.nicarnaecar.service.EmployeeService;
import com.prj.nicarnaecar.service.MemberService;
import com.prj.nicarnaecar.service.ProfitService;
import com.prj.nicarnaecar.service.RepairService;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.MemberVO;
import com.prj.nicarnaecar.vo.ProfitVO;
import com.prj.nicarnaecar.vo.VehicleVO;


@RestController
@Controller
public class RestfullController {
	
	@Autowired
	@Qualifier("memberServiceImplXML")
	MemberService memberService;
	
	// 아이디 찾기
		@RequestMapping("/findID")
		public String findID(@ModelAttribute("find") MemberVO find,BindingResult result, Model model){
			if(result.hasErrors()) {
				return "redirect:/";	
			}else {
				MemberVO memberVO = memberService.findID(find);
					return memberVO.getCemail();
			}
		}

		// 비밀번호 찾기
		@RequestMapping("/findPW")
		public String findPW(@ModelAttribute("find") MemberVO find,BindingResult result, Model model){
			if(result.hasErrors()) {
				return "redirect:/";	
			}else {
				MemberVO memberVO = memberService.findPW(find);
					return memberVO.getCpasswd();
				}
		}
		
		// 회원가입시 이메일 중복체크
		@RequestMapping(value="/memEmailCheck", method=POST, produces = "text/json; charset=UTF-8")
		public ResponseEntity<String> memberEmailCheck(@RequestBody String cemail) throws UnsupportedEncodingException {
			ResponseEntity<String> responseEntity = null;
			try {
				responseEntity = new ResponseEntity<String>(String.valueOf(memberService.idCheck(URLDecoder.decode(cemail.substring(7), "UTF-8"))),  HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				responseEntity = new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
		}
		
		
		@Autowired
		@Qualifier("employeeServiceImplXML")
		EmployeeService employeeService;
		
		// 직원추가시 아이디 중복체크
		@RequestMapping(value="/emplEmailCheck", method=POST, produces = "text/json; charset=UTF-8")
		public ResponseEntity<String> employeesEmailCheck(@RequestBody String eemail) throws UnsupportedEncodingException {
			ResponseEntity<String> responseEntity = null;
			String aa = URLDecoder.decode(eemail.substring(7), "UTF-8") +"@ncnc.com";
			try {
				responseEntity = new ResponseEntity<String>(String.valueOf(employeeService.eidCheck(aa)), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				responseEntity = new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
			}
			return responseEntity;
		}
		
		
		
		
		@Autowired
		@Qualifier("bookingServiceImplXML")
		BookingService bookingService;
		
		// 예약 시도 시 연장된 거래 유무 체크
		@RequestMapping(value="/bookingCheck", method=POST)
		public ResponseEntity<String> bookingCheck(Principal principal) {
			ResponseEntity<String> responseEntity = null;
			String cemail = principal.getName();
			
			try {
				responseEntity = new ResponseEntity<String>(String.valueOf(bookingService.bookingCheck(cemail)), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				responseEntity = new ResponseEntity<String>("Fail", HttpStatus.BAD_REQUEST);
			}
			
			return responseEntity;
		}
		
		
		@Autowired
		@Qualifier("profitServiceImplXML")
		ProfitService profitService;
		
		@RequestMapping(value="/detail/{bnumber}")
		public ResponseEntity<List<ProfitVO>> detail(@PathVariable int bnumber) {
			ResponseEntity<List<ProfitVO>> responseEntity = null;
			try {
				responseEntity = new ResponseEntity<List<ProfitVO>>(profitService.customerDetailProfit(bnumber), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				responseEntity = new ResponseEntity<List<ProfitVO>>(HttpStatus.BAD_REQUEST);
			}
			
			return responseEntity;
		}
		
		
		
	     @Autowired
	      @Qualifier("repairServiceImplXML")
	      RepairService repairService;
	      
	      @RequestMapping(value="/vnumforbnum", method=POST)
	      public ResponseEntity<List<BookingVO>> vnumforbnum(@ModelAttribute VehicleVO vehicleVO){
	         ResponseEntity<List<BookingVO>> responseEntity = null;
	         
	         try {
	         responseEntity = new ResponseEntity<List<BookingVO>>(repairService.bookList(vehicleVO), HttpStatus.OK);
	         return responseEntity;
	         
	         }catch(Exception e) {
	            
	            e.printStackTrace();
	            responseEntity = new ResponseEntity<List<BookingVO>>(HttpStatus.BAD_REQUEST);
	            return responseEntity;
	         }
	         
	      }
			
}
