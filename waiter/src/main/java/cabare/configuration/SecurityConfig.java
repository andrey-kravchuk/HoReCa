package cabare.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers("/waiter/**").hasAuthority("WAITER_ROLE")
        .antMatchers("/resources/**").permitAll()
        .and()
        .formLogin().loginPage("/waiter/login").successForwardUrl("/waiter/user_data").permitAll()
        .and()
        .logout().logoutUrl("/waiter/logout").logoutSuccessUrl("/").permitAll()
        .and()
        .csrf().disable();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication().withUser("slon").password("admin").roles("WAITER_ROLE");
    auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(
            "select emp.email as username, emp.password, enabled as enabled from employee emp "
                + "where emp.email=?")
        .authoritiesByUsernameQuery(
            "select emp.email as username, r.role as role from employee emp "
                + "join user_role ur "
                + "join role r "
                + "on r.id=ur.role_id and emp.id=ur.user_id "
                + "where emp.email=?");
  }
}
