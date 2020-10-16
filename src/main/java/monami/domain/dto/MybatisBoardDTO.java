package monami.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MybatisBoardDTO {
	
	private Long no;
	private String url;
	private String brand;
	private String title;
	private String price;
	private String tag1;
	private String tag2;
	
	public MybatisBoardDTO(MybatisBoardDTO dto) {
		String path = "/images/board/monamipet/"; 
		this.no = dto.getNo();
		this.url = "url('"+path+dto.getUrl()+"')";
		this.brand = dto.getBrand();
		this.title = dto.getTitle();
		this.price = dto.getPrice();
		this.tag1 = dto.getTag1();
		this.tag2 = dto.getTag2();
	}
	
	
	
	
}
