package org.uniplovdiv.fmi.mavenproject1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController
{
  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model)
  {
    model.addAttribute("name", name);
    return "hello";
  }

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String hello2()
  {
        return "hello";
  }

}
