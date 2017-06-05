package gui;
import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class Dict extends JFrame implements ActionListener{
	
	private JButton b2;
	
	private JLabel l1;
	private JLabel l2;
	
	private JTable table;
	DefaultTableModel model;
	private String[][] content;
	JScrollPane scroll;
	private String head[]={"잡은날짜","포켓몬","속성","종족"};
	
	public JPanel panel;
	
	sqlOrder sql=new sqlOrder();	
	
	public Dict(int cha_code){
		//this.setModal(true);
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("포켓몬 도감");
		
		b2=new JButton("뒤로");
		
		model = new DefaultTableModel(head,0);
		content=sql.sqlCatch_log(cha_code); 
		String[] arr = new String[head.length];
		for(int i=0;i<content.length;i++){
	        arr[0] = content[i][0];
	        arr[1] = content[i][1];
	        arr[2] = content[i][2];
	        arr[3] = content[i][3];
	        model.addRow(arr);
		}
		
		table=new JTable(model);
		
		b2.addActionListener(this);
		
		JScrollPane scroll=new JScrollPane(table);
		panel.add(scroll);
		
		panel.add(l1);
		
		
		panel.add(b2);
		
		scroll.setBounds(50,30,680,350);
		l1.setBounds(50,5,120,20);
		b2.setBounds(5,400,90,30);
		
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==b2){
			this.setVisible(false);
			//new GameMenu();
		}
	}

	public static void main(String[] args){
		//new Dict(1001);
	
	}
}