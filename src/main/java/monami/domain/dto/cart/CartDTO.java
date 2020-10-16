package monami.domain.dto.cart;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import monami.domain.entity.cart.Cart;

@Data
@NoArgsConstructor
public class CartDTO {
	
	private long no;
	private long board_no;
	private String user_id;
	private String url;
	private String brand;
	private String title;
	private int price;
	private LocalDateTime reg_date;
	
	
	public Cart toEntity() {
		return Cart.builder().board_no(board_no).user_id(user_id).url(url).brand(brand).title(title).price(price).build();
	}
	
	public CartDTO(Cart c) {
		this.no = c.getNo();
		this.board_no = c.getBoard_no();
		this.user_id = c.getUser_id();
		this.url = "/images/board/"+c.getUrl();
		this.brand = c.getBrand();
		this.title = c.getTitle();
		this.price = c.getPrice();
		this.reg_date = c.getReg_date();
	}

}
