package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity 
@Table(name="arrendador") 
public class Arrendador implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idArrendador; 
	
	@NotEmpty(message = "Ingrese su nombre")
	@Column(name="nomArrendador", nullable=false, length=60)
	private String nomArrendador; 
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Ingrese DNI")
	@Column (name="dni_arrendador", nullable=false, length = 8)
	private String dniArrendador;
	
	@Column(name="edad_arrendador", nullable=false)
	private int edadArrendador;
	
	@Size(min = 6, max = 9)
	@NotEmpty(message = "Ingrese su telefono")
	@Column(name="cel_arrendador", nullable=false, length=9)
	private String celArrendador;
	
	@NotEmpty(message = "Ingrese su correo")
	@Column(name="email_arrendador", nullable=false, length=60)
	private String emailArrendador;
	
	@NotEmpty(message = "Ingrese su Usuario")
	@Column(name="username", nullable=false, length=20)
	private String username;
	
	@NotEmpty(message = "Ingrese su contraseña")
	@Column(name="password", nullable=false, length=20)
	private String password;

	public Arrendador() {
		super();
	}

	public Arrendador(int idArrendador, String nomArrendador, String dniArrendador, int edadArrendador,
			String celArrendador, String emailArrendador, String username, String password) {
		super();
		this.idArrendador = idArrendador;
		this.nomArrendador = nomArrendador;
		this.dniArrendador = dniArrendador;
		this.edadArrendador = edadArrendador;
		this.celArrendador = celArrendador;
		this.emailArrendador = emailArrendador;
		this.username = username;
		this.password = password;
	}

	public int getIdArrendador() {
		return idArrendador;
	}

	public void setIdArrendador(int idArrendador) {
		this.idArrendador = idArrendador;
	}

	public String getNomArrendador() {
		return nomArrendador;
	}

	public void setNomArrendador(String nomArrendador) {
		this.nomArrendador = nomArrendador;
	}

	public String getDniArrendador() {
		return dniArrendador;
	}

	public void setDniArrendador(String dniArrendador) {
		this.dniArrendador = dniArrendador;
	}

	public int getEdadArrendador() {
		return edadArrendador;
	}

	public void setEdadArrendador(int edadArrendador) {
		this.edadArrendador = edadArrendador;
	}

	public String getCelArrendador() {
		return celArrendador;
	}

	public void setCelArrendador(String celArrendador) {
		this.celArrendador = celArrendador;
	}

	public String getEmailArrendador() {
		return emailArrendador;
	}

	public void setEmailArrendador(String emailArrendador) {
		this.emailArrendador = emailArrendador;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
