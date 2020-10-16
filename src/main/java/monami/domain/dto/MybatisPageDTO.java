package monami.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MybatisPageDTO {
	
	private int page;
	private int size;
	private int total;
	
	private int from;
	private int to;
	
	public MybatisPageDTO(int page, int total){
		this.page = page;
		size = 3;
		this.total = total;
		
		int pageBlockNo = page / size;
		if(0 != page % size) {
			pageBlockNo++;
		}
		
		to = pageBlockNo * size;
		from = to - size + 1;
		
		if(to > total) {
			to = total;
		}
	}

}
