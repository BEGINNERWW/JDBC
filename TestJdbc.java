import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc {

	public static void main(String[] args) throws Exception{
		//driver 로딩
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Connection 객체 생성
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.47:1521:orcl", "scott","123456");
		//Statement 객체 생성 //Statement 객체를 만드는 메소드(createStatement) - statement 객체사용해서 sql 이용
		Statement stmt = con.createStatement();
		//sql
		String sql = "select ename, deptno from emp";
		//sql 실행 후 resultSet 객체 반환
		ResultSet rset = stmt.executeQuery(sql); 
		// executeQuery : select 구문 실행 (테이블 구조의 객체를 반환 : ResultSet 타입), executeUpdate : insert, update, delete 실행
		int deptno = 0;
		String name = null;

		while(rset.next()) {
			name=rset.getString("ename");
			deptno = rset.getInt("deptno");
			System.out.println(name + "    " + deptno);
		}
}
}
