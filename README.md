GET
http://localhost:8084/recursos

POST 
http://localhost:8084/recursos
{
  "nombre": "Avión Cisterna Arauco 01",
  "tipoRecurso": "Aereo",
  "estadoRecurso": "Disponible",
  "incendioId": null
}

PUT
http://localhost:8084/recursos/1/asignar/5
