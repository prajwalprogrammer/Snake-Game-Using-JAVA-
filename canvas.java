package snake;
import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
@SuppressWarnings({ "serial", "unused" })
public class canvas extends Applet implements Runnable,KeyListener,ActionListener 
{
	public Image img;
	public game G;
	public Thread T1;
	public int fx,fy,score=0;
	Button 	start;
	int stop=0;
	public void init()
	{
		Color c1=new Color(255,128,64);
		Font f1=new Font("Dialog",Font.BOLD ,30);
    	setFont(f1);
	    img=getImage(getDocumentBase(),"logo.png");
		resize(1200,1200);
		setBackground(c1);
		G=new game();
    	requestFocus();
	   start = new Button("      START       ");	
       add(start);
       start.setSize(250,250);
		generatefood();
		start.addActionListener(this);
		addKeyListener(this);
		T1=new Thread(this);
	   	T1.start();
	}
	public void paint(Graphics g)
	{	
      g.drawImage(img,700,250, 250, 250, this);
		if(!endgame())
	{		
		g.setColor(Color.white);
		g.drawRect(150,150, 500, 500);
	    G.draw(g);
	    g.setColor(Color.red);
	  g.fillOval(fx,fy,10,10);
	 }
		else
   	  {
   		g.setColor(Color.red);
   		g.drawString("GAMEOVER",100,100);
   	  }
		g.drawString("SCORE="+score,500,100);
	}
	public void repaint(Graphics g)
	{
		paint(g);
	}

	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
     	if(!G.getmoving())
	{
     		int code=e.getKeyCode();
		switch(code)
		{
		case 37:
			if(G.getdirx()!= 1)
		{
		G.setdir1(-1, 0);
	    }
		break;
		case 38: 
			if(G.getdiry()!= 1)
		{
			 G.setdir1(0,-1);
		}
		break;
		case 39:
			if(G.getdirx()!= -1)
		{
		G.setdir1(1, 0);
	    }
		break;
		case 40: 
			if(G.getdiry()!= -1)
		{
			G.setdir1(0,1);
		}
		break;
		}
	}	
	}
	public void keyReleased(KeyEvent e) {
	}
	
	@SuppressWarnings("static-access")
	public void run() 
	{
		if(!endgame())
		{
			for(;;)
		{
			G.mave();
			eat();
			endgame();
			this.repaint();
			try {
				T1.sleep(40);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}	    
		}	
	 }
	}
		public boolean endgame()
	{
		int x=G.getheadx();
		int y=G.getheady();
		if(x<150 || x>643 || y<150 || y>643)
		{
			return true;
		}
		if(stop==1)
		{
			for(int i=1;i<G.snakepoint.size();i++)
		{
			if(x==G.snakepoint.get(i).getX()&&y==G.snakepoint.get(i).getY())
			{
				stop=2;
				break;
			
			}
		}
		}
		if(stop==2) {
			return true;
		}
		return false;	
	}
	public void generatefood()
	{
		boolean collision=false;
		do
		{
			 fx=(int)(Math.random()*300+150);
			 fy=(int)(Math.random()*300+150);
			for(int i=0;i<G.snakepoint.size();i++)
			{
				if(fx == G.snakepoint.get(i).getX() && fy == G.snakepoint.get(i).getY())
				{
					collision=true;
					break;
				}			
			}
		}while(collision);
		repaint();
	}
	public void eat()
	{
		int x1=G.getheadx()+4;
		int y1=G.getheady()+4;
		if(x1 >= fx-2 &&x1<=(fx+14))
		{		if(y1>= fy-2 &&y1<=(fy+14))
		{stop=1;
			G.grow();
		score=score+10;
		generatefood();
		}
	   }
	 }
	@Override
	public void actionPerformed(ActionEvent arg0) {
		requestFocus();	
	}
}

