package io;


import model.InformacionMedicion;
import service.MapeoCiudadCodigo;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class HtmlWriter {


    /**
     * Este método utiliza la dirección introducida por el usuario para escribir en ella el documento html con el informe completo.
     * Utiliza los datos ya mapeados en objetos Medicion, en dos listas: temperatura y contaminacion. Internamnete llama al método procesarHtml
     *
     * @param medicionesTemp
     * @param medicionesCont
     * @param initTime
     * @param ruta
     * @throws IOException
     */

    public void generarHtml(List<InformacionMedicion> medicionesTemp, List<InformacionMedicion> medicionesCont, long initTime, String ciudad, Path ruta) throws IOException {
        //Con esta líneas iniciales nos aseguramos de que la ciudad esté bien escrita en el informe
        MapeoCiudadCodigo mcc = new MapeoCiudadCodigo();
        HashMap<String, String> mapaCiudad = mcc.mapearCiudadCiudadBien();
        ciudad = mapaCiudad.get(ciudad);

        List<String> texto = procesarHtml(medicionesTemp, medicionesCont, initTime, ciudad);
        Path path = Paths.get(ruta.toString() + File.separator + ciudad + ".html");
        Files.write(path, texto, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

    }


    /**
     * Este método recibe los objetos con los datos ya procesados de medias y estadísticas para almacenarlos en una lista de String
     * incorporando en ellas las etiquetas html necesarias para su correcta visualización en el navegador.
     *
     * @param medicionesTemp
     * @param initTime
     * @return
     */
    private List<String> procesarHtml(List<InformacionMedicion> medicionesTemp, List<InformacionMedicion> medicionesCont, long initTime, String ciudad) {
        LocalDate ld = LocalDate.now();
        Calendar cldr = Calendar.getInstance();
        List<String> texto = new LinkedList<>();
        texto.add("<html><head><title>Mario&Andrea proyect</title></head><body><h4>Práctica de Acceso a Datos by Andrea Gómez De Pablo y Mario González Gómez</h4>");
        texto.add("<img src=\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyTGSdHD9cZg4fzanfhuydnOH4c0dLXoiHZw&usqp=CAU\" width=\"290\" height=\"174\">");
        texto.add("<hr/><h1>Informe de datos de " + ciudad + "</h1>");
        texto.add("<ul><li>Fecha de inicio de la medición: </li><li>Fecha de fin de la medición: </li><li>Estación/estaciones asociadas:</li></ul>");
        texto.add("<hr/><h2>************************   DATOS METEOROLÓGICOS   *******************************</h2>");
        texto.add("<h4>TEMPERATURA</h4><ul><li>Temperatura media mensual: " + medicionesTemp.get(2).getMediaMensual() + "</li> <li>Momento y temperatura máxima:" + medicionesTemp.get(1).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y temperatura mínima: " + medicionesTemp.get(1).getMomentoYMinima().getMedicion() + "</li> </ul> <img src=\"" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoTemp.png\"></br>");
        texto.add("<h4>RADIACIÓN SOLAR</h4> <ul><li>Radiación solar media mensual: " + medicionesTemp.get(5).getMediaMensual() + "</li> <li>Momento y radiación máxima: " + medicionesTemp.get(4).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y radiación mínima: " + medicionesTemp.get(4).getMomentoYMinima().getMedicion() + "</li> </ul> <img src=\"" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoSolar.png\"></br>");
        texto.add("<h4>PRECIPITACIÓN</h4> <ul><li>Precipitación media mensual: " + medicionesTemp.get(6).getMediaMensual() + "</li> <li>Lista de días que ha llovido y precipitación de cada día: </li></ul>");
        texto.add("<h4>HUMEDAD</h4> <ul><li>Humedad relativa media mensual: " + medicionesTemp.get(3).getMediaMensual() + "</li> <li>Momento y humedad máxima: " + medicionesTemp.get(3).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y humedad mínima : " + medicionesTemp.get(3).getMomentoYMinima().getMedicion() + "</li></ul> <img src=\"" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoHumedad.png\"></br>");
        texto.add("<h4>VELOCIDAD DEL VIENTO</h4> <ul><li>Velocidad media del viento mensual: " + medicionesTemp.get(0).getMediaMensual() + "</li> <li>Momento y velocidad máxima: " + medicionesTemp.get(0).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y velocidad mínima : " + medicionesTemp.get(0).getMomentoYMinima().getMedicion() + "</li></ul> <img src=\"" + System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoViento.png\"></br>");
        texto.add("<hr/><h2>************************   DATOS CONTAMINACIÓN   *******************************</h2>");
        //  texto.add("<h4>MONÓXIDO DE CARBONO</h4><ul><li>Media mensual: " + medicionesCont.get(0).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(0).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(0).getMomentoYMinima().getMedicion() + "</li> </ul>");
        // texto.add("<h4>MONÓXIDO DE NITRÓGENO</h4><ul><li>Media mensual: " + medicionesCont.get(1).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(1).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(1).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>DIÓXIDO DE NITRÓGENO</h4><ul><li>Media mensual: " + medicionesCont.get(2).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(2).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(2).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>PARTÍCULAS EN SUSPENSIÓN  menores a PM2,5</h4><ul><li>Media mensual: " + medicionesCont.get(3).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(3).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(3).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>PARTÍCULAS EN SUSPENSIÓN menores a  PM10</h4><ul><li>Media mensual: " + medicionesCont.get(4).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(4).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(4).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>ÓXIDO DE NITRÓGENO</h4><ul><li>Media mensual: " + medicionesCont.get(5).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(5).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(5).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>OZONO</h4><ul><li>Media mensual: " + medicionesCont.get(6).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(6).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(6).getMomentoYMinima().getMedicion() + "</li> </ul>");
        texto.add("<h4>TOLUENO</h4><ul><li>Media mensual: " + medicionesCont.get(7).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(7).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(7).getMomentoYMinima().getMedicion() + "</li> </ul>");
        //  texto.add("<h4>BLACK CARBON</h4><ul><li>Media mensual: " + medicionesCont.get(8).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(8).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(8).getMomentoYMinima().getMedicion() + "</li> </ul>");
        //  texto.add("<h4>BENCENO</h4><ul><li>Media mensual: " + medicionesCont.get(9).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(9).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(9).getMomentoYMinima().getMedicion() + "</li> </ul>");
        // texto.add("<h4>HIDROCARBUROS</h4><ul><li>Media mensual: " + medicionesCont.get(10).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(10).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(10).getMomentoYMinima().getMedicion() + "</li> </ul>");
        // texto.add("<h4>HIDROCARBUROS NO METÁNICOS</h4><ul><li>Media mensual: " + medicionesCont.get(11).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(11).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(11).getMomentoYMinima().getMedicion() + "</li> </ul>");
        // texto.add("<h4>METAPARAXILENO</h4><ul><li>Media mensual: " + medicionesCont.get(12).getMediaMensual() + "</li> <li>Momento y valor máximo:" + medicionesCont.get(12).getMomentoYMaxima().getMedicion() + "</li> <li>Momento y valor mínimo: " + medicionesCont.get(12).getMomentoYMinima().getMedicion() + "</li> </ul>");

        texto.add("<p><i>Informe generado el " + ld.getDayOfMonth() + "/" + ld.getMonth() + "/" + ld.getYear() + " a las " + cldr.get(Calendar.HOUR_OF_DAY)
                + ":" + cldr.get(Calendar.MINUTE) + ":" + cldr.get(Calendar.SECOND)
                + " en " + (System.currentTimeMillis() - initTime) / 1000 + " segundos.</p></i>");
        texto.add("</body></html>");

        return texto;
    }
}




