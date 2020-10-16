package monami.domain.entity.cart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	@Query("select count(id) from Cart where user_id=?1")
	long count(String id);

	@Query("select c from Cart c where user_id=?1")
	List<Cart> findByUser_id(String user_id);

	@Query("delete from Cart c where user_id=?1")
	@Transactional
	@Modifying
	void deleteByUser_id(String user_id);

	@Query("delete from Cart c where no=?1 and user_id=?2")
	@Modifying
	@Transactional
	void cartDelete(long no, String user_id);



}
