package gui;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Canv extends Canvas{
	Image img;
	public Canv(String dir){
		img=Toolkit.getDefaultToolkit().getImage(dir);
	}
	public void setImg70(){
		img=img.getScaledInstance(70, 70, Image.SCALE_DEFAULT);
	}
	public void setImg150(){
		img=img.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
	}
	public void setImg110(){
		img=img.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
	}
	public void reImg(String dir){
		img=Toolkit.getDefaultToolkit().getImage(dir);
	}
	@Override
	public void paint(Graphics g){
		g.drawImage(img,0,0, this);
	}
}