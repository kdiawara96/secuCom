package sc.ml.secusecum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import sc.ml.secusecum.Config.RsakeysConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableConfigurationProperties(RsakeysConfig.class)
public class SecuSecumApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecuSecumApplication.class, args);
	}

}
