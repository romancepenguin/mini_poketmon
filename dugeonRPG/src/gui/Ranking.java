package gui;
import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;

public class Ranking extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	
	private JLabel l1;
	private JLabel l2;
	
	private JTable table;
	DefaultTableModel model1;
	DefaultTableModel model2;
	private String[][] content1;
	JScrollPane scroll;
	private String head1[]={"순위","캐릭터","레벨"};
	private String head2[]={"순위","캐릭터","포켓몬","레벨"};
	
	public JPanel panel;
	
	sqlOrder sql=new sqlOrder();	
	
	public Ranking(){
		//this.setModal(true);
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("캐릭터 랭킹");
		
		b1=new JButton("포켓몬");
		b2=new JButton("뒤로");
		
		model1 = new DefaultTableModel(head1,0);
		content1=sql.sqlRank_Cha(); 
		String[] arr = new String[head1.length];
		for(int i=0;i<content1.length;i++){
	        arr[0] = String.valueOf(i+1);
	        arr[1] = content1[i][0];
	        arr[2] = content1[i][1];
	        model1.addRow(arr);
		}
		
		model2 = new DefaultTableModel(head2,0);
		String[][] content=sql.sqlRank_Po(); 
		String[] arr2 = new String[head2.length];
		for(int i=0;i<content.length;i++){
	        arr2[0] = String.valueOf(i+1);
	        arr2[1] = content[i][0];
	        arr2[2] = content[i][1];
	        arr2[3] = content[i][2];
	        model2.addRow(arr2);
		}
		
		table=new JTable(model1);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		JScrollPane scroll=new JScrollPane(table);
		panel.add(scroll);
		
		panel.add(l1);
		
		panel.add(b1);
		panel.add(b2);
		
		scroll.setBounds(50,30,680,350);
		l1.setBounds(50,5,120,20);
		b2.setBounds(5,400,90,30);
		b1.setBounds(680,400,90,30);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			if(l1.getText().equals("캐릭터 랭킹")){
				l1.setText("포켓몬 랭킹");
				b1.setText("포켓몬");
				table.setModel(model2);
				table.updateUI();
			}
			else{
				l1.setText("캐릭터 랭킹");
				b1.setText("캐릭터");
				table.setModel(model1);
				table.updateUI();	
			}
		}
		if(e.getSource()==b2){
			this.setVisible(false);
			//new GameMenu();
		}
	}

	public static void main(String[] args){
		//new Ranking();
	
	}
}
