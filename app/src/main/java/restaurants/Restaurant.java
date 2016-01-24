package restaurants;

public class Restaurant {

	private String name;
	private String address;
	private String phone;
	private String[] categories;


	public String toString() {
		String str1 = this.name + "\n" + this.address + "\n" + /**this.phone + */"\n";
		String str2 = new String();

		/**
		for (int i = 0; i < this.categories.length; i++) {
			str2 += this.categories[i] + " ";
		} */

		return str1 + str2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}
}