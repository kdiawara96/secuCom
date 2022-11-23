package secuCom.example.SecuCom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import secuCom.example.SecuCom.Config.RsakeysConfig;

@SpringBootApplication
//@EnableConfigurationProperties(RsakeysConfig.class)
public class SecuComApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuComApplication.class, args);
	}

}
