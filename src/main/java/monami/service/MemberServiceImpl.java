package monami.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import monami.domain.dto.MemberLoginDTO;
import monami.domain.entity.Member;
import monami.domain.entity.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberRepository repository;
	
	@Override
	public MemberLoginDTO login(MemberLoginDTO dto) {
		MemberLoginDTO member = repository.findByIdAndPw(dto.getId(), dto.getPwd());
		if(member == null)
			return null;
		return member;
	}
	 
	@Override
	public Member reg_id_check(String id) {
		Optional<Member> result = repository.findById(id);
		return result.orElse(null);
	}

	@Override
	public Member reg_email_check(String email) {
		Optional<Member> result = repository.findByEmail(email);
		return result.orElse(null);
	}

	@Override
	public void memberReg(MemberLoginDTO dto) {
		repository.save(dto.toEntity());
	}

	@Override
	public MemberLoginDTO findIdPwd(String name, String email) {
		MemberLoginDTO dto = repository.findByNameAndEmail(name, email);
		return dto;
	}

	public MemberLoginDTO findEmail(String id, String name, String email) {
		MemberLoginDTO dto = repository.findEmail(id, name, email);
		return dto;
	}

	@Override
	public MemberLoginDTO myPagePwdCheck(String id, String pwd) {
		MemberLoginDTO dto = repository.findByIdAndPw(id,pwd);
		return dto;
	}

	@Transactional
	@Override
	public void mypageChange(MemberLoginDTO dto) {
		repository.findById(dto.getId())
					.map(e -> e.upate(dto))
					.orElse(null);
	}

}
