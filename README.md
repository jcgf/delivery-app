# Delivery API
Delivery API para deliveryapp

# Guia de instalacion
Guia de instalacion de servicio Rest de deliveryapp. Es necesario tener encuenta que esta aplicacion es totalmente para pruebas por lo cual las configuraciones y la guia aqui dada es para una instalacion local en cualquier equipo o servidor. se dejara un despliegue montado enlinea.

## 1. Instalacion de docker
ir al sitio web de [descarga](https://www.docker.com/get-started) y seguir los pasos para instalar docker


## 2. Instalar imagen de mysql
antes de realizar la instalacion de la imagen de mysql verificar que se tenga instalado docker:
```bash
docker version
```
en caso de no tenerlo regresar al paso 1. 
continuamos con la instalacion de la imagen:
```bash
docker pull mysql
```

## 3. Instalar e iniciar contenedor: 
procedemos a instalar el contenedor

  1- descargamos los scripts con los que se crean los contenedores con las bases de datos desde el siguiente [repositorio](https://github.com/jcgf/scripts-contenedores.git), en    este caso solo usaremos el que se llama mysqlV1.yml
  
  2- despues de descargarlo, obtenemos la ruta donde se almaceno el archivo y ejecutamos el siguiente comando, donde "path" es la ruta:  
  ```bash
docker-compose -f "path"\mysqlV1.yml up
  ```
  
  3- luego usamos el comando de docker para obtener el nombre y el id del contenedor el cual seria:
  ```bash
  docker ps
  ```
  
  4- Ejecutamos consola para manejar la instancia de mysql:
  ```bash
docker exec -it "nombrecontenedor" bash
  ```
  
  5- Nos conectamos a mysql:
  ```bash
mysql -u root -ppassword (Sin espacios entre la bandera -p y la clave) 
  ```
  
## 4. Creacion de bd
Ejecutamos el siguiente script para crear la bd:
  ```sql
CREATE DATABASE deliverydb;
  ```

## 5. Instalacion de Gradle
es necesario tener [Gradle](https://gradle.org/install/) instalado desde la version [7.4](https://services.gradle.org/distributions/gradle-7.4-all.zip)

## 6. Ejecutar API
Ejecutar el siguiente comando en la consola:
```bash
gradle bootRun
```



