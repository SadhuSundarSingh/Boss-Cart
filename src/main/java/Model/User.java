package Model;

import java.util.ArrayList;
import java.util.List;

public class User {
	 String firstName;
	 String lastName;
	 String mobileNumber;
	 String userName;
	 String password;
	 String address;
	 Cart cart;
	 ArrayList<Cart> myOrders;
	 ArrayList<String> wishlistProducts;
	 
	 public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	} 
	 public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public ArrayList<Cart> getMyOrders() {
		return myOrders;
	}
	public void setMyOrders(ArrayList<Cart> myOrders) {
		this.myOrders = myOrders;
	}
	public ArrayList<String> getWishlistProducts() {
		return wishlistProducts;
	}
	public void setWishlistProducts(ArrayList<String> wishlistProducts) {
		this.wishlistProducts = wishlistProducts;
	}
}
