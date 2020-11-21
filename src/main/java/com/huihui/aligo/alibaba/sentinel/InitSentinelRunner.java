package com.huihui.aligo.alibaba.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Sentinel使用方式一：手动编写规则：
 * 项目启动时自动加载sentinel规则
 *
 * @author minghui.y
 * @create 2020-11-21 1:40 下午
 **/
@Component
public class InitSentinelRunner implements ApplicationRunner {
    private static final Logger LOGGER =  LoggerFactory.getLogger(InitSentinelRunner.class);

    private static final String HELLO_SENTINEL_KEY = "helloSentinel";


    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource(HELLO_SENTINEL_KEY);
        rule.setCount(1);
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setLimitApp("default");

        flowRules.add(rule);

        FlowRuleManager.loadRules(flowRules);

        LOGGER.info("Sentinel限流规则加载成功！！！");

    }
}
