package org.mantou.ad.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class CustomSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.err.println("当当的定时任务。。。。");
    }
}
