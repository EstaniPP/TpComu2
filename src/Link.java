
public class Link {
	char id;
	int costo;
	Router router1;
	Router router2;
	boolean caido;
	
	@Override
	public boolean equals(Object obj) {
		return (id == ((Link)obj).getId());
	}
	
	public char getId() {
		return id;
	}
}
