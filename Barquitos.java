import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeSupport;

public class Barquitos extends Frame implements IBarquitos {
	private int[] longBarcos;
	private int maxsizebarcos;
	private int cantidadbarcos = 0;
	private int sizetablero;
	private Tablero tablero;

	public Barquitos(int[] numBarcosLong, int tamanioTablero) {
	
		longBarcos = new int[numBarcosLong.length];
		for (int i = 0; i < numBarcosLong.length; i++) {
			longBarcos[i] = numBarcosLong[i];
			cantidadbarcos += longBarcos[i];
		}
		
		
		maxsizebarcos = longBarcos.length;
		this.sizetablero = tamanioTablero;
		PropertyChangeSupport supportcontrol=new PropertyChangeSupport(this);
		PropertyChangeSupport supportmodelo=new PropertyChangeSupport(this);

		Controlador controlador= new Controlador(supportcontrol);
		tablero = new Tablero(sizetablero, supportmodelo, cantidadbarcos);
		Vista vista= new Vista(tablero);
		
		supportcontrol.addPropertyChangeListener(tablero);
		supportmodelo.addPropertyChangeListener(vista);
		vista.addMouseListener(controlador);
		colocarBarcos();
		
		
		
		
		/*//establecer relaciones
		//controlador es escuchado por modelo, modelo es escuchado por vista
		//clase controlador que escucha del mouse recoge un point		
		//vista es escuchada por controlador
		//crear vista y frame*/
		
		Frame frame= new Frame();
		frame.add(vista);
		frame.setVisible(true);
		frame.setSize(sizetablero*15, sizetablero*15+23);
		
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				System.exit(0);
			}
		});
		

	}

	// Crea un juego de los Barquitos.
	@Override
	public void colocarBarcos() {
		int index = 1;
		for (int i = 0; i < longBarcos.length; i++) {
			int aux = longBarcos[i];// barcos de i+1 longitud que tengo que colocar
			while (aux != 0) {// abandono este buble cuando ya he colocado todos los barcos de i+1 longitud
				double b = Math.random();
				boolean vertical;// decido aleatoriamente si el barco se pone vertical o horizontal
				if (b > 0.5)
					vertical = true;
				else
					vertical = false;// entonces horizontal
				int x = (int) Math.floor(Math.random() * sizetablero);// coordenadas de la cabeza del barco
				int y = (int) Math.floor(Math.random() * sizetablero);
				// i+1 es la longitud del barco, contando la cabeza
				if (esfactible(x, y, vertical, i + 1, tablero, index)) {// si esta dentro de rango y cabe entero se actualiza
					
					aux--;// he logrado colocar el barco
					index++;// solo si lo coloco le asigno un indice a un barco
				}
			}
		}

	}

	public boolean esfactible(int x, int y, boolean vertical, int longitud, Tablero tablero, int index) {
		boolean sepuede = false;
		if (tablero.getCasilla(x, y).getBarco() == 0) {// 0 significa que no hay barco
			if (vertical) {
				if (x + longitud - 1 < sizetablero) {
					sepuede = true;// esta dentro de rango
					int j = 0;// numero de casillas que tengo que comprobar. no incluyo la cabeza
					int i = x + 1;//empiezo desde fila+1 y me desplazo de fila
					while (j < longitud - 1 && sepuede) {
						if (tablero.getCasilla(i, y).getBarco() != 0)
							sepuede = false;
						j++;
						i++;
					}// compruebo que no se solapa con otro barco ya puesto aleatoriamente
					if (sepuede)// actualizo
					{
						for(int k=0; k<longitud; k++) {
							tablero.getCasilla(x+k, y).setBarco(index);
							
						}
					}

				}
			} else {
				if (y + longitud - 1 < sizetablero) {
					sepuede = true;// esta dentro de rango
					int j = 0;// numero de casillas que tengo que comprobar. no incluyo la cabeza
					int i = y + 1;//empiezo desde fila+1 y me desplazo de fila
					while (j < longitud - 1 && sepuede) {
						if (tablero.getCasilla(x, i).getBarco() != 0)
							sepuede = false;
						j++;
						i++;
					}// compruebo que no se solapa con otro barco ya puesto aleatoriamente
					if (sepuede)// actualizo
					{
						for(int k=0; k<longitud; k++) {
							tablero.getCasilla(x, y+k).setBarco(index);
							
						}
					}
				}
			}

		}

		return sepuede;
	}

	@Override
	public void disparo(int x, int y) {
		// TODO Auto-generated method stub
		

	}

	@Override
	public boolean finPartida() {
		// TODO Auto-generated method stub
		return tablero.getcantidadhundidos() == cantidadbarcos;
	}

	@Override
	public void mostrarFinPartida() {
		// TODO Auto-generated method stub

	}

	public int getTamanioMaximoBarco() {
		return longBarcos.length;
	}

	// Devuelve el número de casillas máximo que puede ocupar un barco.
	public int getTamanioTablero() {
		return this.maxsizebarcos;
	}
	// Devuelve el tamaño de la dimensión del tablero (cuadrado).

	public static void main(java.lang.String[] args) {
		int[] nuevo = { 0,3, 2 };
		Barquitos b = new Barquitos(nuevo, 8);

	}

}
