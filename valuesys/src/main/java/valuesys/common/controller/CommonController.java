package valuesys.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import valuesys.common.dao.UsersDao;
import valuesys.common.dto.BoardDto;
import valuesys.common.dto.ReplyDto;
import valuesys.common.dto.UsersDto;
import valuesys.common.util.FileUtil;
import valuesys.common.util.PagingUtil;

@Controller
public class CommonController {

	@Autowired
	private UsersDao usersDao;
	
	// 메인 페이지로 이동
	@RequestMapping("/view/main.do")
	public String mainView()
	{
		return "main";
	}
	
	// 회원가입 페이지로 이동
	@RequestMapping("/view/registration.do")
	public String registration()
	{
		return "registration";
	}
	
	// 회원가입 진행
	@RequestMapping(value="/view/registration.do",method=RequestMethod.POST)
	public ModelAndView registrationProcess(@ModelAttribute UsersDto usersDto)
	{
		ModelAndView mav = new ModelAndView();

		int regResult = usersDao.registration(usersDto);
		mav.addObject(regResult);
		
		if (regResult==1)
		{
			mav.setViewName("login");
		}
		else
		{
			mav.setViewName("main");
		}
		
		return mav;
	}
	
	// 로그인 페이지로 이동
	@RequestMapping("/view/login.do")
	public String login()
	{
		return "login";
	}

	// 로그인 기능 수행
	@RequestMapping(value="/view/login.do", method=RequestMethod.POST)
	public String loginProcess(HttpSession session, HttpServletResponse response,
			@RequestParam("id") String id, @RequestParam("password") String password,
			@RequestParam("checkState") Boolean checkState) throws IOException, Exception
	{
		Map<String, String> loginInfo = new HashMap<String, String>();
		loginInfo.put("id", id);
		loginInfo.put("password", password);
		
		String result = "";

		int count = usersDao.userLogin(loginInfo);
		if(count == 0)
		{
			count = usersDao.mgrLogin(loginInfo);
			if(count == 0)
			{
				result = "loginFail";
			}
			else
			{
				/*MgrLoginDto mgrLoginDto = new MgrLoginDto();
				mgrLoginDto.setMgrId(id);
				mgrLoginDto.setMgrLoginState('1');
				commonDao.mgrLoginLog(mgrLoginDto);
				
				int shopCode = commonDao.mgrShopCode(id);
				session.setAttribute("userType", 2);
				session.setAttribute("shopCode", shopCode);	*/		
				
				session.setAttribute("id", id);
			
				Cookie cookie = new Cookie("id", id);
				if(checkState)
				{
					cookie.setMaxAge(60 * 60 * 24);
				}
				else
				{
					cookie.setMaxAge(0);
				}					
				response.addCookie(cookie);				
				
				result = "main";	
			}
		}
		else
		{
			/*UserLoginDto userLoginDto = new UserLoginDto();
			userLoginDto.setUserId(id);
			userLoginDto.setUserLoginState('1');
			commonDao.userLoginLog(userLoginDto);		
									
			int userGradeCode = commonDao.userGradeCode(id);
						
			if(userGradeCode == 0)
			{
				session.setAttribute("userType", 3);
			}
			else
			{
				session.setAttribute("userType", 1);
			}*/
			
			session.setAttribute("id", id);
			
			Cookie cookie = new Cookie("id", id);
			if(checkState)
			{
				cookie.setMaxAge(60 * 60 * 24);
			}
			else
			{
				cookie.setMaxAge(0);
			}					
			response.addCookie(cookie);	
			
			result = "main";	
		}
		return result;
	}
	
	// 로그아웃 기능 수행
		@RequestMapping("/view/logout.do")
		public String logoutProcess(HttpSession session)
		{
			/*String id = (String)session.getAttribute("id");
			int userType = (Integer) session.getAttribute("userType");
			
			if(userType == 1)
			{
				UserLoginDto userLoginDto = new UserLoginDto();
				userLoginDto.setUserId(id);
				userLoginDto.setUserLoginState('0');
				commonDao.userLoginLog(userLoginDto);			
			}
			else if(userType == 2)
			{
				MgrLoginDto mgrLoginDto = new MgrLoginDto();
				mgrLoginDto.setMgrId(id);
				mgrLoginDto.setMgrLoginState('0');
				commonDao.mgrLoginLog(mgrLoginDto);
			}*/
			session.invalidate();
			
			return  "main";
		}
	
