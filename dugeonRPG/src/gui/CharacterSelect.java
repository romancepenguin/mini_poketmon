package gui;
import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class CharacterSelect extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	//private JButton b3;
	
	private JLabel l1;
	
	private JTable table;
	
	public JPanel panel;

	private String[][] content;//={{"ab","ab","ab","ab","ab","ab"},{"ab","ab","ab","ab","ab","ab"}};
	
	public CharacterSelect(String id){
		//this.setModal(true);
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		//String head[]={"캐릭터","level","경험치","돈","도감","마지막 접속일자"};
		String head[]={"넘버","캐릭터","level","경험치","돈"};
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("Character Select");
		
		b1=new JButton("선택");
		b2=new JButton("종료");
		
		sqlOrder sql=new sqlOrder();
		content=sql.sqlMyCharacter(id);
		//System.out.println(content);
		//content={{"ab","ab"},{"ab","ab"},{"ab","ab"}};
		
		table=new JTable(content,head);
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		JScrollPane scroll=new JScrollPane(table);
		panel.add(scroll);
		
		panel.add(l1);
		
		panel.add(b1);
		panel.add(b2);
		//panel.add(b3);
		
		scroll.setBounds(50,30,380,350);
		l1.setBounds(50,5,120,20);
		
		b1.setBounds(380,400,90,30);
		b2.setBounds(5,400,90,30);
		//b3.setBounds(280,400,90,30);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			System.out.println(table.getValueAt(table.getSelectedRow(),0));
			new GameMenu(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)));
			//System.out.print(table.getValueAt(table.getSelectedRow(),0));
			setVisible(false);
		}
		if(e.getSource()==b2){
			new login();
			setVisible(false);
		}

	}

	public static void main(String[] args){
		//new CharacterSelect();
	}
	

}