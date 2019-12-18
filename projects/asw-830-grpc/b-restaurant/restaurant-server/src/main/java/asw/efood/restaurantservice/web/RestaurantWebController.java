package asw.efood.restaurantservice.web;

import asw.efood.restaurantservice.domain.*; 

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.ModelAttribute; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.ui.Model;

import java.util.*; 

@Controller
@RequestMapping(path="/web")
public class RestaurantWebController {

	@Autowired 
	private RestaurantService restaurantService; 
	
	@GetMapping("/restaurants/{restaurantId}")
	public String getRestaurant(Model model, @PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}

	@GetMapping("/restaurants")
	public String getRestaurants(Model model) {
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addAttribute("restaurants", restaurants);
		return "get-restaurants";
	}
	
	@GetMapping(value="/restaurants", params={"add"})
	public String getAddRestaurantForm(Model model) {
		model.addAttribute("form", new AddRestaurantForm());
		return "add-restaurant-form";
	}
	
	@PostMapping("/restaurants")
	public String addRestaurant(Model model, @ModelAttribute("form") AddRestaurantForm form) {
		Restaurant restaurant = restaurantService.createRestaurant(form.getName(), form.getLocation());
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}
}