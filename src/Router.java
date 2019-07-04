import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Router {
	private static int ID_GLOBAL = 1;
	private int id;
	private HashMap<Link,Router> adyacentes;
	private ArrayList<Entrada> tablaRuteo;
	private boolean converge;
	private String nombre;
	
	public Router(String nombre) {
		id = ID_GLOBAL++;
		adyacentes = new HashMap<Link,Router>();
		tablaRuteo = new ArrayList<Entrada>();
		tablaRuteo.add(new Entrada(id,0,'-'));	
		converge=false;
		this.nombre=nombre;
	}
	
	public void addAdyacente(Link link, Router router) {
		if(!adyacentes.containsKey(link)) {
			adyacentes.put(link, router);
		}
	}
	
	public ArrayList<Entrada> getTabla() {
		return tablaRuteo;
	}
	
	public void actualizarTabla(ArrayList<Entrada> mensajes) {
		for(Entrada e:mensajes) {
			int costoLink=0;
			ArrayList<Link> links=(ArrayList<Link>) adyacentes.keySet();
			for(Link l:links) {
				if(l.getId()==e.getLink()) {
					costoLink=l.getCosto();
					break;
				}
			}
			if(tablaRuteo.contains(e)) {
				if((e.getCosto()+costoLink)<tablaRuteo.get(tablaRuteo.indexOf(e)).getCosto()) {
					tablaRuteo.remove(tablaRuteo.indexOf(e));
					e.setCosto(e.getCosto()+costoLink);
					tablaRuteo.add(e);
				}
			}
			else {
				e.setCosto(e.getCosto()+costoLink);
				tablaRuteo.add(e);
			}	
		}
	}
	
	public int getId() {
		return id;
	}
	
	public HashMap<Link, Router> getAdyacentes() {
		return adyacentes;
	}
	
	public String getNombre() {
		return nombre;
	}
}
