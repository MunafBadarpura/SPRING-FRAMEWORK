import java.sql.*;
import java.util.Scanner;

public class a03_TransactionManagement {
    private static String url = "jdbc:postgresql://localhost:5432/demo";
    private static String user = "postgres";
    private static String password = "0000";

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("ClassNot Found " + e);
        }

        try{
            Connection con = DriverManager.getConnection(url,user,password);
            con.setAutoCommit(false); // Turn off auto-commit for transaction management
            Statement statement = con.createStatement();

            // Queries
            String debitQuery = "UPDATE \"accounts\" SET \"balance\" = \"balance\" - ? WHERE \"id\" = ?";
            String creditQuery = "UPDATE \"accounts\" SET \"balance\" = \"balance\" + ? WHERE \"id\" = ?";

            // PreparedStatements
            PreparedStatement debitPreparedStatement = con.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement = con.prepareStatement(creditQuery);

            // Taking input from user
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter your id : ");
            int id = sc.nextInt();
            System.out.print("Enter amount : ");
            double balance = sc.nextDouble();
            System.out.print("Enter id for send money : ");
            int id2 = sc.nextInt();

            debitPreparedStatement.setDouble(1,balance);
            debitPreparedStatement.setInt(2,id);
            creditPreparedStatement.setDouble(1,balance);
            creditPreparedStatement.setInt(2,id2);

            // Execute
            int debitResult = debitPreparedStatement.executeUpdate();
            int creditResult = creditPreparedStatement.executeUpdate();
            if (isSufficient(id, balance, con) == true && debitResult == 1 && creditResult == 1) {
                // Commit the transaction if both operations were successful
                con.commit();
                System.out.println("Transaction successful.");
            } else {
                // Roll back the transaction in case of an error
                con.rollback();
                System.out.println("Transaction failed. Rolled back.");
            }

            // Close
            debitPreparedStatement.close();
            creditPreparedStatement.close();
            con.close();
        }
        catch (SQLException e){
            System.out.println("SQLException " + e);
        }



    }
    static boolean isSufficient (int id, double balance, Connection con){
        if(balance <= 0) return false; // if amount is negative or zero it return false
        String query = "SELECT \"balance\" FROM accounts WHERE \"id\" = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                double currentBalance = rs.getDouble("balance");
                return currentBalance >= balance; // this return true or false
            }

            rs.close();
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
        return false;
    }
}
