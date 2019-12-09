package asw.spring.show;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import asw.spring.show.config.ShowConfig;

public class Main {

	private static Logger logger = Logger.getLogger("asw.spring.show");

	public static void main(String[] args) {
    	ApplicationContext context = new AnnotationConfigApplicationContext(ShowConfig.class);
    	Artist artist = (Artist) context.getBean("hendrix");
		logger.info( artist.perform() );
//		((ConfigurableApplicationContext)context).close();
	}

}
