import React, { useEffect, useState } from 'react'
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

export default function EditarEmpleado() {

    const urlBase = "http://localhost:8080/rh-app/empleados"; //Ruta base para editar empleado

    let navegacion = useNavigate();

    const {id} = useParams();//


    const [empleado, setEmpleados] = useState({
        nombre:"",
        departamento: "",
        sueldo: ""
    })
    const{nombre, departamento, sueldo} = empleado

    //Hooke
    useEffect(()=>{
        cargarEmpleado();
    },[]); //no se hace peticiones infinitas

     const cargarEmpleado = async () => {
        const resultado = await axios.get(`${urlBase}/${id}`)
        setEmpleados(resultado.data);
     }

    const onInputChange = (e) => {
        //Spread operator ... (expandir los atributos)
        setEmpleados({...empleado, [e.target.name]: e.target.value})
    }

    const onSubmit = async (e) => {
        e.preventDefault();
        await axios.put(`${urlBase}/${id}`, empleado); //ya no es post es put
        //redirigimos a la página de inicio
        navegacion('/');
    }

    return (
        <div className='container'>
            <div className='container text-center' style={{ margin: "30px" }}>
                <h3>Editar Empleado</h3>
            </div>
            <form className='container 'style={{width: "50%"}} onSubmit={(e)=> onSubmit(e)}>
                <div className="mb-3">
                    <label for="nombre" className="form-label">Nombre: </label>
                    <input type="text" className="form-control" id="nombre" name='nombre'
                    value={nombre} onChange={(e)=> onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label for="departamento" className="form-label">Departamento: </label>
                    <input type="text" className="form-control" id="departamento" name='departamento'
                    value={departamento} onChange={(e) => onInputChange(e)}/>
                </div>
                <div className="mb-3">
                    <label for="sueldo" className="form-label">Sueldo: </label>
                    <input type="text" className="form-control" id="sueldo" name='sueldo'
                    value={sueldo} onChange={(e) => onInputChange (e)}/>
                </div>
                
                <div className='text-center'>
                    <button type="submit" className="btn btn-warning btn-sm me-3">Guardar</button>
                    <a href='/' className='btn btn-danger btn-sm'>Regresar</a>
                </div>
                
            </form>
        </div>
    )
}

