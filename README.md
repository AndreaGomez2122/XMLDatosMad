# XMLDatosMad

### Proyecto realizado por Andrea Gómez de Pablo y Mario González Gómez
<img src="https://www.nacion.com/resizer/drZBcSeo8cIAvIrbUX72xZzsBYg=/1440x0/filters:format(jpg):quality(70)/cloudfront-us-east-1.images.arcpublishing.com/gruponacion/OXGPAXOCTZGSTP7O2DVDQ44F4M.jpg" alt="contaminacion" width="500px" height="400px" aling="center"/>

---
# Sobre la aplicación :rocket:
Este programa lee los datos abiertos de la Comunidad de Madrid en materia de Meteorología y Contaminación y genera una serie de informes.
1. La información viene en formato **CSV**. Tras leerlo con **Java Nio2**, hemos dividido sus cadenas en tokens. 


2. Posteriormente las hemos transformado en un **XML**. Para ello, hemos utilizado **JDOM**.


3. Leeremos este mismo XML con JDOM, aprovechando para filtrar los datos por la ciudad que nos interesa. Dos puntos de interés:
    - Como en los datos obtenemos el código numérico de la ciudad, pero no su nombre, hemos creado un *Map* que los relaciona.
    - Para filtrarlo, hemos usado la **API Stream**, y posteriormente mapeamos los elementos a objetos en nuestro programa. Aquí teneis el fragmento de filtrado, siendo *mcc* el Map mencionado.
<pre>listaMediciones.stream().filter(x -> x.getChild("puntoMuestreo").getText().substring(0, 8).equals(mcc.mapearCiudadCodigo().get(ciudad)))...</pre>
</br>

4. Con la información ya en nuestro sistema, empezamos a procesarla para obtener medias y otros datos derivados de interés.

5. Como salida, generaremos un primer informe en formato html. Este contiene la información más detallada, con fechas de generación de los datos, medias, máximos y mínimos registrados, y gráficas para mostrarlo visualmente.
    - Para estas gráficas hemos utilizado la librería **JFreeChart**


6. Por otro lado, también generaremos una base de datos XML con los datos más relevantes, identificando cada conjunto de datos con un UUID.
`String uuid = java.util.UUID.randomUUID().toString();`


7. Por último, haremos una tercera salida de los datos en formato markdown. Es interesante en este apartado el uso de expresiones XPath para la obtención de los datos de nuestro Xml generado:
`XPath xp = XPathFactory.newInstance().newXPath();`
~~~
NodeList ciudades = (NodeList) xp.compile("//resultado").evaluate(d, XPathConstants.NODESET);
~~~

---

# Funcionamiento :robot:

La aplicación está pensada para ser utilizada desde consola a través de un .jar, introduciendo como parámetros el nombre de la ciudad de la que queremos obtener los informes, y la dirección donde queremos guardar el informe html
~~~
java -jar MeteoMadXML.jar Leganés C:\Users\User\Desktop
~~~

---
Pasaos a ver el vídeo explicativo en nuestro [YouTube](https://www.youtube.com/) :smile:

