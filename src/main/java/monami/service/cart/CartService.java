package monami.service.cart;

import java.util.List;

import monami.domain.dto.cart.CartDTO;

public interface CartService {
	
	long cartTotal(String id);

	void cartWrite(CartDTO dto);

	List<CartDTO> cartList(String user_id);

	int priceTotal(String user_id);

	void cleanCart(String user_id);

	void cartDelete(long no, String user_id);


}
