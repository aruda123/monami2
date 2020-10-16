package monami.domain.entity;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import monami.domain.dto.MemberLoginDTO;

public interface MemberRepository extends JpaRepository<Member, String>{

	@Query("select m from Member m where id=:id and pwd=:pwd")
	MemberLoginDTO findByIdAndPw(@Param("id")String id, @Param("pwd") String pwd);

	Optional<Member> findByEmail(String email);

	@Query("select m from Member m where name=:name and email=:email")
	MemberLoginDTO findByNameAndEmail(@Param("name")String name, @Param("email")String email);

	@Query("select m from Member m where id=:id and name=:name and email=:email")
	MemberLoginDTO findEmail(@Param("id")String id, @Param("name")String name, @Param("email")String email);

	
	@Query("update Member set pwd=?1 where email=?2")
	@Transactional
	@Modifying
	void updatePassword(String str, String email);

}
