package asw.spring.show.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import asw.spring.show.*;


@Configuration
@ComponentScan("asw.spring.show")
@PropertySource("classpath:config.properties")
public class ShowConfig {
	
}
