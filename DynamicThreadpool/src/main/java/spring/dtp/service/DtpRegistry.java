package spring.dtp.service;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import spring.dtp.properties.ThreadPoolProperties;

public class DtpRegistry {
    /**
     * 储存线程池
     */
    private static final Map<String, ThreadPoolExecutor> EXECUTOR_MAP = new ConcurrentHashMap<>();

    /**
     * 8 * 获取线程池
     * 9 * @param executorName 线程池名字
     * 10
     */
    public static ThreadPoolExecutor getExecutor(String executorName) {
        return EXECUTOR_MAP.get(executorName);
    }


    public static Collection<String> getAllExecutorNames() {
        return EXECUTOR_MAP.keySet();
    }


    public static Collection<ThreadPoolExecutor> getAllDtpExecutor() {
        return EXECUTOR_MAP.values();
    }

    /**
     * 线程池注册
     *
     * @param executorName 线程池名字
     */
    public static void registry(String executorName, ThreadPoolExecutor executor) {
        //注册
        EXECUTOR_MAP.put(executorName, executor);
    }


    /**
     * 刷新线程池参数
     *
     * @param executorName 线程池名字
     * @param properties   线程池参数
     */
    public static void refresh(String executorName, ThreadPoolProperties properties) {
        ThreadPoolExecutor executor = EXECUTOR_MAP.get(executorName);
        //刷新参数

        executor.setCorePoolSize(properties.getCorePoolSize());
        executor.setMaximumPoolSize(properties.getMaximumPoolSize());
        executor.setKeepAliveTime(properties.getKeepAliveTime(), TimeUnit.SECONDS);
    }
}
