package io;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;


public class InformeXmlReader {

    /**
     * Un método de lectura XML utilizando la parte de SAX de JDOM. Devuelve un Document con los datos
     * que nos serán útiles para procesarlos y sacarlos a distintos informes
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public Document leerInforme() throws IOException, JDOMException {
        SAXBuilder builder = new SAXBuilder();
        Document d = builder.build(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "java" +
                 File.separator + "db" + File.separator + "mediciones.xml");
        return d;
    }
}
