package sc.ml.secusecum.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@NoArgsConstructor

public class AuthController {

    //Pour generer un token à l'auth nous devons injecter jwt encoder
    private JwtEncoder jwtEncoder;


    private  AuthenticationManager authenticationManager;






    @PostMapping("/token")
    //(1)   Pour faire l'authentification j'aurais besoin d'envoyé le userName et le mot de pass
    //(1)   Ici comme nous utilisons l'auth basic (dans la requête http nous envoyons username et le role en mode base64 et spring securité va s'en charger de l'auth )nous pouvons utiliser l'autentification
    //(1)   ça va nous permettre de recuperer username et les roles

    //(2)  Utilisateur va envoyé le userName et le password et nous allons faire l'authentification et pour le faire je dois déclarer  l'authenticationManager
    //(2) Parce que dans le auth basique ces spring qui le fait automatiquement (dans la requête http nous envoyons username et le role en mode base64 et spring securité va s'en charger de l'auth )nous pouvons utiliser l'autentification)
    //(2)  Mais maintenant c'est à nous de le faire au niveau du code

    public Map<String, String> jwtToken(String username, String password){

        //Nous allons demander à spring ici authentifie nous cette utilisateur
        //pour cela nous allons lui trensmettre un objet New UsernamePasswordAuthenticationToken(username, password)

       Authentication authentication = authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(username, password)
        );

        Map<String, String> idToken = new HashMap<>();


        //l'objet instance va nous permettre de capturer la date instante
        Instant instant = Instant.now();

        // Nous allons prendre les autorisations et les séparer avec de l'espace et cela va nous donner
        //Une chaine de caractère

       String scope = authentication
               .getAuthorities()
               .stream()
               .map(auth-> auth
               .getAuthority())
               .collect(Collectors
               .joining(" "));

         //Dans le jwt nous avons un ensemble de claim

        //Nous avons subject qui represente username et nous allons recuperer avec le authentication.getName

        //issuedAt contient la date de la generation du token c'est pour ça nous utilisons instant

        //expirestAt va contenir la date d'expiration du token et nous spefissons la date d'expiration

        //issuer va contenir le nom de l'application qui à generer le token

        // claim va contenir les autorisation

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(authentication.getName())
                .issuedAt(instant)
                .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                .issuer("security-com")
                .claim("scope", scope)
                .build();

        //
        String jwtAccesToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        idToken.put("accessToken", jwtAccesToken);

        return idToken;
    }
}
