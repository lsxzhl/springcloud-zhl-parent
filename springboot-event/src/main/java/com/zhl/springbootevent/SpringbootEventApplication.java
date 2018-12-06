package com.zhl.springbootevent;

import com.zhl.springbootevent.configevent.MyApplicationStartingEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@Slf4j
@ServletComponentScan
public class SpringbootEventApplication {

    public static void main(String[] args) {
        //SpringApplication.run(SpringbootEventApplication.class, args);
        SpringApplication app = new SpringApplication(SpringbootEventApplication.class);
        app.addListeners(new MyApplicationStartingEventListener());
        app.run(args);
        log.info("spring-boot-event-listener-chapter32启动!");
    }
}
