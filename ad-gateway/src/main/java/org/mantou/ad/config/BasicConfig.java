package org.mantou.ad.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class BasicConfig {

    @Value("${api.whiteUrl}")
    private String whiteUrl;


}
