package org.uniplovdiv.fmi.javadbweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController
{
  @RequestMapping("/")
  public void greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model)
  {
    System.out.println("greeting");
    model.addAttribute("name", name);
    //return "index";
  }

  @RequestMapping("/test40")
  public String home()
  {
    System.out.println("test");
    return "Spring boot is working!";
  }
  
//  @RequestMapping(value = "", method = RequestMethod.GET)
//  public String index()
//  {
//    System.err.println("index");
//    return "index method";
//  }
}
