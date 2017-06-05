package gui;

import dugeonRPG.Fight_Cul;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DB.sqlOrder;

import java.awt.event.*;

public class Fight extends JFrame implements ActionListener{
	
	sqlOrder sql=new sqlOrder();
	
	Fight_Cul fight=new Fight_Cul();
	
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	
	private JButton ab1; //�������� ��ư
	private JButton ab2;
	private JButton ab3;
	private JButton ab4;
	
	private JButton cb1; //��ü���� ��ư
	private JButton cb2;
	private JButton cb3;
	private JButton cb4;
	private JButton cb5;
	private JButton cb6;
	
	private JLabel l1;
	private JLabel l2;
	private JLabel l3;
	private JLabel l4;
	//private JLabel l5;
	private JLabel l6;
	private JLabel l7;
	private JLabel l8;
	private JLabel l9;
	
	private JTextField t3;
	
	DefaultTableModel model;
	private JTable table;
	private String head[]={"�����ڵ�","�����۸�","����","ȿ��"};
	private String[][] content;
	JScrollPane scroll;
	
	Canv myBackImage;
	Canv MatchImage;
	
	private int hp;
	private int ep;
	private int y_level; //y�� ���������
	private int m_level;
	private int y_po_code; //m�� ���� ����
	private int m_po_code;
	private int train_code; //Ʈ���� �ڵ� �⺻Ű
	public int[] y_spec=new int[4];
	public int[] m_spec=new int[4];//BASE_AP,BASE_SP,BASE_HP,BASE_SPEED
	private String[] skill_list=new String[4];
	private String[] my_poketmon_list=new String[6];
	
	private int ch_co; //�ٸ� Ŭ������ ���º���-ĳ���� �ڵ�
	
	public JPanel panel;
	
