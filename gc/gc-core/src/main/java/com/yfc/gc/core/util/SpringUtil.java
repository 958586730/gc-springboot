package com.yfc.gc.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Component
public class SpringUtil implements ApplicationContextAware
        , BeanFactoryAware
        , EnvironmentAware {

    /*
     * spring上下文
     */
    private static ApplicationContext applicationContext;

    /*
     * bean工厂
     */
    private static BeanFactory beanFactory;

    /*
     * 配置文件信息
     */
    private static Environment environment;

    public static ApplicationContext getApplicationContext() {
        Assert.condition(null == applicationContext, "spring未启动完成");
        return applicationContext;
    }

    public static BeanFactory getBeanFactory() {
        Assert.condition(null == beanFactory, "spring未启动完成");
        return beanFactory;
    }

    public static Environment getEnvironment() {
        Assert.condition(null == environment, "spring未启动完成");
        return environment;
    }

    /**
     * 获得spring容器管理的bean
     *
     * @param @param  clazz
     * @param @return 设定文件
     * @return T    返回类型
     * @throws
     * @Title: getBean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 获得spring容器管理的bean
     *
     * @param @param  clazz
     * @param @return 设定文件
     * @return T    返回类型
     * @throws
     * @Title: getBean
     */
    public static <T> T getBean(Class<T> clazz, Object... args) {
        return getApplicationContext().getBean(clazz, args);
    }

    /**
     * 获得多个bean对象
     *
     * @param clazz
     * @return
     */
    public static <T> List<T> getBeans(Class<T> clazz) {
        return new ArrayList<>(getApplicationContext().getBeansOfType(clazz).values());
    }

    /**
     * 获得spring容器管理的bean
     *
     * @param @param  name
     * @param @param  clazz
     * @param @return 设定文件
     * @return T    返回类型
     * @throws
     * @Title: getBean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name, Class<T> clazz) {
        return (T) getApplicationContext().getBean(name);
    }

    /**
     * 获得配置信息
     * @param key
     * @param map
     * @param <R>
     * @return
     */
    public static <R> R get(String key, Function<Optional<String>, R> map){
        return map.apply(Optional.ofNullable(getEnvironment().getProperty(key)));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }

    public static void register(String beanName, BeanDefinition beanDefinition) {
        BeanFactory beanFactory = getBeanFactory();
        if (beanFactory instanceof DefaultListableBeanFactory) {
            ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(beanName, beanDefinition);
            log.debug("向beanFactory添加'{}':{}", beanName, beanDefinition);
        } else {
            log.error("beanFactory 类型为 {}", beanFactory.getClass().getName());
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }
}
