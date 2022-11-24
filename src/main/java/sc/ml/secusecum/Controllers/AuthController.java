package sc.ml.secusecum.Controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AuthController {

    //Pour generer un token à l'auth nous devons injecter jwt encoder
    private JwtEncoder jwtEncoder;

    public  AuthController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping("/token")
    //Pour faire l'authentification j'aurais besoin d'envoyé le userName et le mot de pass
    //Ici comme nous utilisons l'auth basic (dans la requête http nous envoyons username et le role en mode base64 et spring securité va s'en charger de l'auth )nous pouvons utiliser l'autentification
    //ça va nous permettre de recuperer username et les roles
    public Map<String, String> jwtToken(Authentication authentication){

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
