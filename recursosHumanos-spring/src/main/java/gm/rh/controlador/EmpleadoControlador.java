package gm.rh.controlador;

import gm.rh.excepcion.RecursoNoEncontradoExcepcion;
import gm.rh.modelo.Empleado;
import gm.rh.servicio.IServicioEmpleado;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("rh-app") //http://localhost:8080/rh-app/
@CrossOrigin(value= "http://localhost:3000") //Local host por default de React
public class EmpleadoControlador {
    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IServicioEmpleado servicioEmpleado;

    @GetMapping("/empleados") //http://localhost:8080/rh-app/empleados
    public List<Empleado> obtenerEmpleados() {
        var empleados = servicioEmpleado.listarEmpleados();
        empleados.forEach((empleado -> logger.info(empleado.toString())));
        return empleados;
    }

    @PostMapping("/empleados")
    public Empleado agregarEmpleado(@RequestBody Empleado empleadoAgregado) {
        logger.info("Empleado a agregar: " + empleadoAgregado);
        return servicioEmpleado.guardarEmpleado(empleadoAgregado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = servicioEmpleado.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el id: " + id);
        } else {
            return ResponseEntity.ok(empleado);
        }
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleadoRecibido) {
        Empleado empleado = servicioEmpleado.buscarEmpleadoPorId(id);
        if (empleado == null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
            empleado.setNombre(empleadoRecibido.getNombre());
            empleado.setDepartamento(empleadoRecibido.getDepartamento());
            empleado.setSueldo(empleadoRecibido.getSueldo());
            servicioEmpleado.guardarEmpleado(empleado);
            return ResponseEntity.ok(empleado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id){
        Empleado empleado = servicioEmpleado.buscarEmpleadoPorId(id);
        if (empleado==null)
            throw new RecursoNoEncontradoExcepcion("El id recibido no existe: " + id);
            servicioEmpleado.eliminarEmpleado(empleado);
            //json {"eliminado" : "true"}
            Map<String, Boolean> respuesta= new HashMap<>();
            respuesta.put("eliminado", Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
    }

}
