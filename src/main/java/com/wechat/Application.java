package com.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.wechat.token.ValidTokenServlet;

//开启事物管理
@EnableTransactionManagement 
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.wechat.dao")
public class Application extends SpringBootServletInitializer{
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	    // 注意这里要指向原先用main方法执行的Application启动类
	    return builder.sources(Application.class);
	  }
	
	@Bean   //将servlet注入ioc,name值默认为类名首字母小写  
    public ServletRegistrationBean servletRegistrationBean() {  
        //匹配请求localhost:8080/token  
        return new ServletRegistrationBean(new ValidTokenServlet(), "/token"); 
      }  
	
	 public static void main(String[] args) {
		    SpringApplication.run(Application.class, args);
	  }
	 
}
