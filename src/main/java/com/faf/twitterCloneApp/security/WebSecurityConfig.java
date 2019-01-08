package com.faf.twitterCloneApp.security;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private DataSource dataSource;


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder() );
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select username , password , enabled from twitter_user where username=?")
                .authoritiesByUsernameQuery("select username , role from twitter_user inner join authority on twitter_user.id = authority.twitter_user_id where username=?");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/*").permitAll();

        http
                .authorizeRequests()
                .antMatchers("/twitterUser/userDetails*").hasRole("USER")
                .antMatchers("/twitterUser/userTweets*").hasRole("USER")
                .antMatchers("/twitterUser/followUser*").hasRole("USER")
                .antMatchers("/twitterUser/homePage*").hasRole("USER")
                .antMatchers("/twitterUser/profilePage*").hasRole("USER")
                .antMatchers("/tweetFollow/tweetFollow*").hasRole("USER")
                .antMatchers("/sendMail").hasRole("USER")
                .antMatchers("/**").permitAll()
                //.antMatchers("/public/**").permitAll()
                //.antMatchers("/tweet/tweetFrom").permitAll()
                .and().formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/loginPage")
                .defaultSuccessUrl("/")
                .failureUrl("/loginPage?error")
                .permitAll()
                .and().logout()
                .logoutUrl("/logoutPage")
                .logoutSuccessUrl("/loginPage?logout")
                .permitAll()
                .and().httpBasic();



//        http
//                .authorizeRequests()
//                .antMatchers("/userdetails*").hasRole("USER")
//                .antMatchers("/usertwitts*").hasRole("USER")
//                .antMatchers("/*").permitAll()
//                .and().formLogin()
//                .permitAll()
//                .and().logout().permitAll()
//                .and().httpBasic();
    }



}
