package com.huyu.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@ConfigurationProperties("spring.cloud.gateway")
public class GatewayConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                //博客相关
                .route("blog-module", r -> r.path("/api/blog/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb:http://blog-module"))
                //登录验证相关
                .route("authorization-module", r -> r.path("/api/authorization/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb:http://authorization-module"))
                //用户相关
                .route("user-module", r -> r.path("/api/user/**")
                        .filters(f -> f.rewritePath("/api/(?<segment>.*)", "/$\\{segment}"))
                        .uri("lb:http://user-module"))
                .build();
    }

}
