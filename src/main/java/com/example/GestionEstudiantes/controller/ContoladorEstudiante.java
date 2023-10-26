package com.example.GestionEstudiantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GestionEstudiantes.model.Estudiante;
import com.example.GestionEstudiantes.servicio.ServicioEstudiante;

@Controller
public class ContoladorEstudiante {

	@Autowired
	private ServicioEstudiante servicio;
	
	/**
	 * Metodo que utiliza el GET para la ruta ("/")
	 * 
	 * Utiliza la lista d estudiantes para mostrarla al completo en la
	 * vista principal.
	 * 
	 * @param model el objeto Model proporcionado por Spring MVC, utilizado para pasar 
	 *               atributos a la vista. 
	 * @return El nombre de la vista principal donde se renderiza
	 */
	@GetMapping("/")
	public String mostrarPrimeraLista(Model model) {
		List<Estudiante> listaDeEstudiantes = servicio.allEstudiantes();
		Double promedio = servicio.promedioEdad();
		
		model.addAttribute("listaEstudiantes", listaDeEstudiantes);
		model.addAttribute("promedioEdad" , promedio);
		
		return "index";
	}
	
	/**
	 * Metodo que utiliza el GET para la ruta ("/buscar")
	 * 
	 * Busca un estudiante por el nombre que le pasas el cliente y lo muestra en la vista principal
	 * 
	 * 
	 * @param nombre Nombre que le pasas por parametro
	 * @param model el objeto Model proporcionado por Spring MVC, utilizado para pasar 
	 *               atributos a la vista.
	 * @return El nombre de la vista principal donde se renderiza
	 */
	@GetMapping("/buscar")
	public String buscarEstudianteNombre(@RequestParam String nombre, Model model) {
		List<Estudiante> listaEstudianteNombre = servicio.buscarEstudiantePorNombre(nombre);
		
		model.addAttribute("listaEstudiantes", listaEstudianteNombre);
		
		return "index";
	}
	
	/**
	 * Metodo que utiliza el GET para la ruta ("/filtrar")
	 * 
	 * Filtra los estudiantes por el curso que le pase el cliente y lo muestra en la vista principal
	 * 
	 * @param curso string que le pasa el cliente
	 * @param model model el objeto Model proporcionado por Spring MVC, utilizado para pasar 
	 *               atributos a la vista.
	 * @return
	 */
	@GetMapping("/filtrar")
	public String filtrarEstudiantePorCurso(@RequestParam String curso, Model model) {
		List<Estudiante> listaEstudianteCurso = servicio.filtrarEstudiantePorCurso(curso);
		
		model.addAttribute("listaEstudiantes", listaEstudianteCurso);
		
		return "index";
	}
	
	/**
	 * Metodo que utiliza el GET para la ruta ("/agregar")
	 * 
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/agregar")
	public String mostrarFormulario(Model model) {
		Estudiante e = new Estudiante();
		
		model.addAttribute("listaEstudiante", e);
		
		Double promedio = servicio.promedioEdad();
		model.addAttribute("promedioEdad" , promedio);
		
		return "formularioAdd";
	}
	
	@PostMapping("/agregar")
    public String agregarEstudiante(@ModelAttribute("listaEstudiante") Estudiante estudiante, BindingResult result) {
    	
    	//Control de errores con condicionales
        if (estudiante.getNombre().trim().isEmpty()) {
            result.rejectValue("nombre", "error.nombre", "El campo nombre no puede estar vacío");
        }
        if (estudiante.getEdad() == null || estudiante.getEdad() <= 0) {
            result.rejectValue("edad", "error.edad", "La edad debe ser un número positivo");
        }
        if (estudiante.getCurso().trim().isEmpty()) {
            result.rejectValue("curso", "error.curso", "El campo curso no puede estar vacío");
        }

        // Si hay errores, volver al formulario
        if (result.hasErrors()) {
            return "formularioAdd";
        }
   
        servicio.addEstudiante(estudiante);
        return "redirect:/"; // redirigir a la página principal
    }
}
