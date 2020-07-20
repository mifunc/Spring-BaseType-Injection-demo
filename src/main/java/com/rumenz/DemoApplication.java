package com.rumenz;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.beans.factory.xml.XmlReaderContext;
import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.UUID;


@PropertySource("classpath:application.properties") //1
public class DemoApplication  {

    @Autowired
    private static UUID uuid;

    @Autowired
    private static  Environment env;

   
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext();
        XmlBeanDefinitionReader xr=new XmlBeanDefinitionReader(ac);
        xr.loadBeanDefinitions("Beans.xml");

        ac.register(DemoApplication.class);
        ac.refresh();
        Db bean = ac.getBean(Db.class);
        System.out.println("字符串"+bean.getOne());
        System.out.println("int"+bean.getIntVal());
        System.out.println("数组"+Arrays.toString(bean.getArr()));
        System.out.println("List"+Arrays.toString(bean.getList().toArray()));
        System.out.println("Map"+bean.getMap().toString());
        System.out.println("枚举"+bean.getEnum1());
        System.out.println("Optional"+bean.getOpt().get().getName());
        System.out.println("resource"+bean.getResource());
        ac.close();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        //2
        //propertySourcesPlaceholderConfigurer.setLocations(new ClassPathResource("application.properties"));
        return propertySourcesPlaceholderConfigurer;
    }


}