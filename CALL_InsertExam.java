package day1027;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class InsertExam_CALL {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con = null;
		CallableStatement stmt = null;
		
		String hakbun, name, addr, phone;
		
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			System.out.println("Member 테이블에 값 추가하기.....");
			System.out.print("학번 입력 : ");
			hakbun = br.readLine();
			System.out.print("이름 입력 : ");
			name = br.readLine();
			System.out.print("주소 입력 : ");
			addr = br.readLine();
			System.out.print("핸드폰번호 입력 : ");
			phone = br.readLine();

			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott","123456");
			stmt = con.prepareCall("{call call_insert(?,?,?,?)}");
			stmt.setString(1, hakbun);
			stmt.setString(2, name);
			stmt.setString(3, addr);
			stmt.setString(4, phone);
			stmt.executeUpdate();
			
			System.out.println("데이터베이스 연결 성공!");
		}
		catch(Exception e) {
			System.out.println("데이터베이스 연결 실패! = "+ e.getMessage());
		}
		finally {
			try {
				if(con!=null) con.close();
				if(stmt != null)stmt.close();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}
	}
	}
/* 오라클 저장프로시저

CREATE OR REPLACE PROCEDURE CALL_INSERT (
HAKBUN MEMBER.HAKBUN%TYPE,
NAME MEMBER.NAME%TYPE, ADDR MEMBER.ADDR%TYPE, PHONE MEMBER.PHONE%TYPE)
IS BEGIN
INSERT INTO MEMBER VALUES(HAKBUN, NAME, ADDR, PHONE);
END;
/ */