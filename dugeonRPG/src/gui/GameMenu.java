package gui;
import DB.sqlOrder;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage; //¿ÃπÃ¡ˆ
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class GameMenu extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	//private JButton b8;
	
	private JLabel l1;
	private JLabel l2;
	
	BufferedImage img[]=new BufferedImage[6];
	
	public JPanel panel;
	
	private int cha_code;
	sqlOrder sql=new sqlOrder();
	
	public GameMenu(int character_code){
		cha_code=character_code;
		//this.setModal(true);
		int count=0;
		setSize(580,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255));
		
		int[] img_code=sql.sqlTrain(character_code);
		
		Canv[] ca=new Canv[6];
		for(int i=0;i<6;i++){
			if(img_code[0]!=0){
				count++;
				String dir="C:\\Users\\∆Î±œ\\workspace\\dugeonRPG\\po_image\\"+String.valueOf(img_code[i])+".jpg";
				System.out.println(dir);
				ca[i]=new Canv("C:\\Users\\∆Î±œ\\workspace\\dugeonRPG\\po_image\\"+String.valueOf(img_code[i])+".jpg");
				ca[i].setImg70();
				ca[i].repaint();
			}
		}
		
		//System.out.println(img_code[0]);
/*
			for(int i=0;i<6;i++){
				try{
				//System.out.println(String.valueOf(img_code[i])+".gif");
				//img=ImageIO.read(new File("C:\\Users\\∆Î±œ\\workspace\\dugeonRPG\\po_image\\200001.gif"));
				img[i]=ImageIO.read(new File("C:\\Users\\∆Î±œ\\workspace\\dugeonRPG\\po_image\\"+String.valueOf(img_code[i])+".gif"));
				System.out.println("succ"+i);
				if(img_code[i]!=0) count++;
				} catch(IOException e) {
					System.out.println("error\n");
				}
			}*/
			
			//Image im=Toolkit.getDefaultToolkit().getImage("C:\\Users\\∆Î±œ\\workspace\\dugeonRPG\\po_image\\200001.gif");
			//cv.setSize(0, 0);
/*
			class Canv extends Canvas{
				public void paint(Graphics g,Image image){
					g.drawImage(image, 0, 0, this);
				}
			}
			Graphics g = null;
			//img[i].getScaledInstance(70, 70, Image.SCALE_DEFAULT)
			Canv[] cv=new Canv[6];
			for(int i=0;i<6;i++){
				cv[i].paint(g,img[i].getScaledInstance(70, 70, Image.SCALE_DEFAULT));
			}*/

		l1=new JLabel("My Poketmon!!");
		l2=new JLabel("version:1.0");
		
		b1=new JButton("ªÁ≥…");
		b2=new JButton("∞·≈ı");
		b3=new JButton("ªÛ¡°");
		b4=new JButton("»∏∫π");
		b5=new JButton("∑©≈∑");
		b6=new JButton("µµ∞®");
		b7=new JButton("¡æ∑·");
		//b8=new JButton("º≥¡§");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		
		panel.add(l1);
		panel.add(l2);
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		panel.add(b5);
		panel.add(b6);
		panel.add(b7);
		
		for(int a=0;a<count;a++){
			panel.add(ca[a]);
		}
		for(int a=3;a<count;a++){
			panel.add(ca[a]);
		}
		
		l1.setBounds(5,5,90,40);
		l2.setBounds(450,265,80,20);
		
		b1.setBounds(450,5,90,30);
		b3.setBounds(450,50,90,30);
		b4.setBounds(450,95,90,30);
		b5.setBounds(450,140,90,30);
		b6.setBounds(450,185,90,30);
		b7.setBounds(450,230,90,30);
		
		//b2.setBounds(5,230,90,30);
		
		for(int a=0;a<count;a++){
			ca[a].setBounds(115+110*a, 50,70,70);
		}
		for(int a=3;a<count;a++){
			ca[a].setBounds(115+110*(a-3), 140,70,70);
		}
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			setVisible(false);
			sql.sqlExit();
			new Fight(cha_code);
		}
		else if(e.getSource()==b2){
			
		}
		else if(e.getSource()==b3){
			setVisible(false);
			sql.sqlExit();
			new Market(cha_code);
		}
		else if(e.getSource()==b4){
			new Heal();//∏ﬁºº¡ˆ √‚∑¬
			sql.sqlFull_hp(cha_code);
		}
		else if(e.getSource()==b5){
			//setVisible(false);
			//sql.sqlExit();
			new Ranking();
		}
		else if(e.getSource()==b6){
			new Dict(cha_code);
		}
		else if(e.getSource()==b7){
			setVisible(false);
		}
	}

	public static void main(String[] args){
		//new GameMenu(1001);
	}
	

}

class Heal extends JDialog{
    JLabel jlb1 = new JLabel("*∆˜ƒœ∏Û¿ª ¿¸∫Œ »∏∫πΩ√ƒ◊Ω¿¥œ¥Ÿ*");
    public Heal(){
    		this.setLayout(new BorderLayout());
    		add(jlb1,BorderLayout.CENTER);

            this.setSize(300,200);
            this.setModal(true);
            this.setVisible(true);
           
    }
}