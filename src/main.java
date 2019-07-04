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
	
	public void actualizarRed() {
		HashMap<Router,ArrayList<Entrada>> mensajes=new HashMap<>();
		for(Router r:routers) {
			ArrayList<Entrada> aux = new ArrayList<Entrada>();
			HashMap<Link,Router> adyacentes=r.getAdyacentes();
			Set<Link> keys= adyacentes.keySet();
			for(Link l:keys) {
				ArrayList<Entrada> aux2 = adyacentes.get(l).getTabla();
				for(Entrada e:aux2) {
					if(l.getId()==e.getLink()){
						aux.add(new Entrada(e.getDestino(), Integer.MAX_VALUE,e.getLink()));
					}
					else {
						aux.add(new Entrada(e.getDestino(), e.getCosto(),e.getLink()));
					}
				}
			}
			mensajes.put(r, aux);
		}
		for(Router r:routers) {//PARA CADA ROUTER
			r.actualizarTabla(mensajes.get(r));//ACTUALIZO
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

