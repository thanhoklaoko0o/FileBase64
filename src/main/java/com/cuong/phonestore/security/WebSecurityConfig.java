package com.cuong.phonestore.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cuong.phonestore.security.jwt.JwtAuthEntryPoint;
import com.cuong.phonestore.security.jwt.JwtAuthTokenFilter;
import com.cuong.phonestore.services.impl.UserDetailsServiceImpl;

/*import com.cuong.phonestore.security.jwt.JwtAuthEntryPoint;
import com.cuong.phonestore.security.jwt.JwtAuthTokenFilter;
import com.cuong.phonestore.services.impl.UserDetailsServiceImpl;*/


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
    

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/order/**", "/historyOrder/**").access("hasRole('ROLE_USER')")
                .antMatchers("/api/order/**", "/api/category", "/api/brand/**", "/api/product/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/","/css/**", "/js/**", "/fonts/**", "/images/**", "/account/**", "/cart/**",
                		"/payment/**","/test/**","/product/**", "/api/auth/**").permitAll()
                /*.anyRequest().authenticated()*/
                .and()
                .formLogin()//
        		/*.loginProcessingUrl("/j_spring_security_login")//
*/        		.loginPage("/login")//
        		.defaultSuccessUrl("/")//
        		.failureUrl("/login?message=error")//
        		.usernameParameter("username")//
        		.passwordParameter("password")
        		// Cấu hình cho Logout Page.
        		.and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?message=logout");
        		/*.and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);*/        
        
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}