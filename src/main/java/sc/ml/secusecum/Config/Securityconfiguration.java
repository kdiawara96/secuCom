package sc.ml.secusecum.Config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
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
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Securityconfiguration{

    private RsakeysConfig rsakeysConfig;

    public Securityconfiguration(RsakeysConfig rsakeysConfig) {
        this.rsakeysConfig = rsakeysConfig;
    }


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



//Pour verifier la signature du token j'ai besoin du public key
    @Bean
    JwtDecoder jwtDecoder(){
  return NimbusJwtDecoder.withPublicKey(rsakeysConfig.publicKey()).build();
    }

    //Pour signer le token j'ai besoin d'utiliser le private key et public key
    @Bean
     JwtEncoder jwtEncoder(){

        JWK jwk = new RSAKey.Builder(rsakeysConfig.publicKey()).privateKey(rsakeysConfig.privateKey()).build();
        JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwkSource);
    }

    //Nous avons besoin de créer un netpoint qui permet de generer le token

}
