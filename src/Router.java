import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Router {
	private static int ID_GLOBAL = 1;
	private int id;
	private HashMap<Link,Router> adyacentes;
	private ArrayList<Entrada> tablaRuteo;
	private ArrayList<Entrada> tablaRuteoAnterior;
	private String nombre;
	
	public Router(String nombre) {
		id = ID_GLOBAL++;
		adyacentes = new HashMap<Link,Router>();
		tablaRuteo = new ArrayList<Entrada>();
		tablaRuteo.add(new Entrada(nombre,0,'-'));
		tablaRuteoAnterior = new ArrayList<Entrada>();
		this.nombre=nombre;
	}
	
	public void addAdyacente(Link link, Router router) {
		if(!adyacentes.containsKey(link)) {
			adyacentes.put(link, router);
		}
	}
	
	public ArrayList<Entrada> getTablaRuteo() {
		return tablaRuteo;
	}
	
	public void actualizarTabla(ArrayList<Entrada> mensajes) {
		tablaRuteoAnterior = getCopiaArray(tablaRuteo);
		tablaRuteo = new ArrayList<Entrada>();
		tablaRuteo.add(new Entrada(nombre,0,'-'));
		for(Entrada e:mensajes) {
			int costoLink = 0;
			for(Link l:adyacentes.keySet()) {
				if(l.getId() == e.getLink()) {
					costoLink = l.getCosto();
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
	
	private ArrayList<Entrada> getCopiaArray(ArrayList<Entrada> tablaRuteo) {
		ArrayList<Entrada> copia = new ArrayList<Entrada>();
		for(Entrada e: tablaRuteo) {
			copia.add(new Entrada(e.getDestino(),e.getCosto(),e.getLink()));
		}
		return copia;
	}

	public boolean converge() {
		if(tablaRuteo.size()!=tablaRuteoAnterior.size())
			return false;
		for(Entrada e : tablaRuteo) {
			if(tablaRuteoAnterior.isEmpty())
				return false;
			for(Entrada e_ant : tablaRuteoAnterior)
				if (e.getDestino() == e_ant.getDestino() && e.getCosto() != e_ant.getCosto()) {
					return false;
				}
		}
		return true;
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
	
	@Override
	public boolean equals(Object obj) {
		return nombre.equals(((Router)obj).getNombre());
	}
}
