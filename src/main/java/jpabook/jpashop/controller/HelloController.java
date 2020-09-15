package jpabook.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by JongHyun Lee on 2020-09-14
 */
@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "hello!");
    return "hello";
  }

}
