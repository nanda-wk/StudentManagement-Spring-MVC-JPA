package studentmanagement.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import studentmanagement.dto.ClassDTO;
import studentmanagement.service.JPAUtil;

@Component
@Repository
public class ClassDAOImpl implements ClassDAO {

	@Override
	public int insertClass(ClassDTO dto) {
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

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDTO> selectOne(ClassDTO dto) {
		EntityManager em = null;
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select c from ClassDTO c where c.id=:id or c.name=:name").setParameter("id", dto.getId()).setParameter("name", dto.getName()).getResultList();
		} finally {
			em.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDTO> selectAll() {
		EntityManager em = null;
		List<ClassDTO> list = new ArrayList<ClassDTO>();
		try {
			em = JPAUtil.getEntityManagerFactory().createEntityManager();
			list = em.createQuery("select c from ClassDTO c").getResultList();
		} finally {
			em.close();
		}
		return list;
	}

}
