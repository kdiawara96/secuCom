package sc.ml.secusecum.Config;


import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

//cette mapping va nous permettre de dire à la classe va dans l'application.propertie et recupère tous les fichier commençant par rsa (prefix"rsa")
@ConfigurationProperties(prefix = "rsa")
public record RsakeysConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {
}
