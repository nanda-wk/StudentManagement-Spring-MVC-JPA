package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.UserDTO;
import studentmanagement.service.JPAUtil;

@Component
@Repository
public class UserDAOImpl implements UserDAO {

	@Override
	public int insertUser(UserDTO dto) {
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
	public int updateUser(UserDTO dto) {
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
	public int deleteUser(UserDTO dto) {
		int i = 0;
		EntityManager em = null;
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			em.getTransaction().begin();
			UserDTO outputDTO = em.find(UserDTO.class, dto.getId());
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
	public List<UserDTO> selectOne(UserDTO dto) {
		EntityManager em = null;
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select u from UserDTO u where u.id=:id or u.name=:name")
					.setParameter("id", dto.getId()).setParameter("name", dto.getName()).getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserDTO> selectAll() {
		EntityManager em = null;
		List<UserDTO> list = new ArrayList<UserDTO>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select u from UserDTO u").getResultList();
		} finally {
			em.close();
		}
		return list;
	}

}
