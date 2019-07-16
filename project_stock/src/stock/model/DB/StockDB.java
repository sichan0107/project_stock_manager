package stock.model.DB;

import java.util.ArrayList;
import java.util.Arrays;
import model.domain.Clothes;

public class StockDB {
	private static StockDB instance = new StockDB();
	private ArrayList<Clothes> stockList = new ArrayList<Clothes>();
	private StockDB() {
		stockList.add(new Clothes("¹ÝÆÈÆú·Î¼ÅÃ÷",19900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Top", "Grey", new ArrayList<Integer>(Arrays.asList(10,11,9))));
		stockList.add(new Clothes("½ºÆ®¶óÀÌÇÁÆú·Î¼ÅÃ÷",19900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Top", "White", new ArrayList<Integer>(Arrays.asList(2,2,3))));
		stockList.add(new Clothes("¶ó¿îµå³Ø¹ÝÆÈÆ¼¼ÅÃ÷",19900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Top", "Black", new ArrayList<Integer>(Arrays.asList(1,5,6))));
		stockList.add(new Clothes("½½¸²ÇÇÆ®µ¥¹ÌÁöÁø",29900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Trousers", "Indigo", new ArrayList<Integer>(Arrays.asList(4,1,3))));
		stockList.add(new Clothes("¼¿ºñÁö½½¸²ÇÇÆ®Áø",59900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Trousers", "White", new ArrayList<Integer>(Arrays.asList(4,1,2))));
		stockList.add(new Clothes("¸°³ÙÇÏÇÁÆÒÃ÷",29900,new ArrayList<>(Arrays.asList("90", "95","100")),
				"Trousers", "Beige", new ArrayList<Integer>(Arrays.asList(3,5,7))));
		
	}
	public static StockDB getInstance() {
		return instance;
	}
	public ArrayList<Clothes> getStockList(){
		return stockList;
	}
	public void insertClothes(Clothes newProject) {
		stockList.add(newProject);
	}
}
