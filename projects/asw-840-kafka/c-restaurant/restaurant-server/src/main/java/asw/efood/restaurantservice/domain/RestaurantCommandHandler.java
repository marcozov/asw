package asw.efood.restaurantservice.domain;

import asw.efood.common.api.command.Command; 
import asw.efood.restaurantservice.api.command.*; 

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RestaurantCommandHandler {

    private Logger logger = Logger.getLogger(RestaurantCommandHandler.class.toString());

	@Autowired
	private RestaurantService restaurantService;

	public void onCommand(Command command) {
		logger.info("PROCESSING COMMAND: " + command);
		if (command.getClass().equals(CreateRestaurantCommand.class)) {
			CreateRestaurantCommand crc = (CreateRestaurantCommand) command;
			createRestaurant(crc); 
//			restaurantService.createRestaurant(crc.getName(), crc.getLocation()); 
		} else if (command.getClass().equals(GetRestaurantCommand.class)) {
			GetRestaurantCommand grc = (GetRestaurantCommand) command;
			getRestaurant(grc);
		} else if (command.getClass().equals(GetAllRestaurantsCommand.class)) {   
			GetAllRestaurantsCommand garc = (GetAllRestaurantsCommand) command;
			getAllRestaurants(garc);
		} else {
			logger.info("UNKNOWN COMMAND: " + command);			
		}
	}
	
	private void createRestaurant(CreateRestaurantCommand command) {
//		restaurantService.createRestaurant(command.getName(), command.getLocation(), command.getMenu()); 
		Restaurant restaurant = restaurantService.createRestaurant(command.getName(), command.getLocation());
		// from asw.efood.restaurantservice.common.domain.MenuItem to asw.efood.restaurantservice.domain.MenuItem
		restaurant = restaurantService.createRestaurantMenu(restaurant.getId(), command.getMenuItems().stream().map(i -> new MenuItem(i.getId(), i.getName(), i.getPrice())).collect(Collectors.toList()));
	}
	
	private void getRestaurant(GetRestaurantCommand command) {
		Restaurant restaurant = restaurantService.getRestaurant(command.getId());
	}
	
	private void getAllRestaurants(GetAllRestaurantsCommand command) {
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
	}
}