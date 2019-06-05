package microservices.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${gateway.ip}")
    private String gatewayIp;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // restrict by ip address
        http.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIp);
        //http.authorizeRequests().antMatchers("/users/**").permitAll();
        //allow h2-console frame for development
        http.headers().frameOptions().disable();
    }
}
