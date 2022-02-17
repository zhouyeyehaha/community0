package com.jc.community.config;

import com.jc.community.quartz.OneJob;
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
}
