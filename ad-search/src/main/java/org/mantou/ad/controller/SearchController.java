package org.mantou.ad.controller;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.mantou.ad.common.RespWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/search")
public class SearchController {



    @RequestMapping("/hello")
    public String search(){


        CacheBuilder.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        System.err.println("zhi--->"+s);
                        return null;
                    }
                });

        return "hello search!!!";
    }


}
