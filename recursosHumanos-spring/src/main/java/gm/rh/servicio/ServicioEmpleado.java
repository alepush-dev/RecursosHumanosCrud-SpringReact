package gm.rh.servicio;

import gm.rh.modelo.Empleado;
import gm.rh.repositorio.EmpleadoReposotorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServicioEmpleado implements IServicioEmpleado{

    @Autowired
    private EmpleadoReposotorio empleadoRepositorio;


    @Override
    public List<Empleado> listarEmpleados() {
        return empleadoRepositorio.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Integer idEmpleado) {
        Empleado empleado= empleadoRepositorio.findById(idEmpleado).orElse(null);
        return empleado;
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleadoGuardado) {
        Empleado empleado= empleadoRepositorio.save(empleadoGuardado);
        return empleado;
    }

    @Override
    public void eliminarEmpleado(Empleado empleadoEliminado) {
        empleadoRepositorio.delete(empleadoEliminado);
    }
}
