
public class Link {
	char id;
	int costo;
	Router router1;
	Router router2;
	boolean caido;
	
	
	public Link(char id, int costo, Router router1, Router router2) {
		super();
		this.id = id;
		this.costo = costo;
		this.router1 = router1;
		this.router2 = router2;
		this.caido = false;
	}
	
	public boolean isCaido() {
		return caido;
	}

	public void setCaido(boolean caido) {
		this.caido = caido;
	}

	public int getCosto() {
		return costo;
	}

	public Router getRouter1() {
		return router1;
	}

	public Router getRouter2() {
		return router2;
	}

	@Override
	public boolean equals(Object obj) {
		return (id == ((Link)obj).getId());
	}
	
	public char getId() {
		return id;
	}
}
