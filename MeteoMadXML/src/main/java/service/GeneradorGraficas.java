
package service;


import model.Medicion;
import model.MedicionHora;
import model.MedicionesMeteo;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;


public class GeneradorGraficas {
    private List<Double> mediasDiariasTemp = new LinkedList<>();
    private List<Double> mediasDiariasVelVient = new LinkedList<>();
    private List<Double> mediasDiariasHumedad = new LinkedList<>();
    private List<Double> mediasDiariasPresion = new LinkedList<>();
    private List<Double> mediasDiariasSolar = new LinkedList<>();



    /**
     * Utilizando la librería JFreeChart, esta clase implementa el código necesario para generar las gráficas de las
     * mediciones. Adicionalmente, utiliza condiciones con un index para poder hacer gráficos personalizados y
     * más adecuados para cada tipo de dato
     *
     * @param diarias
     * @param index
     * @param nombreTabla
     * @return
     */
    private JFreeChart hacerGrafica(List<Double> diarias, int index, String nombreTabla) {
        JFreeChart grafica = null;
        //Para las gráficas de pastel debemos hacer DefaultPieDataset
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        double acumulado = 0.0;
        for (int i = 0; i < diarias.size(); i++) {
            datos.addValue(diarias.get(i), nombreTabla, "" + (i + 1));
            acumulado = acumulado + diarias.get(i);
        }
        if (index == 2) {
            grafica = ChartFactory.createLineChart(nombreTabla + " día a día", "Día del mes", "ºC", datos, PlotOrientation.VERTICAL, true, false, false);
        }
        if (index == 5) {
            grafica = ChartFactory.createBarChart(nombreTabla + " día a día", "Día del mes", "W/m2", datos, PlotOrientation.VERTICAL, true, false, false);
        }
        if (index == 3) {
            grafica = ChartFactory.createAreaChart(nombreTabla + " día a día", "Día del mes", "%", datos, PlotOrientation.VERTICAL, true, false, false);
        }
        if (index == 0) {
            grafica = ChartFactory.createBarChart(nombreTabla + " día a día", "Día del mes", "m/s", datos, PlotOrientation.HORIZONTAL, true, false, false);
        }
        grafica.setBackgroundPaint(new Color(250, 6, 34, 48));
        return grafica;
    }



    /**
     * Este método genera las imágenes que utilizaremos en el html. Estas se incorporan en el propio documento html
     * y derivan de los mismos datos que se expondrán en el informe.
     *
     * @throws IOException
     */



    public void generarGraficas(MedicionesMeteo mm) throws IOException {
        List<DoubleStream> prueba = new LinkedList<>();


        Double media;

        for (Medicion meed : mm.getTemperatura()
        ) {
            media = meed.getMedicionesHoras().stream().filter(me -> me.getMedicion() != null)
                    .collect(Collectors.summarizingDouble(MedicionHora::getMedicion)).getAverage();
            mediasDiariasTemp.add(media);
        }

        for (Medicion meed : mm.getHumedadRelativa()
        ) {
            media = meed.getMedicionesHoras().stream().filter(me -> me.getMedicion() != null)
                    .collect(Collectors.summarizingDouble(MedicionHora::getMedicion)).getAverage();
            mediasDiariasHumedad.add(media);
        }

        for (Medicion meed : mm.getRadiacionSolar()
        ) {
            media = meed.getMedicionesHoras().stream().filter(me -> me.getMedicion() != null)
                    .collect(Collectors.summarizingDouble(MedicionHora::getMedicion)).getAverage();
            mediasDiariasSolar.add(media);
        }

        for (Medicion meed : mm.getVelocidadViento()
        ) {
            media = meed.getMedicionesHoras().stream().filter(me -> me.getMedicion() != null)
                    .collect(Collectors.summarizingDouble(MedicionHora::getMedicion)).getAverage();
            mediasDiariasVelVient.add(media);
        }


        try {
            Files.createDirectory(Paths.get(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas"));
        } catch (FileAlreadyExistsException e) {
            System.out.println("Guardando en carpeta Gráficas");
        }

        JFreeChart graficoTemp = hacerGrafica(mediasDiariasTemp, 2, "Temperatura");
        File graficoTemperatura = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoTemp.png");
        ChartUtils.saveChartAsPNG(graficoTemperatura, graficoTemp, 600, 400);

        JFreeChart graficoSolar = hacerGrafica(mediasDiariasSolar, 5, "Radiación Solar");
        File graficoRadiacionSolar = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoSolar.png");
        ChartUtils.saveChartAsPNG(graficoRadiacionSolar, graficoSolar, 600, 400);

        JFreeChart graficoHum = hacerGrafica(mediasDiariasHumedad, 3, "Humedad relativa");
        File graficoHumedad = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoHumedad.png");
        ChartUtils.saveChartAsPNG(graficoHumedad, graficoHum, 600, 400);

        JFreeChart graficoViento = hacerGrafica(mediasDiariasVelVient, 0, "Velocidad del viento");
        File graficoVelocidadCiento = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "graficas" + File.separator + "graficoViento.png");
        ChartUtils.saveChartAsPNG(graficoVelocidadCiento, graficoViento, 600, 400);

    }
}




