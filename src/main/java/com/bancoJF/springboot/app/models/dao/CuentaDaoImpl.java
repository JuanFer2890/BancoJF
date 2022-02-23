package com.bancoJF.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bancoJF.springboot.app.models.entity.Cuenta;

@Repository("cuentaDao")
public class CuentaDaoImpl implements ICuentaDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cuenta> findAll() {
		return em.createQuery("from cuentas").getResultList();
	}

	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		if(cuenta.getIdCuenta() != null && cuenta.getIdCuenta() > 0) //que exista la cuenta y que tenga un id mayor a 0
		{
			em.merge(cuenta); //para fusionar los datos que vamos a escribir
		}
		else
		{
			em.persist(cuenta); //si el id es nulo o menor a cero, se crea uno
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Cuenta findOne(Long id) {
		return em.find(Cuenta.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));

	}

}
