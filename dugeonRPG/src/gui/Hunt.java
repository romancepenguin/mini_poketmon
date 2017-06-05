package gui;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Hunt extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	
	public JPanel panel;
	
	public Hunt(){
		//this.setModal(true);
		setSize(500,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("Hunting!!");
		l2=new JLabel("적트레이너");
		l3=new JLabel("내꺼트레이너");
		
		t1=new JTextField(10);
		t2=new JTextField(10);
		t3=new JTextField(10);
		
		b1=new JButton("도망");
		b2=new JButton("종료");

		String skill[]={"a","b","c"};
		String item[]={"a","b","c"};
		String change[]={"a","b","c"};
		
		c1=new JComboBox(skill);
		c2=new JComboBox(item);
		c3=new JComboBox(change);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		
		panel.add(t1);
		panel.add(t2);
		panel.add(t3);
		
		panel.add(b1);
		panel.add(b2);
		
		panel.add(c1);
		panel.add(c2);
		panel.add(c3);
		
		l1.setBounds(5,5,100,20);
		l2.setBounds(30,30,80,20);
		l3.setBounds(230,230,80,20);
		
		t1.setBounds(30,60,60,20);
		t2.setBounds(230,260,60,20);
		t3.setBounds(5,300,300,50);
		
		b1.setBounds(350,310,90,30);
		b2.setBounds(350,5,90,30);
		
		c1.setBounds(350,190,90,30);
		c2.setBounds(350,230,90,30);
		c3.setBounds(350,270,90,30);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			
		}
		if(e.getSource()==b2){
			
		}
	}

	public static void main(String[] args){
		//new Hunt();
	}
	

}