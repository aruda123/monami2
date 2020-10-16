package monami.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import monami.domain.dto.cart.CartDTO;
import monami.service.cart.CartService;

@Controller
public class CartController {
	
	@Autowired
	private CartService service;
	
	//장바구니 전체 개수 가져오기
	@PostMapping("/cart/cartTotal")
	@ResponseBody
	public long cartTotal(@Param("id")String id) {
		long count = service.cartTotal(id);
		return count;
	}
	
	//장바구니 등록
	@PostMapping("/board/cart")
	public void cart(CartDTO dto, HttpServletResponse response) throws IOException {
		service.cartWrite(dto);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String path = "/board/stationery_detail/"+dto.getBoard_no();
		out.println("<script>alert('장바구니에 담았습니다.');location.href='"+path+"'</script>"); 
		out.flush();
	}
	
	//장바구니 페이지 DB데이터 가져와서 이동 
	@GetMapping("/cart/cartList")
	public String cartList(@Param("user_id")String user_id, Model model) {
		List<CartDTO> list = service.cartList(user_id);
		model.addAttribute("cart", list);
		
		//전체 금액
		int total = service.priceTotal(user_id);
		model.addAttribute("totalPrice", total);
		return "/cart/cartList";
	}
	
	//결제 완료 후 장바구니 비우기
	@PostMapping("/cart/cleanCart")
	@ResponseBody
	public void cleanCart(@Param("user_id")String user_id) {
		service.cleanCart(user_id);
	}
	
	//장바구니 삭제
	@PostMapping("/cart/cartDelete")
	@ResponseBody
	public String cartDelete(@Param("no")long no, @Param("user_id")String user_id) {
		service.cartDelete(no, user_id);
		return "장바구니가 삭제되었습니다.";
	}

}
