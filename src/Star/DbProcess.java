package Star;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DbProcess{
	Connection connection = null;
	ResultSet rs = null;

	String userMySql="root"; 
	String passwordMySql="xingqing111129";
	String urlMySql = "jdbc:mysql://localhost:3306/liaoqing?user=" 
						+userMySql+"&password="+passwordMySql + "&useUnicode=true&characterEncoding=gbk&&useUnicode=true&characterEncoding=utf8&useSSL=false";
	
	public DbProcess() {
		try {
			//mysql数据库设置驱动程序类型
			Class.forName("com.mysql.jdbc.Driver"); 
			System.out.println("mysql数据库驱动加载成功");

		}
		catch(java.lang.ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void connect(){
		try{
			//mysql数据库
			connection = DriverManager.getConnection(urlMySql); 
			if(connection!=null){
	            System.out.println("数据库连接成功");
	        }
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void disconnect(){
		try{
			if(connection != null){
				connection.close();
				connection = null;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public ResultSet executeQuery(String sql) {
		try {
			System.out.println("executeQuery(). sql = " + sql);
			PreparedStatement pstm = connection.prepareStatement(sql);
			// 执行查询
			rs = pstm.executeQuery();
		} 
		catch(SQLException ex) { 
			ex.printStackTrace();
		}
		return rs;
	}
	public int executeUpdate(String sql) {
		int count = 0;
		connect();
		try {
			Statement stmt = connection.createStatement();
			count = stmt.executeUpdate(sql);
		} 
		catch(SQLException ex) { 
			System.err.println(ex.getMessage());		
		}
		disconnect();
		return count;
	}
}
