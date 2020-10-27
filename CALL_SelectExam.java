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
		//�������ν����� �����Լ��� ����Ҷ� ��
		//PreparedStatement stmt =null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			
			stmt = con.prepareCall("{call call_select(?)}");
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			//stmt = con.prepareStatement(sql);
			stmt.executeQuery(); //ResultSet ��ü ��ȯ
			rs = (ResultSet)stmt.getObject(1);
			//stms.getObject >> object ��ü�� ���Ϲ޾� ResultSet���� ����ȯ(select-���̺� �����̱� ����)
			
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
}
/* ����Ŭ �������ν���
 
CREATE OR REPLACE PROCEDURE CALL_SELECT(
V_MEMBER_CURSOR OUT SYS_REFCURSOR
-- OUT(��ȯ) SYS_REFCURSOR Ŀ��Ÿ���� �� (Ŀ����ü)
)
IS 
BEGIN
OPEN V_MEMBER_CURSOR FOR SELECT * FROM MEMBER ORDER BY HAKBUN;
END;*/