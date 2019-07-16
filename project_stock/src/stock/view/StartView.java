package stock.view;

import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Clothes;
import stock.controller.StockController;

public class StartView {

	public static void main(String[] args) {
		// public으로 선언된 객체 타입들을 이용해 starview에 필요한 새로운 변수들 생성
				StockController controller = StockController.getInstance();
				
				//새로운 상품
				Clothes stockNew = new Clothes("헨리넥셔츠", 25900, new ArrayList<>(Arrays.asList("90", "95","100")),
						"Top", "White", new ArrayList<Integer>(Arrays.asList(2,3,1)));
				
				
			//----------------------상품 정보 출력 Test------------------------
				
				System.out.println("-------------모든 상품 재고 출력-------------");
				controller.AllStockView();
				
				System.out.println("-------------검색 상품 재고 출력-------------");
				controller.OneStockView("반팔폴로셔츠");
				controller.OneStockView("슈퍼와이드바지"); //예외발생
				
				System.out.println("-------------새 상품 추가 후 전체 재고 출력-------------");
				controller.insertStockView(stockNew);
				controller.AllStockView();
				
				//System.out.println("-------------상품 삭제 후 전체 재고 출력-------------");
				//controller.deleteStock(name);
				//controller.AllStockView();
				
				//System.out.println("-------------상품 수정 후 전체 재고 출력-------------");
				//controller.updateStockView("반팔폴로셔츠");
				
				//System.out.println("------------상품명으로 재고와 장바구니에서 해당상품 제거-----------");
				//controller.deleteStockView("반팔폴로셔츠");
				
			//-----------------고객 정보 및 카트 정보 출력 Test-------------------
				System.out.println("----------모든 고객 출력----------");
				controller.allCustomerView();
				
				System.out.println("------------고객 검색------------");
				controller.searchCustomerView("송시찬");
				
				System.out.println("-----------장바구니 내역 추가 후 확인--------");
				controller.addProductInCartView("김감자", "반팔폴로셔츠", "100"); //예외발생
				controller.addProductInCartView("송시찬", "반팔폴로셔츠", "95");
				//controller.searchCustomerView("송시찬");
				
				//System.out.println("-----------장바구니 내역 삭제 후 확인--------");
				//controller.delProductInCartView("송시찬", "반팔폴로셔츠", "95");
				//controller.searchCustomerView("송시찬");
				
				
				System.out.println("-----------고객 장바구니 내역 출력 후 계산------------");
				controller.sumPriceView("송시찬");
				
				//System.out.println("----------특정 물품 구매한 고객 검색------------");
				//controller.searchSpecificCustomer("반팔폴로셔츠");
	}
}