
public interface ITablero {
	void	borra();
	//Inicializa los valores de todas las casillas del Tablero a su estado inicial ("agua").
	Casilla	getCasilla(int x, int y);
	//Devuelve el valor de la casilla indicada.
	Tablero	getTablero();
	//Devuelve el Tablero como una matriz.
	int	getTamanio();
	//Indica el tamaño que tiene el tablero: la dimensión de la matriz de casillas.
	int	ocupacion();
	//Indica el número de casillas que no contienen "agua".
	void	setCasilla(int x, int y, Casilla c);
	//Modifica el valor de la casilla indicada.
	void	setTablero(Casilla[][] tablero);
	//Modifica el contenido del Tablero completo.
}
