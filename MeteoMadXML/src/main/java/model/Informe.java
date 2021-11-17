package model;

import javax.xml.bind.annotation.*;
import java.lang.String;
import java.util.List;

@XmlRootElement
@XmlType(propOrder = {"uuid", "nombreCiudad" ,"fecha","informacionMeteorologica","informacionContaminacion" })
public class Informe {
    private String uuid;
    private String nombreCiudad;
    private String fecha;

    private List<InformacionMedicion> informacionMeteorologica;
    private List<InformacionMedicion> informacionContaminacion;

@XmlAttribute
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @XmlElementWrapper(name = "datos_meteorologicos")
    @XmlElement
    public List<InformacionMedicion> getInformacionMeteorologica() {
        return informacionMeteorologica;
    }

    public void setInformacionMeteorologica(List<InformacionMedicion> informacionMeteorologica) {
        this.informacionMeteorologica = informacionMeteorologica;
    }

    @XmlElementWrapper(name = "datos_contaminacion")
    @XmlElement
    public List<InformacionMedicion> getInformacionContaminacion() {
        return informacionContaminacion;
    }

    public void setInformacionContaminacion(List<InformacionMedicion> informacionContaminacion) {
        this.informacionContaminacion = informacionContaminacion;
    }
}