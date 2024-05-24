package spring.dtp.component;

import lombok.extern.slf4j.Slf4j;
import spring.dtp.domain.DtpThreadPoolExecutor;
import spring.dtp.properties.DtpProperties;
import spring.dtp.properties.ThreadPoolProperties;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;
import java.util.Objects;

@Slf4j
public class DtpBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {
    private Environment environment;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,
                                        BeanDefinitionRegistry registry) {
        BindResult<DtpProperties> bindResult =
                Binder.get(environment).bind("spring.dtp", DtpProperties.class);
        DtpProperties dtpProperties = bindResult.get();
        List<ThreadPoolProperties> executors = dtpProperties.getExecutors();
        if (Objects.isNull(executors)) {
            log.info("未检测本地到配置文件线程池");
            return;
        }

        for (ThreadPoolProperties properties : executors) {
            BeanDefinitionBuilder builder =
                    BeanDefinitionBuilder.genericBeanDefinition(DtpThreadPoolExecutor.class);
            builder.addConstructorArgValue(properties);
            registry.registerBeanDefinition(properties.getPoolName(),
                    builder.getBeanDefinition());
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
