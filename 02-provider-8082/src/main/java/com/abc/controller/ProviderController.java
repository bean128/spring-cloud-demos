package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: lixiaopeng
 * @date: 2019/10/15 17:16
 * @desc:
 */
@RequestMapping("/provider")
@RestController
public class ProviderController {

    private Map<String, String> users;

    @Autowired
    private DiscoveryClient client;

    public ProviderController() {
        users = new HashMap<>();
        users.put("1", "张三");
        users.put("2", "李四");
        users.put("3", "王五");
        users.put("4", "刘六");
    }

    @GetMapping("/get/{id}")
    public String getName(@PathVariable("id") String id) {
        return users.get(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteName(@PathVariable("id") String id) {
        users.remove(id);
        return true;
    }

    @GetMapping("/discovery")
    public Object discoveryHandle() {
        // 获取注册中心的所有微服务名称
        List<String> services = client.getServices();
        for (String serviceId: services) {
            // 获取指定名称的所有服务提供者主机
            List<ServiceInstance> instances = client.getInstances(serviceId);
            for (ServiceInstance instance: instances) {
                System.out.println("host:"+instance.getHost());
                System.out.println("port:"+instance.getPort());
                System.out.println("instanceId:"+instance.getInstanceId());
            }
        }
        return services;
    }
}
