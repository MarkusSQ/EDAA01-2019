package mountain;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import fractal.Fractal;
import fractal.TurtleGraphics;
import mountain.RandomUtilities;

public class Mountain extends Fractal{
	private int length;
	private Side s1;
	private Side s2;
	private Side s3;
	
	private Point p1;
	private Point p2;
	private Point p3;
	
	private Point m;
	private RandomUtilities dy;
	private double dev;
	
	private HashMap<Side, Point> map = new HashMap<>();
	
	
	public Mountain(int length, double dev) {
		super();
		this.length=length;
		dy = new RandomUtilities();
		this.dev = dev;
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Mountain";
	}

	@Override
	public void draw(TurtleGraphics turtle) {
		m = new Point((int) turtle.getWidth()/ 2, (int) (turtle.getHeight()/2));
		p1= new Point((int) (m.getX() - length / 2.0),(int) (m.getY() + Math.sqrt(3.0) * length / 4.0 -5));
		p2= new Point((int) (m.getX() + length / 2.0), (int) (m.getY() + Math.sqrt(3.0) * length / 4.0 +5) );
		p3 = new Point((int) (m.getX()), (int) ( m.getY() - Math.sqrt(3)*length/2 ));
		
		turtle.moveTo(p1.getX(), p1.getY());
		
		fractalLine(turtle, order, p1,p2,p3, dev);
		// TODO Auto-generated method stub
		
	}
	
	private void fractalLine(TurtleGraphics turtle, int order ,Point p1, Point p2, Point p3, double dev) {
		
		
			Point mid1= mid(p1, p2, (int) dev);
			Point mid2= mid(p2, p3, (int) dev);
			Point mid3 = mid(p3, p1, (int) dev);
			
			dev=dev/2;
		
		if(order==0) {
			turtle.moveTo(p1.getX(), p1.getY());
			turtle.forwardTo(p2.getX(), p2.getY());
			turtle.forwardTo(p3.getX(), p3.getY());
			turtle.forwardTo(p1.getX(), p1.getY());
		} else {
			fractalLine(turtle,order-1, p1, mid1,mid3, dev );
			fractalLine(turtle,order-1, mid1, p2,mid2, dev );
			fractalLine(turtle,order-1, p3, mid2,mid3, dev );
			fractalLine(turtle,order-1, mid1, mid2,mid3, dev );
		}
	}
	
	public Point mid(Point one, Point two, int dev) {
		Side newside = new Side(one,two);
		if(map.containsKey(newside)) {
			return map.remove(newside);
		}

		
		Point newmid = new Point((int) ((one.getX() + two.getX()) / 2),(int) (((one.getY() + two.getY()) / 2) + RandomUtilities.randFunc(dev)));
		map.put(newside, newmid);
		return newmid;
	}

}

