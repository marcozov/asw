package asw.efood.restaurantservice.api.command;

import asw.efood.common.api.command.Command;

public class GetRestaurantCommand implements Command {
	private long id;
	
	public GetRestaurantCommand() {
		
	}
	
	public GetRestaurantCommand(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "GetRestaurantCommand(" + this.id +")";
	}
}
