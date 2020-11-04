package monami.service.comment;

import java.util.List;

import monami.domain.dto.CommentDTO;
import monami.domain.dto.ReplyUpdateDTO;

public interface CommentService {

	void commet_write(CommentDTO dto);

	List<CommentDTO> comment(Long no);

	void delete(long no);

	void update(ReplyUpdateDTO dto);

}
