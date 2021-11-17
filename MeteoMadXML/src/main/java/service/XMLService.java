package service;


import lombok.NonNull;
import model.Medicion;
import model.MedicionHora;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Clase que convierte los xml en objetos con el parser JAXB
 */
public class XMLService {
    private static XMLService controller = null;
    private String uri = null;
    private Document data = null;

    //Se le pasa la uri del XML que vamos a mapear
    private XMLService(String uri) {
        this.uri = uri;
    }

    public static XMLService getInstance(@NonNull String uri) {
        controller = new XMLService(uri);
        return controller;
    }

    /**
     * Carga los datos en el Document
     * @throws IOException
     * @throws JDOMException
     */
    public void loadData() throws IOException, JDOMException {
        data = new Document();
        this.data = data;
        SAXBuilder builder = new SAXBuilder();
        File xmlFile = new File(this.uri);
        this.data = builder.build(xmlFile);
    }

    /**
     * Filtra por ciudad y convierte a objetos un xml
     * @param ciudad
     * @return
     */
    public List<Medicion> getMedicionesPorCiudad(String ciudad) {
        System.out.println("Se van a mapear los XML");
        Element root = this.data.getRootElement();
        List<Element> listaMediciones = root.getChildren("medicion");
        List<Medicion> medicionesLista = new ArrayList<>();

        MapeoCiudadCodigo mcc = new MapeoCiudadCodigo();

        listaMediciones.stream().filter(x -> x.getChild("puntoMuestreo").getText().substring(0, 8).equals(mcc.mapearCiudadCodigo().get(ciudad))).forEach(medicionElement -> {
            Medicion medicion = new Medicion();
            medicion.setProvincia(medicionElement.getChildText("provincia"));
            medicion.setMunicipio(medicionElement.getChildText("municipio"));
            medicion.setEstacion(medicionElement.getChildText("estacion"));
            medicion.setMagnitud(Integer.parseInt(medicionElement.getChildText("magnitud")));
            medicion.setPuntoMuestreo(medicionElement.getChildText("puntoMuestreo"));
            medicion.setAnio(Integer.parseInt(medicionElement.getChildText("anio")));
            medicion.setMes(Integer.parseInt(medicionElement.getChildText("mes")));
            medicion.setDia(Integer.parseInt(medicionElement.getChildText("dia")));

            for (int i = 1; i <= 24; i++) {
                if (!medicionElement.getChildText("h" + i).contains("null")) {
                    NumberFormat nf = NumberFormat.getInstance(Locale.GERMAN);
                    try {
                        double med = nf.parse(medicionElement.getChildText("h" + i)).doubleValue();
                        MedicionHora medicionHora = new MedicionHora(med, i + "Horas");
                        medicion.getMedicionesHoras().add(medicionHora);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {

                    MedicionHora medicionHora = new MedicionHora(null, null);
                    medicion.getMedicionesHoras().add(medicionHora);
                }
            }
            medicionesLista.add(medicion);
        });
        return medicionesLista;
    }
}

