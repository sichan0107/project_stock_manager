package model.domain;

import java.util.ArrayList;
import lombok.Data;
@Data
public class Clothes {
	public Clothes() {}
	public Clothes(String name, int price, ArrayList<String> size, String category, String color,
			ArrayList<Integer> stock) {
		super();
		this.name = name;
		this.price = price;
		this.size = size;
		this.category = category;
		this.color = color;
		this.stock = stock;
	}
	private String name;
	private int price;
	private ArrayList<String> size;
	private String category;
	private String color;
	private ArrayList<Integer> stock;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 상품명 : ");
		builder.append(name);
		builder.append("\n2. 가격 : ");
		builder.append(price);
		builder.append("\n3. 사이즈: ");
		builder.append(size);
		builder.append("\n4. 재고 수량: ");
		builder.append(stock);
		builder.append("\n5. 색상 : ");
		builder.append(color);
		builder.append("\n6. 분류 : ");
		builder.append(category);
		return builder.toString(); 
	}
}
