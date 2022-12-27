package com.fis.sdc.checkfile;

import com.fis.sdc.checkfile.MODEL.Account;
import com.fis.sdc.checkfile.SERVICE.AccountService;
import com.fis.sdc.checkfile.SERVICE.Impl.AccounDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import javax.sql.DataSource;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class    SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountService dao;
    @Autowired
    private AccounDetail service;
    @Autowired
    private DataSource dataSource;
    @Autowired
    BCryptPasswordEncoder pe;
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(username -> {
            System.out.println(username);
            try {
                Account user = dao.findOne(username);
                String password = passwordEncoder().encode(user.getPass());
                String[] roles = user.getRoleaccs().stream().map(au -> au.getRole().getIdrole())
                        .collect(Collectors.toList()).toArray(new String[0]);
                return User.withUsername(username).password(user.getPass()).roles(roles).build();
            }catch(NoSuchElementException e) {
                throw new UsernameNotFoundException(username + "not found!");
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests().antMatchers( "/login", "/logout").permitAll();

        http.authorizeRequests().antMatchers("/admin","/**").hasAnyRole("admin", "user").anyRequest()
                .permitAll();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");


        http.formLogin().defaultSuccessUrl("/admin",true);
        http.authorizeRequests().and().formLogin()//
                .defaultSuccessUrl("/admin")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//        http.authorizeRequests().and() //
//                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
//                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
    }



//    @Bean
//    public PersistentTokenRepository persistentTokenRepository() {
//        JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//        db.setDataSource(this.dataSource);
//        return db;
//    }
}
