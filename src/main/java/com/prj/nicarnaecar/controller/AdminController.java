package com.prj.nicarnaecar.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prj.nicarnaecar.service.EmployeeService;
import com.prj.nicarnaecar.service.FileUpLoad;
import com.prj.nicarnaecar.service.ProfitService;
import com.prj.nicarnaecar.service.RepairService;
import com.prj.nicarnaecar.service.SearchService;
import com.prj.nicarnaecar.service.VehicleService;
import com.prj.nicarnaecar.vo.BookingVO;
import com.prj.nicarnaecar.vo.EmployeeVO;
import com.prj.nicarnaecar.vo.ProfitVO;
import com.prj.nicarnaecar.vo.RepairVO;
import com.prj.nicarnaecar.vo.UploadFile;
import com.prj.nicarnaecar.vo.VehicleVO;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	@Qualifier("searchServiceImplXML")
	SearchService searchService;
	
	@Autowired
	@Qualifier("profitServiceImplXML")
	ProfitService profitService;
	
	@Autowired
	@Qualifier("employeeServiceImplXML")
	EmployeeService employeeService;
	
   @Autowired
   @Qualifier("vehicleServiceImplXML")
   VehicleService vehicleService;
   
   @Autowired
   FileUpLoad fileupload;
   
   @Autowired
   @Qualifier("repairServiceImplXML")
   RepairService repairService;
	
   @RequestMapping("/profitChart")
	public void admin(HttpServletRequest request) {
		List<ProfitVO> list = profitService.profitChart();
		request.setAttribute("list", list);
	}
	// 인도할 차량 전부보기
	@RequestMapping("/delivery")
	public void deliveryCar(HttpServletRequest request) {
		searchService.deliverySearch(request);
	}
	// 반납할 차량 전부보기
	@RequestMapping("/return")
	public void returnCar(HttpServletRequest request) {
		searchService.returnSearch(request);
	}
	
	
	@RequestMapping("/profit")
	public void profit(HttpServletRequest request) {
		profitService.profitList(request);
	}
	
	@RequestMapping("/profitInsertOK")
	public String profitInsertOK(ProfitVO profitVO, Principal principal) {
		profitVO.setEemail(principal.getName());
		System.out.println(profitVO.toString());
		profitService.profitInsert(profitVO);
		return "redirect:/admin/profit";
	}
	
	@RequestMapping(value="/profitDeleteOK/{pnumber}")
	public String profitDeleteOK(@PathVariable int pnumber) {
		profitService.profitDelete(pnumber);
		return "redirect:/admin/profit";
	}
	
	
	
	
	@RequestMapping(value="/deliveryOK/{bnumber}")
	public String deliveryOK(@PathVariable int bnumber, Principal principal) {
		searchService.deliveryCar(bnumber, principal.getName());
		return "redirect:/admin/delivery";
	}
	
	@RequestMapping(value="/returnOK", method=POST)
	public String returnOK(BookingVO bookingVO, Principal principal) {
		bookingVO.setBreturn(principal.getName());
		searchService.returnCar(bookingVO);
		return "redirect:/admin/return";
	}
	
	/*@RequestMapping(value="/employees")
	public String employees(HttpServletRequest request) {
		List<EmployeeVO> list = employeeService.Elist();
		request.setAttribute("employees", list);
		return "admin/employees";
	}
	*/
	@RequestMapping(value="/employees")
	public void employees(HttpServletRequest request) throws Exception {
		employeeService.Elist(request);
	}
	
	
   @RequestMapping(value="/insert",method=RequestMethod.POST)
   public String insert(EmployeeVO employeeVO, BindingResult result) {
	   System.out.println(employeeVO.toString());
      if(result.hasErrors()) {
         return "/admin/employees";
      }else {
         employeeService.Einsert(employeeVO);
         return "redirect:/admin/employees";
      }
   }
   
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(EmployeeVO employeeVO, BindingResult result) {
		if(result.hasErrors()) {
			return "/admin/employees";
		}else {
			employeeService.EUpdate(employeeVO);
			return "redirect:/admin/employees";
		}
	}
	
	
	
/*	
   @RequestMapping("/carList")
   public void carList(HttpServletRequest request) {
      List<VehicleVO> vlist = vehicleService.VehicleList();
      request.setAttribute("vlist", vlist);
   }*/
   @RequestMapping("/carList")
   public void carList(HttpServletRequest request) {
      vehicleService.VehicleList(request);
   }
   
   
   
   
   @RequestMapping(value="/insertCarOK", method = RequestMethod.POST)
   public String insertCarOK(VehicleVO vehicleVO, UploadFile uploadFile, HttpServletRequest request) {
   	
   	if(fileupload.upload(uploadFile, request, vehicleVO.getVmodel())) {
   		vehicleService.insertCar(vehicleVO);
   	}
      return "redirect:/admin/carList";
   }
   
   @RequestMapping(value="/updateCarOK", method = RequestMethod.POST)
   public String updateCarOK(VehicleVO vehicleVO, UploadFile uploadFile, HttpServletRequest request) {
   	if(fileupload.upload(uploadFile, request, vehicleVO.getVmodel())) {
   		vehicleService.updateCar(vehicleVO);
   	}   	
      return "redirect:/admin/carList";
   }
   
   @RequestMapping(value="/deleteCarOK/{vnumber}", method = RequestMethod.GET)
   public String deleteCarOK(@PathVariable String vnumber) {
      vehicleService.deleteCar(vnumber);
      return "redirect:/admin/carList";
   }
   
   
   
   @RequestMapping(value="/profitGraph")
   public String profitGraph() {
   	return "/admin/profitGraph";
   }
   
   
   @RequestMapping(value="/repair")
   public void gorepair(HttpServletRequest request) {
      repairService.repairList(request);
   }
   
   @RequestMapping(value="repairIn", method=RequestMethod.POST)
   public String repairIn(RepairVO repairVO, BindingResult result) {
      if(result.hasErrors()) {
         return "redirect:/admin/repair";
      }else {
         repairService.repairIn(repairVO);
         return "redirect:/admin/repair";
      }
   }
   @RequestMapping(value="repairOut/{vnumber}/{rnumber}")
   public String repairOut(@PathVariable String vnumber, @PathVariable int rnumber) {
         repairService.repairOut(vnumber);
         repairService.toComplete(rnumber);
         return "redirect:/admin/repair";
   }
   @RequestMapping(value="repairUpdate", method=RequestMethod.POST)
   public String repairUpdate(RepairVO repairVO, BindingResult result) {
      if(result.hasErrors()) {
         return "/admin/repair";
      }else {
         repairService.repairUpdate(repairVO);
         return "redirect:/admin/repair";
      }
   }
   @RequestMapping(value="repairDelete/{rnumber}")
   public String repairDelete(@PathVariable int rnumber) {
         repairService.repairDelete(rnumber);
         return "redirect:/admin/repair";
   }
}