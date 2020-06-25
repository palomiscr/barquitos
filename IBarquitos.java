
public interface IBarquitos {
	void	colocarBarcos();
	//Coloca aleatoriamente los barcos en el Tablero.
	void	disparo(int x, int y);
	//Actualiza el Tablero en función de su estado actual y del disparo realizado en la casilla (x,y).
	boolean	finPartida();
	//Indica si se ha llegado al final de la partida (todos los barcos hundidos).
	void	mostrarFinPartida();
	//Muestra todo el Tablero (agua, barcos tocados y barcos hundidos).

}
