package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Habitacion;
import pe.edu.upc.spring.model.Aviso;

import pe.edu.upc.spring.service.IHabitacionService;
import pe.edu.upc.spring.service.IAvisoService;


@Controller
@RequestMapping("/aviso")
public class AvisoController {
	
	@Autowired
	private IHabitacionService pService;
	
	@Autowired
	private IAvisoService aService;
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoAvisos(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		return "/aviso/listAviso";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {	

		model.addAttribute("habitacion", new Habitacion());
		model.addAttribute("aviso", new Aviso());
		
		model.addAttribute("listaHabitaciones", pService.listar());
		
		return "/aviso/aviso";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Aviso objAviso, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaHabitaciones", pService.listar());
				
				return "/aviso/aviso";
			}
		else {
			boolean flag = aService.registrar(objAviso);
			if (flag)
				return "redirect:/aviso/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/aviso/irRegistrar";
			}
		}
	}
	
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Aviso> objAviso = aService.buscarId(id);
		if (objAviso == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/aviso/listar";
		}
		else {
			model.addAttribute("listaHabitaciones", pService.listar());
					
			if (objAviso.isPresent())
				objAviso.ifPresent(o -> model.addAttribute("aviso", o));
			
			return "/aviso/aviso";
		}
	}
	@Secured("ROLE_ARRENDADOR")
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaAvisos", aService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaAvisos", aService.listar());
			
		}
		return "/aviso/listAviso";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		
		return "/aviso/listAviso";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Aviso aviso) 
	throws ParseException
	{
		aService.listarId(aviso.getIdAviso());
		return "/aviso/listAviso";
	}	
	
	@RequestMapping("/listaestudiante")
	public String listaestudiante(Map<String, Object> model) {
		model.put("listaAvisos", aService.listar());
		return "/estudiante/listAvisoE";
	}
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("aviso", new Aviso());
		return "/aviso/buscarAviso";
	}
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Aviso aviso)
			throws ParseException
	{
		List<Aviso> listaAvisos; 
		aviso.setRangoPrecio(aviso.getRangoPrecio());
        listaAvisos = aService.buscarPrecio(aviso.getRangoPrecio());
      
        
        if(listaAvisos.isEmpty()) {
            listaAvisos=aService.buscarDistrito(aviso.getRangoPrecio());
        }
        /*if(listaHabitaciones.isEmpty()) {
            listaHabitaciones=pService.buscarArrendador(habitacion.getDesHabitacion());
        }*/
        if(listaAvisos.isEmpty()) {
            model.put("mensaje", "No existen coincidencias");
        }
        model.put("listaAvisos", listaAvisos);
        return "/aviso/buscarAviso";
	}		
}
