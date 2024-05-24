package spring.dtp.component;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import spring.dtp.domain.DtpThreadPoolExecutor;
import spring.dtp.service.DtpRegistry;

public class DtpBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DtpThreadPoolExecutor) {
            //直接纳入管理
            DtpRegistry.registry(beanName, (DtpThreadPoolExecutor) bean);
        }
        return bean;
    }
}
