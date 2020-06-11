package com.bracongo.callcenter.securityconfigs;

import com.bracongo.callcenter.service.impl.CustomUserDetailsService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 *
 * @author Valmy Roi Kenfack <vr.kenfack at bracongo.cd>
 */
@Configuration
@EnableWebSecurity
@Order(1000)
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().disable().csrf().disable().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/users/login").permitAll().antMatchers("/users/register").permitAll()
                .antMatchers("/users/test/**").hasAuthority("SUPER_ADMIN").anyRequest().authenticated().and().csrf()
                .disable().exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    http.cors();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/induction/**", "/articles/**", "/articles/{id}", "/induction/**", "/agents", "/visites/**", "/reception/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/receptions/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/receptions/login/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/agents/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/sites/divisions/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/visites/typeid/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/visites/visiteur/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/visites/visiteur/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/induction/visiteur/**");
        web.ignoring().antMatchers(HttpMethod.POST, "/induction/prestataire/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/prestataire/entreprises/**");
       
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized");
    }

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }
    
}
