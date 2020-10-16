package monami.domain.dto;


import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import monami.domain.entity.Member;

@NoArgsConstructor
@Data
public class MemberLoginDTO {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String phone;
	private String birth;
	private String gibun;
	private String addr;
	private String addrDetail;
	private LocalDateTime reg_date;
	
	
	public MemberLoginDTO(Member member) {
		this.id = member.getId();
		this.pwd = member.getPwd();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phone = member.getPhone();
		this.birth = member.getBirth();
		this.gibun = member.getGibun();
		this.addr = member.getAddr();
		this.addrDetail = member.getAddr_detail();
		this.reg_date = member.getReg_date();
	}
	
	public Member toEntity() {
		return Member.builder()
				.id(id).pwd(pwd).name(name).email(email).phone(phone).birth(birth).gibun(gibun).addr(addr).addr_detail(addrDetail)
				.build();
	}

}
