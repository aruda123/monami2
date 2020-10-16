package monami.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import monami.domain.dto.BoardDTO;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@Table(name = "monami_stationery")
public class BoardStationery {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private String url;
	
	@Column(nullable = false)
	private String brand;
	
	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private int price;
	
	private String tag;
	
	@CreatedDate
	private LocalDateTime reg_date;

	@Builder
	public BoardStationery(String url, String brand, String title, int price, String tag) {
		this.url = url;
		this.brand = brand;
		this.title = title;
		this.price = price;
		this.tag = tag;
	}
	
	public BoardStationery update(BoardDTO dto) {
		if(dto.getUrl() != null) {
			this.url = dto.getUrl();
		}
		this.brand = dto.getBrand();
		this.title = dto.getTitle();
		this.price = dto.getPrice();
		this.tag = dto.getTag();
		return this;
	}

}
