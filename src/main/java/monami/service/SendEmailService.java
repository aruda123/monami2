package monami.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import monami.domain.dto.MailDTO;
import monami.domain.entity.MemberRepository;

@Service
@AllArgsConstructor
public class SendEmailService {
	
	@Autowired
	private MemberRepository repository;
	
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "aruda123456@gamil.com";
	
	//임시 비밀번호 변경, 보낼 이메일 내용 DTO에 설정 메서드
	public MailDTO createMailAndChangePassword(String email, String name) {
		String str = getTempPassword();
		MailDTO dto = new MailDTO();
		dto.setAddress(email);
		dto.setTitle(email+"님의 모나미연습 사이트 임시비밀번호 안내 이메일 입니다.");
		dto.setMessage("모나미연습 사이트 임시 비밀번호 안내 이메일 입니다." + "임시 비밀번호는 "+str+" 입니다.");
		
		updatePassword(str, email); //임의의 비밀번호로 변경
		return dto;
	}

	//비밀번호 변경 처리 메서드
	public void updatePassword(String str, String email) {
		String pwd = str;
		repository.updatePassword(pwd, email);
	}
	
	//임의의 비밀번호 만드는 메서드
	public String getTempPassword() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
		String str = "";
		int idx = 0;
		for(int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			str += charSet[idx];
		}
		return str;
	}
	
	//이메일 전송 메서드
	public void mailSend(MailDTO mailDto) {
		System.out.println("이메일 전송 완료");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDto.getAddress());
		message.setFrom(SendEmailService.FROM_ADDRESS);
		message.setSubject(mailDto.getTitle());
		message.setText(mailDto.getMessage());
		mailSender.send(message);
	}

}
