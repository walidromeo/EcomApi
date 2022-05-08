package com.ecomapi.ws.security;


import com.ecomapi.ws.filter.CostumAuthorizationFilter;
import com.ecomapi.ws.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.POST,"/login").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/upload/image/**").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/products").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/products/single/**").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/products/getProductByCategory").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/products/getProductIdByCategory").permitAll().and().authorizeRequests().antMatchers(HttpMethod.POST,"/commande/add_commande").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/categorie").permitAll().and().authorizeRequests().antMatchers(HttpMethod.GET,"/products/product_category/**").permitAll().and().authorizeRequests().antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html**","/webjars/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CostumAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