	//자유게시판으로 이동
	@RequestMapping("/view/board.do")
	public ModelAndView board(@RequestParam(value="pageNum", defaultValue="1") int currentPage,
			@RequestParam(value="keyField", defaultValue="") String keyField, @RequestParam(value="keyWord", defaultValue="") String keyWord,
			@ModelAttribute BoardDto boardDto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("keyField", keyField); //검색분야
		map.put("keyWord", keyWord); //검색어
		
//		int getRowCount = usersDao.getRowCount(boardDto);
		int getRowCount = usersDao.getRowCount(map);
		
		
		//추가
		String paramString = null;
		if("".equals(keyField) && "".equals(keyWord))
		{
			paramString = null;
		}
		else
		{
			paramString = "?keyField=" + keyField + "&keyWord=" + keyWord;
		}
		
		PagingUtil page = new PagingUtil(paramString, currentPage, getRowCount, 5, 5, "board.do");
				
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		
		
		//기존
		List<BoardDto> boardList =null;
		
		if(getRowCount > 0)
		{			
			boardList = usersDao.boardList(map);
		}
		else
		{
			boardList = Collections.emptyList();
		}	
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board");
		mav.addObject("getRowCount", getRowCount);
		mav.addObject("boardList", boardList);
		mav.addObject("pagingHtml", page.getPagingHtml());
		return mav;
	}
	
	//글쓰기 페이지로 이동
	@RequestMapping("/view/boardWrite.do") 
	public String boardWriteView( ) 
	{
		return "boardWrite";
	}
	
