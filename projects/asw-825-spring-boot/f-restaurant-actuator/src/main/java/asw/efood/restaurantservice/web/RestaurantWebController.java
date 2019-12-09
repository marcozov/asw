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

import javax.servlet.http.HttpServletRequest; 

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
	
	@GetMapping("/restaurants/{restaurantId}/menu")
	public String getRestaurantMenu(Model model, @PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("menu", menu);
		return "get-restaurant-menu";
	}

	@GetMapping("/restaurants")
	public String getRestaurants(Model model) {
		Collection<Restaurant> restaurants = restaurantService.getAllRestaurants();
		model.addAttribute("restaurants", restaurants);
		return "get-restaurants";
	}
	
	@GetMapping("/restaurantsByAttribute")
	public String getRestaurantsByAttribute(Model model, @ModelAttribute("form") AddRestaurantForm form) {
		Collection<Restaurant> restaurants = null;
		if (!form.getLocation().equals("")) {
			restaurants = restaurantService.getAllRestaurantsByLocation(form.getLocation());
		} else if (!form.getName().equals("")) {
			restaurants = restaurantService.getAllRestaurantsByName(form.getName());
		} else if (!form.getFounder().equals("")) {
			restaurants = restaurantService.getAllRestaurantsByFounder(form.getFounder());
		} else {
			restaurants = restaurantService.getAllRestaurants();
		}
		model.addAttribute("restaurants", restaurants);
		return "get-restaurants";
	}
	
	@GetMapping(value="/restaurants", params={"add"})
	public String getAddRestaurantForm(Model model) {
		model.addAttribute("form", new AddRestaurantForm());
		return "add-restaurant-form";
	}
	
	@GetMapping(value="/restaurants", params={"get"})
	public String getGetRestaurantForm(Model model) {
		model.addAttribute("form", new AddRestaurantForm());
		return "get-restaurant-form";
	}
	
	@PostMapping("/restaurants")
	public String addRestaurant(Model model, @ModelAttribute("form") AddRestaurantForm form) {
		Restaurant restaurant = restaurantService.createRestaurant(form.getName(), form.getLocation(), form.getFounder());
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}
	
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"edit"})
	public String getEditRestaurantMenuForm(Model model, @PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		RestaurantMenu menu = restaurantService.getRestaurantMenu(restaurantId);
		List<MenuItem> menuItems = menu.getMenuItems(); 
		if (menuItems==null) {
			menuItems = new ArrayList<>();
			// menu.setMenuItems(menuItems); 
		}		
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("form", new EditRestaurantMenuForm(menuItems));
		return "edit-restaurant-menu-form";
	}
	
	@PostMapping("/restaurants/{restaurantId}/menu")
	public String addRestaurantMenu(Model model, @PathVariable Long restaurantId, @ModelAttribute("form") EditRestaurantMenuForm form) {
		List<MenuItem> menuItems = form.getMenuItems(); 
		if (menuItems==null) {
			menuItems = new ArrayList<>();
		}
		Restaurant restaurant = restaurantService.createRestaurantMenu(restaurantId, menuItems);
		model.addAttribute("restaurant", restaurant);
		return "get-restaurant";
	}
	
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"addMenuItem"})
	public String addMenuItem(Model model, @PathVariable Long restaurantId, @ModelAttribute("form") EditRestaurantMenuForm form) {
		List<MenuItem> menuItems = form.getMenuItems(); 
		if (menuItems==null) {
			menuItems = new ArrayList<>();
			form.setMenuItems(menuItems);
		}
		menuItems.add(new MenuItem());
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("form", form);
		return "edit-restaurant-menu-form";
	}
	
	@PostMapping(value="/restaurants/{restaurantId}/menu", params={"removeMenuItem"})
	public String removeMenuItem(Model model, @PathVariable Long restaurantId, 
				@ModelAttribute("form") EditRestaurantMenuForm form, HttpServletRequest req) {
		int index = Integer.valueOf(req.getParameter("removeMenuItem")).intValue();
		form.getMenuItems().remove(index);
		Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
		model.addAttribute("restaurant", restaurant);
		model.addAttribute("form", form);
		return "edit-restaurant-menu-form";
	}
}