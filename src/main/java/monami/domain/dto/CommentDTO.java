package monami.domain.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import monami.domain.entity.Comment;

@Data
@NoArgsConstructor
public class CommentDTO {
	
	private long no;
	private long board_no;
	private int count;
	private String id;
	private String content;
	private String user_ip;
	private LocalDateTime reg_date;
	
	public CommentDTO(Comment co) {
		this.no = co.getNo();
		this.board_no = co.getBoard_no();
		this.count = co.getCount();
		this.id = co.getId();
		this.content = co.getContent();
		this.user_ip = co.getUser_ip();
		this.reg_date = co.getReg_date();
	}
	
	public Comment toEntity() {
		return Comment.builder().board_no(board_no).id(id).content(content).user_ip(user_ip).build();
	}

}
