package stock.controller;

import java.util.ArrayList;
import model.domain.*;
import stock.exception.NotExistException;
import stock.service.Service;
import stock.view.*;

public class StockController {
	private static StockController instance = new StockController(); 
	private Service service = Service.getInstance(); 

	private StockController() {
	}

	public static StockController getInstance() {
		return instance;
	}
	// -----------------------------To manage stock-------------------------------

	// Search all stock
	public void AllStockView() {
		ArrayList<Clothes> allStockList = service.getAllClothes();
		if (allStockList.size() != 0) {
			EndView.AllStockListView(allStockList);
		} else {
			EndView.messageView("상품이 하나도 존재하지 않습니다.");
		}
	}

	// Search a stock
	public void OneStockView(String name) { // 재능 기부 유형 이름으로 검색
		if (service.getClothes(name) != null) {
			EndView.stockView(service.getClothes(name));
		} else {
			EndView.messageView("검색 요청하신 상품은 존재하지 않습니다.\n");
			// e.printStackTrace();
		}
	}

	// save new stock
	public void insertStockView(Clothes newStock) {
		service.insertClothes(newStock);
		System.out.println("새 상품이 저장되었습니다.");
	}

	// update size & amount stock
	public void updateStockView(String name, String size, int stock) {
		try {
			service.changeStock(name, size, stock);
			System.out.println("상품 정보가 수정되었습니다.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
			// e.printStackTrace();
		}
	}

	// update price of stock
	public void updatePriceView(String name, int price) {
		try {
			service.changePrice(name, price);
			System.out.println("상품의 가격이 수정되었습니다.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// delete stock information
	public void deleteStockView(String Name) {
		service.delClothes(Name);
		System.out.println("상품이 재고와 장바구니에서 삭제되었습니다.");
	}

	// -------------------------------To manage customers-----------------------------------
	// search all customers
	public void allCustomerView() {
		ArrayList<Customer> allCustomerList = service.getAllCustomer();
		if (allCustomerList.size() != 0) {
			EndView.allCustomerView(allCustomerList);
		} else {
			EndView.messageView("모든 고객정보가 비어있습니다.");
		}
	}

	// search a customer
	public void searchCustomerView(String name) {
		try {
			EndView.messageView(service.getCustomer(name).toString());
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// add product in cart
	public void addProductInCartView(String name, String clothesName, String size) {
		try {
			service.addCart(name, clothesName, size); // 예외발생
			System.out.println(name + "님이 요청하신 " + clothesName + " 상품을 장바구니에 추가했습니다.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
			// e.printStackTrace();

		}
	}

	// delete a product from cart
	public void delProductInCartView(String name, String clothesName, String size) {
		try {
			service.delCart(name, clothesName, size);
			System.out.println(clothesName + "을 장바구니목록에서 뺐습니다");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}

	// make total price
	public void sumPriceView(String name) {
		try {
			//System.out.println(name + "고객님의 장바구니 정보 : \n" + service.getCart(name));
			System.out.println("계산하실 금액은 등급 할인 적용해서 " + service.getAmounts(name) + "원 입니다.");
		} catch (NotExistException e) {
			FailView.failMessageView(e.getMessage());
		}
	}
	// 특정물품 장바구니에 담은 고객 검색
	/*
	public void searchSpecificCustomer(String clothes) {
		ArrayList<Customer> specificCustomer = new ArrayList<Customer>();
		try {
			specificCustomer = service.searchCustomerWhoBought(clothes);
			EndView.allCustomerView(specificCustomer);
		} catch (NotExistException e) {
			//e.printStackTrace();
		}
	}
	*/
}
