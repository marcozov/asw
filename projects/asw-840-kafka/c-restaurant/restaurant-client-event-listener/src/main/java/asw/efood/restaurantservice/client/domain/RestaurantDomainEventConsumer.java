package asw.efood.restaurantservice.client.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.*;
import java.util.stream.Collectors;

@Service
public class RestaurantDomainEventConsumer {

	private static final Logger logger = Logger.getLogger(RestaurantDomainEventConsumer.class.toString());

	public void onEvent(DomainEvent event) {
		if (event.getClass().equals(RestaurantCreatedEvent.class)) {
			RestaurantCreatedEvent rce = (RestaurantCreatedEvent) event;
			restaurantCreated(rce);
//			Restaurant restaurant = new Restaurant(rce.getId(), rce.getName(), rce.getLocation());
//			logger.info("CREATED RESTAURANT: " + restaurant);
		} else if (event.getClass().equals(MenuCreatedEvent.class)) {
			MenuCreatedEvent mce = (MenuCreatedEvent) event;
			menuCreated(mce);
		} else if (event.getClass().equals(RestaurantGetEvent.class)) {
			RestaurantGetEvent rge = (RestaurantGetEvent) event;
			restaurantGet(rge);
		} else if (event.getClass().equals(RestaurantsGetAllEvent.class)) {
			RestaurantsGetAllEvent rgae = (RestaurantsGetAllEvent) event;
			restaurantsGetAll(rgae);
		} else {
			logger.info("UNKNOWN EVENT: " + event);			
		}
	}
	
	private void restaurantCreated(RestaurantCreatedEvent event) {
		Restaurant restaurant = new Restaurant(event.getId(), event.getName(), event.getLocation());
		logger.info("CREATED RESTAURANT: " + restaurant);
	}
	
	private void menuCreated(MenuCreatedEvent event) {
		List<MenuItem> menuItems = event.getMenuItems().stream().map(i -> new MenuItem(i.getId(), i.getName(), i.getPrice())).collect(Collectors.toList());
		logger.info("CREATED MENU: " + menuItems);
	}
	
	private void restaurantGet(RestaurantGetEvent event) {
		List<MenuItem> menuItems = event.getRestaurant().getMenu().getMenuItems().stream().map(i -> new MenuItem(i.getId(), i.getName(), i.getPrice())).collect(Collectors.toList());
		Restaurant restaurant = new Restaurant(event.getRestaurant().getId(), event.getRestaurant().getName(), event.getRestaurant().getLocation());
		logger.info("RETRIEVED RESTAURANT: " + restaurant + ", " + menuItems);
	}
	
	private void restaurantsGetAll(RestaurantsGetAllEvent event) {
		List<Restaurant> restaurants = event.getRestaurants().stream().map(i -> new Restaurant(i.getId(), i.getName(), i.getLocation())).collect(Collectors.toList());
		logger.info("RETRIEVED RESTAURANTS: " + restaurants);
	}
}