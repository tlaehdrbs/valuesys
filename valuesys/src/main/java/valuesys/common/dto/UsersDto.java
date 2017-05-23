package valuesys.common.dto;

public class UsersDto {

	private String usersId;
	private String usersPassword;
	private String usersName;
	private String usersPhone;
	private String usersEmail;
	
	public String getUsersId() {
		return usersId;
	}
	public void setUsersId(String usersId) {
		this.usersId = usersId;
	}
	public String getUsersPassword() {
		return usersPassword;
	}
	public void setUsersPassword(String usersPassword) {
		this.usersPassword = usersPassword;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getUsersPhone() {
		return usersPhone;
	}
	public void setUsersPhone(String usersPhone) {
		this.usersPhone = usersPhone;
	}
	public String getUsersEmail() {
		return usersEmail;
	}
	public void setUsersEmail(String usersEmail) {
		this.usersEmail = usersEmail;
	}
	
	@Override
	public String toString() {
		return "UsersDto[userId="+usersId+", usersPassword="+usersPassword+", usersName="+usersName+", usersPhone="+usersPhone+", usersEmail="+usersEmail+"]";
	}
	
}
