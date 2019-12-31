package asw.efood.restaurantservice.common.domain;

import java.util.List;

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
