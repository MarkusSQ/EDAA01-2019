package mountain;

public class Side {
	Point start;
	Point end;
	Point m;
	
	public Side(Point start, Point end) {
		this.start = start;
		this.end = end;
		this.m = new Point((int) ((start.getX() + end.getX()) / 2),(int) ((start.getY() + end.getY()) / 2 ));
	}
	
	public Point getStart() {
		return start;
	}
	
	public Point getEnd() {
		return end;
	}
	
	public Point getMid() {
		return m;
	}
	
	@Override
	public boolean equals(Object  s) {
		if(s instanceof Side) {
		if(((Side) s).getStart().getX() == this.getStart().getX() && ((Side) s).getStart().getY() == this.getStart().getY() && ((Side) s).getEnd().getX() == this.getEnd().getX() && ((Side) s).getEnd().getY() == this.getEnd().getY()) {
			return true;
		}else if(((Side) s).getStart().getX()== this.getEnd().getX() && ((Side) s).getStart().getY()==this.getEnd().getY() && ((Side) s).getEnd().getX() == this.getStart().getX() && ((Side) s).getEnd().getY()== this.getStart().getY()) {
			return true;
		}else return false;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return start.hashCode() + end.hashCode();
	}
}
