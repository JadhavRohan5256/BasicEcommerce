
import java.util.List;

import com.dao.OrderDetailsDao;
import com.dao.UserDao;
import com.entities.OrderDetails;
import com.entities.User;

public class Testing {
	public static void main(String args[]) {
		User user =	UserDao.getUserCredential("jadhav@gmail.com", "rohanJadhav");
		if(user == null) {
			System.out.println("user are null");
			return;
		}
		System.out.println(user);
		
		OrderDetails order  = new OrderDetails();
		List<OrderDetails> orders = OrderDetailsDao.getOrderByUser(user.getUserId());
		for(OrderDetails item:orders) {
			System.out.println(item);
		}
	}
}
