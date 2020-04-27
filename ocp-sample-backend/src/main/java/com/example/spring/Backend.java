package com.example.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class Backend extends SpringBootServletInitializer{

    @Autowired
    private ApplicantRepository applicantRepository;

    @RequestMapping("/status")
    String home() {
        return "Spring Boot running on " + System.getenv("HOSTNAME");
    }

    @RequestMapping("/numApplicants")
    long numApplicants() {
        try {
            return applicantRepository.count();
        } catch (Exception ex) {
            System.out.println(ex.getClass().getSimpleName());
            System.out.println(ex.getMessage());
        }
        return -1;
    }

    @RequestMapping("/addApplicants/{num}")
    long addApplicants(@PathVariable("num") int num) {
        for (int i = 0; i < num; i++) {
          	Applicant applicant = new Applicant(RandomNameGenerator.getFirstName(), RandomNameGenerator.getLastName());
            applicantRepository.save(applicant);
          	System.out.println("Created new applicant: "+applicant);
        }
        return numApplicants();
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app){
        return app.sources(Backend.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Backend.class, args);
    }
}
