package asw.efood.restaurantservice.api.command;

import asw.efood.common.api.command.Command;

public class GetAllRestaurantsCommand implements Command {
	private long id;
	public GetAllRestaurantsCommand() {
		this.id = 0;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "GetAllRestaurantCommand()";
	}
}
