
public class Entrada {

	String destino;
	int costo;
	char link;


	public Entrada(String destino, int costo, char link) {
		this.costo=costo;
		this.destino=destino;
		this.link=link;
	}
	
	
	public String getDestino() {
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
	
	public void setDestino(String destino) {
		this.destino = destino;
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return (this.destino==((Entrada)obj).getDestino());
	}
}
