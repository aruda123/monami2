package monami.domain.dto;

import lombok.Data;

@Data
public class MyBatisBoardRequestDTO {
	
	private Long no;
	private String url;
	private String brand;
	private String title;
	private String price;
	private String tag1;
	private String tag2;

}
