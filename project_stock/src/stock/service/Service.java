package stock.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;

import model.domain.Cart;
import model.domain.Clothes;
import model.domain.Customer;
import stock.exception.NotExistException;
import stock.model.DB.CustomerDB;
import stock.model.DB.StockDB;

public class Service {

	private static Logger logger = Logger.getLogger(Service.class.toString());
	private static Service instance = new Service();
	private StockDB stockList = StockDB.getInstance();
	private CustomerDB customerList = CustomerDB.getInstance();

	public static Service getInstance() {
		return instance;
	}
	// -----------------------재고 관리 기능----------------------------
	
	// 상품명으로 상품 검색
	public Clothes getClothes(String name) {
		for (Clothes c : stockList.getStockList()) {
			if (name.equals(c.getName())) {
				logger.info("find product" + name);
				return c;
			}
		}
		logger.warn("fail to find product" + name);
		return null;
	}

	// 모든 품목 반환
	public ArrayList<Clothes> getAllClothes() {
		logger.info("print all products");
		return stockList.getStockList();
	}

	// 새 상품 추가
	public void insertClothes(Clothes c) {
		stockList.insertClothes(c);
	}

	// 품목 가격변경
	public void changePrice(String name, int price) throws NotExistException {
		Clothes c = getClothes(name);
		if (c != null) {
			logger.info("change price of product " + name + " to " + price);
			c.setPrice(price);
		} else {
			logger.warn("fail to change price of product " + name);
			throw new NotExistException("상품이 존재하지 않습니다");
		}
	}

	// 재고 내용 변경(수량, 사이즈)
	public void changeStock(String name, String size, int stock) throws NotExistException {
		Clothes c = getClothes(name);
		if (c != null) {
			ArrayList<Integer> s = c.getStock();
			ArrayList<String> sizes = c.getSize();
			s.set(sizes.indexOf(size), s.get(sizes.indexOf(size)) + stock);
			c.setStock(s);
			logger.info("change stock of product " + name + "(" + size + ")");
		} else {
			logger.warn("change stock of product " + name + "(" + size + ")");
			throw new NotExistException("상품이 존재하지 않습니다");
		}
	}

	// 물품 전체 삭제(고객 장바구니에서도 삭제)
	public void delClothes(String name) {
		Clothes c = getClothes(name);
		if (c != null) {
			stockList.getStockList().remove(c);
			for (Customer customer : customerList.getCustomerList()) {
				if (customer.getCart().getNames().contains(name)) {
					customer.getCart().del(name);
				}
			}
		}
	}

	// --------------------------고객 관리 기능------------------------------

	// 모든 고객 정보 출력
	public ArrayList<Customer> getAllCustomer() {
		return customerList.getCustomerList();
	}

	// 이름으로 고객 검색
	public Customer getCustomer(String name) throws NotExistException {
		for (Customer c : customerList.getCustomerList()) {
			if (name.equals(c.getName())) {
				logger.info("find customer " + name);
				return c;
			}
		}
		logger.warn("fail to find customer " + name);
		throw new NotExistException("존재하지 않는 고객입니다");
	}

	// 장바구니 추가
	public void addCart(String name, String clothesName, String size) throws NotExistException {
		Clothes c = getClothes(clothesName);
		Customer customer = getCustomer(name);
		if (c != null && customer != null) {
			ArrayList<String> sizes = c.getSize();
			ArrayList<Integer> stocks = c.getStock();
			if (stocks.get(sizes.indexOf(size)) > 0) {
				Cart cart = customer.getCart();
				if (cart.getAmounts() == 0) {
					cart.setAmounts(c.getPrice());
					cart.setNames(new ArrayList<>(Arrays.asList(clothesName)));
					cart.setPrices(new ArrayList<>(Arrays.asList(c.getPrice())));
					cart.setSizes(new ArrayList<>(Arrays.asList(size)));
					System.out.println(cart);
					customer.setCart(cart);
					changeStock(clothesName, size, -1);
				} else {
					cart.add(clothesName, size, c.getPrice());
					System.out.println(cart);
					changeStock(clothesName, size, -1);
				}
			} else {
				throw new NotExistException("재고가 부족합니다");
			}
		} else {
			throw new NotExistException("고객명 또는 제품명이 잘못되었습니다");
		}
	}

	// 장바구니 제거
	public void delCart(String name, String clothesName, String size) throws NotExistException {
		Clothes c = getClothes(clothesName);
		Customer customer = getCustomer(name);
		if (c != null && customer != null) {
			Cart cart = customer.getCart();
			if (cart.getNames().contains(clothesName)) {
				cart.del(clothesName, size, c.getPrice());
				changeStock(clothesName, size, +1);
			} else {
				throw new NotExistException("해당 품목이 장바구니에 없습니다");
			}
		} else {
			throw new NotExistException("고객명 또는 제품명이 잘못되었습니다");
		}
	}

	// 총액 계산
	public int getAmounts(String name) throws NotExistException {
		Customer c = getCustomer(name);
		System.out.println(c);
		if (c != null) {
			if (c.getGrade().equals("Gold")) {
				logger.info("print amount of customer" + name);
				return (int) Math.round(getCustomer(name).getCart().getAmounts() / 100 * 0.8) * 100;
			} else if (c.getGrade().equals("Silver")) {
				logger.info("print amount of customer" + name);
				return (int) Math.round(getCustomer(name).getCart().getAmounts() / 100 * 0.9) * 100;
			} else if (c.getGrade().equals("Bronze")) {
				logger.info("print amount of customer" + name);
				return getCustomer(name).getCart().getAmounts();
			}
		}
		logger.warn("fail to print amount of customer" + name);
		throw new NotExistException("존재하지 않는 고객입니다");
	}

	// 고객 장바구니 반환
	public Cart getCart(String name) throws NotExistException {
		Customer c = getCustomer(name);
		if (c != null) {
			return c.getCart();
		}
		throw new NotExistException("존재하지 않는 고객입니다");
	}

	// 특정물품 장바구니에 담은 고객 검색
	/*
	public ArrayList<Customer> searchCustomerWhoBought(String clothes) throws NotExistException {
		ArrayList<Customer> found = new ArrayList<Customer>();
		for (Customer customer : customerList.getCustomerList()) {
			if (customer.getCart().getNames().contains(clothes)) {
				found.add(customer);
			}
			throw new NotExistException("검색하신 품목을 장바구니에 담으신 고객이 없습니다.");				
			
		}
		return found;
	}
	*/

}
