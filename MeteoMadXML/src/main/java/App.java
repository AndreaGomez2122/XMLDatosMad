import controller.Controller;
import org.jdom2.JDOMException;
import service.Utiles;

import javax.xml.bind.JAXBException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws IOException, JDOMException, JAXBException, XPathExpressionException {

       /* if (args.length != 2) {
            System.out.println("Número de parámetros incorrecto: para usar este programa necesitas introducir dos parámetros: " +
                    "1.nombre de la ciudad de la que se desean obtener los datos " +
                    "2. directorio donde queremos guardar el informe resultante");
        } else {
            String ciudad = Utiles.normalizar(args[0]);
            Path ruta = Paths.get(args[1]);*/

        String ciudad = "leganes";
        Path ruta = Path.of(System.getProperty("user.dir"));
        Controller controller = new Controller(ciudad, ruta);

    }
}



