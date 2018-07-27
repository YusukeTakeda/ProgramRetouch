package beans;

import java.io.Serializable;


/**
 * 配送方法
 * @author d-yamaguchi
 *
 */
public class DeliveryMethodDataBeans implements Serializable {
	private int id;
	private String name;
	private int price;


	public int getId() {
		return id;
	}
	public int setId(int id) {
		return this.id = id;
	}
	public String getName() {
		return name;
	}
	public String setName(String name) {
		return this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public int setPrice(int price) {
		return this.price = price;
	}
}
