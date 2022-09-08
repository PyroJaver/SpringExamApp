package examapp.config;

import examapp.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailService userDetailService;

@Autowired
    public SecurityConfig(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;

    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(getPasswordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception{
      http.csrf().disable()
              .authorizeRequests()
              .antMatchers("/admin/**").hasRole("ADMIN")
              .antMatchers("/auth/login", "/error", "/auth/registration" ).permitAll()
              .anyRequest().hasAnyRole("USER", "ADMIN")
              .and()
              .formLogin().loginPage("/auth/login")
              .loginProcessingUrl("/process_login")
              .defaultSuccessUrl("/user/home", true)
              .failureUrl("/auth/login?error")
              .and()
              .logout()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/auth/login");
    }
    @Bean
    public PasswordEncoder getPasswordEncoder(){
    return new BCryptPasswordEncoder();
    }
}
