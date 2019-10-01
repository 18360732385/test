package com.zj.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zj.stream.constants.SecretKey;
import com.zj.stream.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApplicationTests {
    static String transformation = "RSA";

    @Autowired
    private SecretKey secretKey;

    @Test
    public void propsTest() throws JsonProcessingException {
        System.out.println("私钥: " + secretKey.privateKeyBase64);
        System.out.println("公钥: " + secretKey.publicKeyBase64);
    }

    @Test
    public void contextLoads() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(1L, "王一博", 16, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 18, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        //testMap(students);
        //testFilter(students);
        //testDistinct1();
        //testSort2(students);
        testReduce();
    }

    /**
     * 集合转换
     * @param students
     * @return
     */
    private static void testMap(List<Student> students) {
        //在地址前面加上部分信息，只获取地址输出
        List<String> addresses = students.stream().map(s -> "住址:" + s.getAddress()).collect(Collectors.toList());
        addresses.forEach(a ->System.out.println(a));
    }

    /**
     * 集合的筛选
     * @param students
     * @return
     */
    private static void testFilter(List<Student> students) {
        //筛选年龄大于15岁的学生
        List<Student> collect = students.stream().filter(s -> s.getAge()>=15).collect(Collectors.toList());
        //筛选住在浙江省的学生
        //List<Student> collect = students.stream().filter(s -> "浙江".equals(s.getAddress())).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 集合去重（基本类型）
     */
    private static void testDistinct1() {
        //简单字符串的去重
        List<String> list = Arrays.asList("111","222","333","111","222");
        list.stream().distinct().forEach(c-> System.out.println(c));
    }

    private static void testSort2(List<Student> students) {
        students.stream()
                .sorted((s2,s1)->Long.compare(s2.getId(),s1.getId()))
                .sorted((s1,s2)->Integer.compare(s2.getAge(),s1.getAge()))
                .forEach(System.out::println);
    }

    private static void testReduce() {
        String str = "无锡";
        List<String> list = Arrays.asList("欢","迎","你");
        String appendStr = list.stream().reduce(str,(a,b) -> a+b);
        System.out.println(appendStr);
    }


}
