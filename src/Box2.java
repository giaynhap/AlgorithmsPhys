import java.awt.Graphics2D;
import java.util.ArrayList;

public class Box2 {
public class point{
	public float x,y,oldx,oldy;
}
public class stick
{
	public point p0;
	public point p1;
	public double length = 0;
	public void calcDistance()
	{
		length = distance(p0,p1);
	}
}
public double distance(point p0, point p1) {
	double dx = p1.x - p0.x,
		dy = p1.y - p0.y;
	return Math.sqrt(dx * dx + dy * dy);
}


public  void precalc()
{
	
	int pointLen = mPoint.size();
	int has[] = new int[pointLen];
	for (int i=0;i<pointLen-1;i++)
	{
		for (int j=pointLen-1;j>=i+1;j--)
		{ 
		 
			stick stk = new stick();
			stk.p0 = mPoint.get(i);
			stk.p1 = mPoint.get(j);
			stk.calcDistance();
			mSticks .add(stk);
			
			 
		
		}
	}
}
public void addPoint(float x, float y)
{
	point	point = new point();
	point.x = x;
	point.y=y;
	point.oldx = x;
	point.oldy= y;
	mPoint.add(point);
}

public ArrayList <point> mPoint = new ArrayList <point>();
public ArrayList <stick> mSticks = new ArrayList <stick>();
public double friction = 0.9;
public double gravity = 0.5;
public double bounce = 0.999;
public Vector Velocity = new Vector(10,2);
private Graphics2D graph;
public Box2(Graphics2D context)
{
	graph = context;
}
public void update()
{
	UpdatePoint();
	constrainPoints();
	updateSticks() ;
}
public void UpdatePoint()
{
	int pointLen = mPoint.size();
	for(int i = 0; i < pointLen; i++) {
		point p = mPoint.get(i);
			double vx = (p.x - p.oldx) * friction,
			vy = (p.y - p.oldy) * friction;
			Velocity.multiply((float)friction);
		p.oldx = p.x;
		p.oldy = p.y;
		p.x += vx+Velocity.x;
		p.y += vy+Velocity.y;
		p.y += gravity;
	}
}
int width = 800,height = 500;
public void constrainPoints() {
	int pointLen = mPoint.size();
	for(int i = 0; i <pointLen; i++) {
		point p = mPoint.get(i);
			double vx = (p.x - p.oldx) * friction,
			vy = (p.y - p.oldy) * friction;

		if(p.x > width) {
			p.x = width;
			p.oldx = (float) (p.x + vx * bounce);
		}
		else if(p.x < 0) {
			p.x = 0;
			p.oldx = (float) (p.x + vx * bounce);
		}
		if(p.y > height) {
			p.y = height;
			p.oldy = (float) (p.y + vy * bounce);
		}
		else if(p.y < 0) {
			p.y = 0;
			p.oldy = (float) (p.y + vy * bounce);
		}
	}
}
public void updateSticks() {
	for(int i = 0; i < mSticks.size(); i++) {
		stick s = mSticks.get(i);
		double	dx = s.p1.x - s.p0.x,
			dy = s.p1.y - s.p0.y,
			distance = Math.sqrt(dx * dx + dy * dy),
			difference = s.length - distance,
			percent = difference / distance / 2,
			offsetX = dx * percent,
			offsetY = dy * percent;

		s.p0.x -= offsetX;
		s.p0.y -= offsetY;
		s.p1.x += offsetX;
		s.p1.y += offsetY;
	}
}
public void render()
{
 
	 for (int i=0;i<mSticks.size();i++)
	 {
 
		 stick current = mSticks.get(i);
	 
		 graph.drawLine((int)current.p0.x, (int)current.p0.y, (int)current.p1.x, (int)current.p1.y);
	 }
 
}
}
