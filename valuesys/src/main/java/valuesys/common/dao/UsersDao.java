package valuesys.common.dao;

import java.util.List;
import java.util.Map;

import valuesys.common.dto.BoardDto;
import valuesys.common.dto.UsersDto;

public interface UsersDao {
	
	//회원가입
	public int registration(UsersDto usersDto);
	
	//회원 로그인 검사
	public int userLogin(Map<String, String> loginInfo);
	
	//관리자 로그인 검사
	public int mgrLogin(Map<String, String>  loginInfo);
	
	
	
	//게시판 글 조회
	public int getRowCount(BoardDto boardDto);
	
	//게시판 글 목록
	public List<BoardDto> boardList(Map<String, Object> map);
	
	//게시판 글 입력
	public void boardInsert(BoardDto boardDto);
	
	//게시판 해당글 상세보기
	public BoardDto selectBoard(int boardNumber);
	
	
}
