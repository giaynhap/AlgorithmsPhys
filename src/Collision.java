
public class Collision {
	 public static double Distance(Vector vec1,Vector vec2)
	 {
		 Vector vec = new Vector(vec1.x,vec1.y);
		 vec.subtract(vec2);
		 return  vec.getLength();
	 }
	
 public static boolean circleCollision(Object c1, Object c2)
 {
	 return Distance(c1.position,c2.position) <= c1.radium+c2.radium;
 }
 public static boolean circlePoint(Object c1,float x,float y)
 {
	 return Distance(c1.position,new Vector(x,y)) <= c1.radium;
 }

 public static boolean rectPoint(Object rect,float x,float y)
 {
	 return inRange(x,rect.position.x,rect.position.x+ rect.width) && inRange(y,rect.position.y,rect.position.y+ rect.height) ;
 }
 public static boolean rectIntersect(Object r1,Object r2)
 {
	 return rangeIntersect(r1.position.x,r1.position.x+r1.width,r2.position.x,r2.position.x+r2.width )&&
			rangeIntersect(r1.position.y,r1.position.y+r1.height,r2.position.y,r2.position.y+r2.height ) ;
 }
 private static boolean inRange(float value,float max,float min)
 {
	 return value >=Math.min(min,max)&& value <= Math.max(max,min);
 }
 private static boolean rangeIntersect(float max0,float max1,float min0,float min1)
 {
	 return Math.max(min0, max0)>=Math.min(min1, max1)&&Math.min(min0, max0)<=Math.max(max1, min1);
 }
}
