package com.munaf.A12_SPRINGBOOT_WEB;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }


      // This is a Servlet way with session
//    @RequestMapping("add")
//    public String add(HttpServletRequest req, HttpSession session){
//        int num1 = Integer.parseInt(req.getParameter("num1")); // parse because it is by default string
//        int num2 = Integer.parseInt(req.getParameter("num2"));
//        int result = num1 + num2;
//
//        session.setAttribute("ans", result);
//
//        return "add";
//    }

    // Model way
//    @RequestMapping("add")
//    public String add(@RequestParam("num1") int num1,@RequestParam("num2") int num2, Model model){
//        int result = num1 + num2;
//        model.addAttribute("ans", result);
//
//        return "add";
//    }

    // Model and View
    @RequestMapping("add")
    public ModelAndView add(@RequestParam("num1") int num1, @RequestParam("num2") int num2, ModelAndView mv){
        int result = num1 + num2;
        mv.addObject("ans", result);
        mv.setViewName("add");

        return mv;
    }

    // controller for Alien
    @RequestMapping("alien")
    public ModelAndView saveAlien( ModelAndView mv){
        mv.setViewName("homeAlien");
        return mv;
    }

//    @RequestMapping("addAlien")
//    public ModelAndView addAlien(@RequestParam("aid") int aid,@RequestParam("aname") String aname,ModelAndView mv){
//
//        Alien alien = new Alien();
//        alien.setAid(aid);
//        alien.setAname(aname);
//
//        mv.addObject(alien);
//        mv.setViewName("addAlien");
//
//        return mv;
//    }

    // Model Attribute
    @RequestMapping("addAlien")
    public String addAlien(@ModelAttribute Alien alien){ //@ModelAttribute is optional
        return "addAlien";
    }

    @ModelAttribute("course")
    public String courseName(){
        return "JAVA";
    }


    // Calculator
    @RequestMapping("homeCalc")
    public String homeCalc(){
        return "homeCalc";
    }

    @RequestMapping("resultCalc")
    public String homeCalc(@RequestParam("num1") float num1, @RequestParam("num2") float num2, @RequestParam("Char") char Char, Model model){

        float ans = 0;
        int check = 0;

        if(Char == '+') ans = num1+num2;
        else if(Char == '-') ans = num1-num2;
        else if(Char == '*') ans = num1*num2;
        else if(Char == '/' && num2 != 0) ans =  num1 /num2;
        else check = 1;

        if (check == 0) model.addAttribute("ans", ans);
        else model.addAttribute("ans", "Invalid Input");

        return "resultCalc";
    }

}
