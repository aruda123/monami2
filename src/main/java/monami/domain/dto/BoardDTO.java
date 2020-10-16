package monami.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import monami.domain.entity.BoardStationery;

@Data
@NoArgsConstructor
public class BoardDTO {
	
	private Long no;
	private String url;
	private String brand;
	private String title;
	private int price;
	private String tag;
	
	public BoardStationery toEntity() {
		return BoardStationery.builder()
				.url(url).brand(brand).title(title).price(price).tag(tag)
				.build();
	}
	
	public BoardDTO(BoardStationery board) {
		String path = "/images/board/stationery/"; 
		no = board.getNo();
		//url = "url('"+path+board.getFile()+"')";
		url = path+board.getUrl();
		brand = board.getBrand();
		title = board.getTitle();
		price = board.getPrice();
		tag = board.getTag();
	}

}
