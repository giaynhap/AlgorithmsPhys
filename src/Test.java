import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
public class Test {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}
	
	Box2 box2 ;
	Object Weight ;
	Kinematics kine; 
	Object sun,planet;
	Graphics2D hGrap;
	BufferedImage canvas = new BufferedImage(800,600,BufferedImage.TYPE_INT_ARGB);
	JLabel lb ;
	sticks sticks;
	private long timest = System.currentTimeMillis();
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lb = new JLabel(new ImageIcon(canvas));
		frame.getContentPane().add(lb);
		  hGrap = canvas.createGraphics();
		hGrap.setBackground(Color.decode("#4286f4"));
		hGrap.clearRect(0,0,800,600);
		kine = new Kinematics(hGrap,5,100); 
		sun = new Object(hGrap,300,300,0,0);
		planet = new Object(hGrap,300+200,300,10,-Math.PI/2) ;
		Weight= new Object (hGrap,500,400,10,Math.PI);
		Weight.friction =0.95f;
		Weight.gravity.y=0.5f;
		
		
		
		 int maxSize =5;
		
		sticks = new sticks(hGrap);

		for (int i=0;i<maxSize;i++)
		{
			int x = (int)(Math.cos((float)i/maxSize*Math.PI*2)*50);
			int y = (int)(Math.sin((float)i/maxSize*Math.PI*2)*50);
			sticks.addPoint(new Vector(x,y)); 
		}
		
  
		sticks.preCalc();
		
		
		 box2 = new Box2(hGrap);
		
		for (int i=0;i<maxSize;i++)
		{
			int x = (int)(Math.cos((float)i/maxSize*Math.PI*2 )*50);
			int y = (int)(Math.sin((float)i/maxSize*Math.PI*2 )*50);
		box2.addPoint(y,x);
		}

		box2.precalc();
		Thread thread = new Thread()
				{
					@Override
					public void run()
					{
						while (true)
						{
							long current = System.currentTimeMillis();
							double delta = (timest - current)/1000.0f;
							timest = current;
							int FPS = Math.abs((int) (1/delta));
							 
							try {
								Thread.sleep(10);
								hGrap.clearRect(0,0,800,600);
								process();
								 drawPoint();
								lb.repaint();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
						}
					}
				};
				thread.start();
				lb .addMouseMotionListener(new MouseMotionListener(){

					@Override
					public void mouseDragged(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseMoved(MouseEvent arg0) {
						 
						point.x = arg0.getX();
						point.y=arg0.getY();
					}
					
				});
	}
	
	
	
	public void drawPoint()
	{
		/*
			for (point p : polygon)
			{
				
				
			}
			hGrap.setColor(new Color(255, 178, 79));
			  for (int i=0;i<polygon.length;i++)
			  {
				  int next = i+1;
				  if (next >= polygon.length) next=0;	
				//  hGrap.fillArc(200+(int)(polygon[i].x*40),200+ (int)(polygon[i].y*40), 5,5, 0,360);
				  hGrap.drawLine((int)(polygon[i].x),(int)(polygon[i].y),
				 (int)(polygon[next].x), (int)(polygon[next].y));
			  }
			  hGrap.fillArc((int)point.x,(int)point.y, 10,10, 0,360);
			  if (check(  ))
			  {
				  hGrap.drawString("Ben Trong",200,100);;
			  }
			  
			  hGrap.setColor(Color.white);
			  hGrap.fillRect(0, 600-100, 800, 100);	 
			  */
		
		kine.render();
		sun.render();
		planet.render();
		Weight.render();
		sticks.render();
		box2.render();
			  
	}
	float k =0.1f;
	
	void process()
	{
		Weight.radium = 20;
		 
		sun.mass=20000;
		kine.drag((int)Weight.position.x,(int)Weight.position.y);
		 planet.gravitateTo(sun);
		planet.update();
		box2.update();
		
	   Vector mouseVec= new Vector(point.x,point.y);
	   mouseVec.subtract(Weight.position);
	   
	  // mouseVec.multiply(k);
	   double distance = mouseVec.getLength();
	   double springForce = (distance - 100) * k;
	   mouseVec.multiply((float)(1.0f/ distance * springForce));
			   
	   Weight.Velocity.add(mouseVec);
	   Weight.update();
	   sticks.update();
	   
		
		
	}
	point [] polygon= new point[]{new point(240,200),new point(280,240),new point(280,280),new point(200,280) ,new point(200,240)};
	point point = new point(245,210);
	public class point {
		public point(float x, float y) {
		 this.x =x;
		 this.y=y;
		}
		float x;
		float y;
		public float mul(point point)
		{
			return x*point.x+point.y*y;
		}
		public float len()
		{
			return (float)Math.sqrt(x*x+y*y);
		}
		public double angle(point point)
		{
			double rs = Math.acos(mul(point)/(len()*point.len()));
			return  rs ;
		}
		public point subtract(point point )
		{
			return new point(x-point.x,y-point.y);
		}
	}
	
 
	public   boolean  check(  )
	{
	  double angle = 0;
	  for (int i=0;i<polygon.length;i++)
	  {
		  int next = i+1;
		  if (next >= polygon.length) next=0;	
		  angle+=point.subtract(polygon[i]).angle(point.subtract(polygon[next]));
	  }
	  
	  	if (Math.abs(angle-Math.PI*2)<0.001) return true; // sai so!
	  	
	  	
	  	return false;
	}

}
