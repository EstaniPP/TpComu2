import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class main {
	private ArrayList<Router> routers;
	private ArrayList<Link> links;
	private ArrayList<String> pasosConvergencia;
	private int numeroPasosConvergencia;
	
	public main() {
		routers = new ArrayList<>();
		links = new ArrayList<>();
		pasosConvergencia = new ArrayList<>();
		numeroPasosConvergencia = 0;
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
			obtenerRouter(router1).addAdyacente(link, obtenerRouter(router2));
			obtenerRouter(router2).addAdyacente(link, obtenerRouter(router1));
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
	
	public String getTablasRuteoRed() {
		String informacion = "";
		for(Router r : routers) {
			ArrayList<Entrada> entradasRouter = r.getTablaRuteo();
			for (Entrada e : entradasRouter) {
				informacion += "Router " + r.getNombre() + ": " + e.getDestino() + " - " + e.getCosto() + " - " + e.getLink() + "\n";
			}
		}
		return informacion;
	}
	
	public void realizarIntercambio() {
		HashMap<Router,ArrayList<Entrada>> mensajes = new HashMap<>();
		for(Router r : routers) {
			ArrayList<Entrada> mensajesRouter = new ArrayList<Entrada>();
			HashMap<Link,Router> adyacentes = r.getAdyacentes();
			for(Link l : adyacentes.keySet()) {
				ArrayList<Entrada> 	mensajesAdyacente = adyacentes.get(l).getTablaRuteo();
				for(Entrada e : mensajesAdyacente) {
					if(l.getId() == e.getLink()){
						mensajesRouter.add(new Entrada(e.getDestino(), 1000,e.getLink()));
					}
					else {
						mensajesRouter.add(new Entrada(e.getDestino(), e.getCosto(),l.getId()));
					}
				}
			}
			mensajes.put(r, mensajesRouter);
		}
		for(Router r:routers) {//PARA CADA ROUTER
			r.actualizarTabla(mensajes.get(r));//ACTUALIZO
		}
		pasosConvergencia.add(getTablasRuteoRed());
		System.out.println(getTablasRuteoRed());
	}
	
	public ArrayList<String> getInformacionConvergencia() {
		return pasosConvergencia;
	}
	
	public int getNumeroPasosConvergencia() {
		return numeroPasosConvergencia;
	}
	
	public void aplicarAlgoritmo() {	
		pasosConvergencia.add(getTablasRuteoRed());

		System.out.println(getTablasRuteoRed());
		System.out.println("----------");
		
		while(!convergeRed()){
			realizarIntercambio();
			numeroPasosConvergencia++;
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