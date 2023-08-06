package jpabook.jpashop.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.logging.Logger;

@Controller
@Slf4j //이게 뭐에 쓰는거?
public class HomeController {

    @RequestMapping("/")
    public String Home(){
        log.info("home controller");
        return "home"; //home.html
    }
}
