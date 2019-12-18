package asw.efood.restaurantservice.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantMenuResponse {

	private Long restaurantId;
	private Collection<MenuItemElement> menuItems;

}
