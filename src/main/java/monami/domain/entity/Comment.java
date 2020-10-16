package monami.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "jpa_comment")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long no;
	
	@Column(nullable = false)
	private long board_no;
	
	private int count;
	
	@Column(nullable = false)
	private String id;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String user_ip;
	
	@CreatedDate
	private LocalDateTime reg_date;

	@Builder
	public Comment(long board_no, int count, String id, String content, String user_ip) {
		this.board_no = board_no;
		this.count = count;
		this.id = id;
		this.content = content;
		this.user_ip = user_ip;
	}

}
