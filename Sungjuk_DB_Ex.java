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
			System.out.println("프로그램 종료!");
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
		
	}//메인
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
			System.out.println("<성적정보 입력>");
			System.out.print("학번 입력 : ");
			hakbun = br.readLine();
			System.out.print("이름 입력 : ");
			irum = br.readLine();
			System.out.print("국어 입력 : ");
			kor = Integer.parseInt(br.readLine());
			System.out.print("영어 입력 : ");
			eng = Integer.parseInt(br.readLine());
			System.out.print("수학 입력 : ");
			math = Integer.parseInt(br.readLine());
			tot = kor + eng + math;
			avg = tot/3;
			switch((int)(avg/10)) {
			case 10:
			case 9:
				grade = "수";
				break;
			case 8:
				grade = "우";
				break;
			case 7:
				grade = "미";
				break;
			case 6:
				grade = "양";
				break;
			default:
				grade = "가";
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
			
			System.out.println("데이터베이스 연결 성공!");
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 실패! = "+ e.getMessage());
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
			System.out.print("조회할 학번 입력 : ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			//stmt = con.createStatement();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hakbun);
			rs = stmt.executeQuery(); //ResultSet 객체 반환
			//rs = stmt.executeQuery();
			
			System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t평균\t등급");
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
			System.out.println("데이터베이스 연결 실패!");
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
	}//서치
	
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
			System.out.println("<수정할 성적정보 입력>");
			System.out.print("학번 입력 : ");
			hakbun = br.readLine();
			System.out.print("국어 입력 : ");
			kor = Integer.parseInt(br.readLine());
			System.out.print("영어 입력 : ");
			eng = Integer.parseInt(br.readLine());
			System.out.print("수학 입력 : ");
			math = Integer.parseInt(br.readLine());
			tot = kor + eng + math;
			avg = tot/3;
			switch((int)(avg/10)) {
			case 10:
			case 9:
				grade = "수";
				break;
			case 8:
				grade = "우";
				break;
			case 7:
				grade = "미";
				break;
			case 6:
				grade = "양";
				break;
			default:
				grade = "가";
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
			System.out.println("데이터베이스 내용 수정 완료!");
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 실패 !"+ e.getMessage());
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
	}//수정
	
	static void delete() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		PreparedStatement stmt = null;
		
		String hakbun = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("<성적정보 삭제>");
			System.out.print("삭제할 학번 입력 : ");
			hakbun = br.readLine();
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			//con.setAutoCommit(false);
			stmt = con.prepareStatement("Delete from sungjuk where hakbun =?");
			stmt.setString(1, hakbun);
			stmt.executeUpdate();
			
			System.out.println("데이터베이스 내용 삭제 완료!");
			//con.commit();
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 실패 !"+ e.getMessage());
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
	}//삭제
	static void printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println();
		System.out.println("*** 성적관리 ***");
		System.out.println("1. 성적정보 입력\n2. 성적정보 출력\n3. 성적정보 조회\n4. 성적정보 수정\n5. 성적정보 삭제\n6. 프로그램 종료");
		System.out.println("\n메뉴 선택 => ");
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
			rs = stmt.executeQuery(); //ResultSet 객체 반환
			//rs = stmt.executeQuery();
			
			System.out.println("학번\t이름\t국어\t영어\t수학\t총점\t평균\t등급");
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
			System.out.println("데이터베이스 연결 실패!");
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
}//클래스
