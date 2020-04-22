package org.mantou.ad;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mantou.ad.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestCloud {

    private String token = "eyJhbGciOiJSUzUxMiJ9.eyJqdGkiOiIxIiwic3ViIjoidG9tIiwiZXhwIjoxNTc3ODU3NDk3LCJwYXNzd29yZCI6MTIzLCJhZGRyZXNzIjoi5Lit5Zu955-z5a625bqEIiwiY29tcGFueSI6IumYv-mHjCJ9.LkF3QxaupkZlIJ7qlrqysxq5T2ki9sLQYtuAK8Wewkgoxj_ukpCfCXf7Fxc71K3zcvuhZ1g6ZvDAwa7aBb4yv9Tt4J-UR966CnculaKeybcdGy3dVgU-yjHlV9BlccaIGUCYijtI5RM_L7rBk9B28yv-D3qzKzuda7AXSap1F-k";

    @Test
    public void test1() {
        HashMap<String, Object> objectObjectHashMap = Maps.newHashMap();
        objectObjectHashMap.put("password", 123);
        objectObjectHashMap.put("company", "阿里");
        objectObjectHashMap.put("address", "中国石家庄");

        JWTUtils jwt = JWTUtils.getInstance();
//        String token = jwt.getToken("1","tom",objectObjectHashMap);
//        System.out.println(token);

        JWTUtils.JWTResult jwtResult = jwt.checkToken(token);
        System.out.println(jwtResult);

    }

    @Test
    public void test2() {
        Map<String, String> map = Maps.newHashMap();
        map.put("auth", "Bearer 123321");
        String auth = map.get("auth");
        String bearer_1 = auth.replace("Bearer ", "");
        System.err.println(bearer_1);
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test3(){
        String aaa = "";
        System.out.println(StringUtils.isNotBlank(aaa));
//        Long expire = stringRedisTemplate.getExpire("token:123");
//        System.out.println(expire);
    }
}
