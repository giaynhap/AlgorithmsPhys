import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class Arm {
	Graphics2D graph;
	int length ;
	double angle = 0;
	public float x,y;
	public Arm paren ;
	
	public Arm(Graphics2D contex,int length)
	{
		this.graph = contex;
		this.length = length;
	}
	public void setAngle (double angle)
	{
		this.angle = angle;
	}
	public  float getEndX()
	{
		return (float) (Math.cos(angle)*length+x);
	}
	public  float getEndY()
	{
		return (float) (Math.sin(angle)*length+y);
	}
	public void setAngle (int angle)
	{
		this.angle = angle/180.0*Math.PI;
	}
	public void render()
	{
	 
		graph.drawLine((int)this.x, (int)this.y, (int)this.getEndX(),(int)this.getEndY());
	}
	public void pointAt(float x,float y)
	{
		float dx = x-this.x;
		float dy = y-this.y;
		this.angle = Math.atan2(dy,dx);
		
		
	}
	public void drag(float x,float y)
	{
		 pointAt( x, y);
		 this.x = (float) (x-Math.cos(this.angle)*this.length);
		 this.y = (float) (y-Math.sin(this.angle)*this.length);
		 if (paren!=null)
		 {
			 paren.drag(this.x, this.y);
		 }
	}
	public void update()
	{
		 
		 
	}
}