	public Fight(int cha_code){
		//this.setModal(true);
		ch_co=cha_code;
		
		setSize(500,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Drpg");
		
		panel=new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(255,255,255));
	
		m_po_code=sql.sqlPo_code(cha_code);
		train_code=sql.sqlPo_train(m_po_code, cha_code,1);
		m_level=sql.sqlPo_level(train_code);
		m_spec=sql.sqlHpApSp(m_po_code);
		
		myBackImage=new Canv("C:\\Users\\���\\workspace\\dugeonRPG\\ffight_image_back\\"+String.valueOf(sql.sqlImage_toPofromChc_First(cha_code))+".jpg");
		myBackImage.setImg150();
		myBackImage.repaint();

		y_po_code=fight.getRan_Po();
		y_level=fight.getRan_level();
		y_spec=sql.sqlHpApSp(y_po_code);
		
		MatchImage=new Canv("C:\\Users\\���\\workspace\\dugeonRPG\\fight_image\\"+String.valueOf(
				sql.sqlImage(y_po_code))+".jpg");
		MatchImage.setImg110();
		MatchImage.repaint();
		
		l1=new JLabel("Fighting!!");
		l2=new JLabel(sql.sqlPoName(y_po_code)); //��
		l3=new JLabel("����:"+String.valueOf(y_level));
		l4=new JLabel("ü��:"+String.valueOf(fight.getHp_point(y_level, y_spec[2])));
		
		l6=new JLabel(sql.sqlPoName(m_po_code)); //��
		l7=new JLabel("ü��:"+sql.sqlTr_hp(train_code));
		l8=new JLabel("����:"+String.valueOf(m_level));
		l9=new JLabel("����ġ:"+String.valueOf(sql.sqlPo_ep(train_code)));
		
		t3=new JTextField(10);
		t3.setEditable(false);
		t3.setDragEnabled(false);
		
		b1=new JButton("����");
		b2=new JButton("������");
		b3=new JButton("��ü");
		b4=new JButton("����");
		
		skill_list=sql.sqlPo_skill_list(train_code);
		ab1=new JButton(skill_list[0]);
		ab2=new JButton(skill_list[1]);
		ab3=new JButton(skill_list[2]);
		ab4=new JButton(skill_list[3]);
		
		my_poketmon_list=sql.sqlMy_poketmon(cha_code);
		cb1=new JButton(my_poketmon_list[0]);
		cb2=new JButton(my_poketmon_list[1]);
		cb3=new JButton(my_poketmon_list[2]);
		cb4=new JButton(my_poketmon_list[3]);
		cb5=new JButton(my_poketmon_list[4]);
		cb6=new JButton(my_poketmon_list[5]);
		
		b1.setBackground(new Color(255,255,255));
		b2.setBackground(new Color(255,255,255));
		b3.setBackground(new Color(255,255,255));
		b4.setBackground(new Color(255,255,255));
		ab1.setBackground(new Color(255,255,255));
		ab2.setBackground(new Color(255,255,255));
		ab3.setBackground(new Color(255,255,255));
		ab4.setBackground(new Color(255,255,255));
		cb1.setBackground(new Color(255,255,255));
		cb2.setBackground(new Color(255,255,255));
		cb3.setBackground(new Color(255,255,255));
		cb4.setBackground(new Color(255,255,255));
		cb5.setBackground(new Color(255,255,255));
		cb6.setBackground(new Color(255,255,255));
		
		b1.setRolloverEnabled(true);
		b2.setRolloverEnabled(true);
		b3.setRolloverEnabled(true);
		b4.setRolloverEnabled(true);
		ab1.setRolloverEnabled(true);
		ab2.setRolloverEnabled(true);
		ab3.setRolloverEnabled(true);
		ab4.setRolloverEnabled(true);
		cb1.setRolloverEnabled(true);
		cb2.setRolloverEnabled(true);
		cb3.setRolloverEnabled(true);
		cb4.setRolloverEnabled(true);
		cb5.setRolloverEnabled(true);
		cb6.setRolloverEnabled(true);
		
		model = new DefaultTableModel(head,0);
		content=sql.sqlMyItem_list(cha_code);
		String[] arr = new String[4];
		for(int i=0;i<content.length;i++){
	        arr[0] = content[i][0];
	        arr[1] = content[i][1];
	        arr[2] = content[i][2];
	        arr[3] = content[i][3];
			model.addRow(arr);
		}
		
		table=new JTable(model);
		scroll=new JScrollPane(table);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		
		ab1.addActionListener(this);
		ab2.addActionListener(this);
		ab3.addActionListener(this);
		ab4.addActionListener(this);
		
		cb1.addActionListener(this);
		cb2.addActionListener(this);
		cb3.addActionListener(this);
		cb4.addActionListener(this);
		cb5.addActionListener(this);
		cb6.addActionListener(this);
		
		panel.add(l1);
		panel.add(l2);
		panel.add(l3);
		panel.add(l4);
		//panel.add(l5);
		panel.add(l6);
		panel.add(l7);
		panel.add(l8);
		panel.add(l9);
		
		panel.add(t3);
		panel.add(scroll);
		
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		
		panel.add(ab1);
		panel.add(ab2);
		panel.add(ab3);
		panel.add(ab4);
		
		panel.add(cb1);
		panel.add(cb2);
		panel.add(cb3);
		panel.add(cb4);
		panel.add(cb5);
		panel.add(cb6);
		
		panel.add(myBackImage);
		panel.add(MatchImage);
		
		l1.setBounds(5,5,100,20);
		l2.setBounds(170,20,70,20);
		l3.setBounds(240,20,50,20);
		l4.setBounds(170,50,100,20);
		//l5.setBounds(30,30,80,20);
		l6.setBounds(280,160,70,20);
		l7.setBounds(280,185,100,20);
		l8.setBounds(350,160,50,20);
		l9.setBounds(280,210,100,20);
		
		ab1.setBounds(40,260,190,25);
		ab2.setBounds(240,260,190,25);
		ab3.setBounds(40,295,190,25);
		ab4.setBounds(240,295,190,25);
		
		cb1.setBounds(40,260,123,25);
		cb2.setBounds(173,260,123,25);
		cb3.setBounds(306,260,123,25);
		cb4.setBounds(40,295,123,25);
		cb5.setBounds(173,295,123,25);
		cb6.setBounds(306,295,123,25);
		
		scroll.setBounds(30,250,410,80);
		
		ab1.setVisible(false);
		ab2.setVisible(false);
		ab3.setVisible(false);
		ab4.setVisible(false);
		
		cb1.setVisible(false);
		cb2.setVisible(false);
		cb3.setVisible(false);
		cb4.setVisible(false);
		cb5.setVisible(false);
		cb6.setVisible(false);
		
		scroll.setVisible(false);

		t3.setBounds(30,250,410,80);
		
		b1.setBounds(40,350,90,30);
		b2.setBounds(140,350,90,30);
		b3.setBounds(240,350,90,30);
		b4.setBounds(340,350,90,30);

		myBackImage.setBounds(80,110,150,150);
		MatchImage.setBounds(280,20,110,110);
		
		add(panel);
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1){
			if(b1.getText().equals("����")){
				t3.setVisible(false);
				ab1.setVisible(true);
				ab2.setVisible(true);
				ab3.setVisible(true);
				ab4.setVisible(true);
				b1.setText("���");
				b2.setEnabled(false);
				b3.setEnabled(false);
				b4.setEnabled(false);
				
			}
			else{
				t3.setVisible(true);
				ab1.setVisible(false);
				ab2.setVisible(false);
				ab3.setVisible(false);
				ab4.setVisible(false);
				b1.setText("����");
				b2.setEnabled(true);
				b3.setEnabled(true);
				b4.setEnabled(true);
			}
		}
		if(e.getSource()==b2){
			if(b2.getText().equals("������")){
				
				t3.setVisible(false);
				scroll.setVisible(true);
				b2.setText("���");
				b1.setEnabled(false);
				b3.setText("���");
				b4.setEnabled(false);
			}
			else{
				t3.setVisible(true);
				scroll.setVisible(false);
				b2.setText("������");
				b3.setText("��ü");
				b1.setEnabled(true);
				b4.setEnabled(true);
			}
		}
		if(e.getSource()==b3){ //��ü
			
			if(b3.getText().equals("��ü")){
				t3.setVisible(false);
				cb1.setVisible(true);
				cb2.setVisible(true);
				cb3.setVisible(true);
				cb4.setVisible(true);
				cb5.setVisible(true);
				cb6.setVisible(true);
				b3.setText("���");
				b1.setEnabled(false);
				b2.setEnabled(false);
				b4.setEnabled(false);
				
			}
			else if(b3.getText().equals("���")){
				t3.setVisible(true);
				cb1.setVisible(false);
				cb2.setVisible(false);
				cb3.setVisible(false);
				cb4.setVisible(false);
				cb5.setVisible(false);
				cb6.setVisible(false);
				b3.setText("��ü");
				b1.setEnabled(true);
				b2.setEnabled(true);
				b4.setEnabled(true);
				
			}
			else{
				//�����ۻ�� ���κ�
				String type_name=(String)table.getValueAt(table.getSelectedRow(),2);
				String item_name=(String)table.getValueAt(table.getSelectedRow(),1);
				int amount=Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),3));
				
				sql.sqlDel_Item(Integer.parseInt((String)table.getValueAt(table.getSelectedRow(),0))); //������ ����� ����
				int rowindex = table.getSelectedRow(); //���õ� Row�� rowindex�� ����
			    model.removeRow(rowindex); 
			    model.fireTableDataChanged();//������ row �� �ݿ��ǵ��� ���̺��� ������Ʈ�Ѵ�.
			    table.updateUI();
				
				if(type_name.equals("���Ϻ�")){
					int train_count=sql.sqlTrain_Count(ch_co); //���� Ű��� ���ϸ��
					if(train_count>=6){
						System.out.println("���ϸ� ����Ƚ���ʰ�");
						t3.setText("���ϸ� ����Ƚ���ʰ�");
					}
					else{
						if(fight.get_Poketmon(amount)){ //��°Ϳ� ����!
							sql.sqlCatch_poketmon(ch_co,y_po_code,y_level);
							new Catch_sucess();//�޽��� ���
							sql.sqlExit();
							new GameMenu(ch_co); //���Ӹ޴� �ٽý���
							this.setVisible(false); //â�ݱ�
							
						}
						else{
							t3.setText("���ϸ��� ��µ� �����Ͽ����ϴ�.");
							System.out.println("�ƽ��׿� �����ϼ̾��..");
						}
					}
				}
				else if(type_name.equals("��ȭ��")){
					//System.out.println((String)table.getValueAt(table.getSelectedRow(),1));
					String spec_type=sql.sqlItem_type(item_name);
					if(spec_type.equals("���ݷ�")){
						m_spec[0]+=amount;
					}
					else if(spec_type.equals("����")){
						System.out.println("����"+m_spec[1]);
						m_spec[1]+=amount;
						System.out.println("����"+m_spec[1]);
					}
					else{//���ǵ�
						m_spec[3]+=amount;
					} 
					
				}
				else{ //ȸ����
					l7.setText("ü��"+String.valueOf(Integer.parseInt(l4.getText().substring(3))+amount));
				}
				
				t3.setVisible(true);
				scroll.setVisible(false);
				b2.setText("������");
				b3.setText("��ü");
				b1.setEnabled(true);
				b4.setEnabled(true);
				
				//System.out.print(table.getValueAt(table.getSelectedRow(),0));
			}
			
		}
		if(e.getSource()==b4){
			sql.sqlExit();
			new GameMenu(ch_co);
			setVisible(false);
		}
		if(e.getSource()==ab1){
			attack(ab1.getText());
		}
		if(e.getSource()==ab2){
			attack(ab2.getText());
		}
		if(e.getSource()==ab3){
			attack(ab3.getText());
		}
		if(e.getSource()==ab4){
			attack(ab4.getText());
		}
		if(e.getSource()==cb1){
			change(cb1.getText(),1);
		}
		if(e.getSource()==cb2){
			change(cb2.getText(),2);
		}
		if(e.getSource()==cb3){
			change(cb3.getText(),3);
		}
		if(e.getSource()==cb4){
			change(cb4.getText(),4);
		}
		if(e.getSource()==cb5){
			change(cb5.getText(),5);
		}
		if(e.getSource()==cb6){
			change(cb6.getText(),6);
		}
	}
	
	public void change(String text,int index){
		m_po_code=sql.sqlPo_code_fromName(text);
		train_code=sql.sqlPo_train(m_po_code, ch_co,index);
		m_level=sql.sqlPo_level(train_code);
		m_spec=sql.sqlHpApSp(m_po_code); //�⺻ ���ϸ� ����
		
		myBackImage.reImg("C:\\Users\\���\\workspace\\dugeonRPG\\ffight_image_back\\"+String.valueOf(
				sql.sqlImage(m_po_code))+".jpg");
		myBackImage.setImg150();
		myBackImage.repaint();
		
		l6.setText(sql.sqlPoName(m_po_code)); //��
		l7.setText("ü��:"+String.valueOf(fight.getHp_point(m_level, m_spec[2])));
		l8.setText("����:"+String.valueOf(m_level));
		l9.setText("����ġ:"+String.valueOf(sql.sqlPo_ep(train_code)));
		
		skill_list=sql.sqlPo_skill_list(train_code);
		ab1.setText(skill_list[0]);
		ab2.setText(skill_list[1]);
		ab3.setText(skill_list[2]);
		ab4.setText(skill_list[3]);
	}
	
	public void attack(String text){
		int type=sql.sqlPo_skill_type(text);
		if(type==0){ //��ųŸ�� Ȯ�� 0 ���� / 1 ���� / 2 ����
			if(m_spec[3]*m_level>=y_spec[3]*y_level){ //���ǵ��
				int a=fight.getAttack_point(m_level, m_spec[0]);
				int b=sql.sqlSkill_Amount(text);
				int damage=a*b/100-y_spec[1];
				if(damage<=0) damage=1;
				l4.setText("ü��:"+String.valueOf(Integer.parseInt(l4.getText().substring(3))-damage));
				//
				if(Integer.parseInt(l4.getText().substring(3))<=0){// �����ϸ� ����
					new Hunt_Success(); //�����޽��� ���
					//sql. 
					sql.sqlPo_ep_plus(train_code);
					sql.sqlCha_ep_plus(ch_co);
					int conlevel=sql.sqlCon_level(m_po_code);
					if(conlevel==0){System.out.println("�� �̻� ��ȭ�� ��ü����");}
					else{ //�ƴϸ� ��ȭ���� üũ
						if(conlevel<=sql.sqlPo_level(train_code)){ //���� ��ȭ
							//���ϸ� �ڵ常 �����ֱ�
							sql.sqlEvol(train_code);//����
							new Evolution();//��ȭ ���ϸ޼���
						}
					}
					//���ϸ� ep����-���ϸ� levelup check ��ȭ check 
					sql.sqlPrice_plus(ch_co);
					System.out.println(Integer.parseInt(l7.getText().substring(3)));
					sql.sqlPo_Hp_update(train_code,Integer.parseInt(l7.getText().substring(3)));
					sql.sqlExit();
					new GameMenu(ch_co); //���Ӹ޴� �ٽý���
					this.setVisible(false); //â�ݱ�
				}

				else attack_me();
				
				System.out.println(l7.getText().substring(3));
				if(Integer.parseInt(l7.getText().substring(3))<=0){ //�й�
					new Hunt_fault();
					sql.sqlPo_Hp_update(train_code,1);
					new GameMenu(ch_co); //���Ӹ޴� �ٽý���
					sql.sqlExit();
					this.setVisible(false); //â�ݱ�
					
				}
			}
			else{
				attack_me();
				System.out.println(l7.getText().substring(3));
				if(Integer.parseInt(l7.getText().substring(3))<=0){ //�й�
					new Hunt_fault();
					sql.sqlPo_Hp_update(train_code,0);
					new GameMenu(ch_co); //���Ӹ޴� �ٽý���
					sql.sqlExit();
					this.setVisible(false); //â�ݱ�
				}
				
				else {
					int a=fight.getAttack_point(m_level, m_spec[0]);
					int b=sql.sqlSkill_Amount(text);
					int damage=a*b/100-y_spec[1];
					if(damage<=0) damage=1;
					l4.setText("ü��:"+String.valueOf(Integer.parseInt(l4.getText().substring(3))-damage));
				}
				
				if(Integer.parseInt(l4.getText().substring(3))<=0){// �����ϸ� ����
					new Hunt_Success(); //�����޽��� ���
					//sql. 
					sql.sqlPo_ep_plus(train_code);
					sql.sqlCha_ep_plus(ch_co); //���ϸ� ep���� or ������
					int conlevel=sql.sqlCon_level(m_po_code);
					if(conlevel==0){System.out.println("�� �̻� ��ȭ�� ��ü����");}
					else{ //�ƴϸ� ��ȭ���� üũ
						if(conlevel<=sql.sqlPo_level(train_code)){ //���� ��ȭ
							//���ϸ� �ڵ常 �����ֱ�
							sql.sqlEvol(train_code);//����
							new Evolution();//��ȭ ���ϸ޼���
						}
					}
					//���ϸ� ep����-���ϸ� levelup check ��ȭ check 
					sql.sqlPrice_plus(ch_co);
					sql.sqlPo_Hp_update(train_code,Integer.parseInt(l7.getText().substring(3)));					
					sql.sqlExit(); //�������
					new GameMenu(ch_co); //���Ӹ޴� �ٽý���
					this.setVisible(false); //â�ݱ�
				}
			}
		}
		else if(type==1)//����
		{
			int amount=sql.sqlSkill_Amount(text);
			String skill_type=sql.sqlCurse_type(sql.sqlSkill_code(text));
			
			if(skill_type.equals("���ݷ�")){
				y_spec[0]-=amount;
			}
			if(skill_type.equals("����")){
				y_spec[1]-=amount;
			}
			else{ //���ǵ�
				y_spec[3]-=amount;
			}
			//attack_me();
		}
		else//����
		{
			int amount=sql.sqlSkill_Amount(text);
			String skill_type=sql.sqlBuff_type(sql.sqlSkill_code(text));
			
			if(skill_type.equals("���ݷ�")){
				m_spec[0]+=amount;
			}
			if(skill_type.equals("����")){
				m_spec[1]+=amount;
			}
			else{ //���ǵ�
				m_spec[3]+=amount;
			} 
			//attack_me();
		} 
	}
	public void attack_me(){
		int damage=y_spec[0]*10-m_spec[1];
		if(damage<=0) damage=1;//������ ���ݷº��� ������� �������� 1���ش�.
		l7.setText("ü��:"+String.valueOf(Integer.parseInt(l7.getText().substring(3))-damage));
	}

	public static void main(String[] args){
		//new Fight(1001);
	}
	

}

