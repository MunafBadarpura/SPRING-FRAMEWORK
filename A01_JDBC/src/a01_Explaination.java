/*
* execute()
Return Type: boolean
Purpose: This method is used to execute a SQL statement that may return multiple types of results. It can handle both SELECT queries (which return results) and INSERT, UPDATE, or DELETE statements (which typically do not return results).
Usage: If the result is a ResultSet, the method returns true. If it’s an update count (as in an INSERT), it returns false.
Example: boolean hasResultSet = statement.execute("SELECT * FROM users");
*

* executeUpdate()
Return Type: int
Purpose: This method is used for executing SQL statements that modify data, such as INSERT, UPDATE, or DELETE, and it returns the number of rows affected.
Usage: Ideal when you expect to change data and want to know how many rows were impacted.
Example: int rowsAffected = statement.executeUpdate("UPDATE users SET username='newName' WHERE id=1");

* executeQuery()
Return Type: ResultSet
Purpose: This method is specifically designed for executing SQL SELECT statements and retrieving the results as a ResultSet.
Usage: Use this method when you want to read data from the database.
Example: ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
*
Prepared Statements
PreparedStatement: A PreparedStatement is a precompiled SQL statement that allows you to execute the same query multiple times with different parameters efficiently. It helps prevent SQL injection and enhances performance.
*
Parameter Types :

* Positional Parameters (e.g., ?)
Description: These are placeholders in the SQL query that represent parameters. They are indexed starting from 1 (not 0). You set their values using methods like setInt, setString, etc.
Example: PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?"); pstmt.setInt(1, userId); // Sets the first parameter (id) to userId

* Named Parameters (e.g., :)
Description: Named parameters allow you to reference parameters by name instead of by position. However, JDBC doesn’t natively support named parameters; they are typically used in libraries like Hibernate or JPA.
Example: String sql = "SELECT * FROM users WHERE username =
"; // Implementation would depend on the specific framework being used.
* */


import java.sql.*;

public class a01_Explaination
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException
    {
        System.out.println( "Hello World!" );

        /*
         * Execute - return Boolean
         * ExecuteUpdate - return integer
         * ExecuteQuery  - return ResultSet
         * Prepared Statement - ? ordinal start from 1
         * positional parameter = ? = ordinal start with 1
         * Named Parameter = : = :name, this will be passed
         *
         */

        /*
         * CREATING JDBC connection with postgresql
         * import Package
         * Load and Register Driver
         * Create Connection
         * Create Statement
         * Execute Statement
         * Process the results
         * Close the Connection
         */

        //Load the Driver
        Class.forName("org.postgresql.Driver");

        //Create Connection
        String url = "jdbc:postgresql://localhost:5432/TestDB";
        String user = "postgres";
        String password = "Tharun@1234$$";
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println("Connection Established");

        //Create Statement
        Statement statement = conn.createStatement();

        //GET
        String query1 =  "SELECT \"StudentId\", \"StudentName\", \"StudentRoll\" FROM public.\"StudentTable\"";
        ResultSet rs = statement.executeQuery(query1);
        System.out.println("Getting all--->");
        while(rs.next())
        {
            System.out.println(rs.getInt(1)+" "+
                    rs.getString(2)+" "+rs.getInt(3));
        }

        //GET
        System.out.println("Getting All--->");
        String query2 = "Select * from public.\"StudentTable\"";
        ResultSet rs2 = statement.executeQuery(query2);
        while(rs2.next())
        {
            System.out.println(rs2.getInt("studentId")+" "+rs2.getString("StudentName")+" "+rs2.getInt("StudentRoll"));
        }

        //GET by Id
        System.out.println("Getting by id 183--->");
        String query3 = "select * from public.\"StudentTable\" where \"StudentId\" = 183";
        ResultSet rs3 = statement.executeQuery(query3);
        while(rs3.next())
        {
            System.out.println(rs3.getInt("studentId")+" "+rs3.getString("StudentName")+" "+rs3.getInt("StudentRoll"));
        }


        //INSERT
        System.out.println("Inserting..");
        String insertQuery = "INSERT INTO public.\"StudentTable\" (\"StudentId\", \"StudentName\", \"StudentRoll\") VALUES (?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(insertQuery);
        //ordinal start with 1
        ps.setInt(1,183);
        ps.setString(2,"Zach");
        ps.setInt(3,183);
        int noOfRowsUpdated = ps.executeUpdate();
        System.out.println("no of rows Updated: "+noOfRowsUpdated);
        //getting all
        System.out.println("getting All --->");
        ResultSet rs5 = statement.executeQuery(query2);
        while(rs5.next())
        {
            System.out.println(rs5.getInt("studentId")+" "+rs5.getString("StudentName")+" "+rs5.getInt("StudentRoll"));
        }


        //UPDATE
        int studentIdToUpdate = 183;
        String newStudentName = "Updated Name";
        int newStudentRoll = 200;
        System.out.println("updating");
        String updateQuery = "UPDATE public.\"StudentTable\" SET \"StudentName\" = '" + newStudentName + "', \"StudentRoll\" = " + newStudentRoll +
                " WHERE \"StudentId\" = " + studentIdToUpdate;
        int noOfRowsUpdated2 = statement.executeUpdate(updateQuery);
        System.out.println("noOf Rows Updated -> "+noOfRowsUpdated2);
        //getting all
        System.out.println("getting All --->");
        ResultSet rs6 = statement.executeQuery(query2);
        while(rs6.next())
        {
            System.out.println(rs6.getInt("studentId")+" "+rs6.getString("StudentName")+" "+rs6.getInt("StudentRoll"));
        }

        //DELETE
        String deleteQuery = "DELETE FROM public.\"StudentTable\" WHERE \"StudentId\" = " + "183";
        int rowsDeleted = statement.executeUpdate(deleteQuery);
        System.out.println("Rows deleted: " + rowsDeleted);
        //getting all
        System.out.println("getting All --->");
        ResultSet rs7 = statement.executeQuery(query2);
        while(rs7.next())
        {
            System.out.println(rs7.getInt("studentId")+" "+rs7.getString("StudentName")+" "+rs7.getInt("StudentRoll"));
        }


    }
}