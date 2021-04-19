package com.example.kisvakondkerteszbolt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class KisvakondKerteszboltApplication /*implements CommandLineRunner*/ {

    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(KisvakondKerteszboltApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("DATASOURCE = " + dataSource);
//        List<Lakcim> list = userRepository.findAll();
//        System.out.println(list.size());
//        list.forEach(System.out::println);
//
////        exit(0);
//    }
}
