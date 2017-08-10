import java.awt.Graphics2D;

public class Object {
	public Vector position;
	public Vector oldposition;
	public Vector Velocity = new Vector(0,0);
	public float mass =1.0f;
	public int width=20 ,height=20;
	private Graphics2D graph;
	public float radium = 10;
	public float friction= 1 ;
	public float bounce =-1;
	public Vector gravity = new Vector(0,0);
	public Object(Graphics2D context)
	{
		graph = context;
		
	}
	public Object(Graphics2D context,int x, int y , float speed,double direction)
	{
		position = new Vector(x,y);
		Velocity = new Vector();
		Velocity.setLength(speed);
		Velocity.setAngle(direction);
		graph = context;
	}
	public void accelerate(Vector accel)
	{
		
		this.Velocity.add(accel);
	}
	public void update()
	{
		this.Velocity.multiply (this.friction);
		this.Velocity.add(this.gravity);
		this.position.add(this.Velocity);
		
		
			
		 
	}
	public double angleTo(Object obj)
	{
		return Math.atan2(obj.position.y - this.position.y,obj.position.x-this.position.x);
		
	}
	public double angleTo(Vector position)
	{
		return Math.atan2(position.y - this.position.y,position.x-this.position.x);
	}
	public double distanceTo(Object obj)
	{
		double dx = obj.position.x - this.position.x;
		double dy = obj.position.y - this.position.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	public double distanceTo(Vector position)
	{
		double dx = position.x - this.position.x;
		double dy = position.y - this.position.y;
		return Math.sqrt(dx*dx+dy*dy);
	}
	public void gravitateTo(Object obj)
	{
		Vector grav = new Vector();
		double dist = this.distanceTo(obj);
		grav.setLength((float)(obj.mass/(dist*dist)));
		
		grav.setAngle ( this.angleTo(obj));
	 
		this.Velocity.add(grav);
		
	}
	public void render()
	{
		graph.fillArc((int)this.position.x-width/2, (int)this.position.y-width/2, width, height, 0, 360);
	}
}