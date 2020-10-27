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
			System.out.println("Member ���̺� �� �����ϱ�.....");
			System.out.print("������ �й� �Է� : ");
			hakbun = br.readLine();
			System.out.print("�� �ּ� �Է� : ");
			addr = br.readLine();
			System.out.print("�� �ڵ�����ȣ �Է� : ");
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

	}
}
/* ����Ŭ �������ν���

CREATE OR REPLACE PROCEDURE CALL_UPDATE(
P_HAKBUN MEMBER.HAKBUN%TYPE, P_ADDR MEMBER.ADDR%TYPE, P_PHONE MEMBER.PHONE%TYPE)
IS BEGIN
UPDATE MEMBER SET ADDR = P_ADDR, PHONE = P_PHONE WHERE HAKBUN = P_HAKBUN;
END;
/ */