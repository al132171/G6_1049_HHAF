package modelo.datos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "dni", "fecha", "titulo","subtitulo", "descripcion"})
@Entity
@NamedQueries({
	@NamedQuery(name="Noticia.encuentraTodas", query = "SELECT n FROM Noticia n"),
    @NamedQuery(name = "Noticia.encuentraPorId", query = "SELECT n FROM Noticia n WHERE n.id = :id"),
	@NamedQuery(name = "Noticia.borraPorId", query = "DELETE FROM Noticia n WHERE n.id = :id")
})

public class Noticia {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlTransient
	private Long id;
	private String dni;
	private String fecha;
	private String titulo;
	private String subtitulo;
	private String descripcion;
	
	public Noticia() {
		super();
	}
	
	public Noticia(Long id, String dni, String fecha, String titulo,
			String subtitulo, String descripcion) {
		this.id = id;
		this.dni = dni;
		this.fecha = fecha;
		this.titulo = titulo;
		this.subtitulo = subtitulo;
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getSubtitulo() {
		return subtitulo;
	}
	
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", dni=" + dni + ", fecha=" + fecha
				+ ", titulo=" + titulo + ", subtitulo=" + subtitulo
				+ ", descripcion=" + descripcion + "]";
	}

}
