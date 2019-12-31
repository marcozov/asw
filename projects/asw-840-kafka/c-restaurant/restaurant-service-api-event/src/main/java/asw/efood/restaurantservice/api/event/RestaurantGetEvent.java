package asw.efood.restaurantservice.api.event;


import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.event.domain.Restaurant;

public class RestaurantGetEvent implements DomainEvent {
	private Restaurant restaurant;
	
	public RestaurantGetEvent() {
		
	}
	
	public RestaurantGetEvent(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "RestaurantGetEvent(" + this.restaurant + ")";
	}
}
