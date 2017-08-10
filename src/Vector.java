
public class Vector {
	public float x,y;
	public double note;
	public  Vector()
	{
		
	}
	public Vector(float x, float y)
	{
		this.x = x;
		this.y =y;
	}
	public double getAngle ()
	{
		return Math.atan2(this.x,this.y);
	}
	public void setAngle (double angle)
	{
		double length = this.getLength();
	 
		this.x = (float) (Math.cos(angle)*length);
		this.y = (float) (Math.sin(angle)*length);
	}
	public double getLength()
	{
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	public void setLength(float length)
	{
		double angle =  getAngle ();
		this.x = (float) (Math.cos(angle)*length);
		this.y = (float) (Math.sin(angle)*length);
	}
	public void add(float x,float y)
	{
		this.x+=x;
		this.y+=y;
	}
	public void add(Vector vector)
	{
		this.x+=vector.x;
		this.y+=vector.y;
	}
	
	public void subtract(float x,float y)
	{
		this.x-=x;
		this.y-=y;
	}
	public void subtract(Vector vector)
	{
		this.x-=vector.x;
		this.y-=vector.y;
	}
	
	public double multiply(Vector vector)
	{
		return Math.sqrt(this.x*vector.x+this.y*vector.y);	
	}
	public void  multiply(float val)
	{
		 this.x *=val;
		 this.y*=val;
	}
	public Vector clone()
	{
		return new Vector(this.x,this.y);
	}
	
	
	
}
