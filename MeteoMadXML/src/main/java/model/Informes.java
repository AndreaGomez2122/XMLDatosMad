package model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Informes {

    private List<Informe> listaInformes = new ArrayList<>();

    @XmlElementWrapper(name = "informes")
    @XmlElement
    public List<Informe> getListaInformes() {
        return listaInformes;
    }

    public void setListaInformes(List<Informe> listaInformes) {
        this.listaInformes = listaInformes;
    }
}
