package com.jc.community.config;

import com.jc.community.quartz.OneJob;
import com.jc.community.quartz.PostScoreRefreshJob;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

// 配置 -> 数据库 ->调用
@Configuration
public class QuartzConfig {

//    @Bean
    public JobDetailFactoryBean oneJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(OneJob.class);
        factoryBean.setName("oneJob");
        factoryBean.setGroup("oneJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

//    @Bean
    public SimpleTriggerFactoryBean oneTrigger(JobDetail oneJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(oneJobDetail);
        factoryBean.setName("oneTrigger");
        factoryBean.setGroup("oneTriggerGroup");
        factoryBean.setRepeatInterval(3000);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }

    // 刷新帖子分数的任务
    @Bean
    public JobDetailFactoryBean postScoreRefreshJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(PostScoreRefreshJob.class);
        factoryBean.setName("PostScoreRefreshJob");
        factoryBean.setGroup("communityJobGroup");
        factoryBean.setDurability(true);
        factoryBean.setRequestsRecovery(true);
        return factoryBean;
    }

    @Bean
    public SimpleTriggerFactoryBean postScoreRefreshTrigger(JobDetail postScoreRefreshJobDetail) {
        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(postScoreRefreshJobDetail);
        factoryBean.setName("postScoreRefreshTrigger");
        factoryBean.setGroup("communityTriggerGroup");
        factoryBean.setRepeatInterval(1000 * 60 * 5);
        factoryBean.setJobDataMap(new JobDataMap());
        return factoryBean;
    }


}
