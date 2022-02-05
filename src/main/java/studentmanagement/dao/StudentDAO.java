package studentmanagement.dao;

import java.util.List;

import studentmanagement.dto.StudentDTO;

public interface StudentDAO {

	public int insertStudent(StudentDTO dto);
	
	public int updateStudent(StudentDTO dto);
	
	public int deleteStudent(StudentDTO dto);
	
	public List<StudentDTO> selectOne(StudentDTO dto);
	
	public List<StudentDTO> selectAll();
}
