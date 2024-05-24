package spring.dtp.component;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import spring.dtp.properties.ThreadPoolProperties;

@Getter
public class DtpEvent extends ApplicationEvent {

    private ThreadPoolProperties properties;
    public DtpEvent(ThreadPoolProperties properties) {
        super(properties);
        this.properties = properties;
    }
}
