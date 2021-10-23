package com.belolipetsckiy.spring.rest;

import com.belolipetsckiy.spring.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final RestTemplate restTemplate;

    private final String URL = "http://91.241.64.178:7081/api/users";
    private String cookies;

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<User>>() {});

        cookies = responseEntity.getHeaders().get("Set-Cookie").stream().collect(Collectors.joining(";"));
        System.out.println(cookies);
        List<User> list = responseEntity.getBody();
        return list;
    }

    public String saveUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie",cookies);
        HttpEntity<User> newUser = new HttpEntity<>(user, headers);
        HttpEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, newUser, String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie",cookies);
        HttpEntity<User> upUser = new HttpEntity<>(user, headers);
        HttpEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, upUser, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(int id){
        String resourceUrl = "http://91.241.64.178:7081/api/users/" + id;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie",cookies);
        HttpEntity<User> delUser = new HttpEntity<>(headers);
        HttpEntity<String> responseEntity = restTemplate.exchange(resourceUrl, HttpMethod.DELETE, delUser, String.class);
        return responseEntity.getBody();
    }
}