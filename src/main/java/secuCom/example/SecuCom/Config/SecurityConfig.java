package secuCom.example.SecuCom.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration // Va indiquer à spring qu'il sagit d'une classe de config 
@EnableWebSecurity //cette annotation va indiquer a spring ou se trouve web

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    

    
    @Override
    //cette methode va nous permettre de gerer nos filtres
    public void configure(HttpSecurity http) throws Exception{
     

        //
        http.authorizeRequests()
        .antMatchers("/admin").hasRole("ADMIN")
        .antMatchers("user").hasRole("USER")
        .antMatchers("/coll").hasRole("COLL")
        .anyRequest().authenticated()
        .and()
        .formLogin();
    }
}
