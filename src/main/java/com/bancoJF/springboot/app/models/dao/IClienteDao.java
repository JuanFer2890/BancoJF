package com.bancoJF.springboot.app.models.dao;
//1
import java.util.List;

import com.bancoJF.springboot.app.models.entity.Cliente;


public interface IClienteDao {

		
		public List<Cliente> findAll();
		
		public void save(Cliente cliente);
		
		public Cliente findOne(Long id);
		
		public void delete(Long id);

	
}
