package service;

import lombok.NonNull;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CSVService {

    private static CSVService controller;
    private String uri;
    private Document data = null;
    private List<Element> listaElementos = new ArrayList<>();
    private BufferedReader bf =null;

    private CSVService(String uri) {
        this.uri=uri;
    }

    /**
     * Obtiene una instancia del controlador
     *
     * @param uri
     * @return
     */
    public static CSVService getInstance(@NonNull String uri) {

        if (controller == null)
            controller = new CSVService(uri);
        return controller;
    }

    /**
     * Este método crea el lector necesario para obtener la información del CSV,
     * mientras prepara el elemento Root para la generación del XML
     */
    public void createLector() {
        this.data = new Document();
        this.data.setRootElement(new Element("mediciones"));

        try {
            this.bf = new BufferedReader(new FileReader(this.uri));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Este es el método que mapea los elementos del csv al xml. Utiliza el StringTokenizer para
     * fragmentar la información y así poder añadirla elemento a elemento al xml teniendo en
     * cuenta la posición
     */
    public void convertCSVtoXML() {

        createLector();
        String line;
        try {
            while ((line = this.bf.readLine()) != null) { //***********************************//
                if (!line.contains("provincia")) {
                    line = line.replaceAll(";;", ";null;");
                    StringTokenizer tokens = new StringTokenizer(line, ";");
                    while (tokens.hasMoreTokens()) {
                        Element medicionElement = new Element("medicion");
                        medicionElement.addContent(new Element("provincia").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("municipio").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("estacion").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("magnitud").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("puntoMuestreo").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("anio").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("mes").setText(tokens.nextToken()));
                        medicionElement.addContent(new Element("dia").setText(tokens.nextToken()));
                        int contador = 01;
                        while (tokens.hasMoreTokens()) {

                            medicionElement.addContent(new Element("h" + contador).setText(tokens.nextToken()));
                            medicionElement.addContent(new Element("v" + contador).setText(tokens.nextToken()));
                            contador++;
                        }
                        listaElementos.add(medicionElement);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Element root = this.data.getRootElement();
        root.addContent(listaElementos);

    }

    /**
     * Preparación de los elementos para la escritura xml, utilizando
     * el formato pretty para estructurarlo
     * @return
     */
    private XMLOutputter preProcess() {
        XMLOutputter xmlOutput = new XMLOutputter();
        xmlOutput.setFormat(Format.getPrettyFormat());
        return xmlOutput;
    }

    /**
     * Este método genera el xml con los datos obtenidos del mapeo en la uri especificada en el constructor
     * @param uri
     * @throws IOException
     */
    public void writeXMLFile(String uri) throws IOException {
        XMLOutputter xmlOutput = this.preProcess();
        xmlOutput.output(this.data, new FileWriter(uri));
        System.out.println("Fichero XML generado con éxito");
        controller=null;

    }


}
