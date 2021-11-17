package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement(name="medicion")
@XmlAccessorType(XmlAccessType.FIELD)



public class Medicion {
    private String nombreMedicion;
    private String unidadMedida;
    private String provincia;
    private String municipio;
    private String estacion;
    private int magnitud;
    private String puntoMuestreo;
    private int anio;
    private int mes;
    private int dia;
    List<MedicionHora> MedicionesHoras = new ArrayList<>();


    @Override
    public String toString() {
        return "Medicion{" +
                "provincia='" + provincia + '\'' +
                ", municipio='" + municipio + '\'' +
                ", estacion='" + estacion + '\'' +
                ", magnitud=" + magnitud +
                ", puntoMuestreo='" + puntoMuestreo + '\'' +
                ", anio=" + anio +
                ", mes=" + mes +
                ", dia=" + dia +
                ", MedicionesHoras=" + MedicionesHoras +
                '}';
    }
}
