package stock.model.DB;

import java.util.ArrayList;

import lombok.Data;
import model.domain.Cart;
import model.domain.Customer;

@Data
public class CustomerDB {
	private static CustomerDB instance = new CustomerDB();
	private ArrayList<Customer> customerList = new ArrayList<Customer>();
	private CustomerDB() {
		customerList.add(new Customer("최성국","001","Silver",new Cart() ));
		customerList.add(new Customer("송시찬","002","Gold",new Cart() ));
		customerList.add(new Customer("한우석","003","Bronze",new Cart() ));
		customerList.add(new Customer("김혜경","004","Gold",new Cart() ));
	}
	public static CustomerDB getInstance() {
		return instance;
	}
	public ArrayList<Customer> getCustomerList(){
		return customerList;
	}
	public void insertCustomer(Customer newCustomer) {
		customerList.add(newCustomer);
	}
}
