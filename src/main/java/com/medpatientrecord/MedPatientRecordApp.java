package com.medpatientrecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
@Configuration
@ComponentScan({"com.medpatientrecord"})
@EnableScheduling
@EnableJpaRepositories("com.medpatientrecord.")
public class MedPatientRecordApp{  
	public static void main(String[] args) {
		SpringApplication.run(MedPatientRecordApp.class, args);
    }       
        
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
//    return builder.sources(MedPatientRecordApp.class);
//    }
}     
