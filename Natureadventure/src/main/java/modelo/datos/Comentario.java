package modelo.datos;

/**
 * @author appujimatica
 * Objeto de dominio Comentario
 */

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id","idActividad", "nombreU","contenido","puntuacion"})
@Entity
@NamedQueries({
		
       @NamedQuery(name="Comentario.encuentraTodosActividad", query = "SELECT p FROM Comentario p WHERE p.idActividad=:idActividad"),
      // @NamedQuery(name="Comentario.encuentraTodosUsuario", query = "SELECT p FROM Comentario p WHERE p.idUsuario=:idUsuario"),
       @NamedQuery(name="Comentario.borraPorId", query = "DELETE FROM Comentario p WHERE p.id = :id"),
	})


public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private Long id;
    private Long idActividad;
    private String nombreU;
    private String contenido;
    private int puntuacion;
    public Comentario() {
        super();
    }

	public Comentario(Long id, Long idActividad, String nombreU, String contenido,int puntuacion) {
		this.id = id;
		this.idActividad = idActividad;
		this.nombreU=nombreU;
		this.contenido = contenido;
		this.puntuacion=puntuacion;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdActividad() {
		return idActividad;
	}

	public void setIdActividad(Long idActividad) {
		this.idActividad = idActividad;
	}

	public String getNombreU() {
		return nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}
	
	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
}
