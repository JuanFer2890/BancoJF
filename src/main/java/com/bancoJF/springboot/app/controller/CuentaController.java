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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bancoJF.springboot.app.models.dao.ICuentaDao;
import com.bancoJF.springboot.app.models.entity.Cuenta;



@Controller
@SessionAttributes("cuenta")
public class CuentaController {

	@Autowired
	private ICuentaDao cuentaDao;
	
	@RequestMapping(value="/lista-Cuentas", method = RequestMethod.GET)
	public String cuentaLista(Model model, Map<String, Object> modelCuenta)
	{
		Cuenta cuenta = new Cuenta();
		modelCuenta.put("cuenta", cuenta);
		model.addAttribute("titulo", "Lista de cuentas"); //el de string atribute name
		model.addAttribute("cuenta", cuentaDao.findAll());
		return "lista-Cuentas";
	}
	
	@RequestMapping(value = "/form-Cuenta")
	public String crear(Map<String, Object> model)
	{
		Cuenta cuenta = new Cuenta();
		model.put("cuenta", cuenta);
		model.put("titulo", "Nueva cuenta, llene los datos");
		return "form-Cuenta";
	}
	
	@RequestMapping(value = "form-Cuenta/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model)
	{
		Cuenta cuenta = null;
		
		if(id>0)
		{
			cuenta = cuentaDao.findOne(id);
		}
		else
		{
			return "redirect:/index";
		}
		model.put("cuenta", cuenta);
		model.put("titulo", "Edite la cuenta");
		return "form-Cuenta";
	}
	
	@RequestMapping(value = "form-Cuenta", method = RequestMethod.POST)
	public String guardar(@Valid Cuenta cuenta, BindingResult result, Model model, SessionStatus status)
	{
		if(result.hasErrors())
		{
			model.addAttribute("titulo", "Formulario de la cuenta");
			return "form-Cuenta";
		}
		cuentaDao.save(cuenta);
		status.setComplete();
		
		return "redirect:index";
	}
	
	@RequestMapping(value = "/eliminar-Cuenta/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash)
	{
		if(id>0)
		{
			cuentaDao.delete(id);
		}
		return "redirect:index";
	}
	
}
