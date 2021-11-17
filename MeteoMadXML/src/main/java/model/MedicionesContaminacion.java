package model;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class MedicionesContaminacion {

    private List<Medicion> dioxidoAzufre = new ArrayList<>();
    private List<Medicion>monoxidoCarbono= new ArrayList<>();
    private List<Medicion>monoxidoNitrogeno = new ArrayList<>();
    private List<Medicion>dioxidoNitrogeno = new ArrayList<>();
    private List<Medicion>particulas2_5 = new ArrayList<>();
    private List<Medicion>particulas10 = new ArrayList<>();
    private List<Medicion>oxidosNitrogeno = new ArrayList<>();
    private List<Medicion> ozono = new ArrayList<>();
    private List<Medicion>tolueno= new ArrayList<>();
    private List<Medicion>blackCarbon = new ArrayList<>();
    private List<Medicion>benceno = new ArrayList<>();
    private List<Medicion>hicrocarburosTotales = new ArrayList<>();
    private List<Medicion>hidrocarburosNoMetanicos = new ArrayList<>();
    private List<Medicion>metaParaXileno = new ArrayList<>();

}
