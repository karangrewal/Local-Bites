package restaurants;

import java.util.ArrayList;
import java.util.List;

public class RestaurantGroup {

	private Restaurant[] restaurants;

	public RestaurantGroup(Restaurant[] objects) {
		this.restaurants = objects;
	}

	public String toString() {
		String toReturn = new String();
		for (int i = 0; i < this.restaurants.length; i++) {
			toReturn += this.restaurants[i].toString() + "\n\n";
		}
		return toReturn;
	}

	public Restaurant[] getRestaurants() {
		return this.restaurants;
	}

	public void setRestaurants(Restaurant[] restaurants) {
		this.restaurants = restaurants;
	}

	//public void setRestaurants(int i; )
}