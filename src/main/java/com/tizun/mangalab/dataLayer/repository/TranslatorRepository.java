package com.tizun.mangalab.dataLayer.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tizun.mangalab.dataLayer.interfaces.ICommonRepository;
import com.tizun.mangalab.domainLayer.entity.Translator;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class TranslatorRepository implements ICommonRepository<Translator>{
	private EntityManager _entityManager;
	
	@Autowired
	public TranslatorRepository(EntityManager entityManager) {
		_entityManager = entityManager;
	}

	@Override
	public List<Translator> List(int page, int pageSize, String searchValue) {
		// TODO Auto-generated method stub
		String theQuery = "from Translator";
		if (searchValue != null && !searchValue.isEmpty()) {
			theQuery += " where TranslatorName like :searchValue";
		}
		TypedQuery<Translator> qr = _entityManager.createQuery(theQuery, Translator.class);
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
		// TODO Auto-generated method stub
		String theQuery = "select count(t) from Translator t";
		if (searchValue != null && !searchValue.isEmpty()) {
			theQuery += " where TranslatorName like :searchValue";
		}
		TypedQuery<Long> qr = _entityManager.createQuery(theQuery, Long.class);
		if (searchValue != null && !searchValue.isEmpty()) {
	        qr.setParameter("searchValue", "%" + searchValue + "%");
	    }
		return qr.getSingleResult();
	}

	@Override
	public Optional<Translator> Get(int id) {
		// TODO Auto-generated method stub
		String theQuery = "from Translator where TranslatorID = :id";
		TypedQuery<Translator> qr = _entityManager.createQuery(theQuery, Translator.class);
		qr.setParameter("id", id);
		
		return Optional.ofNullable(qr.getSingleResult());
	}

	@Override
	public boolean Delete(int id) {
		// TODO Auto-generated method stub
		Translator translator = _entityManager.find(Translator.class, id);
		if (translator == null) {
			return false;
		}
		_entityManager.remove(translator);
		return true;
	}

	@Override
	public boolean InUsed(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Translator Save(Translator data) {
		// TODO Auto-generated method stub
		Translator newTranslator = _entityManager.merge(data);
		return newTranslator;
	}
}
