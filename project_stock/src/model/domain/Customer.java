package model.domain;


import lombok.Data;

@Data
public class Customer extends People {
	private String grade;
	private Cart cart;

	public Customer() {
	}

	public Customer(String name, String phone, String grade, Cart cart) {
		super(name, phone);
		this.grade = grade;
		this.cart = cart;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append("\n3. 고객 등급 : ");
		builder.append(grade);
		builder.append("\n4. <장바구니 내역>  \n");
		builder.append(cart);
		return builder.toString();
	}

}
