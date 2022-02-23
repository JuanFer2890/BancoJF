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

import com.bancoJF.springboot.app.models.dao.ITarjetaDao;
import com.bancoJF.springboot.app.models.entity.Tarjeta;

@Controller
public class TarjetaController {

	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@RequestMapping(value="/lista-Tarjeta", method = RequestMethod.GET)
	public String clienteLista(Model model,  Map<String, Object> modelTarjeta)
	{
		Tarjeta tarjeta = new Tarjeta();
		modelTarjeta.put("tarjeta", tarjeta);
		model.addAttribute("titulo", "Lista de tarjetas"); //el de string atribute name
		model.addAttribute("tarjeta", tarjetaDao.findAll());
		return "lista-Tarjeta";
	}
	
	@RequestMapping(value = "/form-Tarjeta")
	public String crear(Map<String, Object> model)
	{
		Tarjeta tarjeta = new Tarjeta();
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Nueva tarjeta, llene los datos");
		return "form-Tarjeta";
	}
	
	@RequestMapping(value = "form-Tarjeta/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model)
	{
		Tarjeta tarjeta = null;
		
		if(id>0)
		{
			tarjeta = tarjetaDao.findOne(id);
		}
		else
		{
			return "redirect:/index";
		}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Edite la tarjeta");
		return "form-Tarjeta";
	}
	
	@RequestMapping(value = "form-Tarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status)
	{
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Formulario de la tarjeta");
			return "form-Tarjeta";
		}
		tarjetaDao.save(tarjeta);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/eliminar-Tarjeta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id)
	{
		if(id>0)
		{
			tarjetaDao.delete(id);
		}
		return "redirect:index";
	}
	
}
