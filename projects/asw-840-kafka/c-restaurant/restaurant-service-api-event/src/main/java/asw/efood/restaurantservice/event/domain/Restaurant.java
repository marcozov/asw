package asw.efood.restaurantservice.event.domain;

import java.util.ArrayList;

import asw.efood.restaurantservice.event.domain.MenuItem;
import asw.efood.restaurantservice.event.domain.RestaurantMenu;

public class Restaurant {

	private Long id; 
	private String name; 
	private String location;
	private RestaurantMenu menu;
	
	public Restaurant() {
	}

	public Restaurant(Long id, String name, String location) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.location = location;
		this.menu = new RestaurantMenu(new ArrayList<MenuItem>());
	}
	
	public Restaurant(Long id, String name, String location, RestaurantMenu menu) {
		this(); 
		this.id = id; 
		this.name = name; 
		this.location = location;
		this.menu = menu;
	}

	public Long getId() {
		return id; 
	}
	
	public void setId(Long id) {
		this.id = id; 
	}

	public String getName() {
		return name; 
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public String getLocation() {
		return location; 
	}
	
	public void setLocation(String location) {
		this.location = location; 
	}

	public RestaurantMenu getMenu() {
		return menu;
	}

	public void setMenu(RestaurantMenu menu) {
		this.menu = menu;
	}

	public String toString() {
		return "Restaurant(" + 
			"id=" + id + 
			", name=" + name + 
			", location=" + location + 
			")"; 
	}
}