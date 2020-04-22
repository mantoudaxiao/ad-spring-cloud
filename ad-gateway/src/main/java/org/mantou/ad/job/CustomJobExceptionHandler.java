package org.mantou.ad.job;

import com.dangdang.ddframe.job.executor.handler.JobExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomJobExceptionHandler implements JobExceptionHandler {

    @Override
    public void handleException(String jobName, Throwable throwable) {
        log.error("{}任务异常，异常信息为：{}",jobName,throwable.getMessage());
    }
}
