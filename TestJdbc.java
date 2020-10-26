import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJdbc {

	public static void main(String[] args) throws Exception{
		//driver �ε�
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//Connection ��ü ����
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.47:1521:orcl", "scott","123456");
		//Statement ��ü ���� //Statement ��ü�� ����� �޼ҵ�(createStatement) - statement ��ü����ؼ� sql �̿�
		Statement stmt = con.createStatement();
		//sql
		String sql = "select ename, deptno from emp";
		//sql ���� �� resultSet ��ü ��ȯ
		ResultSet rset = stmt.executeQuery(sql); 
		// executeQuery : select ���� ���� (���̺� ������ ��ü�� ��ȯ : ResultSet Ÿ��), executeUpdate : insert, update, delete ����
		int deptno = 0;
		String name = null;

		while(rset.next()) {
			name=rset.getString("ename");
			deptno = rset.getInt("deptno");
			System.out.println(name + "    " + deptno);
		}
}
}
