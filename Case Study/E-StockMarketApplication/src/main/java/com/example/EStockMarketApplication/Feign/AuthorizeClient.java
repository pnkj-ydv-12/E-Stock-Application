package com.example.EStockMarketApplication.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name="authorization-microservice",value = "authorization-microservice",url="http://localhost:8080")
public interface AuthorizeClient {
    @PostMapping("/authorize")
    public boolean authorize(@RequestHeader("Authorization")String token);

    @PostMapping("/getrole")
    public String getrole(@RequestHeader("Authorization")String token);


}
