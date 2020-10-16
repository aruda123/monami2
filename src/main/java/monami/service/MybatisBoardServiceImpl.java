package monami.service;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import monami.domain.dto.MyBatisBoardRequestDTO;
import monami.domain.dto.MybatisBoardDTO;
import monami.domain.dto.MybatisPageDTO;
import monami.mapper.BoardMapper;

@Service
public class MybatisBoardServiceImpl implements MybatisBoardService{
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public void fileUpload(MybatisBoardDTO dto) {
		mapper.fileUpload(dto);
	}

	@Override
	public ModelAndView list(int page) {
		int limit = 5;
		int offset = (page - 1) * limit;
		// RowBounds를 이용한 페이징 처리
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		//전체 게시물 수 가져오기
		int rowTotal = mapper.getCountOfRows();
		int pageTotal = rowTotal / limit;
		if(rowTotal % limit != 0) {
			pageTotal++;
		}
		MybatisPageDTO pageDTO = new MybatisPageDTO(page, pageTotal);
		
		List<MybatisBoardDTO> result = mapper.getList(rowBounds);
		List<MybatisBoardDTO> lists = new Vector<MybatisBoardDTO>();
		for(MybatisBoardDTO board : result) {
			MybatisBoardDTO dto = new MybatisBoardDTO(board);
			lists.add(dto);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", lists);
		mv.addObject("pageInfo", pageDTO);
		
		return mv;
	}

	@Override
	public MybatisBoardDTO detail(Long no) {
		MybatisBoardDTO result = mapper.detail(no);
		String path = "/images/board/monamipet/"; 
		result.setUrl(path+result.getUrl());
		return result;
	}

	@Override
	public void update(MybatisBoardDTO dto) {
		MyBatisBoardRequestDTO result = mapper.update_select(dto.getNo());
		if(dto.getUrl() == null) {
			dto.setUrl(result.getUrl());
		}
		if(dto.getBrand() == null) {
			dto.setBrand(result.getBrand());
		}
		if(dto.getTitle() == null) {
			dto.setTitle(result.getTitle());
		}
		if(dto.getPrice() == null) {
			dto.setPrice(result.getPrice());
		}
		mapper.update(dto);
	}

	@Override
	public void delete(Long no) {
		mapper.delete(no);
	}

}
