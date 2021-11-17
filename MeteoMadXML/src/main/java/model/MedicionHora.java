package model;

import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"medicion", "hora"})
public class MedicionHora {
    private Double medicion;
    private String hora;


    public MedicionHora(Double medicion, String hora) {
        this.medicion = medicion;
        this.hora = hora;

    }
    public MedicionHora(Double medicion){
        this.medicion=medicion;

    }


    public Double getMedicion() {
        return medicion;
    }

    public void setMedicion(Double medicion) {
        this.medicion = medicion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
