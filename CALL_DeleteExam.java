package day1027;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

public class DeleteExam_CALL {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		CallableStatement stmt = null;
		
		String hakbun = null;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Member ���̺� �� �����ϱ�.....");
			System.out.print("������ �й� �Է� : ");
			hakbun = br.readLine();
			
			//String sql = "Delete From member Where hakbun = ?";
			//System.out.println(sql);
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			//con.setAutoCommit(false);
			stmt = con.prepareCall("{call call_delete(?)}");
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
	}
}
/* ����Ŭ �������ν���

CREATE OR REPLACE PROCEDURE CALL_DELETE(
P_HAKBUN MEMBER.HAKBUN%TYPE)
IS BEGIN
DELETE FROM MEMBER WHERE HAKBUN = P_HAKBUN;
END;
/ */
