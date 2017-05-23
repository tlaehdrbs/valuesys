package valuesys.common.util;

//업로드를 할 때 업로드 할 원본 파일의 이름을 변경, 수정할 때 삭제하기 위해서 필요로 하는 클래스

import java.io.*; //File Class 필요

public class FileUtil {

	public static final String UPLOAD_PATH="C:/Users/kitcoop/git/WePOS/WePOS/src/main/webapp/downloadFile";
	
	//[1]원본 파일의 이름을 변경할 메서드를 작성
	// EX) test.txt or test.TXT => . 을 찾아야 함 => lastIndex(.), substring() 이용
	public static String rename(String boardFile) throws Exception {
		if(boardFile==null) return null; //업로드를 하지 않은 경우
		//규칙 정해주기 => 시스템의 날짜+임의의 숫자를 조합해서 새로운 파일을 생성하자
		String newName=Long.toString(System.currentTimeMillis())+(int)(Math.random()*50); //0~49
		return rename(boardFile, newName);
	}
	
	//1)원래의 이름(originalfilename) 2)새로 변경한 파일 이름
	// EX) text.txt => lastIndexOf(".") => .을 기준으로 끝까지 찾습니다
	public static String rename(String boardFile, String newName) throws Exception {
		if(boardFile==null) return null;
		//확장자 위치 얻기
		int idx=boardFile.lastIndexOf("."); //못찾으면 -1을 리턴
		String extention=""; //확장자만 따로 저장
		String newFileName=""; //새 파일명
		
		if(idx!=-1){
			extention=boardFile.substring(idx);			
		}
		
		//넘어온 파일명 => 확장자가 들어가 있는 경우(text.TXT ==> text.txt)
		int newIdx=newName.lastIndexOf(".");
		
		if(newIdx!=-1){
			newName=newName.substring(0, newIdx); //문자열의 0번째부터 newIdx의 번호 앞번호까지의 문자열
		}
		
		newFileName=newName+extention.toLowerCase(); //확장자가 대문자라면 소문자로 변경
		return newFileName;
	}

	//[2]파일 삭제 => 파일의 정보(위치)를 알아야 합니다 => delete()
	public static void removeFile(String boardFile) {
		File file=new File(UPLOAD_PATH, boardFile); //파일의 경로, 파일명
		if(file.exists()) file.delete(); //존재하면 삭제하라!
	}
	
	public static void removeFile(String fileName, String path)
	{
		File file = new File(path, fileName);
		if(file.exists()) file.delete();
	}

	
}
