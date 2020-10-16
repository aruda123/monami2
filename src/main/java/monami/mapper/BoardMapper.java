package monami.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import monami.domain.dto.MyBatisBoardRequestDTO;
import monami.domain.dto.MybatisBoardDTO;

@Mapper
public interface BoardMapper {

	void fileUpload(MybatisBoardDTO dto);

	List<MybatisBoardDTO> getList(RowBounds rowBounds);

	MybatisBoardDTO detail(Long no);

	void update(MybatisBoardDTO dto);

	MyBatisBoardRequestDTO update_select(Long no);

	void delete(Long no);

	int getCountOfRows();

}
