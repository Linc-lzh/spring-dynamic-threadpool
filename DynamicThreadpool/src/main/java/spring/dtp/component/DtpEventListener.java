package spring.dtp.component;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import spring.dtp.properties.ThreadPoolProperties;
import spring.dtp.service.DtpRegistry;

@Component
public class DtpEventListener {
    @EventListener(DtpEvent.class)
    public void onApplicationEvent(DtpEvent event) {
        ThreadPoolProperties properties = event.getProperties();
        DtpRegistry.refresh(properties.getPoolName(), properties);
    }
}
