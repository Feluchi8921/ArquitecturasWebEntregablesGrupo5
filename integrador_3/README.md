# Arquitecturas Web (TUDAI)- Integrador 2

## Descripción
Este proyecto implementa una API REST utilizando consultas JPQL para gestionar una base de datos de estudiantes, carreras e inscripciones. Los servicios expuestos permiten realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre estos recursos, así como consultas más complejas para obtener información específica.

## Endpoints

### Gestión de Estudiantes
* **A) Dar de alta un estudiante:**
    * **Método:** POST
    * **URL:** http://localhost:8080/estudiantes/
    * **Cuerpo:** JSON con los siguientes campos:
        * nombre (string)
        * apellido (string)
        * dni (string)
        * fechaNacimiento (fecha)
        * género (string)
        * ciudad (string)
    * **Respuesta:** JSON con el estudiante creado, cuyo ID es generado automáticamente.

* **B) Matricular un estudiante en una carrera:**
* **Método:**  POST
    * **URL:** http://localhost:8080/inscripciones/1
  * **Respuesta:** JSON con la inscripcion creada

* **C) Recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple**
* **Método:**  GET
    * **URL:** http://localhost:8080/estudiantes/apellido/Gonzalez
    * **Respuesta:** JSON con todos los estudiantes de apellido Gonzalez
  
* **D) Recuperar un estudiante, en base a su número de libreta universitaria**
* **Método:**  GET
    * **URL:** http://localhost:8080/estudiantes/libreta/223344
    * **Respuesta:** JSON con un estudiante con número de libreta 223344
  
* **E) Recuperar todos los estudiantes, en base a su género**
* **Método:**  GET
    * **URL:** http://localhost:8080/estudiantes/genero/f
    * **Respuesta:** JSON con todas las estudiantes de genero femenino

* **F) Recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos**
* **Método:**  GET
    * **URL:** http://localhost:8080/inscripciones/inscriptos-carreras
    * **Respuesta:** JSON con todas las carreras con estudiantes inscriptos, ordenadas por cantidad de inscriptos

* **G) Recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia**
* **Método:**  GET
    * **URL:** http://localhost:8080/estudiantes/carrera/Fisica/ciudad/Rauch
    * **Respuesta:** JSON con los estudiantes de la ciuad de Rauch

* **H) Generar un reporte de las carreras, que para cada carrera incluya información de los
  inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
  los años de manera cronológica**
* **Método:**  GET
    * **URL:** http://localhost:8080/inscripciones/informe
    * **Respuesta:** JSON con los estudiantes de la ciuad de Rauch

* **Otros métodos del CRUD**:
* Obtener todos los estudiantes: (GET) http://localhost:8080/estudiantes/
* Obtener un estudiante por ID: (GET) http://localhost:8080/estudiantes/1
* Eliminar un estudiante: (DELETE) http://localhost:8080/estudiantes/2
* Obtener todas las carreras: (GET) http://localhost:8080/carreras/
* Obetener una carrera por ID: (GET) http://localhost:8080/carreras/1
* Agregar nueva carrera: (POST) http://localhost:8080/carreras/
* Eliminar carrera: (DELETE) http://localhost:8080/carreras/5

***Nota: En la raíz del proyecto se encuentra el archivo **Integrador 3.postman_collection.json** con los endpoints***

* *Autores: Aguerralde Felicitas, De La Torre Giuiliana, Gramuglia Eliana, Guidi Franco, Rodriguez Farias Julian*


