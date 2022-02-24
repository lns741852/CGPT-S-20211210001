package com.htpe.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * 全局事務管理
 *
 * @author yanglei
 * @date 2020/8/3
 */
@Aspect
@Configuration
public class TransactionalAopConfig {

    /**
     * 配置方法過期時間，默認-1 永不超時
     */
    private final static int METHOD_TIME_OUT = 5000;

    /**
     * 配置切入點表達式
     */
     private static final String POINTCUT_EXPRESSION = "execution(* com.htpe.service..*.*(..))";

    /**
     * 事務管理器
     */
    @Resource
    private PlatformTransactionManager transactionManager;


	@Bean
    public TransactionInterceptor txAdvice() {
        /*事務管理規則配置類**/
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只讀事務，不做更新操作*/
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);	//不使用事務
        /*事務配置規則*/
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        /*異常回滾*/
        required.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        /*默認事務傳播性 1*/
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*超時時間，回滾*/
        required.setTimeout(METHOD_TIME_OUT);
        Map<String, TransactionAttribute> attributesMap = new HashMap<>(30);
        //使用事務方法
        attributesMap.put("save*", required);
        attributesMap.put("remove*", required);
        attributesMap.put("update*", required);
        attributesMap.put("batch*", required);
        attributesMap.put("clear*", required);
        attributesMap.put("add*", required);
        attributesMap.put("append*", required);
        attributesMap.put("modify*", required);
        attributesMap.put("edit*", required);
        attributesMap.put("insert*", required);
        attributesMap.put("delete*", required);
        attributesMap.put("do*", required);
        attributesMap.put("create*", required);
        attributesMap.put("import*", required);
        //不執行事務方法
        attributesMap.put("select*", readOnly);
        attributesMap.put("get*", readOnly);
        attributesMap.put("valid*", readOnly);
        attributesMap.put("list*", readOnly);
        attributesMap.put("count*", readOnly);
        attributesMap.put("find*", readOnly);
        attributesMap.put("load*", readOnly);
        attributesMap.put("search*", readOnly);
        source.setNameMap(attributesMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 設置切面=切點pointcut+通知TxAdvice
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        /* 切面配置類*/
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        /*切入點設置*/
        pointcut.setExpression(POINTCUT_EXPRESSION);
        /*回傳切面*/
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

}
