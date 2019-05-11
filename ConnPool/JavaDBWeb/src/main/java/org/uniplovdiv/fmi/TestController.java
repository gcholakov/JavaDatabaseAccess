package org.uniplovdiv.fmi;

import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController
{
  private Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping("/")
  public String home()
  {
    return "Application is started!";
  }
}
