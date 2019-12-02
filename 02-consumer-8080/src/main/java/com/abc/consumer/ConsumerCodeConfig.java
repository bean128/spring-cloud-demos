package com.abc.consumer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: lixiaopeng
 * @date: 2019/10/15 17:35
 * @desc:
 */
@Configuration
public class ConsumerCodeConfig {

    // 指定使用负载均衡方式访问（默认轮询）
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
