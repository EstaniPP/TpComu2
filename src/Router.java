import java.util.HashMap;
import java.util.List;

public class Router {
	private static int ID_GLOBAL = 1;
	private int id;
	private HashMap<Link,Router> adyacentes;
	private List<Entrada> tablaRuteo; 
}
