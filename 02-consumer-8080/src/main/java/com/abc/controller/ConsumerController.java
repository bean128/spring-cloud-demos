package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lixiaopeng
 * @date: 2019/10/15 17:16
 * @desc:
 */
@RequestMapping("/consumer")
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_PROVIDER = "http://msc-provider";

    @GetMapping("/get/{id}")
    public String getName(@PathVariable("id") String id) {
        String url = SERVICE_PROVIDER + "/provider/get/" + id;
        return restTemplate.getForObject(url, String.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteName(@PathVariable("id") String id) {
        String url = SERVICE_PROVIDER + "/provider/delete/" + id;
        restTemplate.delete(url);
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        String url = SERVICE_PROVIDER + "/provider/discovery";
        return restTemplate.getForObject(url, Object.class);
    }
}
