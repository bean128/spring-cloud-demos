package com.abc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: lixiaopeng
 * @date: 2019/10/22 14:05
 * @desc:
 */
@FeignClient("msc-provider")
@RequestMapping("/provider")
public interface SomeService {
    @DeleteMapping("/delete/{id}")
    boolean del(@PathVariable("id") String id);

    @GetMapping("/get/{id}")
    String getN(@PathVariable("id") String id);
}
