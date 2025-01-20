package c;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "empleados")
public class Empleado {
    @Id
    @Column(name = "empno", nullable = false)
    private BigDecimal id;

    @Column(name = "nombreempno", nullable = false, length = 20)
    private String nombreempno;

    @Column(name = "cargo", length = 10)
    private String cargo;

    @Column(name = "fechaing")
    private LocalDate fechaing;

    @Column(name = "salario")
    private BigDecimal salario;

    @Column(name = "comision")
    private BigDecimal comision;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptno")
    private Departamento deptno;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNombreempno() {
        return nombreempno;
    }

    public void setNombreempno(String nombreempno) {
        this.nombreempno = nombreempno;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public LocalDate getFechaing() {
        return fechaing;
    }

    public void setFechaing(LocalDate fechaing) {
        this.fechaing = fechaing;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public Departamento getDeptno() {
        return deptno;
    }

    public void setDeptno(Departamento deptno) {
        this.deptno = deptno;
    }

}