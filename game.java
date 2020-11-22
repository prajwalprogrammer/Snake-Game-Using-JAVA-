package snake;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
@SuppressWarnings({ "unused" })
public class game 
{
	public ArrayList<Point1> snakepoint	;
	public int xdir,ydir,size=20;
	public Point1 last;
	public boolean moving=false;
	Image img;
	game()
	{
		snakepoint=new ArrayList<Point1>();
		xdir=0;
		ydir=0;
		moving=false;
	snakepoint.add(new Point1(250,250));
	for(int i=1;i<size;i++)
	{
		snakepoint.add(new Point1(250-i*8,250));
	}
	}
	public void draw(Graphics g)
	{
		g.setColor(Color.green);
		for(Point1 p1:snakepoint)
		{
			g.setColor(Color.green);
			g.fillRect(p1.getX(), p1.getY(),8,8);
		}
	}
	public void mave()
	{
		if(!moving)
		{
		Point1 temp=snakepoint.get(0);
	Point1 newstart= new Point1(temp.getX()+getdirx()*8,temp.getY()+getdiry()*8);
	for(int i=snakepoint.size()-1;i>=1;i--)
	{
		snakepoint.set(i, snakepoint.get(i-1));
	}
	snakepoint.set(0,newstart);
		}
	}
public boolean getmoving()
{
	return moving;
	}
public void  setmoving(boolean b)
{
	moving=b;
	}

public int getdirx() {
	return xdir;
}
public int getdiry() {
	return ydir;
}
public void setdir1(int i, int j) {
	xdir=i;
	ydir=j;
}
public int getheadx()
{
	return snakepoint.get(0).getX();
}
public int getheady()
{
	return snakepoint.get(0).getY();
}
public void grow()
{
 snakepoint.add(new Point1(snakepoint.get(snakepoint.size()-1).getX(),snakepoint.get(snakepoint.size()-1).getY()));
}
}


