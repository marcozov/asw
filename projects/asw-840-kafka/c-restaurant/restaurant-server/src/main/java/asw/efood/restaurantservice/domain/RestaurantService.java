package asw.efood.restaurantservice.domain;

import asw.efood.common.api.event.DomainEvent; 
import asw.efood.restaurantservice.api.event.*; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors; 

@Service
@Transactional
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RestaurantDomainEventPublisher domainEventPublisher;

 	public Restaurant createRestaurant(String name, String location) {
		Restaurant restaurant = new Restaurant(name, location); 
		restaurant = restaurantRepository.save(restaurant);
		DomainEvent event = new RestaurantCreatedEvent(restaurant.getId(), restaurant.getName(), restaurant.getLocation());
		domainEventPublisher.publish(event);
		return restaurant; 
	}

 	public Restaurant createRestaurantWithMenu(String name, String location, List<MenuItem> menuItems) {
		RestaurantMenu menu = new RestaurantMenu(menuItems);
		Restaurant restaurant = new Restaurant(name, location, menu); 
		return restaurantRepository.save(restaurant);
	}

 	public Restaurant createRestaurantMenu(Long id, List<MenuItem> menuItems) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		RestaurantMenu menu = new RestaurantMenu(menuItems);
		restaurant.setMenu(menu);
		DomainEvent event = new MenuCreatedEvent(menuItems.stream().map(i -> new asw.efood.restaurantservice.event.domain.MenuItem(i.getId(), i.getName(), i.getPrice())).collect(Collectors.toList()));
		domainEventPublisher.publish(event);
		return restaurantRepository.save(restaurant);
	}
	
 	public Restaurant getRestaurant(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
		asw.efood.restaurantservice.event.domain.RestaurantMenu menu = new asw.efood.restaurantservice.event.domain.RestaurantMenu(
				restaurant.getMenu().getMenuItems().stream().map(i -> new asw.efood.restaurantservice.event.domain.MenuItem(i.getId(), i.getName(), i.getPrice())).collect(Collectors.toList())
				);
		DomainEvent event = new RestaurantGetEvent(new asw.efood.restaurantservice.event.domain.Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getLocation(), menu));
		domainEventPublisher.publish(event);
		return restaurant;
	}

 	public RestaurantMenu getRestaurantMenu(Long id) {
		Restaurant restaurant = restaurantRepository.findByIdWithMenu(id);
		return restaurant.getMenu();
	}

 	public Restaurant getRestaurantByName(String name) {
		return restaurantRepository.findByName(name);
	}
	
	public Collection<Restaurant> getAllRestaurants() {
		Collection<Restaurant> restaurantsLocal = restaurantRepository.findAll();
		List<asw.efood.restaurantservice.event.domain.Restaurant> restaurants = restaurantsLocal
				.stream()
				.map(i -> 
						new asw.efood.restaurantservice.event.domain.Restaurant(i.getId(), i.getName(), i.getLocation()) // if menu is required, this part should be changed
				).collect(Collectors.toList());
		
		DomainEvent event = new RestaurantsGetAllEvent(restaurants);
		domainEventPublisher.publish(event);
		return restaurantsLocal; 
	}
	
	public Collection<Restaurant> getAllRestaurantsByLocation(String location) {
		return restaurantRepository.findAllByLocation(location);
	}
}
