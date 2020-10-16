package monami.service.comment;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import monami.domain.dto.CommentDTO;
import monami.domain.entity.Comment;
import monami.domain.entity.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentRepository repository;

	@Override
	public void commet_write(CommentDTO dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public List<CommentDTO> comment(Long no) {
		List<Comment> lists = repository.findAllByBoardNo(no);
		List<CommentDTO> list = new Vector<CommentDTO>();
		for(Comment co : lists) {
			CommentDTO dto = new CommentDTO(co);
			list.add(dto);
		}
		return list;
	}

	@Override
	public void delete(long no) {
		repository.deleteById(no);
	}

}
