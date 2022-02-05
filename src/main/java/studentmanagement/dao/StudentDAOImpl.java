package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.StudentDTO;
import studentmanagement.service.JPAUtil;

@Component
@Repository
public class StudentDAOImpl implements StudentDAO {

	@Override
	public int insertStudent(StudentDTO dto) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.persist(dto);
			em.getTransaction().commit();
			i = 1;
		} finally {
			em.close();
		}
		return i;
	}

	@Override
	public int updateStudent(StudentDTO dto) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			em.merge(dto);
			em.getTransaction().commit();
			i = 1;
		} finally {
			em.close();
		}
		return i;
	}

	@Override
	public int deleteStudent(StudentDTO dto) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			StudentDTO outputDTO = em.find(StudentDTO.class, dto.getStudentId());
			em.remove(outputDTO);
			em.getTransaction().commit();
			i = 1;
		} finally {
			em.close();
		}
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> selectOne(StudentDTO dto) {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select s from StudentDTO s where s.studentId=:studentId or s.studentName=:studentName or s.className=:className").setParameter("studentId", dto.getStudentId()).setParameter("studentName", dto.getStudentName()).setParameter("className", dto.getClassName()).getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StudentDTO> selectAll() {
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select s from StudentDTO s").getResultList();
		} finally {
			em.close();
		}
		return list;
	}

}
