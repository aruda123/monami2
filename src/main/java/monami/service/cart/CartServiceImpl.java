package monami.service.cart;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monami.domain.dto.cart.CartDTO;
import monami.domain.entity.cart.Cart;
import monami.domain.entity.cart.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository repository;
	
	@Override
	public long cartTotal(String id) {
		long count = repository.count(id);
		return count;
	}
	
	@Override
	public void cartWrite(CartDTO dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public List<CartDTO> cartList(String user_id) {
		List<Cart> list = repository.findByUser_id(user_id);
		List<CartDTO> lists = new Vector<CartDTO>();
		CartDTO dto;
		for(Cart res : list) {
			dto = new CartDTO(res);
			lists.add(dto);
		}
		return lists;
	}

	@Override
	public int priceTotal(String user_id) {
		List<Cart> list = repository.findByUser_id(user_id);
		int total = 0;
		for(Cart res : list) {
			total += res.getPrice();
		}
		return total;
	}

	@Override
	public void cleanCart(String user_id) {
		repository.deleteByUser_id(user_id);
	}

	@Override
	public void cartDelete(long no, String user_id) {
		repository.cartDelete(no, user_id);
	}


}
