package asw.efood.restaurantservice.event.domain;

import java.util.List;

import asw.efood.restaurantservice.event.domain.MenuItem;

public class RestaurantMenu {
	private List<MenuItem> menuItems;

	public RestaurantMenu() {
	}

	public RestaurantMenu(List<MenuItem> menuItems) {
		this(); 
		this.menuItems = menuItems; 
	}
	
	public List<MenuItem> getMenuItems() {
		return menuItems; 
	}
	
	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems; 
	}

	public String toString() {
		return "RestaurantMenu(" + 
			"menuItems=" + menuItems + 
			")"; 
	}
}
