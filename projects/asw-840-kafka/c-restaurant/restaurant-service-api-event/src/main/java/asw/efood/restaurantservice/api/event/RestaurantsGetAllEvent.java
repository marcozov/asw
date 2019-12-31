package asw.efood.restaurantservice.api.event;


import java.util.List;

import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.event.domain.Restaurant;

public class RestaurantsGetAllEvent implements DomainEvent {
	private List<Restaurant> restaurants;
	
	public RestaurantsGetAllEvent() {
		
	}
	
	public RestaurantsGetAllEvent(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	@Override
	public String toString() {
		return "RestaurantsGetAllEvent(" + this.restaurants + ")";
	}
}
