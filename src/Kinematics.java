import java.awt.Graphics2D;

public class Kinematics {
	int length = 0;
	Arm[] arms ;
	Graphics2D graph ;
	public float x=600,y=500;
	Arm lastArm = null;

	public Kinematics(Graphics2D Context,int arnlen, int length)
	{
		graph = Context;
		this.length = length;
	
		arms = new Arm[length];
		for (int i=0;i<length;i++)
		{
			arms[i] = new Arm(graph, arnlen);
			if (lastArm!=null ) arms[i].paren = lastArm ;
			lastArm = arms[i];
		}
		
	}
	public void render()
	{
		for (int i=0;i<length;i++)
		{
			arms[i].render();
		}
	}
	public void drag(int x,int y)
	{
		lastArm.drag(x, y);
	}
	public void reach (int x,int y)
	{
		this.drag(x, y);
		this.update();
	}
	private void update()
	{
		for (int i=0;i<length;i++)
		{
			if (arms[i].paren!=null)
			{
				arms[i].x = arms[i].paren.getEndX();
				arms[i].y = arms[i].paren.getEndY();
			}
			else
			{
			 
				arms[i].x =this.x;
				arms[i].y = this.y ;
			}
			arms[i].update();
		}
	}
}
