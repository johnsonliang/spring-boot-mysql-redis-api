package com.test.springboot.common.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/*
 * 类描述：开启mybatis的支持
 */
@Configuration
@MapperScan("com.test.springboot.*.dao")
public class MyBatisConfig {

}
