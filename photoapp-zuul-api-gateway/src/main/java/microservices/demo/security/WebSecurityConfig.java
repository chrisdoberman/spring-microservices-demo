package microservices.demo.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${api.login.url.path}")
    private String loginUrlPath;

    @Value("${api.registration.url.path}")
    private String registrationUrlPath;

    @Value("${api.h2console.url.path}")
    private String h2ConsoleUrl;

    private final Environment environment;

    public WebSecurityConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        //allow h2-console frame for development
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers(h2ConsoleUrl).permitAll()
                .antMatchers(HttpMethod.POST, loginUrlPath).permitAll()
                .antMatchers(HttpMethod.POST, registrationUrlPath).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthorizationFilter(authenticationManager(), environment));

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
