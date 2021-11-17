package io;


import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;

public class MarkdownWriter {
    private InformeXmlReader ixr = new InformeXmlReader();
    private List<String> nombreCiudades;
    String nombreDoc;

    /**
     * Un escritor que genera un archivo markdown en resources con los datos aportados en una List<String>
     */
    public void generarMarkdown() throws IOException, XPathExpressionException, JDOMException {
        List<String> texto = procesarMarkdown();
        nombreDoc = "informe";
        nombreCiudades.forEach(x -> nombreDoc = nombreDoc + "-" + x);
        Path path = Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                + "resources" + File.separator + nombreDoc + ".md");
        Files.write(path, texto, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }

    /**
     * Genere una lista de tipo String cargando en ella los datos del informe, precedidos de sus respectivas etiquetas markdown para darle formato.
     * Para la lectura de los datos, utiliza expresiones XPath
     *
     * @return List<String> con el texto del .md
     * @throws XPathExpressionException
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private List<String> procesarMarkdown() throws XPathExpressionException, IOException, JDOMException {
        List<String> textoMd = new LinkedList<>();
        Document d = ixr.leerInforme();
        XPath xp = XPathFactory.newInstance().newXPath();
        NodeList informes = (NodeList) xp.compile("//informe").evaluate(d, XPathConstants.NODESET);

        for (int i = 0; i < informes.getLength(); i++) {

            nombreCiudades.add(xp.compile("./nombreCiudad").evaluate(informes.item(i)));

            textoMd.add("##Informe de " + xp.compile("./nombreCiudad").evaluate(informes.item(i)));
            //textoMd.add("-Fecha de inicio de la medición: " + xp.compile("./fxinicio").evaluate(ciudades.item(i)));
            //textoMd.add("-Fecha de fin de la medición: " + xp.compile("./fxfin").evaluate(ciudades.item(i)));
            textoMd.add("---");
            textoMd.add("##Datos Meteorológicos");

            textoMd.add("####Medias mensuales");
            textoMd.add("-Velocidad del viento: " + xp.compile("./datos_meteorologicos/informacionMeteorologica[1]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Temperatura: " + xp.compile("./datos_meteorologicos/informacionMeteorologica[3]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Humedad Relativa: " + xp.compile("./datos_meteorologicos/informacionMeteorologica[4]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Presión Atmosférica : " + xp.compile("./datos_meteorologicos/informacionMeteorologica[5]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Raciación Solar: " + xp.compile("./datos_meteorologicos/informacionMeteorologica[6]/mediaMensual").evaluate(informes.item(i)));

            textoMd.add("---");

            textoMd.add("##Datos de Contaminación");
            textoMd.add("####Medias mensuales");
            textoMd.add("-Monóxido de nitrógeno: " + xp.compile("./datos_contaminacion/informacionContaminacion[3]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Dióxido de nitrógeno: " + xp.compile("./datos_contaminacion/informacionContaminacion[4]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Partículas en suspensión PM2,5: " + xp.compile("./datos_contaminacion/informacionContaminacion[5]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Partículas en suspensión PM10: " + xp.compile("./datos_contaminacion/informacionContaminacion[6]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Óxido de nitrógeno: " + xp.compile("./datos_contaminacion/informacionContaminacion[7]/mediaMensual").evaluate(informes.item(i)));
            textoMd.add("-Ozono: " + xp.compile("./datos_contaminacion/informacionContaminacion[8]/mediaMensual").evaluate(informes.item(i)));

        }
        return textoMd;
    }
}
