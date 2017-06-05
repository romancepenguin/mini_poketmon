package DB;

import java.io.*;
import java.sql.*;

import gui.Ranking;

public class sqlOrder {
	Connection con;

	 public sqlOrder() {
	   String url="jdbc:oracle:thin:@localhost:1521:XE"; 
        /* 11g express edition은 orcl 대신 XE를 입력한다. */
	   String userid="madang";
	   String pwd="madang";

	   try { /* 드라이버를 찾는 과정 */
	     Class.forName("oracle.jdbc.driver.OracleDriver");
	     System.out.println ("드라이버 로드 성공");
	   } catch(ClassNotFoundException e) {
		e.printStackTrace();
	     }

	   try { /* 데이터베이스를 연결하는 과정 */
	     System.out.println ("데이터베이스 연결 준비 ...");
	     con=DriverManager.getConnection(url, userid, pwd);
	     System.out.println ("데이터베이스 연결 성공");
	     } catch(SQLException e) {
	         e.printStackTrace();
		}
	   }

	   public boolean sqlLogin(String id,String password) {
	     String query="SELECT ID,PASSWORD FROM USER_INFO"; /* SQL 문 */
	     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
		   Statement stmt=con.createStatement();
		   ResultSet rs=stmt.executeQuery(query);
		   while(rs.next()) {
			   if(id.equals(rs.getString(1))){
				   if(password.equals(rs.getString(2))){
					   System.out.print("login success");
					   con.close();
					   return true;
				   }
			   }
		   }

		   con.close();
	     } catch(SQLException e) {
		     e.printStackTrace();
		 }
	     System.out.print("login fault");
	     return false;
	   }
	   public boolean sqlSign(String id,String password,String name,String address,int gender) {
		     String query="INSERT INTO USER_INFO VALUES"+"('"+id+"','"+password+"','"+name+"','"+address+"','"+gender+"')"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);
			   
			   return true;
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     System.out.print("signup fault");
		     return false;
	   }
	   
	   public boolean sqlBuy(int ch_code,int item_code) {
		   
		     String query="SELECT POKET_CODE FROM POKET ORDER BY POKET_CODE"; /* SQL 문 */
		   	 int poket_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   System.out.println("버이작동");
			   while(rs.next()){
				   poket_code=rs.getInt(1);
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     poket_code+=1;
		     query="INSERT INTO POKET VALUES("+poket_code+","+ch_code+","+item_code+")"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);
			   
			   return true;
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     System.out.print("INSERT POKET fault");
		     return false;
	   }
	   
	   public boolean sqlDel_Item(int poket_code) {
		     String query="DELETE FROM POKET WHERE POKET_CODE="+poket_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);
	
			   return true;
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     System.out.print("item_delete fault");
		     return false;
	   }
	   
	   public boolean sqlPo_Hp_update(int tr_code,int o_hp) {	
		   	 if(o_hp<=0){o_hp=1;}
		     String query="UPDATE TRAIN_POKETMON SET HP="+o_hp+" WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);
	
			   return true;
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     System.out.print("hp_update fault");
		     return false;
	   }
	   
	   public boolean sqlPrice_Minus(int cha_code,int price) {
		     String query="SELECT MONEY FROM CHARACTER WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     int f_money=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   f_money=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     f_money-=price;
		     query="UPDATE CHARACTER SET MONEY="+f_money+" WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   System.out.println("마이너스작동");
				 Statement stmt=con.createStatement();
				   int rs=stmt.executeUpdate(query);
		
				 return true;
			 } catch(SQLException e) {
				     e.printStackTrace();
			 }
		     System.out.print("MINUS fault");
		     return false;
	   }
	   
	   public boolean sqlPrice_plus(int cha_code) {
		     String query="SELECT MONEY FROM CHARACTER WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     int f_money=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   f_money=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     f_money+=100;
		     query="UPDATE CHARACTER SET MONEY="+f_money+" WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				 Statement stmt=con.createStatement();
				   int rs=stmt.executeUpdate(query);
		
				 return true;
			 } catch(SQLException e) {
				     e.printStackTrace();
			 }
		     System.out.print("plus fault");
		     return false;
	   }
	   
	   public boolean sqlEvol(int tr_code) {
		     String query="UPDATE TRAIN_POKETMON SET POKETMON_CODE=(SELECT UP_GRADE FROM EVOLUTION WHERE DOWN_GRADE="
		     		+ "(SELECT POKETMON_CODE FROM TRAIN_POKETMON WHERE TRAIN_CODE="+tr_code+")) WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				 Statement stmt=con.createStatement();
				   int rs=stmt.executeUpdate(query);
		
				 return true;
			 } catch(SQLException e) {
				     e.printStackTrace();
			 }
		     System.out.print("evol fault");
		     return false;
	   }
	   
	   public boolean sqlFull_hp(int ch_code) { //base_hp*포켓몬레벨 만큼 회복
		     String query="UPDATE TRAIN_POKETMON T SET HP=T.Poketmon_Level*(SELECT BASE_HP FROM POKETMON P WHERE P.POKETMON_CODE=T.POKETMON_CODE) WHERE CHARACTER_CODE="+ch_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				 Statement stmt=con.createStatement();
				   int rs=stmt.executeUpdate(query);
		
				 return true;
			 } catch(SQLException e) {
				     e.printStackTrace();
			 }
		     System.out.print("Fullhp fault");
		     return false;
	   }
	   
	   public boolean sqlCha_ep_plus(int cha_code) {
		     String query="SELECT EP FROM CHARACTER WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     int ep=0;
		     int level=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   ep=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     ep+=10;
		     query="SELECT CHARACTER_LEVEL FROM CHARACTER WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */

		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   level=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     
		     if(ep>=100){ //레벨업
			     query="UPDATE CHARACTER SET EP=0 WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   int rs=stmt.executeUpdate(query);
			
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     level+=1;
			     System.out.println(level);
			     query="UPDATE CHARACTER SET CHARACTER_LEVEL="+level+" WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   stmt.executeUpdate(query);
			
					 return true;
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     System.out.print("EP_plus fault");
		     }
		     
		     else{
			     query="UPDATE CHARACTER SET EP="+ep+" WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   int rs=stmt.executeUpdate(query);
			
					 return true;
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     System.out.print("EP_plus fault");
		     }

		     return false;
	   }
	   
	   
	   
	   public boolean sqlPo_ep_plus(int tr_code) {
		     String query="SELECT EP,POKETMON_LEVEL FROM TRAIN_POKETMON WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
		     int ep=0;
		     int level=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   ep=rs.getInt(1);
			   level=rs.getInt(2);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     ep+=10;	
		     if(ep>=100){ //레벨업
			     query="UPDATE TRAIN_POKETMON SET EP="+0+" WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   int rs=stmt.executeUpdate(query);
			
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     level+=1;
			     query="UPDATE TRAIN_POKETMON SET POKETMON_LEVEL="+level+" WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   int rs=stmt.executeUpdate(query);
			
					 return true;
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     System.out.print("EP_plus fault");
		     }
		     
		     else{
			     query="UPDATE TRAIN_POKETMON SET EP="+ep+" WHERE TRAIN_CODE="+tr_code; /* SQL 문 */
			     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
					 Statement stmt=con.createStatement();
					   int rs=stmt.executeUpdate(query);
			
					 return true;
				 } catch(SQLException e) {
					     e.printStackTrace();
				 }
			     System.out.print("EP_plus fault");
		     }

		     return false;
	   }
	   
	   public boolean sqlCatch_poketmon(int cha_code,int po_code,int level) {
		     String query="SELECT TRAIN_CODE FROM TRAIN_POKETMON"; /* SQL 문 */
		   	 int train_code=0;
		   	 int list_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   train_code=rs.getInt(1);
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     query="SELECT LIST_CODE FROM SKILL_LIST"; /* SQL 문 */
		   	
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   list_code=rs.getInt(1);
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     list_code+=1;
		     train_code+=1;
		     
		     query="INSERT INTO TRAIN_POKETMON VALUES("+train_code+","+po_code+","+level+",1,1,"+cha_code+")"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);

		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     
		     query="INSERT INTO SKILL_LIST VALUES("+list_code+","+train_code+",6,10)"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   int rs=stmt.executeUpdate(query);
	
			   return true;
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     System.out.print("insert train fault");
		     return false;
	   }
	   
	   public String[][] sqlMyCharacter(String id) {
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM CHARACTER WHERE id="+"'"+id+"'"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT * FROM CHARACTER WHERE id="+"'"+id+"'"; /* SQL 문 */
		     //StringBuilder CharacterList[][]=new StringBuilder[cnt][5];
		     //CharacterList=null;
		     String CharacterList[][]=new String[cnt][5];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   //CharacterList[cnt][i].append(rs.getString(i));
				   CharacterList[i][0]=rs.getString(1);
				   CharacterList[i][1]=rs.getString(2);
				   CharacterList[i][2]=String.valueOf(rs.getInt(3));
				   CharacterList[i][3]=String.valueOf(rs.getInt(4));
				   CharacterList[i][4]=String.valueOf(rs.getInt(5));
				   //CharacterList[i][4]=String.valueOf(rs.getInt(6));
				   i++;
			   }
			   
			   con.close();
			   //return CharacterList
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return CharacterList;
	   }	   
	   
	   public String[][] sqlItem_list() {
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM ITEM"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT * FROM ITEM"; /* SQL 문 */
		     String item_list[][]=new String[cnt][5];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   item_list[i][0]=String.valueOf(rs.getInt(1));
				   item_list[i][1]=rs.getString(4);
				   System.out.println(String.valueOf(rs.getInt(1)));
				   System.out.println(item_list[i][0]);
				   
				   if(rs.getInt(2)==0){item_list[i][2]="포켓볼";}
				   else if(rs.getInt(2)==1){item_list[i][2]="회복약";}
				   else{item_list[i][2]="강화제";}
				
				   item_list[i][3]=rs.getString(3);
				   item_list[i][4]=String.valueOf(rs.getInt(5));
				   i++;
			   }
			   
			   //con.close();
			   //return CharacterList
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return item_list;
	   }
	   
	   public String[][] sqlMyItem_list(int cha_code) { //코드 명 종류 효과양
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM POKET WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT POKET_CODE,NAME,ITEM_TYPE,I_AMOUNT(bb.ITEM_CODE) FROM ITEM bb,"
		    		 +"(SELECT * FROM POKET WHERE CHARACTER_CODE="+cha_code+") aa WHERE aa.ITEM_CODE=bb.ITEM_CODE"; /* SQL 문 */
		     String item_list[][]=new String[cnt][4];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   item_list[i][0]=String.valueOf(rs.getInt(1));
				   item_list[i][1]=rs.getString(2);
				   if(rs.getInt(3)==0){item_list[i][2]="포켓볼";}
				   else if(rs.getInt(3)==1){item_list[i][2]="회복약";}
				   else{item_list[i][2]="강화제";}
				
				   item_list[i][3]=String.valueOf(rs.getInt(4));
				   i++;
				   System.out.println(rs.getString(2));
			   }
			   
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return item_list;
	   }
	   
	   public String[][] sqlCatch_log(int cha_code) { //코드 명 종류 효과양
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM DICT WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT TO_CHAR(CATCH_DATE,'yyyy_mm_dd'),NICKNAME,PROPERTIES,KIND "
		     		+ "FROM DICT INNER JOIN POKETMON ON DICT.POKETMON_CODE=POKETMON.POKETMON_CODE "
		     		+ "WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     String dict_log[][]=new String[cnt][4];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   dict_log[i][0]=rs.getString(1);
				   dict_log[i][1]=rs.getString(2);
				   dict_log[i][2]=rs.getString(3);
				   dict_log[i][3]=rs.getString(4);
				   i++;
			   }
			   
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return dict_log;
	   }
	   
	   public String[][] sqlRank_Cha() { 
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM CHARACTER"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT NICKNAME,CHARACTER_LEVEL FROM CHARACTER ORDER BY CHARACTER_LEVEL DESC";
		     String rank_list[][]=new String[cnt][2];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   rank_list[i][0]=rs.getString(1);
				   rank_list[i][1]=String.valueOf(rs.getInt(2));
				   i++;
			   }
			   
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return rank_list;
	   }
	   
	   public String[][] sqlRank_Po() { 
		   	 int cnt=0;
		   	Statement stmt = null;
		   	ResultSet rs;
		     String query="SELECT COUNT(*) FROM TRAIN_POKETMON"; /* SQL 문 */
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
				   stmt=con.createStatement();
				   rs=stmt.executeQuery(query);
				   while(rs.next()){
					   cnt=rs.getInt(1);
				   }
			     } catch(SQLException e) {
				     e.printStackTrace();
				 }
		     query="SELECT CHARACTER.NICKNAME,POKETMON.NICKNAME,POKETMON_LEVEL FROM CHARACTER,POKETMON,TRAIN_POKETMON WHERE POKETMON.POKETMON_CODE=TRAIN_POKETMON.POKETMON_CODE AND TRAIN_POKETMON.CHARACTER_CODE=CHARACTER.CHARACTER_CODE ORDER BY POKETMON_LEVEL DESC";
		     String rank_list[][]=new String[cnt][3];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   rank_list[i][0]=rs.getString(1);
				   rank_list[i][1]=rs.getString(2);
				   rank_list[i][2]=String.valueOf(rs.getInt(3));
				   i++;
			   }
			   
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }

		     return rank_list;
	   }
	   
	   public int[] sqlTrain(int cha_code) {
		     String query="SELECT POKETMON_IMAGE_CODE FROM POKETMON,TRAIN_POKETMON WHERE POKETMON.POKETMON_CODE=TRAIN_POKETMON.POKETMON_CODE AND CHARACTER_CODE="+cha_code
		    		 +" ORDER BY TRAIN_CODE"; /* SQL 문 */
		     int[] img_code=new int[6];
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   img_code[i]=rs.getInt(1);
				   i++;
				   System.out.println(rs.getInt(1));
			   }

		     } catch(SQLException e) {
			     e.printStackTrace();
			     System.out.println("error_imagecode_extract\n");
			 }
		     return img_code;
	   }
	   
	   public int sqlImage(int po_code) {
		     String query="SELECT POKETMON_IMAGE_CODE FROM POKETMON WHERE POKETMON_CODE="+po_code; /* SQL 문 */
			 int image_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   image_code=rs.getInt(1);
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return image_code;
	   }
	   
	   public int sqlMy_money(int cha_code) {
		     String query="SELECT MONEY FROM CHARACTER WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
			 int money=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()) {
				   money=rs.getInt(1);
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return money;
	   }
	   
	   public int[] sqlHpApSp(int po_code) {
		     String query="SELECT BASE_AP,BASE_SP,BASE_HP,BASE_SPEED FROM POKETMON WHERE POKETMON_CODE="+po_code; /* SQL 문 */
		     int[] po_spec=new int[4];
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   po_spec[0]=rs.getInt(1);
			   po_spec[1]=rs.getInt(2);
			   po_spec[2]=rs.getInt(3);
			   po_spec[3]=rs.getInt(4);
			   
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return po_spec;
	   }

	   public String sqlPoName(int po_code) {
		     String query="SELECT NICKNAME FROM POKETMON WHERE POKETMON_CODE="+po_code; /* SQL 문 */
		     String po_name=null;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   po_name=rs.getString(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return po_name;
	   }
	   
	   public String sqlItem_type(String name) {
		     String query="SELECT SPEC_TYPE FROM SPEC_UP WHERE ITEM_CODE=(SELECT ITEM_CODE FROM ITEM WHERE NAME='"+name+"')"; /* SQL 문 */
		     String item_type=null;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   item_type=rs.getString(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return item_type;
	   }
	   
	   public String sqlPropertie(int po_code) {
		     String query="SELECT PROPERTIES FROM POKETMON WHERE POKETMON_CODE="+po_code; /* SQL 문 */
		     String po_proper=null;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   po_proper=rs.getString(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return po_proper;
	   }
	   
	   public String sqlCurse_type(int skill_code) {
		     String query="SELECT CURSE_TYPE FROM CURSE_SKILL WHERE SKILL_CODE="+skill_code; /* SQL 문 */
		     String curse_type=null;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   curse_type=rs.getString(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return curse_type;
	   }
	   
	   public String sqlBuff_type(int skill_code) {
		     String query="SELECT CURSE_TYPE FROM CURSE_SKILL WHERE SKILL_CODE="+skill_code; /* SQL 문 */
		     String buff_type=null;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   buff_type=rs.getString(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return buff_type;
	   }
	   
	   public int sqlPo_code(int cha_code) {
		     String query="SELECT POKETMON_CODE FROM TRAIN_POKETMON WHERE CHARACTER_CODE="+cha_code; /* SQL 문 */
		     int po_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   po_code=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return po_code;
	   }
	   
	   public int sqlSkill_code(String skill_name) {
		     String query="SELECT SKILL_CODE FROM SKILL WHERE NAME='"+skill_name+"'"; /* SQL 문 */
		     int skill_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   skill_code=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return skill_code;
	   }
	   
	   public int sqlPo_code_fromName(String po_name) {
		     String query="SELECT POKETMON_CODE FROM POKETMON WHERE NICKNAME='"+po_name+"'"; /* SQL 문 */
		     int po_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   po_code=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return po_code;
	   }
	   
	   public int sqlPo_level(int train_code) {
		     String query="SELECT POKETMON_LEVEL FROM TRAIN_POKETMON WHERE TRAIN_CODE="+train_code; /* SQL 문 */
		     int level=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   level=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return level;
	   }
	   public int sqlPo_ep(int train_code) {
		     String query="SELECT EP FROM TRAIN_POKETMON WHERE TRAIN_CODE="+train_code; /* SQL 문 */
		     int ep=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   ep=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return ep;
	   }
	   
	   public int sqlPo_train(int po_code,int cha_code,int index) {
		     String query="SELECT TRAIN_CODE FROM TRAIN_POKETMON WHERE POKETMON_CODE="+po_code+"AND CHARACTER_CODE="+cha_code; /* SQL 문 */
		     int train_code=0;
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   if(i>=index)break;
				   train_code=rs.getInt(1);
				   i++;
			   }

		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return train_code;
	   }
	   
	   public int sqlSkill_Amount(String name) {
		     String query="SELECT SELECT_SKILL(NAME) FROM SKILL WHERE NAME LIKE '"+name+"'"; /* SQL 문 */
		     int amount=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   amount=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return amount;
	   }
	   
	   public String[] sqlPo_skill_list(int train_code) {
		     String query="SELECT NAME FROM SKILL WHERE SKILL_CODE IN (SELECT SKILL_CODE FROM SKILL_LIST WHERE TRAIN_CODE = (SELECT TRAIN_CODE FROM TRAIN_POKETMON WHERE TRAIN_CODE="+train_code+"))"; /* SQL 문 */
		     String[] skill_list=new String[4]; //스킬은 최대4개 보유
			 //System.out.println(train_code);
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   skill_list[i]=rs.getString(1);
				   i++;
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return skill_list;
	   }
	   
	   public String[] sqlMy_poketmon(int cha_code) {
		     String query="SELECT NICKNAME FROM POKETMON,TRAIN_POKETMON WHERE TRAIN_POKETMON.POKETMON_CODE=POKETMON.POKETMON_CODE AND CHARACTER_CODE="+cha_code+" ORDER BY TRAIN_CODE"; /* SQL 문 */
		     String[] poket_list=new String[6]; //스킬은 최대4개 보유
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   while(rs.next()){
				   poket_list[i]=rs.getString(1);
				   i++;
			   }
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return poket_list;
	   }
	   
	   public int sqlPo_skill_type(String name) {
		     String query="SELECT skill_type FROM SKILL WHERE NAME LIKE '"+name+"'"; /* SQL 문 */
			 int skill_type=0;
		     int i=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   skill_type=rs.getInt(1);
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return skill_type;
	   }
	   
	   public int sqlImage_toPofromChc_First(int Character_code) {
		     String query="SELECT POKETMON_IMAGE_CODE FROM POKETMON WHERE POKETMON_CODE IN (SELECT POKETMON_CODE FROM TRAIN_POKETMON WHERE CHARACTER_CODE="+Character_code+")"; /* SQL 문 */
			 int image_code=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   image_code=rs.getInt(1);
			   

			   //con.close();
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return image_code;
	   }
	   public int sqlTrain_Count(int cha_code) {
		     String query="SELECT COUNT(*) FROM TRAIN_POKETMON WHERE CHARACTER_CODE="+cha_code;
			 int train_count=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   train_count=rs.getInt(1);
			   
			   //con.close();
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return train_count;
	   }

	   public int sqlCon_level(int po_code) {
		     String query="SELECT CONDITION_LEVEL FROM EVOLUTION WHERE DOWN_GRADE="+po_code;
			 int con_level=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   if(rs.getString(1)==null){
				   return 0; //더이상 진화 개체가 없다
			   }
			   con_level=rs.getInt(1);
			   
			   //con.close();
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return con_level;
	   }
	   
	   public int sqlTr_hp(int tr_code) {
		     String query="SELECT HP FROM TRAIN_POKETMON WHERE TRAIN_CODE="+tr_code;
			 int hp=0;
		     try { /* 데이터베이스에 질의 결과를 가져오는 과정 */
			   Statement stmt=con.createStatement();
			   ResultSet rs=stmt.executeQuery(query);
			   rs.next();
			   hp=rs.getInt(1);
			   
			   //con.close();
		     } catch(SQLException e) {
			     e.printStackTrace();
			 }
		     return hp;
	   }
	   
	   public void sqlExit(){
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	   }
	  
}