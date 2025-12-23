package com.javadeveloper.order_service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "USER-SERVICE", path = "/users-details")
public interface userDetailsInterface {

    @GetMapping("/user/{userName}")
    public UserDetails getUserByUserName(@RequestParam String userName);
}
