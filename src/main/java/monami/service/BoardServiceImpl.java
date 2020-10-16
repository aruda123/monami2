package monami.service;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import monami.domain.dto.BoardDTO;
import monami.domain.dto.PageDTO;
import monami.domain.entity.BoardRepository;
import monami.domain.entity.BoardStationery;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardRepository repository;
	
	@Override
	public long boardTotal() {
		int total = (int) repository.count();
		return total;
	}
	
	@Override
	public ModelAndView list(int page) {
		int size = 8; // 한 페이지에 표현할 게시글 수
		Sort sort = Sort.by(Direction.DESC,"no"); // 게시글 정렬
		Pageable pageable = PageRequest.of(page - 1, size, sort); //0부터 시작이라 -1, 뿌려줄 게시글 수, 정렬
		Page<BoardStationery> resultPage = repository.findAll(pageable);
		PageDTO<BoardStationery> pageDTO = new PageDTO<>(resultPage);
		
		List<BoardStationery> result = resultPage.getContent();
		List<BoardDTO> lists = new Vector<BoardDTO>();
		
		for(BoardStationery board : result) {
			BoardDTO dto = new BoardDTO(board);
			lists.add(dto);
		}
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", lists);
		mv.addObject("pageInfo", pageDTO);
		return mv;
	}
	
	@Override
	public void fileUpload(BoardDTO dto) {
		repository.save(dto.toEntity());
	}
	
	@Override
	public BoardDTO detail(Long no) {
		BoardStationery result = repository.findById(no).orElse(null);
		BoardDTO dto = new BoardDTO(result);
		return dto;
	}

	@Transactional
	@Override
	public void update(BoardDTO dto) {
		repository.findById(dto.getNo())
						.map(e -> e.update(dto))
						.orElse(null);
				
	}

	@Override
	public void delete(Long no) {
		repository.deleteById(no);
	}

}
