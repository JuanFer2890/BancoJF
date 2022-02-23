package com.bancoJF.springboot.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bancoJF.springboot.app.models.dao.IClienteDao;
import com.bancoJF.springboot.app.models.entity.Cliente;


@Controller
public class ClienteController {

	@Autowired
	private IClienteDao clienteDao;
	
	@RequestMapping(value="/lista-Cliente", method = RequestMethod.GET)
	public String clienteLista(Model model, Map<String, Object> modelCliente)
	{
		Cliente cliente = new Cliente();
		modelCliente.put("cliente", cliente);
		model.addAttribute("titulo", "Lista de clientes"); //el de string atribute name
		model.addAttribute("cliente", clienteDao.findAll());
		return "lista-Cliente";
	}
	
	@RequestMapping(value = "/form-Cliente")
	public String crear(Map<String, Object> model)
	{
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Nuevo cliente, llene los datos");
		return "form-Cliente";
	}
	
	@RequestMapping(value = "form-Cliente/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model)
	{
		Cliente cliente = null;
		
		if(id>0)
		{
			cliente = clienteDao.findOne(id);
		}
		else
		{
			return "redirect:/index";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Edite el cliente");
		return "form-Cliente";
	}
	
	@RequestMapping(value = "form-Cliente", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, SessionStatus status)
	{
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Formulario del cliente");
			return "form-Cliente";
		}
		clienteDao.save(cliente);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/eliminar-Cliente/{id}")
	public String eliminar(@PathVariable(value = "id") Long id)
	{
		if(id>0)
		{
			clienteDao.delete(id);
		}
		return "redirect:index";
	}
}

