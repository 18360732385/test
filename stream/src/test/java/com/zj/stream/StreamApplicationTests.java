package com.zj.stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zj.stream.constants.MyKey;
import com.zj.stream.pojo.Student;
import com.zj.stream.util.RsaUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.apache.commons.codec.binary.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StreamApplicationTests {
    static String transformation = "RSA";
    static String privateKeyBase64 = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCt/jN0CE5qmlCfXDiQco6KlM/JwNyIqgsnSCEo0T0JRwUa9NEvPtDcHUJHlQ1fMHI0ARm46fzHWm93yFWEZrrTZdUklHN6zkEgPHScJ+SyE+STI9yBv/KzrDimly07tPIFm4xXeNYRP2lhJ4qvj0ilrwai/5CPVHmFJYclQsfdhDK/MzU2ss9R8BYTJHu2grboD9C7YC43RPzhXbmvW74QES8mtLuB6F49t0u3p3P85g6mFdhvQ6HINk3j7O6VMgCaK/KRiqMQ0vFWgSWiflrOUth+r5fKbs2Txo8/XtzoYDYmx3e1lEZlF8pZ372J7XX4fyQqraMDuDMGgy1qBOu9AgMBAAECggEANS1iEG6ReOddCLeo2g0JH7XHpAP74xEFg6X57H22x+Ua3FhCrsvmDcArh7E4ewVVgitr7yYkidFMDDIAYQz8wKt470sn2Dq9kNqpUSioLv0BeS8Wr9k4lk1mqxO9UWhGKAyYYguecXsN/BGXw3xpWaoavyqYxhWtZlJH8c1tRHgnzcOOGMgEy/jC5S7MM+NVgWWTswzZkpTJennT5YfcAt96D4wl3WEBOcYwnzYfHfA2+YMVHjvIz1Z1pfD8RVSS4w+6bL1Y5VTwDyknVm6E9DhkhvhPqEdaEl7gxaK31GkWjFUpScYF3KPCRAhWHf5zTPLZuxeG83cBQgd5EyQDCQKBgQDfDnCDerL9JBZ7NJKM/KADP6HTABv9XlxuW10VGoZjMAZgVW/LGQ5eff5cKL3mv31MCCX+liXlvRcyCLCgkKG7fEuSwvBHBIqFFLV9RNkwAk+6Juk9XspueRBgrqf9lc8GP/k8mlC0inN0SaNyB0HeZB0sKyuLqiEjblnVj7f9YwKBgQDHsLe0XOiQtz/kQfUrtRyHrrrDrEe8ZUnX+LTflfyfoG0A+13ehcAsjG15AsyjJTyG4gHC+qrFOzZbeWcrP/vOGzXO0Lo9Vvnnvn7QHpYehJNhBAy3Z//aKWXAFMxGOCZ0dyFjRwjIV9J9LOhzaG/P+e/t8/1FtMTiPBrI09HMXwKBgQCt2E0szAaQcP3sjG7j30i+/2saTm2CLXcQs7h6aC/xQk7V6gC1ZBPk5YHHRiGro/b9uSQTpeZnhdZ8MnWmztKekyM7gR9P2mQzkI5a0bwMRODqjsUeBbD0JPr0v2tCou21S0rvRr471+Rf77ypdKqLr69k60iHE9O8D5eIcPjPTQKBgG5hTopSOPOVd6myk9T/HbAac34pwtVun6zT+xcED0olIVvDlpWkFSAK15BmbbtYn+ZnE1Y1vSOcoWVa7B5KFeRNwiZ51hiF9UmWUP61iYGBAv5DgMv4nsJ205mjZW8DJq6GE1yPLVmgwecTZcI1Xmj13RwJHFzlmVb8pkmQXI29AoGAZT55Fx1cdwZoazuudA+/0oHVtn+DVPI1HZKyx7ZYVKUQzADJfm7GDDKh4iZtJKaTUxs/mz0fFb5nEX9J6XHSiW5mi97llB+5YBqPyjVagTc+37YdAqYrt+orOmmJVnK2o3G4I/7pbjO3uRHTcZhBRADAKr7vQEtCpwc7ChYoVhA=";
    String publicKeyBase64 = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCNzRJfYOom5gktTkaTZshIPSm5N+zRGSVr6eWdLaTueFuwSHbO/5egT6HdqtIXTTxD4J1QpUsi7Oq8j3WC7CajxrZnyHdmE1h8HselMuiL5N1FD3k1VS/vxjYPtYJW7FaypgjhgijFwWl+szyBtvli3wvS1rBYFRue9PeTL7RsSQIDAQAB";

    @Autowired
    //@Qualifier("myKey")
    private MyKey myKey;

    @Test
    public void propsTest() throws JsonProcessingException {
        System.out.println("私钥: " + myKey.getPrivateKeyBase64());
        System.out.println("公钥: " + myKey.getPublicKeyBase64());
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

    @Test
    public void testD() throws Exception {
        String str = "BmSF37qGRRa9LK+/IE1qn02IZm6WI6ZYbTAUXE+pQ9npoPulFo602kRp+jqA5fkzarSXxsI2gk6eMU/IHL/0p+gh+XkjB/GyqRR3qOCQO0ho+6InQC5F117BG1YHKnfUware+OGnZ1X7ohWHDeGAIGmstIGSEBZSRA6x0J6L9xx7fB7+wIMQQ1J8vUD2Vp+90WaIpwdAeiI1jINT9JVbeiaBnVLXF3ezhjv4hPJPyYB++3TMXuJGsysdBWCW+jQVh9iUaMbxZoYQhglcIai1LyL4ZZKNbLbotOqzTOVUjWsOb2W6u8a15MSWg4xhhpR2Jpqv41h9u7dTDHRi/Fo3RzOtDx46ctr2phs3bKpewX+yOGGETa0kAa1mUT03gN/ecDbqvLYbV0ZVirIu7hofkRrpW1ScAauPUcSD6KZ0mlNMm7XkHSA8y5ApKQHS4wJmLHpiYbFubTFRQcfQkxnvnlb5h0kqxw99CE9HAAeN1wWF+tIgYOjGCJMBaBSl26Ea+V1vMEQ7sGT/eEPOO8dPKuVrrg8+BYI0pxOf52nbCKSl6lyddM3Gkvq0SyTCV5yHzTKLUfYSLC0pVNfmxk2EDAoAKhs/s8WnhYbWuJDIZ/LMjbyz/2t5nIghHN1LsiG1LsXEFUeC0ovYBtGzMtxv/3MoFKvItTdf5mlflJT5bqFK9dZ1+IEi/THXMnIncQ4h0+UMS7UkacYX9paPt6e2STVC95WGxgzOg77DiXOdzaG/RnwLBCDZbGSJhPjpq7gHkp27QeWE0kMifeFzyoi1tmLi4Ref8bB7xj0DtEQIix0je+JBBKyABttpaA2X6GSNQBxB/HZgeDPm5GhcfHfOcBN1ZantJcLVUN8Edkn7Q+oymN8r+LxLESrePX1QgsxQpmJ3IJdmPxBlZGOAbcjuzQI4UDN00R6WKeDgdUPIWMdq8Wm7IBMdJ6iZ1++FgGUcNyRDXJeMYu2T6tSipnPiPKn7Ezf0lHDTAohYMVcpO/GsZ9q4uWGLP8YkxWJFrEE+jMV/+GgvNghdgsJ4jU6KoptdWsOI6RZw88El38wCAlQHYVEN41wOMIEiuJpDfvYohJD5+bjVoJzFUOOhjEKtyH/YJAFMD02bUef7pTlW866QbyZqdFQ6aJPZRxtkeIzteBgUQnHlDen93sp+JVj13uhK/UaiR4o6bA6nqRMLQkcrWe37s50XJCms8tuvh/YTQduP4G1jT2enPa7xmvUu1bQQz7XgSEhBrWwLQKPHeT6++xdl3ToRtm44g4aF3xtgJ3l2uA/BXXqpZ8P8rlC1a0p9C1TOaQ4JT/uurmgitzOjJnkhh7efaA+WehNEuDV8O1IOYFq0fpO6zzfSUxPkR3o2Wml67UWffgtJeNg1QSKJwANQAEsLD3rvFc6aHeYwANiMKOWtA0ia07+Qhqr5Ub35Y0XJbZu9ftJM8i7Zz1TfJR7UGTl8qtgvftfT/B2EX0p8ibKVyGHk3UCnHXBWzj4jjpBO4G82Jpjjr2wAbo4En3IyHbt/e4BOli1T2JXW6nKRhDLecGDbtV+xH9x9CVi0+NMkkl5bOiUfISK2aF6hQFp9BajJUv2ha/TNRoEps3B0aAytCSHcA6ZMcFvfbewVy+82uXrX5ilxpdfkXhetYG01iDmR5i4TvbAH2vkZhdtz8CABaQdyfMUEoTJulrAtZ+gU5YIOCT96HQAEnlE=";
        String s = RsaUtil.decryptByPrivateKey(str, Base64.decodeBase64(privateKeyBase64), transformation);
        System.out.println("解密报文："+s);
    }

}
