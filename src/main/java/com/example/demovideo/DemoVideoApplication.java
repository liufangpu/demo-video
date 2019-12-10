package com.example.demovideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.util.List;

/**
 * @author liufa
 */
@SpringBootApplication
@Controller
public class DemoVideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoVideoApplication.class, args);
    }


    /**
     * @Description: 递归遍历指定目录下的所有文件
     * @param file：即代表一个文件，也代表一个文件目录
     * @param map：存储文件名的Map集合
     */
    public void listFile(File file, List<String> map) {
// 如果file代表的不是一个文件，而是一个目录
        if (!file.isFile()) {
// 列出该目录下的所有文件和目录
            File files[] = file.listFiles();
// 遍历files[]数组
            for (File f : files) {
// 递归
                listFile(f, map);
            }
        } else {
            map.add(file.getName());
        }
    }
}
