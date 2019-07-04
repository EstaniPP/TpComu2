import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class main {
	ArrayList<Router> routers;
	ArrayList<Link> links;
	
	public main() {
		routers=new ArrayList<>();
		links=new ArrayList<>();
	}
	
	public boolean addRouter(String nombre) {
		Router router=new Router(nombre);
		if(!routers.contains(router)) {
			routers.add(router);
			return true;
		}
		return false;
	}
	public Router obtenerRouter(String r) {
		for(Router routs:routers) {
			if(routs.getNombre().equals(r))
				return routs;
		}
		return null;
	}
	
	public boolean addLink(char id, int costo, String router1, String router2) {
		Link link=new Link(id, costo, obtenerRouter(router1), obtenerRouter(router2));
		if(!links.contains(link)) {
			links.add(link);
			return true;
		}
		return false;
	}
	
	private boolean convergeRed() {
		for(Router r : routers)
			if(!r.converge())
				return false;
		return true;
	}
	
	public void realizarIntercambio() {
		//guardar tablas 
		HashMap<Router,ArrayList<Entrada>> mensajes = new HashMap<>();
		for(Router r : routers) {
			ArrayList<Entrada> mensajesRouter = new ArrayList<Entrada>();
			HashMap<Link,Router> adyacentes = r.getAdyacentes();
			Set<Link> keys = adyacentes.keySet();
			for(Link l : keys) {
				ArrayList<Entrada> 	mensajesAdyacente = adyacentes.get(l).getTabla();
				for(Entrada e : mensajesAdyacente) {
					if(l.getId() == e.getLink()){
						mensajesRouter.add(new Entrada(e.getDestino(), Integer.MAX_VALUE,e.getLink()));
					}
					else {
						mensajesRouter.add(new Entrada(e.getDestino(), e.getCosto(),e.getLink()));
					}
				}
			}
			mensajes.put(r, mensajesRouter);
		}
		for(Router r:routers) {//PARA CADA ROUTER
			r.actualizarTabla(mensajes.get(r));//ACTUALIZO
		}
		//guardar tablas
	}
	
	public void aplicarAlgoritmo() {		
		while(!convergeRed()) {
			realizarIntercambio();
		}
	}
	
	public ArrayList<String> getRouters() {
		ArrayList<String> nombres=new ArrayList<String>();
		for(Router r:routers) {
			nombres.add(r.getNombre());
		}
		return nombres;
	}
}