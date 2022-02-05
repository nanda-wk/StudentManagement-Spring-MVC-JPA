package studentmanagement.dao;

import java.util.List;

import studentmanagement.dto.UserDTO;

public interface UserDAO {
	
	public int insertUser(UserDTO dto);
	
	public int updateUser(UserDTO dto);
	
	public int deleteUser(UserDTO dto);
	
	public List<UserDTO> selectOne(UserDTO dto);
	
	public List<UserDTO> selectAll();

}
