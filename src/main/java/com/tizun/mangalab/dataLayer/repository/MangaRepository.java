package com.tizun.mangalab.dataLayer.repository;

import java.util.Optional;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.dataLayer.interfaces.IMangaRepository;
import com.tizun.mangalab.domainLayer.entity.Category;
import com.tizun.mangalab.domainLayer.entity.Manga;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class MangaRepository implements IMangaRepository{
	// define field for entitymanager
	private EntityManager _entityManager;
	// set up constructor injection
	@Autowired
	public MangaRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}
	
	@Override
	public List<Manga> List(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		String theQuery = "from Manga";
		if (searchValue != null && !searchValue.isEmpty()) {
	        theQuery += " where MangaName like :searchValue or Tags like :searchValue";
	    }
		TypedQuery<Manga> qr = _entityManager.createQuery(theQuery, Manga.class); 
	    if (searchValue != null && !searchValue.isEmpty()) {
	        qr.setParameter("searchValue", "%" + searchValue + "%");
	    }
	    qr.setFirstResult((page - 1) * pageSize);
	    qr.setMaxResults(pageSize);
		return qr.getResultList();
	}

	@Override
	public long Count(String searchValue) {
		// TODO Auto-generated method stub
		String theQuery = "select count(t) from Manga t";
		if (searchValue != null && !searchValue.isEmpty()) {
			theQuery += " where MangaName like :searchValue or Tags like :searchValue";
		}
		TypedQuery<Long> qr = _entityManager.createQuery(theQuery, Long.class);
		if (searchValue != null && !searchValue.isEmpty()) {
	        qr.setParameter("searchValue", "%" + searchValue + "%");
	    }
		return qr.getSingleResult();
	}

	@Override
	public Optional<Manga> Get(int id) {
		// TODO Auto-generated method stub
		String theQuery = "from Manga where MangaID = :id";
		TypedQuery<Manga> qr = _entityManager.createQuery(theQuery, Manga.class);
		qr.setParameter("id", id);
		Manga manga = qr.getSingleResult();
		return Optional.ofNullable(manga);
	}

	@Override
	public boolean Delete(int id) {
		Manga manga = _entityManager.find(Manga.class, id);
		if (manga == null) {
			return false;
		}
		_entityManager.remove(manga);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean InUsed(int id) {
		// TODO Auto-generated method stub
		String theQuery = "SELECT CASE WHEN EXISTS(SELECT 1 FROM Chapter WHERE MangaID = :id) THEN 1 ELSE 0 END";
	    TypedQuery<Integer> qr = _entityManager.createQuery(theQuery, Integer.class);
	    qr.setParameter("id", id);

	    int result = qr.getSingleResult();
	    return result == 1;
	}

	@Override
	public Manga Save(Manga data) {
		// TODO Auto-generated method stub
		Manga savedManga = _entityManager.merge(data);
		return savedManga;
	}

	@Override
	public java.util.List<Manga> List(int page, int pageSize, String searchValue, String orderby, String orderType) {
		// TODO Auto-generated method stub
		StringBuilder theQuery = new StringBuilder("FROM Manga");

	    if (searchValue != null && !searchValue.isEmpty()) {
	        theQuery.append(" WHERE MangaName LIKE :searchValue");
	    }

	    if (orderby != null && !orderby.isEmpty()) {
	        theQuery.append(" ORDER BY ").append(orderby); 

	        // Kiểm tra orderType hợp lệ
	        if ("DESC".equalsIgnoreCase(orderType)) {
	            theQuery.append(" DESC");
	        } else {
	            theQuery.append(" ASC");
	        }
	    }

	    TypedQuery<Manga> qr = _entityManager.createQuery(theQuery.toString(), Manga.class);

	    if (searchValue != null && !searchValue.isEmpty()) {
	        qr.setParameter("searchValue", "%" + searchValue + "%");
	    }

	    qr.setFirstResult((page - 1) * pageSize);
	    qr.setMaxResults(pageSize);

	    return qr.getResultList();
	}
}
