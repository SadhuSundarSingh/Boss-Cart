package Model;

public class Product {
	String name;
    String category;
    int quantity;
    int price;
    String brand;
    String model;
    String electronicType;  
    int battery;
    int ram;
    int storage;
    String screenSize;
    int camera;
    String dressSize;    
    int warranty;
    String type;
    String rating;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getElectronicType() {
		return electronicType;
	}
	public void setElectronicType(String electronicType) {
		this.electronicType = electronicType;
	}
	public int getBattery() {
		return battery;
	}
	public void setBattery(int battery) {
		this.battery = battery;
	}
	public int getRam() {
		return ram;
	}
	public void setRam(int ram) {
		this.ram = ram;
	}
	public int getStorage() {
		return storage;
	}
	public void setStorage(int storage) {
		this.storage = storage;
	}
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	public int getCamera() {
		return camera;
	}
	public void setCamera(int camera) {
		this.camera = camera;
	}
	public String getDressSize() {
		return dressSize;
	}
	public void setDressSize(String size) {
		this.dressSize = size;
	}
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "name=" + name + ",category=" + category + ",quantity=" + quantity + ",price=" + price
				+ ",brand=" + brand + ",model=" + model + ",electronicType=" + electronicType + ",battery="
				+ battery + ",ram=" + ram + ",storage=" + storage + ",screenSize=" + screenSize + ",camera="
				+ camera + ",size=" + dressSize + ",warranty=" + warranty + ",type=" + type + ",rating=" + rating;
	}
	
}
