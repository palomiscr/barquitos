import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.beans.PropertyChangeSupport;
public class Controlador implements MouseListener {
	private PropertyChangeSupport supportcontrol;
	private Point2D point;
	public Controlador(PropertyChangeSupport supportcontrol) {
		// TODO Auto-generated constructor stub
		this.supportcontrol=supportcontrol;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		//recojo point y lo convierto a casilla
		
		point=(Point2D)e.getPoint();		
		supportcontrol.firePropertyChange("Click", 0,point );
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
