
public class Entrada {

	int destino;
	int costo;
	char link;
	
	public Entrada(int destino, int costo, char link) {
		this.costo=costo;
		this.destino=destino;
		this.link=link;
	}
	
	public int getDestino() {
		return destino;
	}
	
	public int getCosto() {
		return costo;
	}
	
	public char getLink() {
		return link;
	}
	
	public void setLink(char link) {
		this.link = link;
	}
	
	public void setCosto(int costo) {
		this.costo = costo;
	}
	
	public void setDestino(int destino) {
		this.destino = destino;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (this.destino==((Entrada)obj).getDestino());
	}
}
