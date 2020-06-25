import java.awt.*;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Vista extends Canvas implements PropertyChangeListener {
	private Graphics2D g2d;
	private Point2D p;
	private int size;
	private Tablero tablero;

	public Vista(Tablero tablero) {
		this.tablero = tablero;
		size = tablero.getTamanio();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("new_tablero")) {
			tablero = (Tablero) evt.getNewValue();
			size = tablero.getTamanio();
			repaint();
		}

	}

	public void paint(Graphics g) {

		g2d = (Graphics2D) g;

		int intervalo = 15;
		// tablero.getCasilla(1, 1).setEstado(Casilla.Estado.HUNDIDO);
		for (int i = 0; i < tablero.getTamanio(); i++) {
			for (int j = 0; j < tablero.getTamanio(); j++) {
				g2d.setColor(tablero.getCasilla(i, j).getColor());
				g2d.fillRect(intervalo * j, intervalo * i, intervalo, intervalo);

			}
		}
		g2d.setColor(Color.BLACK);
		for (int i = 0; i < size + 1; i++) {
			g2d.drawLine(0, i * intervalo, intervalo * size, i * intervalo);
			g2d.drawLine(i * intervalo, 0, i * intervalo, intervalo * size);
		}
		if (tablero.finPartida()) {
			
				for (int i = 0; i < tablero.getTamanio(); i++) {
					for (int j = 0; j < tablero.getTamanio(); j++) {
						if (tablero.getCasilla(i, j).getBarco() == 0)
							g2d.setColor(Color.BLUE);
						else
							g2d.setColor(Color.GREEN);
						g2d.fillRect(intervalo * j, intervalo * i, intervalo, intervalo);

					}
				}
				g2d.setColor(Color.BLACK);
				for (int i = 0; i < size + 1; i++) {
					g2d.drawLine(0, i * intervalo, intervalo * size, i * intervalo);
					g2d.drawLine(i * intervalo, 0, i * intervalo, intervalo * size);
				}
				for (int i = 0; i < tablero.getTamanio(); i++) {
					for (int j = 0; j < tablero.getTamanio(); j++) {
						System.out.print(tablero.getCasilla(i, j).getBarco()+" ");
					}
					System.out.println();
				}
			
		}
	}

}
