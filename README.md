# Proyecto de Consulta y Gestión de Libros "LiterAlura"

Este proyecto tiene como objetivo crear un sistema que permite consultar libros mediante una API, añadir los datos a una base de datos y realizar diversas consultas sobre los libros y autores registrados. A continuación, se detallan las funcionalidades principales del sistema y su implementación.

## Funcionalidades del Proyecto

1. **Consulta de Libros mediante una API**
   - Se utiliza una API externa para obtener información sobre libros.
   - Los datos obtenidos de la API incluyen detalles como el título, autor, año de publicación, idioma, entre otros.

2. **Almacenamiento de Datos en una Base de Datos**
   - Los datos obtenidos de la API se almacenan en una base de datos para facilitar su gestión y consulta posterior.
   - La base de datos está estructurada para almacenar información tanto de libros como de autores.

3. **Listar Libros Registrados**
   - Permite obtener una lista de todos los libros que han sido registrados en la base de datos.
   - La lista incluye detalles como título, autor, año de publicación, y idioma.

4. **Listar Autores Registrados**
   - Permite obtener una lista de todos los autores que han sido registrados en la base de datos.
   - La lista incluye información como el nombre del autor y su fecha de nacimiento.

5. **Listar Autores Vivos en un Determinado Año**
   - Permite obtener una lista de autores que estaban vivos en un año específico.
   - Esta funcionalidad es útil para analizar la producción literaria en un contexto histórico.

6. **Listar Libros por Idioma**
   - Permite obtener una lista de libros filtrados por el idioma en el que fueron escritos.
   - Esta funcionalidad es útil para usuarios interesados en libros de una lengua específica.
