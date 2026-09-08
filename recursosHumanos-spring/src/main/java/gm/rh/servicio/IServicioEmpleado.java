package gm.rh.servicio;

import gm.rh.modelo.Empleado;

import java.util.List;

public interface IServicioEmpleado {
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer idEmpleado);

    public Empleado guardarEmpleado(Empleado empleadoGuardado);

    public void eliminarEmpleado(Empleado empleadoEliminado);
}
