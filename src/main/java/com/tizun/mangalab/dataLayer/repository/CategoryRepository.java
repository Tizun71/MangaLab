package com.tizun.mangalab.dataLayer.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class CategoryRepository implements ICommonRepository<Category> {
	private EntityManager _entityManager;
	
	public CategoryRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	
	@Override
	public java.util.List<Category> List(int page, int pageSize, String searchValue) {
		String theQuery = "from Category";
		if (searchValue != null && !searchValue.isEmpty()) {
			theQuery += "where CategoryName like :searchValue";
		}
		TypedQuery<Category> qr = _entityManager.createQuery(theQuery, Category.class);
		if (searchValue != null && !searchValue.isEmpty()) {
			qr.setParameter("searchValue", "%" + searchValue + "%");
		}
		if (pageSize != 0) {
			qr.setFirstResult((page - 1) * pageSize);
			qr.setMaxResults(pageSize);
		}
		return qr.getResultList();
	}

	@Override
	public long Count(String searchValue) {
		String theQuery = "select count(t) from Category t ";
		if (searchValue != null && !searchValue.isEmpty()) {
			theQuery += "where CategoryName like :searchValue";
		}
		TypedQuery<Long> qr = _entityManager.createQuery(theQuery, Long.class);
		if (searchValue != null && !searchValue.isEmpty()) {
			qr.setParameter("searchValue", "%" + searchValue + "%");
		}
		return qr.getSingleResult();
	}

	@Override
	public Optional<Category> Get(int id) {
		Category category = _entityManager.find(Category.class, id);
		return Optional.ofNullable(category);
	}

	@Override
	public boolean Delete(int id) {
		Category category = _entityManager.find(Category.class, id);
		if (category == null) {
			return false;
		}
		_entityManager.remove(category);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean InUsed(int id) {
	    String theQuery = "SELECT CASE WHEN EXISTS(SELECT 1 FROM Manga WHERE CategoryID = :id) THEN 1 ELSE 0 END";
	    TypedQuery<Long> qr = _entityManager.createQuery(theQuery, Long.class);
	    qr.setParameter("id", id);

	    long result = qr.getSingleResult();
	    return result == 1;
	}

	@Override
	public Category Save(Category data) {
		// TODO Auto-generated method stub
		Category newCategory = _entityManager.merge(data);
		return newCategory;
	}
}
