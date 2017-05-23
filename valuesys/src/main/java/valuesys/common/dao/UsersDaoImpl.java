package valuesys.common.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import valuesys.common.dto.BoardDto;
import valuesys.common.dto.UsersDto;

public class UsersDaoImpl extends SqlSessionDaoSupport implements UsersDao {

	public int registration(UsersDto usersDto) {
		return getSqlSession().insert("registration", usersDto);
	}

	public int userLogin(Map<String, String> loginInfo) {
		return getSqlSession().selectOne("userLogin", loginInfo);
	}
	
	public int mgrLogin(Map<String, String> loginInfo) {
		return getSqlSession().selectOne("mgrLogin", loginInfo);
	}
	
	
	
	 public int getRowCount(BoardDto boardDto) {
		return getSqlSession().selectOne("getRowCount", boardDto);
	}
	
	public List<BoardDto> boardList(Map<String, Object> map) {
		return getSqlSession().selectList("boardList", map);
	}
	
	public void boardInsert(BoardDto boardDto) {
		getSqlSession().insert("boardInsert", boardDto);
	}
	
	public BoardDto selectBoard(int boardNumber) {
		return getSqlSession().selectOne("selectBoard", boardNumber);
	}
	
	
	
}
