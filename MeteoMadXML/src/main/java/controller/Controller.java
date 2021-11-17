package controller;

import io.HtmlWriter;
import io.MarkdownWriter;
import org.jdom2.JDOMException;
import service.*;

import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

/**
 * Clase que hace las llamadas necesarias para el correcto funcionamiento del programa.
 */
public class Controller {

    /**
     * Este método genera los controladores que van a tratar con los datos tanto csv,como xml, tambien delega la generacion tanto de
     * graficas como de html como de bbdd.
     * @param ciudad
     * @param path
     * @throws IOException
     * @throws JDOMException
     * @throws JAXBException
     */
    public Controller(String ciudad, Path path) throws IOException, JDOMException, JAXBException, XPathExpressionException {

        long initTime = System.currentTimeMillis();

        String uriResources =  System.getProperty("user.dir")  + File.separator + "src" + File.separator + "main" + File.separator + "resources";
        String CSV_meteo =uriResources+ File.separator + "calidad_aire_datos_meteo_mes.csv";
        String CSV_contaminacion = uriResources+ File.separator + "calidad_aire_datos_mes.csv";
        String OUTPUT_temperatura = uriResources+ File.separator + "Temperatura.xml";
        String OUTPUT_contaminacion = uriResources+ File.separator + "Contaminacion.xml";


        CSVService CSVContaminacion = CSVService.getInstance(CSV_contaminacion);
        CSVContaminacion.convertCSVtoXML();
        CSVContaminacion.writeXMLFile(OUTPUT_contaminacion);
        CSVService CSVTemperatura = CSVService.getInstance(CSV_meteo);
        CSVTemperatura.convertCSVtoXML();
        CSVTemperatura.writeXMLFile(OUTPUT_temperatura);



        XMLService xmlTemperatura = XMLService.getInstance(OUTPUT_temperatura);
        xmlTemperatura.loadData();

        XMLService xmlContaminacion = XMLService.getInstance(OUTPUT_contaminacion);
        xmlContaminacion.loadData();


        MeteoService meteo = new MeteoService(xmlTemperatura.getMedicionesPorCiudad(ciudad));
        ContaminacionService conta = new ContaminacionService(xmlContaminacion.getMedicionesPorCiudad(ciudad));


        InformeService informe = InformeService.getInstance();
        informe.generarXMLbbdd(ciudad, meteo, conta);


        GeneradorGraficas gg = new GeneradorGraficas();
        gg.generarGraficas(meteo.getMedicionesMeteo());


        HtmlWriter htmlW = new HtmlWriter();
        htmlW.generarHtml(meteo.getEstatisticsMeteo(), conta.getEstatisticsConta(), initTime, ciudad, path);




       // MarkdownWriter mdw = new MarkdownWriter();
        //mdw.generarMarkdown();
    }

    }

