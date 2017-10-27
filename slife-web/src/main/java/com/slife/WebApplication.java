package com.slife;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan(value = {"com.slife.dao"})  //@Mapper  在mapper 接口上加入也行
@SpringBootApplication
//@EnableSwagger2Doc
public class WebApplication {


	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
//