import java.awt.geom.Point2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Tablero implements ITablero , PropertyChangeListener{
	private int size;
	private PropertyChangeSupport supportmodelo;
	private Casilla[][] tablero;
	private int cantidadhundidos;
	private int cantidadbarcos;

	/*public Tablero() {
		size=20;
		tablero=new Casilla[size][size];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j]= new Casilla(Casilla.Estado.CUBIERTO, 0);//inicio casillas con estado pero sin barcos
			}
		}
	}*/
	public Tablero(int tamanio,PropertyChangeSupport supportmodelo, int cantidadbarcos ) {
		this.cantidadbarcos=cantidadbarcos;
		size=tamanio;
		cantidadhundidos=0;
		tablero=new Casilla[size][size];
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j]= new Casilla(Casilla.Estado.CUBIERTO, 0);//inicio casillas con estado pero sin barcos
			}
		}
		this.supportmodelo=supportmodelo;
		
	}
	@Override
	public void borra() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				tablero[i][j]= new Casilla(Casilla.Estado.CUBIERTO, 0);//barco 0 significa no barco
			}
		}
	}
	@Override
	public Casilla getCasilla(int x, int y) {
		// TODO Auto-generated method stub
		return tablero[x][y];
	}
	@Override
	public Tablero getTablero() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public int getTamanio() {
		// TODO Auto-generated method stub
		return this.size;
	}
	@Override
	public int ocupacion() {
		// TODO Auto-generated method stub
		int cont=0;
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++) {
				if(tablero[i][j].getEstado()!=Casilla.Estado.AGUA)
					cont++;
			}
		}
		return cont;
	}
	@Override
	public void setCasilla(int x, int y, Casilla c) {
		// TODO Auto-generated method stub
		tablero[x][y]=c;
	}
	@Override
	public void setTablero(Casilla[][] tablero) {
		// TODO Auto-generated method stub
		this.tablero=tablero;
	}
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// escucho el point y lo actualizo y envio el modelo a la vista
		//actualizo el estado. 
				/*busco todos los de alrededor con su mismo indice, pista: dos direcciones
				 * busco mientras que todos esten tocados
				 * si salgo y todos menos el estaban tocados entonces pongo todos a hundido
				 * si salgo y habia alguno no tocaado actualizo a tocado
				 * 
		
				 */
		if(evt.getPropertyName().equals("Click")) {
			Point2D point=(Point2D) evt.getNewValue();
			int x=(int) point.getY()/15;//el eje y son mis filas x
			int y=(int) point.getX()/15;
			if(getCasilla(x, y).getBarco()==0) {
				getCasilla(x, y).setEstado(Casilla.Estado.AGUA);
			}
			else {
				if(barcohundido(tablero, x, y)) {//si esta hundido actualiza todas a hundido, si no actualiza 1 a tocado
					cantidadhundidos++;
				}
				
			}
			supportmodelo.firePropertyChange("new_tablero", 0, this);
		}
	}
	public int getcantidadhundidos() {
		return cantidadhundidos;
	}
	public int getcantidadbarcos() {
		return cantidadbarcos;
	}
	private boolean barcohundido(Casilla[][] tablero, int i, int j) {// Y ACTUALIZO
		int index=tablero[i][j].getBarco();
		boolean hundido=false;
		tablero[i][j].setEstado(Casilla.Estado.TOCADO);
		if((i<size-1 && index==tablero[i+1][j].getBarco()) || (i>0 && index==tablero[i-1][j].getBarco()) ) {//busco en vertical 
			int tocados=0;
			int longi=0;
			for(int k=0; k<size;k++) {// voy a buscar en toda la columna
				if(index==tablero[k][j].getBarco()) {
					longi++;
					if(tablero[k][j].getEstado()==Casilla.Estado.TOCADO) 
					tocados++;
				}
			}
			if(longi==tocados) {
				hundido=true;//entonces lo hundo
				for(int k=0; k<size;k++) {// voy a buscar en toda la columna
					if(index==tablero[k][j].getBarco()) {
						tablero[k][j].setEstado(Casilla.Estado.HUNDIDO);
					}
				}
			}
			
		}
		 
		else if((j>0 && tablero[i][j-1].getBarco()==index) || (j<size-1 && index==tablero[i][j+1].getBarco())) {//busco horizontal 
			 int tocados=0;
				int longi=0;
				for(int k=0; k<size;k++) {// voy a buscar en toda la fila
					if(index==tablero[i][k].getBarco()) {
						longi++;
						if(tablero[i][k].getEstado()==Casilla.Estado.TOCADO) 
						tocados++;
					}
				}
				if(longi==tocados) {
					hundido=true;//entonces lo hundo
					for(int k=0; k<size;k++) {// voy a buscar en toda la fila
						if(index==tablero[i][k].getBarco()) {
							tablero[i][k].setEstado(Casilla.Estado.HUNDIDO);
						}
					}
				}
		 }
		else {//si no se puede ni vertical ni horizontal entonces es de longitud 1
			tablero[i][j].setEstado(Casilla.Estado.HUNDIDO);
			hundido=true;

		}
		 return hundido;
		 
		
	}
	public boolean finPartida() {
		// TODO Auto-generated method stub
		return cantidadhundidos == cantidadbarcos;
	}


}
