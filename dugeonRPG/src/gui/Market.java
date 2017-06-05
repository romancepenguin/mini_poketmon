package gui;
import DB.sqlOrder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Market extends JFrame implements ActionListener{
	private JButton b1;
	private JButton b2;
	
	private JLabel l1;
	private JLabel l2;
	
	private JTable table;
	
	public JPanel panel;
	
	private int ch_code;

	sqlOrder sql=new sqlOrder();	
	
	public Market(int cha_code){
		ch_code=cha_code;
		//this.setModal(true);
		setSize(800,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		String head[]={"아이템코드","아이템명","종류","설명","가격"};
		String content[][]=sql.sqlItem_list();
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("포켓몬 상점");
		l2=new JLabel("보유포켓머니:"+String.valueOf(sql.sqlMy_money(cha_code)));
		
		b1=new JButton("구매");
		b2=new JButton("뒤로");
		
		table=new JTable(content,head);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		
		JScrollPane scroll=new JScrollPane(table);
		panel.add(scroll);
		
		panel.add(l1);
		panel.add(l2);
		
		panel.add(b1);
		panel.add(b2);
		
		scroll.setBounds(50,30,680,350);
		l1.setBounds(50,5,120,20);
		l2.setBounds(330,400,150,30);
		
		b2.setBounds(5,400,90,30);
		b1.setBounds(680,400,90,30);
		
		add(panel);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			int item_code=Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0));
			int price=Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),4));
			System.out.println("코드"+item_code);
			System.out.println("가격"+price);
			sql.sqlBuy(ch_code,item_code); //구입
			sql.sqlPrice_Minus(ch_code,price); //데이터베이스 돈 차감
			System.out.println("아이템구입 !!");
			l2.setText("보유포켓머니:"+String.valueOf((Integer.parseInt(l2.getText().substring(7))-price))); //라벨 돈차감
		}
		if(e.getSource()==b2){
			sql.sqlExit();
			new GameMenu(ch_code);
			setVisible(false);
		}
	}

	public static void main(String[] args){
		//new Market(1001);
	
	}
}
