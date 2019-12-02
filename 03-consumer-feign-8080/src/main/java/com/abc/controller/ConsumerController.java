package com.abc.controller;

import com.abc.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: lixiaopeng
 * @date: 2019/10/15 17:16
 * @desc:
 */
@RequestMapping("/consumer")
@RestController
public class ConsumerController {

    @Autowired
    private SomeService someService;

    @GetMapping("/get/{id}")
    public String getName(@PathVariable("id") String id) {
        return someService.getN(id);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteName(@PathVariable("id") String id) {
        return someService.del(id);
    }

}
