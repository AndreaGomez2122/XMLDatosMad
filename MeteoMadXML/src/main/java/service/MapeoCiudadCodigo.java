package service;

import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * Esta clase contiene dos HashMap que nos serán de utilidad tanto en lectura como en escritura.
 * En primer lugar, evitaremos posibles errores en la entrada de la ciudad, y utilizaremos
 * el String normalizado para obtener su código y así sacar sus datos de la Base de Datos o del CSV.
 * Por otro lado, también tenemos la operación inversa, que pasará de strings normalizados a bien
 * acentuados y con correctas mayusculas de cara a escribirlas en los informes
 */
@NoArgsConstructor
public class MapeoCiudadCodigo {

    public HashMap<String, String> mapearCiudadCodigo() {
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("alcaladehenares", "28005002");
        mapeo.put("alcobendas", "28006004");
        mapeo.put("alcorcón", "28007004");
        mapeo.put("algete", "28009001");
        mapeo.put("aranjuez", "28013002");
        mapeo.put("argandadelrey", "28014002");
        mapeo.put("elatazar", "28016001");
        mapeo.put("colmenarviejo", "28045002");
        mapeo.put("colladovillalba", "28047002");
        mapeo.put("coslada", "28049003");
        mapeo.put("fuenlabrada", "28058004");
        mapeo.put("getafe", "28065014");
        mapeo.put("guadalixdelasierra", "28067001");
        mapeo.put("leganes", "28074007");
        mapeo.put("majadahonda", "28080003");
        mapeo.put("mostoles", "28092005");
        mapeo.put("oruscodetajuna", "28102001");
        mapeo.put("puertodecotos", "28120001");
        mapeo.put("rivasvaciamadrid", "28123002");
        mapeo.put("sanmartindevaldeiglesias", "28133002");
        mapeo.put("torrejondeardoz", "28148004");
        mapeo.put("valdemoro", "28161001");
        mapeo.put("villadelprado", "28171001");
        mapeo.put("villarejodesalvanes", "28180001");
        return mapeo;
    }

    public HashMap<String, String> mapearCiudadCiudadBien() {
        HashMap<String, String> mapeo = new HashMap<>();
        mapeo.put("alcaladehenares", "Alcalá de Henares");
        mapeo.put("alcobendas", "Alcobendas");
        mapeo.put("alcorcón", "Alcorcón");
        mapeo.put("algete", "Algete");
        mapeo.put("aranjuez", "Aranjuez");
        mapeo.put("argandadelrey", "Arganda del Rey");
        mapeo.put("elatazar", "El Atazár");
        mapeo.put("colmenarviejo", "Colmenar Viejo");
        mapeo.put("colladovillalba", "Collado Villalba");
        mapeo.put("coslada", "Coslada");
        mapeo.put("fuenlabrada", "Fuenlabrada");
        mapeo.put("getafe", "Getafe");
        mapeo.put("guadalixdelasierra", "Guadalix de la Sierra");
        mapeo.put("leganes", "Leganés");
        mapeo.put("majadahonda", "Majadahonda");
        mapeo.put("mostoles", "Móstoles");
        mapeo.put("oruscodetajuna", "Orusco de Tajuña");
        mapeo.put("puertodecotos", "Puerto de Cotos");
        mapeo.put("rivasvaciamadrid", "Rivas-Vaciamadrid");
        mapeo.put("sanmartindevaldeiglesias", "San Martín de Valdeiglesias");
        mapeo.put("torrejondeardoz", "Torrejón de Ardoz");
        mapeo.put("valdemoro", "Valdemoro");
        mapeo.put("villadelprado", "Villa del prado");
        mapeo.put("villarejodesalvanes", "Villarejo de Salvanés");
        return mapeo;
    }
}



