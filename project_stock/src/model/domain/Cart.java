package model.domain;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	private int amounts = 0;
	private ArrayList<String> names;
	private ArrayList<String> sizes;
	private ArrayList<Integer> prices;
	
	public void add (String name, String size, int price) {
		names.add(name);
		sizes.add(size);
		prices.add(price);
		amounts += price;
	}
	
	public void del (String name, String size, int price) {
		for (int i = (names.size()-1) ; i>=0 ; i--) {
			if (names.get(i).equals(name) && sizes.get(i).equals(size)) {
				names.remove(i);
				sizes.remove(i);
				amounts -=prices.get(i);
				prices.remove(i);
			}
		}
	}
	
	public void del (String name) {
		for (int i = (names.size()-1) ; i>=0 ; i--) {
			if (names.get(i).equals(name)) {
				names.remove(i);
				sizes.remove(i);
				amounts -=prices.get(i);
				prices.remove(i);
			}
		}
	}

	
	@Override
	public String toString() {
	      StringBuilder builder = new StringBuilder();
	      builder.append(" 1. 제품명 : ");
	      builder.append(names);
	      builder.append("\n 2. 가격 : ");
	      builder.append(prices);
	      builder.append("\n 3. 사이즈 : ");
	      builder.append(sizes);
	      builder.append("\n 4. 총액 : ");
	      builder.append(amounts);
	      return builder.toString();
		}
}