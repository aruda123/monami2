package monami.service.comment;

import java.util.List;

import monami.domain.dto.CommentDTO;

public interface CommentService {

	void commet_write(CommentDTO dto);

	List<CommentDTO> comment(Long no);

	void delete(long no);

}
