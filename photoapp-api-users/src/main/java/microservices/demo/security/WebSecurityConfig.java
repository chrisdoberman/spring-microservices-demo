package microservices.demo.security;

import microservices.demo.users.services.UsersService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UsersService usersService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment environment;

    @Value("${gateway.ip}")
    private String gatewayIp;

    @Value("${login.url.path}")
    private String loginUrlPath;

    public WebSecurityConfig(UsersService usersService, BCryptPasswordEncoder bCryptPasswordEncoder,
                             Environment environment) {
        this.usersService = usersService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // restrict by ip address. can you restrict by service name? e.g. zuul-gateway
        http.authorizeRequests().antMatchers("/**").hasIpAddress(gatewayIp)
                .and().addFilter(getAuthenticationFilter());

        //http.authorizeRequests().antMatchers("/users/**").permitAll();
        //allow h2-console frame for development
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersService).passwordEncoder(bCryptPasswordEncoder);
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(usersService, environment,
                authenticationManager());
        // customize the login url path, default by spring is /login
        authenticationFilter.setFilterProcessesUrl(loginUrlPath);
        //authenticationFilter.setAuthenticationManager(authenticationManager());
        return authenticationFilter;
    }
}
