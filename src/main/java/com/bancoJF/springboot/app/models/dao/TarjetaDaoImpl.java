package com.bancoJF.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJF.springboot.app.models.entity.Tarjeta;

@Repository("tarjetaDao")
public class TarjetaDaoImpl implements ITarjetaDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findAll() {
		return em.createQuery("from tarjetas").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarjeta tarjeta) {
		if(tarjeta.getIdTarjeta() != null && tarjeta.getIdTarjeta() > 0) 
		{
			em.merge(tarjeta);
		}
		else
		{
			em.persist(tarjeta);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Tarjeta findOne(Long id) {
		return em.find(Tarjeta.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
