package com.jay.cn.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public interface TestApi {

    @GetMapping("/api/hello")
    public String testHello();

    @GetMapping("/api/hello2")
    public String testHello2(@RequestParam("id")String id);

    @GetMapping("/api/hello3")
    public Map testHello3(@RequestParam("id")String id,@RequestParam("name")String name);

    @GetMapping("/api/hello4")
    public Map testHello4(@RequestParam Map map);

    @PostMapping("/api/hello5")
    public Map testHello5(@RequestBody Map map);
}
