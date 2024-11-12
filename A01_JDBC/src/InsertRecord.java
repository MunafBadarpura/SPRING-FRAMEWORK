import java.sql.*;

public class InsertRecord {
    public static void main(String[] args) throws  Exception {
        Connection con = DriverManager.getConnection( "jdbc:postgresql://localhost:5432/demo","postgres","0000");

//        String sql = "insert into students values(5, 'Sohil', 45)";
//
//        Statement statement = con.createStatement();
//
//        boolean status = statement.execute(sql);
//        System.out.println(status);

        // INSERT
        String sql = "insert into students values(5, 'Sohil', 45)";
        Statement statement = con.createStatement();

        
        con.close();
    }
}
