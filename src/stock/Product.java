package stock;

public class Product {
	   
	    private int id;
	    private String name;
	    private double price;
	    private int quantity;

	    // Constructor
	    public Product(int id, String name, double price, int quantity) {
	        this.id = id;
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Getters and Setters
	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    // Business logic method
	    public void reduceStock(int soldQuantity) {
	        if (soldQuantity <= this.quantity) {
	            this.quantity -= soldQuantity;
	        }
	    }
	
}
