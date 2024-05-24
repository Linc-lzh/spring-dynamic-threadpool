package spring.dtp.controller;

import spring.dtp.domain.DtpThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    DtpThreadPoolExecutor dtpExecutor1;

    @GetMapping("/add2")
    public String addOrder2(){
        dtpExecutor1.execute(() ->{
            System.out.println("place order");
        });

        return "success";
    }

}