class Hunt_Success extends JDialog{
    JLabel jlb1 = new JLabel("��ɿ� �����ϼ̾��!!");
    JLabel jlb2 = new JLabel("������ ����ġ�� ���ϸӴϸ� ȹ�� �ϼ̽��ϴ�!!");
    JLabel jlb3 = new JLabel("10ep 100money");
    public Hunt_Success(){
		this.setLayout(new BorderLayout());
		add(jlb1,BorderLayout.PAGE_START);
		add(jlb2,BorderLayout.CENTER);
		add(jlb3,BorderLayout.PAGE_END);
            this.setSize(300,200);
            this.setModal(true);
            this.setVisible(true);
           
    }
}

class Hunt_fault extends JDialog{
    JLabel jlb1 = new JLabel("��ɿ� �����ϼ̾��̤�");
    public Hunt_fault(){
		this.setLayout(new BorderLayout());
		add(jlb1,BorderLayout.CENTER);
            this.setSize(300,200);
            this.setModal(true);
            this.setVisible(true);
           
    }
}

class Catch_sucess extends JDialog{
    JLabel jlb1 = new JLabel("���ϸ��� ��µ� �����ϼ̽��ϴ�!!");
    public Catch_sucess(){
		this.setLayout(new BorderLayout());
		add(jlb1,BorderLayout.CENTER);
            this.setSize(300,200);
            this.setModal(true);
            this.setVisible(true);
           
    }
}


class Evolution extends JDialog{
    JLabel jlb1 = new JLabel("���ϸ��� ��ȭ�߾��!! ����! ����!");
    public Evolution(){
		this.setLayout(new BorderLayout());
		add(jlb1,BorderLayout.CENTER);
            this.setSize(300,200);
            this.setModal(true);
            this.setVisible(true);
           
    }
}
