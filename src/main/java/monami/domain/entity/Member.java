package monami.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import monami.domain.dto.MemberLoginDTO;

@Entity
@Table(name = "monami_member")
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Member {
	
	@Id
	private String id;
	
	@Column(nullable = false)
	private String pwd;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String phone;
	
	@Column(columnDefinition = "varchar(255) default ''")
	private String birth;
	
	
	@Column(columnDefinition = "varchar(10) default ''")
	private String gibun;
	
	@Column(columnDefinition = "varchar(255) default ''")
	private String addr;
	
	@Column(columnDefinition = "varchar(45) default ''")
	private String addr_detail;
	
	@CreatedDate
	private LocalDateTime reg_date;

	@Builder
	public Member(String id, String pwd, String name, String email, String phone, String birth, String gibun, String addr, String addr_detail) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.birth = birth;
		this.gibun = gibun;
		this.addr = addr;
		this.addr_detail = addr_detail;
	}
	
	public Member upate(MemberLoginDTO dto) {
		if(dto.getPwd() != null) {
			this.pwd = dto.getPwd();
		}
		this.email = dto.getEmail();
		this.phone = dto.getPhone();
		this.gibun = dto.getGibun();
		this.addr = dto.getAddr();
		this.addr_detail = dto.getAddrDetail();
		this.birth = dto.getBirth();
		return this;
	}

}
