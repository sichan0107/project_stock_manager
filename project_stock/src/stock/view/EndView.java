package stock.view;

import java.util.ArrayList;
import model.domain.Clothes;
import model.domain.Customer;

public class EndView {
	//-------------------상품 정보 출력------------------	
	//모든 상품 출력
	public static void AllStockListView(ArrayList<Clothes> allStockList){
		for(int index = 0; index < allStockList.size(); index++){			
			System.out.println("[상품"  + (index+1) + "]\n" + allStockList.get(index) +"\n");
		}
	}
	//특정 상품 출력 
	public static void stockView(Clothes stockList){
		System.out.println(stockList);
	}	
	//예외가 아닌 단순 메세지 출력
	public static void messageView(String message) {
		System.out.println(message);
	}
		
	//--------------- 고객 정보 출력 ---------------------
	//고객 1명 정보 출력
	public static void customerView(Customer customer) {
		System.out.println(customer);
	}
	//모든 고객 정보 출력
	public static void allCustomerView(ArrayList<Customer> allCustomerList) {
		for(int index = 0; index < allCustomerList.size(); index++){			
			System.out.println("[고객"  + (index+1) + "]\n" + allCustomerList.get(index) +"\n");
		}
	}
	

}
