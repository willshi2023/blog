package cn.virtualspider.blog;

import javax.servlet.MultipartConfigElement;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan(basePackages="cn.virtualspider.blog.dao")
public class BlogApplication {
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}
	
	@Value("${custom.tmpLocation}")
	private String tmpLocation;
	
	/**
	 * 配置可访问的临时目录，解决file.transferTo访问不到文件的问题
	 * @return
	 */
	@Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setLocation(tmpLocation);
        return factory.createMultipartConfig();
    }
}
