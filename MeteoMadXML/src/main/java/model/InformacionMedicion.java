package model;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlType(propOrder = {"nombreMedicion","mediaMensual", "momentoYMaxima", "momentoYMinima"})
public class InformacionMedicion {


    private String nombreMedicion;
    private Double mediaMensual;
    private MedicionHora momentoYMaxima;
    private MedicionHora momentoYMinima;


@XmlAttribute
    public String getNombreMedicion() {
        return nombreMedicion;
    }

    public void setNombreMedicion(String nombreMedicion) {
        this.nombreMedicion = nombreMedicion;
    }

    public Double getMediaMensual() {
        return mediaMensual;
    }

    public void setMediaMensual( Double mediaMensual) {
        this.mediaMensual = mediaMensual;
    }

    public MedicionHora getMomentoYMaxima() {
        return momentoYMaxima;
    }

    public void setMomentoYMaxima(MedicionHora momentoYMaxima) {
        this.momentoYMaxima = momentoYMaxima;
    }

    public MedicionHora getMomentoYMinima() {
        return momentoYMinima;
    }

    public void setMomentoYMinima(MedicionHora momentoYMinima) {
        this.momentoYMinima = momentoYMinima;
    }


}