	//글쓰기 진행
	@RequestMapping(value="/view/BoardWrite.do", method=RequestMethod.POST)
	public String boardWriteProc(HttpServletRequest request, @ModelAttribute BoardDto boardDto,
			MultipartHttpServletRequest mhsp) throws IllegalStateException, IOException 
	{
		//파일 첨부 유무
		try
		{
			String newName=""; //변경할 파일 이름을 저장하려고
			String filePath=request.getSession().getServletContext().getRealPath("/") + "uploadFile/";
						
			//멀티 파일 업로드 코딩 추가
//			List<MultipartFile> mf = mhsp.getFiles("uploadFile");
//			if(mf.size() == 1 && mf.get(0).getOriginalFilename().equals(""))
//			{
//				
//			}
//			else
//			{
//				for(int i = 0; i < mf.size(); i++)
//				{
//					String genId = UUID.randomUUID().toString(); 
//					String originalfileName = mf.get(i).getOriginalFilename(); 
//					String saveFileName = genId + "." + getExtension(originalfileName);
//				}
//			}

			
			
			//업로드된 파일이 존재한다면
			if(!boardDto.getUpload().isEmpty())  //!를 주어서 isEmpty를 부정
			{ 
				newName=FileUtil.rename(boardDto.getUpload().getOriginalFilename());			
				//DTO의 객체 filename => 변경된 파일명 => 실제로 DB상의 filename				
				boardDto.setBoardFile(newName);
												
				// 실제 업로드 기능 => 업로드된 변경된 파일 => 지정한 업로드 위치로 이동시키기(복사해서!)
				// File file=new File(FileUtil.UPLOAD_PATH+"/"+newName);
				File file = new File(filePath + newName);
				// boardDto.getUpload().transferTo(file);
								
				//전송
				boardDto.getUpload().transferTo(file); //~transferTo(전송할객체명)
			}
			usersDao.boardInsert(boardDto);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:/view/board.do";
	}
	
	//게시판 상세보기
	@RequestMapping("/view/boardDetail.do")
	public ModelAndView selectBoardList(HttpServletRequest request,
			@ModelAttribute BoardDto boardDto,
			@RequestParam(value="boardNumber") int boardNumber)
	{
		String filePath = request.getSession().getServletContext().getRealPath("/") + "uploadFile\\";
		int index = filePath.lastIndexOf("\\valuesys");
		filePath = filePath.substring(index);
		
		usersDao.plusReadCount(boardNumber);
		
		BoardDto selectBoard = usersDao.selectBoard(boardNumber);
		
		if(selectBoard.getBoardFile()!=null)
		{
			String fileName=selectBoard.getBoardFile();
			selectBoard.setBoardFile(filePath + fileName);
		}
		
		//해당 글의 댓글을 불러옵니다 => boardReply.do에서 실행
//		ReplyDto replyDto=new ReplyDto();
//		List<ReplyDto> replyList=null;
//		
//		int repCount = usersDao.getReplyCount(boardNumber);		
//		
//		if(repCount > 0){
//			replyList = usersDao.replyList(boardNumber);
//		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardDetail");
		mav.addObject("selectBoard", selectBoard);
//		mav.addObject("replyList", replyList);
		return mav;
	}
			
	//게시판 수정하기
	@RequestMapping(value="/view/boardUpdate.do", method=RequestMethod.GET)
	 public ModelAndView boardUpdate(@ModelAttribute BoardDto boardDto,
			 @RequestParam(value="boardNumber") int boardNumber)
	 {
		boardDto = usersDao.selectBoard(boardNumber);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardUpdate");
		mav.addObject("boardDto", boardDto);
		System.out.println("boardFile="+boardDto.getBoardFile());
		return mav;
	 }
	 
	
	// 글 수정 기능 수행
	@RequestMapping(value="/view/boardUpdateProc.do", method=RequestMethod.POST)
	public String boardUpdateProcess(HttpServletRequest request,
			@ModelAttribute BoardDto boardDto, @RequestParam(value="boardNumber") int boardNumber) throws IOException, Exception
	{
		BoardDto board = null;
		board=usersDao.selectBoard(boardNumber);
		
		String filePath = request.getSession().getServletContext().getRealPath("/") + "uploadFile/";
		String oldFileName = boardDto.getBoardFile();
	
		if(!boardDto.getUpload().isEmpty())
		{
			boardDto.setBoardFile(FileUtil.rename(boardDto.getUpload().getOriginalFilename()));
			File file = new File(filePath + boardDto.getBoardFile());
			boardDto.getUpload().transferTo(file);
			if(oldFileName != null)
			{
				FileUtil.removeFile(oldFileName, filePath);
			}
		}
		else
		{
			boardDto.setBoardFile(oldFileName);
		}
		
		usersDao.updateBoardProc(boardDto);
		
		return "redirect:/view/boardDetail.do?boardNumber=" + boardDto.getBoardNumber();
	}
	
	//글 삭제 기능
	@RequestMapping("/view/boardDelete.do")
	public String boardDelete(@RequestParam(value="boardNumber") int boardNumber)
	{
		usersDao.deleteBoard(boardNumber);
		return "redirect:/view/board.do";
	}
	
	//글 좋아요 기능
//	@RequestMapping(value="/view/boardLike.do", method=RequestMethod.POST)
//	public String boardList(@RequestParam(value="boardNumber") int boardNumber)
//	{
//		usersDao.boardLike(boardNumber);
//		//return "redirect:/view/boardDetail.do?boardNumber="+boardNumber;
//		return "boardDetail.do?boardNumber="+boardNumber;
//	}
	//글 좋아요 기능
	@RequestMapping(value="/view/boardLike.do", method=RequestMethod.POST)
	public ModelAndView boardList(@ModelAttribute BoardDto boardDto, @RequestParam(value="boardNumber") int boardNumber)
	{
		
		usersDao.boardLike(boardNumber);
		usersDao.minusReadCount(boardNumber);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardDetail");
		mav.addObject("boardNumber", boardNumber);
		return mav;
	}
	
	//매장 자유게시판 댓글 작성
	@RequestMapping(value="/view/boardReplyWrite.do", method=RequestMethod.POST)
	public String shopBoardReplyWriteProcess(HttpSession session, @ModelAttribute ReplyDto replyDto)
	{
		replyDto.setReplyWriter((String)session.getAttribute("id"));
		usersDao.replyWrite(replyDto);
		//return "redirect:/view/boardDetail.do?boardNumber=" + replyDto.getBoardNumber();
		return "redirect:/view/boardReply.do?boardNumber=" + replyDto.getBoardNumber();
	}
	
	//매장 자유게시판 댓글
	@RequestMapping("/view/boardReply.do")
	public ModelAndView boardReplyView(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam("boardNumber") int boardNumber)
	{
		int boardReplyCount = usersDao.getReplyCount(boardNumber);
		
		String paramString = "javascript:replyListPaging";
		PagingUtil page = new PagingUtil(paramString, currentPage, boardReplyCount, 5, 5, null);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardNumber", boardNumber);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<ReplyDto> boardReplyList=null;
		if(boardReplyCount > 0)
		{
			boardReplyList = usersDao.replyList(map);
		}
		else
		{
			boardReplyList = Collections.emptyList();
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardReply");
		mav.addObject("boardReplyList", boardReplyList);
		mav.addObject("boardReplyCount", boardReplyCount);
		mav.addObject("pagingHtml", page.getPagingHtml());
		mav.addObject("pageNum", currentPage);
		return mav;
	}
	
	//댓글 삭제
	@RequestMapping("/view/deleteReply.do")
	public String deleteReply(@RequestParam(value="replyNumber") int replyNumber, @RequestParam(value="boardNumber") int boardNumber)
	{
		usersDao.deleteReply(replyNumber);
		
		return "redirect:/view/boardDetail.do?boardNumber=" + boardNumber;
	}
	
	//댓글 수정
	@RequestMapping("/view/updateReply.do")
	public String updateReply(@ModelAttribute ReplyDto replyDto)
	{
		usersDao.updateReply(replyDto);
		return "redirect:/view/boardDetail.do?boardNumber=" + replyDto.getBoardNumber();
	}
 	
	
	
	//조회수 0건 글 알림
	@RequestMapping("/view/boardAlarm.do")
	public ModelAndView boardAlarm()
	{
		int boardAlarmCount = usersDao.boardAlarm();
		System.out.println("컨트롤러 호출 확인");
		System.out.println("boardAlarmCount="+boardAlarmCount);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTodayAlarm");
		mav.addObject("boardAlarmCount", boardAlarmCount);
		return mav;
		
		//return "boardTodayAlarm.do?boardAlarmCount=" + boardAlarmCount;
	}
	
	
	
	
	
}
