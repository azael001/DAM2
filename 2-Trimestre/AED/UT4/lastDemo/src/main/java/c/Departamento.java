package c;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "departamentos")
public class Departamento {
    @Id
    @Column(name = "deptno", nullable = false)
    private BigDecimal id;

    @Column(name = "nombredeptno", nullable = false, length = 15)
    private String nombredeptno;

    @Column(name = "localidad", nullable = false, length = 15)
    private String localidad;

    @OneToMany(mappedBy = "deptno")
    private Set<Empleado> empleados = new LinkedHashSet<>();

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombredeptno() {
        return nombredeptno;
    }

    public void setNombredeptno(String nombredeptno) {
        this.nombredeptno = nombredeptno;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public Set<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }

}