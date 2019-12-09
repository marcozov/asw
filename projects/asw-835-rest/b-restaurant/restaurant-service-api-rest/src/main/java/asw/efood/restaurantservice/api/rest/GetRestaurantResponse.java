package asw.efood.restaurantservice.api.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRestaurantResponse {
	private long restaurantId;
	private String name;
	private String location;
}
