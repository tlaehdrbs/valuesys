package valuesys.common.dao;

import java.util.List;
import java.util.Map;

import valuesys.common.dto.BoardDto;
import valuesys.common.dto.ReplyDto;
import valuesys.common.dto.UsersDto;

public interface UsersDao {
	
	//회원가입
	public int registration(UsersDto usersDto);
	
	//회원 로그인 검사
	public int userLogin(Map<String, String> loginInfo);
	
	//관리자 로그인 검사
	public int mgrLogin(Map<String, String>  loginInfo);
	
	
	
	//게시판 글 레코드수 조회
//	public int getRowCount(BoardDto boardDto);
	public int getRowCount(Map<String, Object> map);
	
	//게시판 글 목록
	public List<BoardDto> boardList(Map<String, Object> map);
	
	//게시판 글 입력
	public void boardInsert(BoardDto boardDto);
	
	//게시판 해당글 상세보기
	public BoardDto selectBoard(int boardNumber);
	
	//조회수 증가
	public void plusReadCount(int boardNumber);
	
	//게시글 수정 진행
	public void updateBoardProc(BoardDto boardDto);
	
	//게시글 삭제
	public void deleteBoard(int boardNumber);
	
	//글 좋아요 
	public void boardLike(int boardNumber);
	
	//좋아요 눌렀을때 조회수 감소
	public void minusReadCount(int boardNumber);
	
	
	
	//댓글 작성
	public void replyWrite(ReplyDto replyDto);
	
	//댓글 갯수 확인
	public int getReplyCount(int boardNumber);
	
	//댓글 목록 불러옴
	public List<ReplyDto> replyList(Map<String, Object> map);
	
	//댓글 삭제
	public void deleteReply(int replyNumber);
	
	//댓글 수정
	public void updateReply(ReplyDto replyDto);
	
	
	
	//오늘 등록한 글의 갯수
	public int boardAlarm();
	
	
	
	
	
}
