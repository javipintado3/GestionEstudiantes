package com.example.GestionEstudiantes.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.GestionEstudiantes.model.Estudiante;

@Service
public class ServicioEstudiante {
	
	//Lista que utilizamos como base de datos en donde almacenamos los estudiantes.
	private List<Estudiante> listaEstudiantes = new ArrayList<>(List.of(
			new Estudiante("Javier", 16, "3ESO"),
			new Estudiante("Manuel", 18, "1BACH"),
			new Estudiante("Juan", 12, "1ESO"),
			new Estudiante("Pedrito", 13, "2ESO")
			
			));
	
	/**
	 * Metodo que devuelve una lista con todos los estudiantes que hay en listaEstudiantes.
	 * 
	 * @return Una lista de estudiantes.
	 */
	public List<Estudiante> allEstudiantes() {
		return listaEstudiantes;
	}
	
	
	/**
	 * Agrega un nuevo estudiantes a la lista
	 * 
	 * @param e Objeto estudiante para añadir a la lista
	 */
	public void addEstudiante(Estudiante e) {
		listaEstudiantes.add(e);
	}
	
	/**
	 * Metodo busca un estudiante por el nombre que le pases y te devuelve una lista con el.
	 * 
	 * @param nombre Nombre que le pasas del estudiante
	 * 
	 * @return Una lista de estudiantes
	 */
	public List<Estudiante> buscarEstudiantePorNombre(String nombre) {
		
		return listaEstudiantes.stream().filter(e -> e.getNombre().equalsIgnoreCase(nombre))
				.collect(Collectors.toList());
		
	}
	
	/**
	 * Metodo busca un estudiante por el curso que le pases y te devuelve una lista con el.
	 * 
	 * @param curso Curso que le pasas del estudiante
	 * 
	 * @return Una lista de estudiantes
	 */
	public List<Estudiante> filtrarEstudiantePorCurso(String curso) {
		
		return listaEstudiantes.stream()
				.filter(e -> e.getCurso().equals(curso)).collect(Collectors.toList());
	}
	
	/**
	 * Devuelve el promedio de edad de los estudiantes de la lista
	 * 
	 * @return El promedio de la edad de los estudiantes
	 */
	public double promedioEdad() {
		int sumaEdad = 0;
		
		for (Estudiante e: listaEstudiantes) {
			sumaEdad += e.getEdad();	
		}
		
		return sumaEdad / listaEstudiantes.size();
	}
}
