package monami.domain.entity.cart;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "jpa_cart")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;

	@Column(nullable = false)
	private long board_no;
	
	@Column(nullable = false)
	private String user_id;
	
	@Column(nullable = false)
	private String url;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private int price;
	
	@CreatedDate
	LocalDateTime reg_date;

	@Builder
	public Cart(long board_no, String user_id, String url, String brand, String title, int price) {
		String urls[] = url.split("/");
		String url1 = urls[urls.length -2];
		String url2 = urls[urls.length-1];
		url = url1+"/"+url2;
		this.board_no = board_no;
		this.user_id = user_id;
		this.url = url;
		this.brand = brand;
		this.title = title;
		this.price = price;
	}

}
