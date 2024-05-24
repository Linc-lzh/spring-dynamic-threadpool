package spring.dtp.controller;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dtp.component.DtpEvent;
import spring.dtp.properties.ThreadPoolProperties;

@RestController
@RequestMapping("dtp")
public class DtpController implements ApplicationEventPublisherAware {
    ApplicationEventPublisher applicationEventPublisher;
    @PostMapping("/refresh")
    public String refresh(ThreadPoolProperties properties) {
        applicationEventPublisher.publishEvent(new DtpEvent(properties));
        return "success!";
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher=applicationEventPublisher;
    }
}
