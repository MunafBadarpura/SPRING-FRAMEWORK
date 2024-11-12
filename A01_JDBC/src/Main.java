/*
*   STEPS FOR JDBC
*   1. import package
*   2. load and register (optional)
*   3. create connection
*   4. create statement
*   5. execute statement
*   7. close
* */


import java.sql.*;  // 1. import package


public class Main {
    public static void main(String[] args) throws Exception{

        Class.forName("org.postgresql.Driver");  // 2. load and register (optional)

        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "0000";
        Connection con = DriverManager.getConnection(url,user,password);  // 3. create connection
        System.out.println("Connection Established");

//        String sql = "SELECT \"sName\" FROM students WHERE \"sId\" = 1";
        String sql = "SELECT * FROM students";
        Statement statement = con.createStatement();  // 4. create statement
        ResultSet rs = statement.executeQuery(sql);  // 5. execute statement

//        rs.next();
//        String name = rs.getString("sName");
//        System.out.println(name);

        // Fetching All Data
        while(rs.next()){
            System.out.print(rs.getInt(1) + " ");
            System.out.print(rs.getString(2) + " ");
            System.out.print(rs.getInt(3) + " ");
            System.out.println();
        }

        con.close();  // 7. close

    }
}
