package asw.efood.restaurantservice.api.command;

import java.util.ArrayList;
import java.util.List;

import asw.efood.common.api.command.Command;
import asw.efood.restaurantservice.common.domain.MenuItem;

public class CreateRestaurantCommand implements Command {

	private String name;
	private String location;
	private List<MenuItem> menuItems;
	
	public CreateRestaurantCommand() {
	}

	public CreateRestaurantCommand(String name, String location) {
		this();
		this.name = name;
		this.location = location;
		List<MenuItem> items = new ArrayList<MenuItem>();
		this.menuItems = items;
	}
	
	public CreateRestaurantCommand(String name, String location, List<MenuItem> menuItems) {
		this();
		this.name = name;
		this.location = location;
		this.menuItems = menuItems;
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

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public String toString() {
		return "CreateRestaurantCommand(" + 
			"name=" + name + 
			", location=" + location + 
			")"; 
	}
}