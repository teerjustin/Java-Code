package com.teerjustin.code.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Controllers {
	
    @RequestMapping("/")
    public String dashboard(HttpSession session) {
		//if session does not exist
		if (session.getAttribute("count") == null) {
			session.setAttribute("count", 0);
			System.out.println(session.getAttribute("count"));
			return "index.jsp";
		}
		session.setAttribute("count", 0);
		return "index.jsp";
    }
    
   
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@RequestParam(value="password") String password, HttpSession session) {
    	//count will be 0 
    	if (password.equals("bushido")) {
    		//if password is correct, count = 1
    		session.setAttribute("count", 1);
    		return "redirect:/code";
    	}
          return "redirect:/createError";
    }

    @RequestMapping("/code")
    public String code(HttpSession session) {
    	Integer count = (Integer) session.getAttribute("count");
    	if (count == 1) {
    		return "code.jsp";
    	}
    	return "redirect:/createError";
    }
    
    @RequestMapping("/createError")
    public String flashMessages(RedirectAttributes redirectAttributes) {
    	System.out.println("should say errors here");
        redirectAttributes.addFlashAttribute("error", "You must train harder!");
        return "redirect:/";
    }
}

