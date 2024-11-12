import javax.crypto.spec.PSource;
import java.sql.*;
import java.util.Scanner;

/*
* create a query
* execute with statement or preparedStatement
* then print result
*
*   REMEMBER :
*       1. when you get-read data from db -> executeQuery
*       2. when you use INSERT,UPDATE,DELETE -> executeUpdate
* */

public class a02_Practice {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);

        // Load Driver
        Class.forName("org.postgresql.Driver");

        // Create Connection
        String url = "jdbc:postgresql://localhost:5432/demo";
        String user = "postgres";
        String password = "0000";
        Connection con = DriverManager.getConnection(url,user,password);

        // Create Statement
        Statement statement = con.createStatement();

        //GET - READ
        String query1 = "SELECT \"sId\", \"sName\" FROM students";
        ResultSet rs = statement.executeQuery(query1);  //Execute Statement
        System.out.println("Getting all--->");
        while(rs.next()){  // All Rows
            System.out.println(rs.getInt(1) + " " + rs.getString(2));
        }

        //GET
        String query2 = "SELECT * FROM students";
        ResultSet rs2 = statement.executeQuery(query2);  //Execute Statement
        System.out.println("Getting all--->");
        while(rs2.next()){
            System.out.println(rs2.getInt(1) + " " + rs2.getString(2) + " " + rs2.getInt(3));
        }

        //GET BY ID
        System.out.println("Getting by id 2--->");
        String query3 = "select * from \"students\" where \"sId\" = 2";
        ResultSet rs3 = statement.executeQuery(query3);
        rs3.next(); // move cursor
        System.out.println(rs3.getInt(1)+ " " + rs3.getString(2) + " " + rs3.getInt(3));



          //INSERT - CREATE
//        System.out.println("Inserting..");
//        String query4 = "INSERT INTO students VALUES(?, ?, ?)";
//        PreparedStatement ps = con.prepareStatement(query4);
//
//        ps.setInt(1, 7);
//        ps.setString(2, "Sahad");
//        ps.setInt(3,70);
//
//        System.out.println("Number Of Lines Updated : " + ps.executeUpdate()); // return int
//        System.out.println("getting All --->");
//        ResultSet rs4 = statement.executeQuery(query2);
//        while(rs4.next()){
//            System.out.println(rs4.getInt(1) + " " + rs4.getString(2) + " " + rs4.getInt(3));
//        }

        //UPDATE
        System.out.println("updating");
        String query5 = "UPDATE \"students\" SET  \"sName\" = ?, \"sMarks\" = ? WHERE \"sId\" = 5";

        PreparedStatement ps2 = con.prepareStatement(query5);
        ps2.setString(1, "Updated");
        ps2.setInt(2,77);

        ps2.executeUpdate();

        ResultSet rs6 = statement.executeQuery(query2);
        while (rs6.next()){
            System.out.println(rs6.getInt(1) + " " + rs6.getString(2) + " " + rs6.getInt(3));
        }

        //DELETE
        System.out.println("DELETING >>>>");
        String query6 = "DELETE FROM \"students\" WHERE \"sId\" = ?";
        PreparedStatement ps3 = con.prepareStatement(query6);
        ps3.setInt(1, 8);

        ps3.executeUpdate();

        ResultSet rs7 = statement.executeQuery(query2);
        while (rs7.next()){
            System.out.println(rs7.getInt(1) + " " + rs7.getString(2) + " " + rs7.getInt(3));
        }

        // Positional Parameter
        System.out.println("Sid 2 student information --->");
        String query8 = "select * from \"students\" where \"sId\" = ?"; // this is Positional Parameter
        PreparedStatement ps5 = con.prepareStatement(query8);
        ps5.setInt(1,2);

        ResultSet rs5 = ps5.executeQuery();

        rs5.next();
        System.out.println(rs5.getInt(1) + " " + rs5.getString(2) + " " + rs5.getInt(3));


        // BATCH-PROCESSING (WITH Statement)
//        System.out.println("Batch Processing : ");
//        while(true){
//            System.out.print("Enter sId : ");
//            int sId = sc.nextInt();
//
//            sc.nextLine();
//            System.out.print("Enter sName : ");
//            String sName = sc.nextLine();
//
//            System.out.print("Enter sMarks : ");
//            int sMarks = sc.nextInt();
//
//            sc.nextLine();
//            System.out.print("Enter More Data(Y/N)");
//            String choice = sc.next();
//
//            String query9 = String.format("INSERT INTO \"students\"(\"sId\", \"sName\", \"sMarks\") VALUES(%d, '%s', %d)", sId, sName, sMarks);
//            statement.addBatch(query9);
//
//            if(choice.equalsIgnoreCase("N")){
//                break;
//            }
//        }
//        int[] rowsInserted = statement.executeBatch(); // if query execute it store 1 and if not execute it store 0
//        for(int i=0;i<rowsInserted.length;i++){
//            if(rowsInserted[i] == 0){ // it means this number of query not inserted
//                System.out.println("Query " + i + " Not Inserted");
//            }
//        }
//
//        System.out.println("Getting all--->");
//        ResultSet rs8 = statement.executeQuery(query2);
//        while (rs8.next()){
//            System.out.println(rs8.getInt(1) + " " + rs8.getString(2) + " " + rs8.getInt(3));
//        }


        // BATCH-PROCESSING (WITH PreparedStatement)
        System.out.println("Batch Processing : ");
        String query10 = "INSERT INTO students VALUES(?, ?, ?)";
        PreparedStatement ps6 = con.prepareStatement(query10);
        while(true){
            System.out.print("Enter sId : ");
            int sId = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter sName : ");
            String sName = sc.nextLine();

            System.out.print("Enter sMarks : ");
            int sMarks = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter More Data(Y/N)");
            String choice = sc.next();

            ps6.setInt(1, sId);
            ps6.setString(2, sName);
            ps6.setInt(3, sMarks);

            ps6.addBatch();

            if(choice.equalsIgnoreCase("N")){
                break;
            }
        }
        int[] rowInserted = ps6.executeBatch(); // if query execute it store 1 and if not execute it store 0

        System.out.println("Getting all--->");
        ResultSet rs9 = statement.executeQuery(query2);
        while (rs9.next()){
            System.out.println(rs9.getInt(1) + " " + rs9.getString(2) + " " + rs9.getInt(3));
        }

    }
}
