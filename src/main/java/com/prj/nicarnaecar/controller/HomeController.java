package com.prj.nicarnaecar.controller;


import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prj.nicarnaecar.service.FileUpLoad;
import com.prj.nicarnaecar.vo.UploadFile;

@Controller
public class HomeController {
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

   @RequestMapping(value = "/", method = RequestMethod.GET)
   public String home(Principal principal) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if(auth.getName().contains("@ncnc.com")) {
         return "/admin/profitChart";
      }
      return "home";
   }
   
   @RequestMapping(value = "/test")
   public String test() {
      return "test/test2";
   }
   
}