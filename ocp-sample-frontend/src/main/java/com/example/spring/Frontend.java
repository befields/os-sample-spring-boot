package com.example.spring;

import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@RestController
@EnableAutoConfiguration
public class Frontend extends SpringBootServletInitializer{

    @RequestMapping("/status")
    String frontend() {
        return "Spring Boot running on " + System.getenv("HOSTNAME");
    }

    String getBackendServerHostname() {
        String host = System.getenv("BACKEND_HOSTNAME");
        if (host == null) {
            host = "localhost";
            System.out.println("WARNING: The environment variable 'BACKEND_HOSTNAME' is not defined, using '"+host+"'");
        }
        return host;
    }

    String getBackendServerPort() {
        String port = System.getenv("BACKEND_PORT");
        if (port == null) {
            port = "8081";
            System.out.println("WARNING: The environment variable 'BACKEND_PORT' is not defined, using '"+port+"'");
        }
        return port;
    }

    @RequestMapping("/backend/status")
    String callBackend() {
        final String uri = "http://" + getBackendServerHostname() + ":" + getBackendServerPort() + "/status";
        String backendResult;
        RestTemplate restTemplate = new RestTemplate();
        try {
            backendResult = restTemplate.getForObject(uri, String.class);
        } catch (RestClientException ex) {
            ex.printStackTrace();
            backendResult = ex.getMessage();
        }
        return backendResult;
    }

    @RequestMapping("/backend/numApplicants")
    String callBackendNumApplicants() {
        final String uri = "http://" + getBackendServerHostname() + ":" + getBackendServerPort() + "/numApplicants";
        String backendResult;
        RestTemplate restTemplate = new RestTemplate();
        try {
            backendResult = restTemplate.getForObject(uri, String.class) + " entries";
        } catch (RestClientException ex) {
            ex.printStackTrace();
            backendResult = ex.getMessage();
        }
        return backendResult;
    }

    @RequestMapping("/backend/addApplicants/{num}")
    String callBackendAddApplicants(@PathVariable("num") int num) {
        final String uri = "http://" + getBackendServerHostname() + ":" + getBackendServerPort() + "/addApplicants/" + num;
        String backendResult;
        RestTemplate restTemplate = new RestTemplate();
        try {
            backendResult = restTemplate.getForObject(uri, String.class) + " entries";
        } catch (RestClientException ex) {
            ex.printStackTrace();
            backendResult = ex.getMessage();
        }
        return backendResult;
    }

    @RequestMapping("/env")
    String env() {
        StringBuilder sb = new StringBuilder();
        Map<String, String> map = System.getenv();
        for (Map.Entry<String, String> entrySet : map.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            sb.append(key).append("=").append(value).append("<br><br>");
        }
        return sb.toString().trim();
    }
  
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder app){
        return app.sources(Frontend.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Frontend.class, args);
    }
}
