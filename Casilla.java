import java.awt.Color;

public class Casilla {
	public static enum Estado{AGUA, TOCADO, HUNDIDO, CUBIERTO}
	private Estado estado;
	private int Barco;
	
	
	public Casilla(Estado estado, int barco) {
		this.estado = estado;
		Barco = barco;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public int getBarco() {
		return Barco;
	}
	public void setBarco(int barco) {
		Barco = barco;
	}
	public Color getColor() {
		Color color=Color.BLACK;
		switch(this.estado) {
		case AGUA:
			color=Color.BLUE;
			break;
		case TOCADO:
			color=Color.ORANGE;
			break;
		case HUNDIDO:
			color=Color.RED;
			break;
		case CUBIERTO:
			color=Color.GRAY;
			break;		
		}
		return color;
			
	}
}
