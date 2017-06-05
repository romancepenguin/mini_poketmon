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
		
		String head[]={"�������ڵ�","�����۸�","����","����","����"};
		String content[][]=sql.sqlItem_list();
		
		panel=new JPanel();
		panel.setLayout(null);
		
		l1=new JLabel("���ϸ� ����");
		l2=new JLabel("�������ϸӴ�:"+String.valueOf(sql.sqlMy_money(cha_code)));
		
		b1=new JButton("����");
		b2=new JButton("�ڷ�");
		
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
			System.out.println("�ڵ�"+item_code);
			System.out.println("����"+price);
			sql.sqlBuy(ch_code,item_code); //����
			sql.sqlPrice_Minus(ch_code,price); //�����ͺ��̽� �� ����
			System.out.println("�����۱��� !!");
			l2.setText("�������ϸӴ�:"+String.valueOf((Integer.parseInt(l2.getText().substring(7))-price))); //�� ������
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
