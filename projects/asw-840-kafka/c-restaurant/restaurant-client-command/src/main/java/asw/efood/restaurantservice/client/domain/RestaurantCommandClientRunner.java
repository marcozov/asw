package asw.efood.restaurantservice.client.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*;
import asw.efood.restaurantservice.common.domain.MenuItem;

import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

@Component
public class RestaurantCommandClientRunner implements CommandLineRunner {

	private static final Logger logger = Logger.getLogger(RestaurantCommandClientRunner.class.toString());

	@Autowired
	private RestaurantCommandPublisher restaurantCommandPublisher;

	public void run(String[] args) throws InterruptedException {
		Command command; 
		
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
//		menuItems.add(new MenuItem("PIZ", "pizza", 13));
		menuItems.add(new MenuItem("AMA", "amatriciana", 10000));
		command = new CreateRestaurantCommand("Da Mario", "Roma", menuItems); 
		logger.info("ISSUING COMMAND: " + command); 
		restaurantCommandPublisher.publish(command);
		
		menuItems = new ArrayList<MenuItem>();
		menuItems.add(new MenuItem("SPE", "special", 500));
		command = new CreateRestaurantCommand("Da Giovanna", "Torino", menuItems); 
		logger.info("ISSUING COMMAND: " + command); 
		restaurantCommandPublisher.publish(command);
		
		command = new GetRestaurantCommand(2);
		logger.info("ISSUING COMMAND: " + command);
		restaurantCommandPublisher.publish(command);
		
		command = new GetAllRestaurantsCommand();
		logger.info("ISSUING COMMAND: " + command);
		restaurantCommandPublisher.publish(command);
	}
}