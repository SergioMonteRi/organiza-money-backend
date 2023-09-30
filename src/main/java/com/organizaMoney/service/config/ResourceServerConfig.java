package com.organizaMoney.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${cors.origins}")
    private String corsOrigins;

    @Autowired
    private Environment env;
    private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**","/v3/**","/swagger-ui/**"};
    private static final String[] DRIVER_OR_ADMIN_COMPANY_OR_PASSENGER = {"/rides/**", "/companies/**", "/fowards/**","/expenses/**"};
    private static final String[] ADMIN = {"/companies/**","/users/driver","/fees/**"};
    private static final String[] DRIVER = {"/rides/**", "/fowards/**","/expenses/**"};
    private static final String[] OPERATOR = {"/users/driver","/fees/**","/fowards/**","/expenses/**"};
    private static final String[] REGISTER_NEW_USER = {"/users/**"};

    @Override
    public void configure(HttpSecurity http) throws Exception {

        // H2 frames
        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, DRIVER_OR_ADMIN_COMPANY_OR_PASSENGER).permitAll()
                .antMatchers(HttpMethod.POST, REGISTER_NEW_USER).permitAll()
                .antMatchers(DRIVER_OR_ADMIN_COMPANY_OR_PASSENGER).hasAnyRole("DRIVER","ADMIN","COMPANY","PASSENGER","OPERATOR")
                .antMatchers(ADMIN).hasAnyRole("ADMIN")
                .antMatchers(OPERATOR).hasAnyRole("OPERATOR","ADMIN","DRIVER")
                .anyRequest().authenticated();

        http.cors().configurationSource(corsConfigurationSource());
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        String[] origins = corsOrigins.split(",");
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList(origins));
        corsConfig.setAllowedMethods(Arrays.asList("*"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setAllowedHeaders(Arrays.asList("*"));


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }

    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> bean
                = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
