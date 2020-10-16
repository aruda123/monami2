package monami.domain.entity;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query("select c from Comment c where board_no=?1 order by no desc")
	List<Comment> findAllByBoardNo(Long no);

}
