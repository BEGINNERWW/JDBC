package day1027;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class UpdateExam_CALL {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		CallableStatement stmt = null;
		
		String hakbun, addr, phone;
		
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
			System.out.println("Member 테이블에 값 수정하기.....");
			System.out.print("수정할 학번 입력 : ");
			hakbun = br.readLine();
			System.out.print("새 주소 입력 : ");
			addr = br.readLine();
			System.out.print("새 핸드폰번호 입력 : ");
			phone = br.readLine();
			
			//String sql = "Update member Set addr = '"+ addr;
			//sql += "', phone = '" + phone + "' Where hakbun ='";
			//sql += hakbun +"'";
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			stmt = con.prepareCall("{call call_update(?,?,?)}");
			stmt.setString(1, hakbun);
			stmt.setString(2, addr);
			stmt.setString(3, phone);
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

	}
}
/* 오라클 저장프로시저

CREATE OR REPLACE PROCEDURE CALL_UPDATE(
P_HAKBUN MEMBER.HAKBUN%TYPE, P_ADDR MEMBER.ADDR%TYPE, P_PHONE MEMBER.PHONE%TYPE)
IS BEGIN
UPDATE MEMBER SET ADDR = P_ADDR, PHONE = P_PHONE WHERE HAKBUN = P_HAKBUN;
END;
/ */