package reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
	// JDBCドライバの登録
    String driver;
    // データベースの指定
    String server, dbname, url, user, password;
    Connection con;
    Statement stmt;
    ResultSet rs;
	public MySQL() {
		this.driver = "org.gjt.mm.mysql.Driver";
		this.server = "ms000.sist.ac.jp";
		this.dbname = "java2016";
		this.url = "jdbc:mysql://" + server + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8";
		this.user = "java2016";
		this.password = "java2016";
		try {
			this.con = DriverManager.getConnection(url, user, password);
			this.stmt = con.createStatement ();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Class.forName (driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void close(){
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ResultSet selectAll(){
		String sql = "SELECT * FROM `50516042`";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  //try catchで囲む
		return rs;
	}
	public ResultSet selectReservation(String rdate, String facility){
		String sql = "SELECT * FROM reservation WHERE date ='" + rdate + "' AND facility_name = '"+ facility +"' ORDER BY start_time;";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql);  //try catchで囲む
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet selectUser(String userid){
		String sql = "SELECT * FROM user WHERE user_id ='" + userid + "';";
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery (sql);  //try catchで囲む
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public int insertReservation(String rdate, String facility, String st, String et, String userid){
		String sql = "INSERT INTO reservation (date,start_time,end_time,user_id,facility_name) VALUES ( '"
	    		  + rdate +"', '"  + st +"','" + et + "','" + userid +"','" + facility +"');";
		int rs_int = 0;
		try {
			rs_int = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs_int;
	}
	
	public ResultSet facilityExplanation(String facility){
        
        String sql = "SELECT * FROM facility WHERE facility_name ='" + facility +"';";
                  // クエリーを実行して結果のセットを取得
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }
	public ResultSet confirmReservation(String reservation_userid){
	    
        String sql = "SELECT * FROM reservation WHERE user_id ='" + reservation_userid + "'ORDER BY date;";

        try{
            rs = stmt.executeQuery (sql);  //try catchで囲む
        }catch(SQLException e){
            e.printStackTrace();    
        }
        return rs;
    }
	public int cancelReservation(String rdate, String st, String et, String reservation_userid, String facility){
        int rs_int = 0;
        String sql = "DELETE  FROM reservation WHERE date ='" + rdate + "'AND start_time = '" + st + "'AND end_time = '" + et + "'AND user_id = '" + reservation_userid + "'AND facility_name = '" + facility +"';";
                  try {
                    rs_int = stmt.executeUpdate(sql);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
              
                return rs_int;
    }
	public int makeUserid(String reservation_userid, String reservation_username, String reservation_password){
        int rs_int = 0;
        String sql = "INSERT INTO user (user_id,user_name,password) VALUES ( '"
            + reservation_userid +"','"+ reservation_username +"','" + reservation_password +"');";
        try {
            rs_int = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs_int;
    }
	

}
