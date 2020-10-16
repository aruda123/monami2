package monami.domain.dto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;
import lombok.NoArgsConstructor;
import monami.domain.entity.BoardStationery;

@Data
@NoArgsConstructor
public class PageDTO<T> {
	
	private long page;
	private long size; //뿌려줄 페이지 수
	private long pageTotal;
	
	private long from;
	private long to;
	
	public PageDTO(Page<BoardStationery> resultPage) {
		page = resultPage.getNumber() + 1;
		size = 3;
		pageTotal = resultPage.getTotalPages();
		
		long pageBlockNo = page / size;
		if(0 != page % size) {
			pageBlockNo++;
		}
		to = pageBlockNo * size;
		from = to - size + 1;
		
		if(to > pageTotal) {
			to = pageTotal;
		}
		
	}

}
