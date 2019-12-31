package asw.efood.restaurantservice.api.event;

import java.util.List;

import asw.efood.common.api.event.DomainEvent;
import asw.efood.restaurantservice.event.domain.MenuItem;

public class MenuCreatedEvent implements DomainEvent {
	private List<MenuItem> menuItems;
	
	public MenuCreatedEvent() {
		
	}
	
	public MenuCreatedEvent(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(List<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}

	@Override
	public String toString() {
//		return "MenuCreatedEvent(menuItems=" + menuItems.toString() + ")";
		return menuItems.toString();
	}
}
