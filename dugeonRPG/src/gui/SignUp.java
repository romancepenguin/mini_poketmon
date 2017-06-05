package gui;

import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignUp extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	private JLabel l5;
	
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	
	private JRadioButton r1;
	private JRadioButton r2;
	private ButtonGroup g;
	
	public JPanel panel;
	
	public SignUp(){
		//this.setModal(true);
		setSize(250,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("ID");
		l2=new JLabel("PASSWORD");
		l3=new JLabel("name");
		l4=new JLabel("address");
		l5=new JLabel("gender");
		
		t1=new JTextField(10);
		t2=new JTextField(10);
		t3=new JTextField(10);
		t4=new JTextField(10);
		
		b1=new JButton("회원가입");
		b2=new JButton("종료");
		
		r1=new JRadioButton("남");
		r2=new JRadioButton("여");
		
		g=new ButtonGroup();
		
		g.add(r1);
		g.add(r2);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		panel.add(l5);
		
		panel.add(t1);
		panel.add(t2);
		panel.add(t3);
		panel.add(t4);
		
		panel.add(b1);
		panel.add(b2);
		
		panel.add(r1);
		panel.add(r2);
		
		l1.setBounds(50,5,50,20);
		l2.setBounds(50,30,50,20);
		l3.setBounds(50,55,50,20);
		l4.setBounds(50,80,50,20);
		l5.setBounds(50,105,50,20);
		
		t1.setBounds(100,5,110,20);
		t2.setBounds(100,30,110,20);
		t3.setBounds(100,55,110,20);
		t4.setBounds(100,80,110,20);
		
		b1.setBounds(20,140,90,30);
		b2.setBounds(120,140,90,30);
		
		r1.setBounds(100,110,50,20);
		r2.setBounds(170,110,50,20);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			int i=0; //0은 남자 1은 여자
			if(r1.isSelected()) i=0;
			else if(r2.isSelected()) i=1;
			//else 에러처리 두개다 선택안됬을때
			sqlOrder sql=new sqlOrder();
			sql.sqlSign(t1.getText(),t2.getText(),t3.getText(),t4.getText(),i);
			setVisible(false);
			new login();
		}
		if(e.getSource()==b2){
			setVisible(false);
			new login();
		}
	}

	public static void main(String[] args){
		//new SignUp();
	}
	

}


