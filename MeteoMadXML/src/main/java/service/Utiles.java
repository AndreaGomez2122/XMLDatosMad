package service;

import java.text.Normalizer;

public class Utiles {
    /**Este normalizador nos ayudará a evitar errores a la hora de procesar la ciudad que introduzca el usuario.
     *Quitando mayúsculas, carácteres especiales e incluso espacios, para unificar entradas diversas de una misma ciudad como
     * Rivas-Vaciamadrid, rivas vaciamadrid, Rivas vaciamadrid...
     *
     * @param ciudad
     * @return
     */
    public static String normalizar(String ciudad) {
        ciudad = (Normalizer.normalize(ciudad, Normalizer.Form.NFD)).replaceAll("[^\\p{ASCII}]", "").toLowerCase().replace("-", "").replace(" ", "");
        return ciudad;
    }
}
