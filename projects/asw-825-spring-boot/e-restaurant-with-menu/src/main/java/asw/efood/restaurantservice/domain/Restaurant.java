package asw.efood.restaurantservice.domain;

import java.util.ArrayList;

import javax.persistence.*;

@Entity 
public class Restaurant {

	@Id 
	@GeneratedValue
	private Long id; 
	private String name; 
	private String location;
	private String founder;
	
	@Embedded
	private RestaurantMenu menu;
	
	public Restaurant() {
	}

	public Restaurant(String name, String location, String founder) {
		this(); 
		this.name = name; 
		this.location = location;
		this.founder = founder;
		this.menu = new RestaurantMenu(new ArrayList<MenuItem>());
	}
	
	public Restaurant(String name, String location, String founder, RestaurantMenu menu) {
		this(); 
		this.name = name; 
		this.location = location;
		this.founder = founder;
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
	
	public String getFounder() {
		return founder;
	}

	public void setFounder(String founder) {
		this.founder = founder;
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
			", menu=" + menu + 
			")"; 
	}
}