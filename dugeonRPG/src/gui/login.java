package gui;

import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class login extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JLabel l1;
	private JLabel l2;
	private JTextField t1;
	
	private JTextField t2;
	
	public login(){
		setSize(350,190);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");

		
		JPanel panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("ID");
		l2=new JLabel("PASSWORD");
		
		t1=new JTextField(10);
		t2=new JTextField(10);
		
		b1=new JButton("로그인");
		b2=new JButton("회원가입");
		b3=new JButton("종료");
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		panel.add(l1);
		panel.add(l2); 
		panel.add(t1);
		panel.add(t2);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		
		
		l1.setBounds(50,5,50,20);
		l2.setBounds(50,30,50,20);
		t1.setBounds(100,5,110,20);
		t2.setBounds(100,30,110,20);
		
		b1.setBounds(20,80,90,30);
		b2.setBounds(120,80,90,30);
		b3.setBounds(220,80,90,30);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			sqlOrder sql=new sqlOrder();
			if(true==sql.sqlLogin(t1.getText(), t2.getText())){
				setVisible(false);
				new CharacterSelect(t1.getText());	
			}
			
		}
		if(e.getSource()==b2){
			new SignUp();
			setVisible(false);
		}
		if(e.getSource()==b3){
			setVisible(false);
		}
	}
	public static void main(String[] args){
		new login();
	}
}

