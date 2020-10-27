package day1027;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

public class SelectExam_CALL {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		CallableStatement stmt = null;
		//저장프로시저나 저장함수를 사용할때 씀
		//PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			stmt = con.prepareCall("{call call_select(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			//stmt = con.prepareStatement(sql);
			stmt.executeQuery(); //ResultSet 객체 반환
			rs = (ResultSet)stmt.getObject(1);
			//stms.getObject >> object 객체로 리턴받아 ResultSet으로 형변환(select-테이블 구조이기 때문)
			
			System.out.println("hakbun\tname\taddr\tphone");
			System.out.println("-----------------------------------------------");
			while(rs.next()) {
				System.out.print(rs.getNString("hakbun")+"\t");
				System.out.print(rs.getNString("name")+"\t");
				System.out.print(rs.getNString("addr")+"\t");
				System.out.print(rs.getNString("phone")+"\n");
			}
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
}
/* 오라클 저장프로시저
 
CREATE OR REPLACE PROCEDURE CALL_SELECT(
V_MEMBER_CURSOR OUT SYS_REFCURSOR
-- OUT(반환) SYS_REFCURSOR 커서타입의 값 (커서객체)
)
IS 
BEGIN
OPEN V_MEMBER_CURSOR FOR SELECT * FROM MEMBER ORDER BY HAKBUN;
END;*/