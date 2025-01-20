# Foro Hub API REST
Reto de AluraLatam para poner en practica lo aprendido sobre Spring boot, creando una API REST desde cero y la persistencia de datos usando BD Mysql

## Descripción
Este proyecto es una solución para el reto de Alura Latam que consiste en crear una API REST para un foro haciendo uso de los conocimientos obtenidos en los cursos anteriores. El objetivo es desarrollar una API para poder realizar la creacion de diferentes topicos y sus respuestas, asi como la creacion de usuarios con sus respectivas contraseñas encriptadas y haciendo uso de la generacion de JWT(JSON Web Token) para autentificarse y poder usar las diferentes consultas de la API REST, toda la informacion son almacenados en una BD de Mysql para poder ser consultados cuando se desee.

## Contenido del repositorio
Este repositorio contiene el código fuente del proyecto, organizado en los siguientes paquetes y archivos:

* **controllers**: Contiene todas las clases necesarias para poder realizar el enrutamiento de cada uno de los componentes del API REST.
* **domain**: Contienen cada uno de los diferentes componentes con sus respectivas clases y records, en el cual se encuentran los usuarios, topicos, respuestas, perfiles y curso, cada uno cuenta con sus propios repositorios con JPA implementado para realizar las consultas en la BD.
* **infra**: Contiene todas las configuraciones necesarias para la seguridad, tiene el manejo de la autentificación del JWT, y de los usuarios y contraseñas, tambien contiene el filtro para bloquear el acceso a las demas rutas del API REST si no se cuenta con un usuario y contraseña registrado en la BD.
  Se encuentran los archivos de configuracion del la dependencia Spring doc el cual permite el uso de Swagger en el proyecto.
* **README.md**: Este archivo de lectura.
* **ForohubApplication**: Es el archivo raiz de la aplicacion Spring desde la cual se va a ejecutar el programa.

## Requisitos previos
Para ejecutar este proyecto, se requiere:

* Instalar java 17 minimo
* Instalar la ultima version de Mysql.
* Crear una BD que se llame foro_hub_alura
* Registrar las variables de entorno de la bd las cuales serian:
    * DB_HOST : Para determinar el host donde se encuentra la BD
    * DB_USER : Es el usuario con el cual se puede acceder a la BD
    * DB_PASSWORD : Es la contraseña del usuario de la BD
    * JWT_SECRET : Es la contraseña secreta que usara el JWT para generar los tokens.

## Uso
Para poder consumir la API REST:

1. Abre el proyecto en un IDE que pueda ejecutar java.
2. Ingrese a la siguiente ruta `src/main/java/com/aluralatamcursos/forohub/ForohubApplication.java` y Ejecute el programa
3. Puede probar las diferentes consultas de la API REST desde la ruta de su aplicacion agregandole `/swagger-ui/index.html`
4. Primero si no se tienen ningun registro en la BD se necesita crear un usuario nuevo en la tabla `usuarios`,un curso en la tabla `cursos` y un nuevo perfil en la tabla `perfiles`, la contraseña del usuario tiene que ser encriptada usando el algoritmo de encriptacion `Bcrypt`
5. Ya generados esos registros con el correo electronico y la contraseña asignada al usuario, en la api rest se procede a generar un JWT desde la ruta `/login`
6. Una ves generado el JWT se tiene que poner en cada una de las peticiones REST que se hagan en la parte de autorizacion se selecciona `Bearer Token`

## Autor
Este proyecto fue creado por Cesar Moises Arteaga German.

## Agradecimientos
Agradezco a Alura Latam por proporcionar este reto y la oportunidad de desarrollar mis habilidades en programación.