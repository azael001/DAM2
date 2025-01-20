package defaul;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLEADOSDEFI")
public class Empleadosdefi {
    @Id
    @Column(name = "ID_EMPLEADO", nullable = false)
    private Integer id;

    @Column(name = "NOMBRE", length = 50)
    private String nombre;

    @Column(name = "DEPARTAMENTO", length = 30)
    private String departamento;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

}