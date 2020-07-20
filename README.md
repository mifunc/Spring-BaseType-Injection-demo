- 原生类型（Primitive ）: boolean、byte、char、short、int 、float、long、double
- 标量类型（Scalar ）: Number、Character、Boolean、Enum、Locale、Charset、Currency、Properties、UUID
- 常规类型（General ）: Object、String、Timezone、Calendar、Optional Spring 类型：Resource、Inputsource、Formatter


## 部分注入案例

- String字符串注入
- Int类型注入
- 数组注入
- List注入
- Map注入
- 枚举注入
- Optional注入
- Resource注入


**配置文件application.properties**

```
#String字符串注入
rumen.z=11

#int类型注入
rumenz.i=123
#数组类型注入
rumenz.arr=1,2,3,4
#List类型注入
rumenz.list=1,2,3,4,5
#Map类型注入
rumenz.map={"code":200,"msg":"ok","data":"123"}
#枚举类型注入
rumenz.enum=RED
#Resource类型注入
rumenz.resource=test.txt

```

**需要注入属性的实体类Db.java**

```java
package com.rumenz;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


import java.util.List;
import java.util.Map;
import java.util.Optional;

@Configuration
public class Db {

    //String字符串注入
    @Value("${rumen.z}")
    private  String one;
    //int类型注入
    @Value("${rumenz.i}")
    private Integer intVal;
    //数组类型注入
    @Value("${rumenz.arr}")
    private int[] arr;
    //List类型注入
    @Value("#{'${rumenz.list}'.split(',')}")
    private List<Integer> list;
    //Map类型注入
    @Value("#{${rumenz.map}}")
    private Map<String, String> map;
    //枚举类型注入
    @Value("${rumenz.enum}")
    private RumenzEnum enum1;
    //Resource类型注入
    @Value("${rumenz.resource}")
    private Resource resource;
    //Optional类型注入
    @Autowired
    private Optional<Rumenz> opt;


    public Integer getIntVal() {
        return intVal;
    }

    public void setIntVal(Integer intVal) {
        this.intVal = intVal;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }



    public Optional<Rumenz> getOpt() {
        return opt;
    }

    public void setOpt(Optional<Rumenz> opt) {
        this.opt = opt;
    }

    public RumenzEnum getEnum1() {
        return enum1;
    }

    public void setEnum1(RumenzEnum enum1) {
        this.enum1 = enum1;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public int[] getArr() {
        return arr;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    @Override
    public String toString() {
        return "Db{" +
                "one='" + one + '\'' +
                '}';
    }
}

```

**调用案例**

```java
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
```


**输出**

```
字符串11
int123
数组[1, 2, 3, 4]
List[1, 2, 3, 4, 5]
Map{code=200, msg=ok, data=123}
枚举RED
Optional入门小站
resourceclass path resource [test.txt]
```

源码:https://github.com/mifunc/Spring-BaseType-Injection-demo


原文: [https://rumenz.com/rumenbiji/Spring-BaseType-Injection-demo.html](https://rumenz.com/rumenbiji/Spring-BaseType-Injection-demo.html)
