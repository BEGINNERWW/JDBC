import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateExam {

	public static void main(String[] args) {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		Connection con =null;
		Statement stmt = null;
		
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
			
			String sql = "Update member Set addr = '"+ addr;
			sql += "', phone = '" + phone + "' Where hakbun ='";
			sql += hakbun +"'";
			System.out.println(sql);
			Class.forName(driver);
			con = DriverManager.getConnection(url,"scott","123456");
			stmt = con.createStatement();
			stmt.executeUpdate(sql);
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
