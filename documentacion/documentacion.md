/*

  Esta archivo pertenece a la aplicación "generador de objetivos" bajo licencia GPLv2.
  Copyright (C) 2013 Óscar R. Zafra Megías.

  Este programa es software libre. Puede redistribuirlo y/o modificarlo bajo los términos 
  de la Licencia Pública General de GNU según es publicada por la Free Software Foundation, 
  bien de la versión 2 de dicha Licencia o bien (según su elección) de cualquier versión 
  posterior.

  Este programa se distribuye con la esperanza de que sea útil, pero SIN NINGUNA GARANTÍA, 
  incluso sin la garantía MERCANTIL implícita o sin garantizar la CONVENIENCIA PARA UN 
  PROPÓSITO PARTICULAR. Véase la Licencia Pública General de GNU para más detalles.

  Debería haber recibido una copia de la Licencia Pública General junto con este programa. 
  Si no ha sido así, escriba a la Free Software Foundation, Inc., en 675 Mass Ave, Cambridge, 
  MA 02139, EEUU.
*/

# PRÁCTICA 1 : Creación y despliegue de una aplicación en un PaaS

Para el desarrollo de esta práctica he querido utilizar la plataforma [Heroku](www.heroku.com), por tener algo de variedad con los compañeros que casi todos han escogido OpenShift y por ver diferentes plataformas.

### Explicación y cambios a última hora.

De primeras escogí Heroku porque vi que había muchos tutoriales, soporte, para ruby, y eso ha pasado, hay tantas cosas y tan diferentes de hacerlas, que cuando empezaba con uno veía otra diferente y quizá más asequible. Quizá la configuración algo más compleja para un desentendido como yo.
Al final después de muchas horas dando vueltas por páginas y buscando información decidí utilizar Java.

Ya que vi que Heroku también soportaba Java.

### Mi intento de aplicación (Adivina adivinanza...) .

Por falta de tiempo y de saber, no he conseguido vincular una base de datos (MySql, PostGres, etc...) a la aplicación, pero vaya... es cuestión de echarle un rato más.

**Adivina adivinanza:** Trata de un autotest sobre la asignatura, es decir, en el momento que vincule la Base de Datos con la aplicación y pueda meter información. La aplicación hará preguntas sobre la asignatura y el usuario tendrá que contestar de manera muy sencilla (en minúsculas, sin tildes...) para no dar lugar a confusión.

Por lo tanto está a falta de incrustarle PostGresql y añadir preguntas.

Ahora mismo solo tiene una pregunta y es siempre la misma, aunque la hiré cambiando si veo que la gente entra y la responde y le gusta el sistema ( o terminaré de añadir la Base de Datos).

### Tecnología usada:

