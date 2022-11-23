package secuCom.example.SecuCom.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // Va indiquer à spring qu'il sagit d'une classe de config 
@EnableWebSecurity //cette annotation va indiquer a spring ou se trouve web

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    @Override
    protected void  configure(AuthenticationManagerBuilder auth) throws Exception{

        auth.inMemoryAuthentication()
                .withUser("springuser").password(passwordEncoder().encode("spring123")).roles("USER")
                .and()
                .withUser("springadmin").password(passwordEncoder().encode("admin123")).roles("ADMIN", "USER");
    }
    
    @Override
    //cette methode va nous permettre de gerer nos filtres
    public void configure(HttpSecurity http) throws Exception{

        //les autorisations
        http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("user").hasRole("USER")
        .antMatchers("/coll").hasRole("COLL")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .and()
        .oauth2Login();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
