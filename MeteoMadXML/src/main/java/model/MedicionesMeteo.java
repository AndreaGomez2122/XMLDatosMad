package model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class MedicionesMeteo {

    private List<Medicion>velocidadViento = new ArrayList<>();
    private List<Medicion>direccionViento= new ArrayList<>();
    private List<Medicion>temperatura = new ArrayList<>();
    private List<Medicion>humedadRelativa = new ArrayList<>();
    private List<Medicion>presionAtmosferica = new ArrayList<>();
    private List<Medicion>radiacionSolar = new ArrayList<>();
    private List<Medicion>precipitacion = new ArrayList<>();


}