He usado Java con un framework llamado [wicket](http://wicket.apache.org/), muy potente y para proyectos a largo plazo más que a corto.

He utilizado esta tecnología porque me resulta muy cómodo una vez que empiezas a trabajar con ella, aunque eso si... la configuración puede llegar a ser bastante compleja aunque para eso está la ayuda de [maven](http://maven.apache.org/) [2](http://es.wikipedia.org/wiki/Maven).

Cuando se empieza un proyecto desde cero, maven tiene una muy buena manera de ayudarnos a comenzar y es por medio de arquetipos, son como plantillas hechas por la gente que se pueden utilizar sin problema.



### Empezando con Heroku

El ejercicio 13 viene documentado para registrarse, [aquí el enlace](https://github.com/oskyar/InfraestructuraVirtual/blob/master/Ejercicios07102013.md).

Una vez registrados tenemos que descargarnos [Heroku Toolbelt](https://toolbelt.heroku.com/), una aplicación que es muy parecida a Git y como estamos ya familiarizados nos ayudará a seguir adelante fácilmente.

Después tenemos que loguearnos desde la consola:

	heroku login

	oskyar@oskyar-M60Vp:~/practica1-iv$ heroku login
	Enter your Heroku credentials.
	Email: oskyar@gmail.com
	Password (typing will be hidden): 
	Authentication successful.

y ya estaríamos logueados y casi listos para ponernos manos a la obra.

Ojo al dato, si es la primera vez, puede decirnos lo siguiente.

	Could not find an existing public key.
	Would you like to generate one? [Yn] 
	Generating new SSH public key.
	Uploading ssh public key /Users/adam/.ssh/id_rsa.pub

Esto quiere decir que nosotros deberemos subir nuestra ssh si la tenemos configurada con git, en caso de no tenerla escribimos una "n" y nos genera una para usarla con la aplicación de heroku.

Se sube con el siguiente comando:

	heroku keys:add ~/.ssh/id_rsa.pub 


Hay muchas maneras de crear una aplicación con Heroku, ó desde la consola

	heroku create

Y te asigna un nombre de repositorio al azar (que se puede cambiar después con)

	heroku apps:rename NuevoNombreApp

Se sabe en qué aplicació estás porque se supone que estás dentro de la carpeta de la aplicación.

Ó desde la [página](https://dashboard.heroku.com/apps):

Si no hemos iniciado sesión lo hacemos.

Y ahora tan fácil como seguir las instrucciones.

[captura3](https://github.com/oskyar/Practica1-PaaS/blob/master/documentacion/3.%20creando%20aplicacion%20heroku.png);

1. Escribimos el nombre de la aplicación.
2. Le damos a "Create app" para crearla.

[captura4](https://github.com/oskyar/Practica1-PaaS/blob/master/documentacion/4.%20Nombre%20de%20la%20aplicacion.png);


A continuación podemos hacer dos cosas. 
1. Clonarnos el repositorio y empezar a trabajar.

ó

2. Pinchar en [Get started with Heroku](https://devcenter.heroku.com/articles/quickstart) donde nos enseñarán como crear una aplicación rápidamente y de manera fácil ó eso dicen...

[captura5](https://github.com/oskyar/Practica1-PaaS/blob/master/documentacion/5.%20Terminado%20.png);
	

Tenemos que tener en cuenta que debemos crear un archivo en la raíz de la carpeta de la aplicación llamada "Procfile".

En este archivo guardaremos un script que lo ejecutará la instancia de Heroku al subir nuestra aplicación para que se ejecute.


### Comandos rápidos de la aplicación de heroku

Hay algunas aplicaciones que se tienen que crear así, para la aplicación de Heroku detecte qué Framework utilizamos para que él mismo suba la configuración o los archivos necesarios para desplegar la aplicación en el servidor.
	
	heroku create -s cedar

Cambiar nombre a la aplicación:

	heroku apps:rename NuevoNombre

Eliminar la aplicación:

	heroku apps:destroy NombreAplicacion

	y después pedirá la confirmación del nombre de la aplicación.


Añadir clave ssh a heroku

	heroku keys:add <claveSsh.pub>

Eliminarla

	heroku keys:clean

Ver nuestra aplicación desplegada y que nos redirija al navegador.

	heroku open


Y lo más importante...

	git push heroku master

para que suba todos los cambios y todo lo que necesite el servidor para desplegar la aplicación.


### Aspecto de la [aplicación](http://adivina-adivinanza.herokuapp.com/).

[captura6](https://github.com/oskyar/Practica1-PaaS/blob/master/documentacion/6.%20aplicacion.png)




### Dirección de los repositorios y aplicación.

+ [Repositorio GitHub](https://github.com/oskyar).

+ [Repositorio Práctica 1 - Paas](https://github.com/oskyar/Practica1-PaaS).

+ [Aplicación PaaS - Adivina adivinanza...](http://adivina-adivinanza.herokuapp.com/)


#### Bibliografía

+ [https://github.com/dashorst/heroku-wicket-quickstart](https://github.com/dashorst/heroku-wicket-quickstart)
 
+ [http://www.wicket-library.com/wicket-examples/index.html](http://www.wicket-library.com/wicket-examples/index.html)

+ [https://devcenter.heroku.com/categories/java](https://devcenter.heroku.com/categories/java)

+ [https://devcenter.heroku.com/articles/getting-started-with-java](https://devcenter.heroku.com/articles/getting-started-with-java)
