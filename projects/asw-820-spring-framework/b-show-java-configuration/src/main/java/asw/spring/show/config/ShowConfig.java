package asw.spring.show.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import asw.spring.show.*;

@Configuration
public class ShowConfig {
	
	@Bean
	public Artist hendrix() {
		return new Musician("Jimi", stratocaster());
	}

	@Bean 
	public Instrument stratocaster() {
		Guitar stratocaster = new Guitar(); 
		stratocaster.setSound("Ta ta taa");
		return stratocaster; 
	}
}
