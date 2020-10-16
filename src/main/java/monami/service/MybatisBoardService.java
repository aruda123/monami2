package monami.service;

import org.springframework.web.servlet.ModelAndView;

import monami.domain.dto.MybatisBoardDTO;

public interface MybatisBoardService {

	void fileUpload(MybatisBoardDTO dto);

	ModelAndView list(int page);

	MybatisBoardDTO detail(Long no);

	void update(MybatisBoardDTO dto);

	void delete(Long no);

}
