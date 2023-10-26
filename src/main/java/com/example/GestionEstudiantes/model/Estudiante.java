package com.example.GestionEstudiantes.model;

/**
 * Clase que instancia un Estudiante
 */
public class Estudiante {

	/**
	 * Nombre del estudiante
	 */
	private String nombre;
	
	/**
	 * Edad del estudiante
	 */
	private Integer edad;
	
	/**
	 * Curso del estudiante
	 */
	private String curso;
	
	/**
	 * Constructor por defecto
	 */
	public Estudiante() {
		
	}
	
	
	/**
	 * Metodo constructor de la clase
	 * @param nombre Nombre del estudiante
	 * @param edad Edad del estudiante
	 * @param curso curso del estudiante
	 */
	public Estudiante(String nombre, Integer edad, String curso) {
		super();
		this.nombre = nombre;
		this.edad = edad;
		this.curso = curso;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", edad=" + edad + ", curso=" + curso + "]";
	}
	
	
	
	
}
