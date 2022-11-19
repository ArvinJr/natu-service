package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author 唐僧
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NatuApplication {
    public static void main(String[] args) {
        SpringApplication.run(NatuApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  那兔网启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
