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
