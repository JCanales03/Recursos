GET
http://localhost:8083/recursos

POST 
http://localhost:8083/recursos
{
  "nombre": "Avión Cisterna Arauco 01",
  "tipoRecurso": "Aereo",
  "estadoRecurso": "Disponible",
  "incendioId": null
}

PUT
http://localhost:8083/recursos/1/asignar/5
