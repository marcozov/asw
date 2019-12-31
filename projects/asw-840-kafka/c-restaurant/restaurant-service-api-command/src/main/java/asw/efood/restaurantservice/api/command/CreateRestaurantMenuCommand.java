package asw.efood.restaurantservice.api.command;

import java.util.List;

import asw.efood.common.api.command.Command;
import asw.efood.restaurantservice.common.domain.MenuItem;
import asw.efood.restaurantservice.common.domain.Restaurant;

public class CreateRestaurantMenuCommand implements Command {
	Restaurant restaurant;
	List<MenuItem> menuItems;
	
	
}
