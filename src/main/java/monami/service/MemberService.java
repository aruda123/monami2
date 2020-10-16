package monami.service;

import monami.domain.dto.MemberLoginDTO;
import monami.domain.entity.Member;

public interface MemberService{

	MemberLoginDTO login(MemberLoginDTO member);

	Member reg_id_check(String id);

	Member reg_email_check(String email);

	void memberReg(MemberLoginDTO dto);

	MemberLoginDTO findIdPwd(String name, String email);

	MemberLoginDTO findEmail(String id, String name, String email);

	MemberLoginDTO myPagePwdCheck(String id, String pwd);

	void mypageChange(MemberLoginDTO dto);


}
