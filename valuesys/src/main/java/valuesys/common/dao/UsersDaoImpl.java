package valuesys.common.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import valuesys.common.dto.BoardDto;
import valuesys.common.dto.ReplyDto;
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
	
	
	
//	 public int getRowCount(BoardDto boardDto) {
//		return getSqlSession().selectOne("getRowCount", boardDto);
//	}
	
	public int getRowCount(Map<String, Object> map) {
		return getSqlSession().selectOne("getRowCount", map);
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
	
	public void plusReadCount(int boardNumber) {
		getSqlSession().update("plusReadCount", boardNumber);
	}
	
	public void updateBoardProc(BoardDto boardDto) {
		getSqlSession().update("updateBoardProc", boardDto);
	}
	
	public void deleteBoard(int boardNumber) {
		getSqlSession().delete("deleteBoard", boardNumber);
	}
	
	public void boardLike(int boardNumber) {
		getSqlSession().selectOne("boardLike", boardNumber);
	}
	
	public void minusReadCount(int boardNumber) {
		getSqlSession().update("minusReadCount", boardNumber);
	}
	
	
	
	public void replyWrite(ReplyDto replyDto) {
		getSqlSession().insert("replyWrite", replyDto);
	}
	
	public int getReplyCount(int boardNumber) {
		return getSqlSession().selectOne("getReplyCount", boardNumber);
	}
	
	public List<ReplyDto> replyList(Map<String, Object> map) {
		return getSqlSession().selectList("replyList", map);
	}
	
	public void deleteReply(int replyNumber) {
		getSqlSession().delete("deleteReply", replyNumber);
	}
	
	public void updateReply(ReplyDto replyDto) {
		getSqlSession().update("updateReply", replyDto);
	}
	
	
	
	
	public int boardAlarm() {
		return getSqlSession().selectOne("boardAlarm");
	}
	
	
	
	
	
	
}
