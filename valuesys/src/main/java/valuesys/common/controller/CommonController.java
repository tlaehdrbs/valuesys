package valuesys.common.controller;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

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
import org.springframework.web.servlet.ModelAndView;

import valuesys.common.dao.UsersDao;
import valuesys.common.dto.BoardDto;
import valuesys.common.dto.UsersDto;
import valuesys.common.util.FileUtil;

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

	// 로그인 기능 수행(세션 userType에서 1 - 일반회원, 2 - 관리자, 3 - WePOS 관리자)
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
	public ModelAndView board(@ModelAttribute BoardDto boardDto)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		int getRowCount = usersDao.getRowCount(boardDto);
		
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
	public String boardWriteProc(HttpServletRequest request, @ModelAttribute BoardDto boardDto) 
	{
		//파일 첨부 유무
		try
		{
			String newName=""; //변경할 파일 이름을 저장하려고
			String filePath=request.getSession().getServletContext().getRealPath("/") + "uploadFile/";
						
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
	public ModelAndView selectBoardList(@ModelAttribute BoardDto boardDto,
			@RequestParam(value="boardNumber") int boardNumber)
	{
		System.out.println("boardNumber="+boardNumber);
		
		BoardDto selectBoard = usersDao.selectBoard(boardNumber);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardDetail");
		mav.addObject("selectBoard", selectBoard);
		return mav;
	}
			
		
	
	
	
	
	
	
	
	
}
