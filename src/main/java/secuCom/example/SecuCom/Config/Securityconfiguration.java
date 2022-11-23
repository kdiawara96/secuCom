package secuCom.example.SecuCom.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.FilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfiguration{

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                User.withUsername("collaborateur").password("{noop}1234").authorities("COLL").build(),
                User.withUsername("user").password("{noop}1234").authorities("USER").build(),
                User.withUsername("admin").password("{noop}1234").authorities("USER","COLl","ADMIN").build()

        );
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws  Exception{

        return httpSecurity
                .csrf(csrf->csrf.disable())
                .authorizeRequests(auth->auth.anyRequest().authenticated())
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    JwtEncoder jwtEncoder(){

    }

    JwtDecoder jwtDecoder(){

    }
}
