package day1027;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Sungjuk_DB_Ex {
	static int menu;
	
	public static void main(String[] args) {
		SungjukList sungjuk = new SungjukList();
		
		while(true) {
		printMenu();
		if(menu == 6) {
			System.out.println("���α׷� ����!");
			return;
			}
		switch(menu) {
		case 1 :
			insert();
			
			break;
		case 2 :
			printSungjuk();
			break;
		case 3 :
			search();
			break;
		case 4 :
			update();
			break;
		case 5 :
			delete();
			break;
		}
		}
		
	}//����
	static void insert() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql ="Insert Into sungjuk (hakbun, irum, kor, eng, math, tot, avg, grade) " +"Values(?,?,?,?,?,?,?,?)";
		String hakbun, irum, grade;
		int kor, eng, math, tot;
		double avg;
		
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			System.out.println("<�������� �Է�>");
			System.out.print("�й� �Է� : ");
			hakbun = br.readLine();
			System.out.print("�̸� �Է� : ");
			irum = br.readLine();
			System.out.print("���� �Է� : ");
			kor = Integer.parseInt(br.readLine());
			System.out.print("���� �Է� : ");
			eng = Integer.parseInt(br.readLine());
			System.out.print("���� �Է� : ");
			math = Integer.parseInt(br.readLine());
			tot = kor + eng + math;
			avg = tot/3;
			switch((int)(avg/10)) {
			case 10:
			case 9:
				grade = "��";
				break;
			case 8:
				grade = "��";
				break;
			case 7:
				grade = "��";
				break;
			case 6:
				grade = "��";
				break;
			default:
				grade = "��";
				break;
			}
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott","123456");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, hakbun);
			pstmt.setString(2, irum);
			pstmt.setInt(3, kor);
			pstmt.setInt(4, eng);
			pstmt.setInt(5, math);
			pstmt.setInt(6, tot);
			pstmt.setDouble(7, avg);
			pstmt.setString(8, grade);

			pstmt.executeUpdate();
			
			System.out.println("�����ͺ��̽� ���� ����!");
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����! = "+ e.getMessage());
		}
		finally {
			try {
				if(con!=null) con.close();
				if(pstmt != null)pstmt.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}
	}
	
	static void search() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		//Statement stmt = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		String hakbun;
		
		String sql = "Select * From SUNGJUK where hakbun = ?";
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			System.out.print("��ȸ�� �й� �Է� : ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			//stmt = con.createStatement();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hakbun);
			rs = stmt.executeQuery(); //ResultSet ��ü ��ȯ
			//rs = stmt.executeQuery();
			
			System.out.println("�й�\t�̸�\t����\t����\t����\t����\t���\t���");
			System.out.println("-------------------------------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getString("hakbun")+"\t");
				System.out.print(rs.getString("irum")+"\t");
				System.out.print(rs.getInt("kor")+"\t");
				System.out.print(rs.getInt("eng")+"\t");
				System.out.print(rs.getInt("math")+"\t");
				System.out.print(rs.getInt("tot")+"\t");
				System.out.print(rs.getDouble("avg")+"\t");
				System.out.print(rs.getString("grade")+"\n");
			}
			System.out.println("-------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����!");
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}//��ġ
	
	static void update() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		PreparedStatement stmt = null;
		
		String hakbun, irum, grade;
		int kor, eng, math, tot;
		double avg;
		
		String sql = "Update sungjuk "
				+ "Set kor = ?, eng = ?, math =?, tot = ?, avg =?, grade = ? where hakbun = ?";
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			System.out.println("<������ �������� �Է�>");
			System.out.print("�й� �Է� : ");
			hakbun = br.readLine();
			System.out.print("���� �Է� : ");
			kor = Integer.parseInt(br.readLine());
			System.out.print("���� �Է� : ");
			eng = Integer.parseInt(br.readLine());
			System.out.print("���� �Է� : ");
			math = Integer.parseInt(br.readLine());
			tot = kor + eng + math;
			avg = tot/3;
			switch((int)(avg/10)) {
			case 10:
			case 9:
				grade = "��";
				break;
			case 8:
				grade = "��";
				break;
			case 7:
				grade = "��";
				break;
			case 6:
				grade = "��";
				break;
			default:
				grade = "��";
				break;
			}
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, kor);
			stmt.setInt(2, eng);
			stmt.setInt(3, math);
			stmt.setInt(4, tot);
			stmt.setDouble(5, avg);
			stmt.setString(6, grade);
			stmt.setString(7, hakbun);

			stmt.executeUpdate();
			System.out.println("�����ͺ��̽� ���� ���� �Ϸ�!");
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� !"+ e.getMessage());
		}
		finally {
			try {
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}//����
	
	static void delete() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		PreparedStatement stmt = null;
		
		String hakbun = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("<�������� ����>");
			System.out.print("������ �й� �Է� : ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			//con.setAutoCommit(false);
			stmt = con.prepareStatement("Delete from sungjuk where hakbun =?");
			stmt.setString(1, hakbun);
			stmt.executeUpdate();
			
			System.out.println("�����ͺ��̽� ���� ���� �Ϸ�!");
			//con.commit();
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ���� !"+ e.getMessage());
		}
		finally {
			try {
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}//����
	static void printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("*** �������� ***");
		System.out.println("1. �������� �Է�\n2. �������� ���\n3. �������� ��ȸ\n4. �������� ����\n5. �������� ����\n6. ���α׷� ����");
		System.out.println("\n�޴� ���� => ");
		menu = sc.nextInt();
		System.out.println();
	}
	static void printSungjuk() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		//Statement stmt = null;
		PreparedStatement stmt =null;
		ResultSet rs = null;
		
		String hakbun;
		
		String sql = "Select * From SUNGJUK Order by hakbun";
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			//stmt = con.createStatement();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery(); //ResultSet ��ü ��ȯ
			//rs = stmt.executeQuery();
			
			System.out.println("�й�\t�̸�\t����\t����\t����\t����\t���\t���");
			System.out.println("-------------------------------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getString("hakbun")+"\t");
				System.out.print(rs.getString("irum")+"\t");
				System.out.print(rs.getInt("kor")+"\t");
				System.out.print(rs.getInt("eng")+"\t");
				System.out.print(rs.getInt("math")+"\t");
				System.out.print(rs.getInt("tot")+"\t");
				System.out.print(rs.getDouble("avg")+"\t");
				System.out.print(rs.getString("grade")+"\n");
			}
			System.out.println("-------------------------------------------------------------------");
		}
		catch(Exception e) {
			System.out.println("�����ͺ��̽� ���� ����!");
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(stmt !=null) stmt.close();
				if(con != null) con.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
}//Ŭ����
