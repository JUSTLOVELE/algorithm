package com.ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author yangzl 2020.07.28
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置客户端
        clients.inMemory()//使用内存设置
                .withClient("client") //client_id
                .secret(passwordEncoder.encode("secret")) // client_secret
                .authorizedGrantTypes("authorization_code")//授权类型
                .scopes("app") //授权范围
                .redirectUris("https://www.zhihu.com/people/bei-luo-shi-men-5-56"); //注册回调地址
    }
}
