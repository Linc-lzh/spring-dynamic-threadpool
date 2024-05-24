package spring.dtp.domain;

import spring.dtp.properties.ThreadPoolProperties;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DtpThreadPoolExecutor extends ThreadPoolExecutor {
    public DtpThreadPoolExecutor(ThreadPoolProperties executorProp) {
        super(
                 executorProp.getCorePoolSize(),
                 executorProp.getMaximumPoolSize(),
                 executorProp.getKeepAliveTime(),
                 executorProp.getTimeUnit(),
                 // 这里的参数我随意写一下， 实际中可以根据配置动态创建
                 new ArrayBlockingQueue<>(executorProp.getQueueSize())
         );
    }

    public DtpThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long
            keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}
