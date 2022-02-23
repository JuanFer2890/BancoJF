package com.bancoJF.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.bancoJF.springboot.app.models.entity.Cliente;

@Repository("clienteDao")
public class ClienteDaoImpl implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return em.createQuery("from clientes").getResultList();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		if(cliente.getIdCliente() != null && cliente.getIdCliente() > 0) //que exista un cliente y que tenga un id mayor a 0
		{
			em.merge(cliente); //para fusionar los datos que vamos a escribir
		}
		else
		{
			em.persist(cliente); //si el id es nulo o menor a cero, se crea uno
		}
	}

	@Override
	//@Transactional(readOnly = true)
	public Cliente findOne(Long id) {
		return em.find(Cliente.class, id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
