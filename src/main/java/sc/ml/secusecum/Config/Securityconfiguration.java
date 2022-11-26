package sc.ml.secusecum.Config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import sc.ml.secusecum.Modeles.Personnes;
import sc.ml.secusecum.Services.PersonnesServices;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//pour spécifier les role dans le controller il faut cette annotation dans le security config
public class Securityconfiguration{

    private RsakeysConfig rsakeysConfig;
    //Nous allons injecter password encoder et declarer dans le constructeur
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;
    private PersonnesServices personnesServices;

    public Securityconfiguration(RsakeysConfig rsakeysConfig, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, PersonnesServices personnesServices) {
        this.rsakeysConfig = rsakeysConfig;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.personnesServices = personnesServices;
    }

    //nous allons remplacer l'authentification basique en authentification personnaliser pour cela nous allons faire ceci
    //cela n'est pas recommander nous allons faire un autre modèle
  //  @Bean
  //  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)throws Exception{
   //     return authenticationConfiguration.getAuthenticationManager();
    //}

    @Bean
   // public AuthenticationManager authenticationManager(UserDetailsService userDetailsService){

    public AuthenticationManager authenticationManager(){
       var authProvider = new DaoAuthenticationProvider();

       //Nous allons attacher le passwordEncoder que nous volons utiliser
       authProvider.setPasswordEncoder(passwordEncoder);
       //
        authProvider.setUserDetailsService(userDetailsService);

        return new ProviderManager(authProvider);
    }



   /* @Bean
    public UserDetailsService inMemoryUserDetailsManager(){
        return new InMemoryUserDetailsManager(
                 //nous allons utiliser password(passwordEncoder.encode( Pour cripter notre code
                User.withUsername("collaborateur").password(passwordEncoder.encode("1234")).authorities("COLL").build(),
                User.withUsername("user").password(passwordEncoder.encode("1234")).authorities("USER").build(),
                User.withUsername("admin").password(passwordEncoder.encode("1234")).authorities("USER","COLl","ADMIN").build()

        );
    }
    */


    public UserDetailsService userDetailsService(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                Personnes personnes = personnesServices.readByUserName(username);

                Collection<GrantedAuthority> authorities = new ArrayList<>();
                personnes.getRoles().forEach(role->{
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                });
                return new User(personnes.getUsername(), personnes.getPassword(),authorities);
            }
        });
        return null;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws  Exception{


        return httpSecurity
                .csrf(csrf->csrf.disable())
                //Il donner l'autorisation au user à s'authentifier à travers ce url
                .authorizeRequests(auth->auth.antMatchers("/token/**").permitAll())
                .authorizeRequests(auth->auth.anyRequest().authenticated())
                .formLogin().and()
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
