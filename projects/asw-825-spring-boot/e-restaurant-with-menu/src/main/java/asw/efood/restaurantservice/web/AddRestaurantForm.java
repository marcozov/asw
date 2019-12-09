package asw.efood.restaurantservice.web;

public class AddRestaurantForm {

	private String name;
	private String location;
	private String founder;
	
	public AddRestaurantForm() {
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
}