import java.awt.Graphics2D;
import java.util.ArrayList;

public class sticks {
 ArrayList<Vector> mPoint;
 ArrayList <Object> mObject;
 private ArrayList<Float> PointLen;
 Graphics2D graph;
	public float gravity = 1f,
	bounce = 1.0f,
		
	friction = 0.9f;
public sticks(Graphics2D context)
{
	mPoint = new  ArrayList<Vector> ();
	mObject = new  ArrayList<Object> ();
	PointLen = new  ArrayList<Float> ();
	graph = context;
}
public void addPoint(Vector point)
{
	
	mPoint.add(point);
}
 public void preCalc()
 {
	 int objLen = mPoint.size();
	 for (int i=0;i<objLen;i++)
	 {
		 Object obj = new Object(graph);
		 obj.position = mPoint.get(i);
		 mObject.add(obj);
		 obj.gravity = new Vector(0,this.gravity);
		 obj.friction=0.9f;
		 obj.bounce =  bounce;
		 obj.Velocity.x=19;
	 }
	
	 for(int i = 0; i < objLen-1; i++) {
		 for(int j = objLen-1; j >=i+1 ; j--) {
				 Vector vec= mPoint.get(i).clone();
				 vec. subtract((Vector)mPoint.get(j));
				 PointLen.add( new Float(vec.getLength()));
				 
			}
		}
 }
 public void update()
 {
	
	 updateSticks();
	 for (Object obj :mObject )
	 {
		
		 if(obj.position.x + obj.radium +obj.Velocity.x > 800) {
			 obj.position.x =(800 -  obj.radium);
			 obj.Velocity.x=(obj.Velocity.x * obj.bounce);
			}
			if(obj.position.x -  obj.radium  +obj.Velocity.x < 0) {
				obj.position.x=(obj.radium);
				obj.Velocity.x=(obj.Velocity.x * obj.bounce);
			}
			if( obj.position.y + obj.radium +obj.Velocity.y> 500) {
				obj.position.y=(500 -obj.radium);
				obj.Velocity.y=(obj.Velocity.y * obj.bounce);
			}
			if(obj.position.y- obj.radium +obj.Velocity.y< 0) {
				obj.position.y=( obj.radium);
				obj.Velocity.y=(obj.Velocity.y * obj.bounce);
			}
			
			 obj.update(); 
	 }
 }
 public void render()
 {
	 Vector center = new Vector(0,0);
	 for (Object obj :mObject )
	 {
		 obj.render();
		 center.add(obj.position);
		 
	 }
	 for (int i=0;i<mPoint.size();i++)
	 {
		 int k =i+1;
		 k = (k>=mPoint.size())?0:k;
		 Vector current = mPoint.get(i);
		 Vector next = mPoint.get(k);
		 graph.drawLine((int)current.x, (int)current.y, (int)next.x, (int)next.y);
	 }
	 center.multiply(1.0f/mObject.size());
	 
	 for (int i=0;i<mPoint.size();i++)
	 {
 
 
		 Vector current = mPoint.get(i);
	 
		 graph.drawLine((int)current.x, (int)current.y, (int)center.x, (int)center.y);
	 }
	
	 graph.fillArc((int)  center.x-20/2, (int)center.y-20/2, 20, 20, 0, 360);
 }
 public void updateSticks() {
	 	int point = 0;
	 	int objLen = mObject.size();
		for(int i = 0; i < objLen-1; i++) {
			for(int j = objLen-1; j >=i+1 ; j--) {
			    Vector obj1 = mObject.get(i).position;
			    Vector obj2 = mObject.get(j).position;
				double dx = obj2.x - obj1.x,
				  dy =obj2.y -obj1.y,
				  distance = Math.sqrt(dx * dx + dy * dy),
				  difference = PointLen.get(point) - distance,
				  percent = difference / distance / 2,
				offsetX = dx * percent,
				offsetY = dy * percent;

				obj1.x -= offsetX;
				obj1.y -= offsetY;
				obj2.x += offsetX;
				obj2.y += offsetY;
			point++;
			}
		}
	}
}
