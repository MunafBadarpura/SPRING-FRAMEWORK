import java.sql.*;
import java.util.Scanner;

public class a04_BankingProject {

    static int whoYouAre(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Who You Are :");
        System.out.println("1. For ADMIN");
        System.out.println("2. For USER");
        int who = sc.nextInt();

        return who;
    }

    // ADMIN CODES STARTS HERE
    static int AdminMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Your Choice :");
        System.out.println("1. For ADD USER");
        System.out.println("2. For REMOVE USER");
        System.out.println("3. For SHOW ALL USERS");
        int adminChoice = sc.nextInt();

        return adminChoice;
    }

    // 1. For ADD USER
    static void createUser(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter New User Details : ");

        System.out.print("Enter ID : ");
        int newId = sc.nextInt();

        sc.nextLine();
        System.out.print("Enter NAME : ");
        String newName = sc.nextLine();

        System.out.print("Enter BALANCE : ");
        double newBalance = sc.nextDouble();

        try {
            String AddQuery = "INSERT INTO bank VALUES (?, ?, ?)";
            PreparedStatement ps1 = con.prepareStatement(AddQuery);
            ps1.setInt(1, newId);
            ps1.setString(2,newName);
            ps1.setDouble(3,newBalance);

            int newUser = ps1.executeUpdate();
            if(newUser == 1) System.out.println("USER ADDED SUCCESSFULLY");
            else System.out.println("SOMETHING WENT WRONG");
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
    }

    // 2. For REMOVE USER
    static void removeUser(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.println("For Remove Enter Id Of User : ");
        int removeId = sc.nextInt();

        try {
            String removeQuery = "DELETE FROM bank WHERE id = ?";
            PreparedStatement ps2 = con.prepareStatement(removeQuery);
            ps2.setInt(1,removeId);

            int removedUser = ps2.executeUpdate();
            if(removedUser == 1) System.out.println("USER REMOVED SUCCESSFULLY");
            else System.out.println("SOMETHING WENT WRONG");
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }

    }

    // 3. For SHOW ALL USERS
    static void showUsers(Connection con){
        System.out.println("USER LIST : ");

        try{
            String showQuery = "SELECT * FROM bank";
            PreparedStatement ps3 = con.prepareStatement(showQuery);
            ResultSet rs = ps3.executeQuery();
            while (rs.next()){
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
            }
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }

    }

    // ADMIN SECTION
    static void adminSection(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Admin Password : ");
        String adminPassword = sc.nextLine();

        if(adminPassword.equals("0000")){
            int adminChoice = AdminMenu();
            if(adminChoice == 1) createUser(con);
            else if(adminChoice == 2) removeUser(con);
            else if(adminChoice == 3) showUsers(con);
            else System.out.println("INVALID INPUT");
        }
        else{
            System.out.println("INVALID PASSWORD");
        }
    }
    // ADMIN CODES ENDS HERE

    // USER CODES STARTS HERE
    static int UserMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Your Choice :");
        System.out.println("1. For MAKE TRANSACTION");
        System.out.println("2. For ADD BALANCE");
        System.out.println("3. For WITHDRAW BALANCE");
        System.out.println("4. For CHECK BALANCE");
        int userChoice = sc.nextInt();

        return userChoice;
    }

    // for checking valid amount
    static boolean isSufficint(int userId,double userAmount, Connection con){
        try{
            PreparedStatement ps = con.prepareStatement("SELECT balance FROM bank WHERE id = ?");
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                double currentBalance = rs.getDouble("balance");
                if(userAmount <= currentBalance && userAmount > 0) return true;
            }

        } catch (SQLException e) {
            System.out.println("SQLEXECEPTION " + e);
        }

        return false;
    }

    // 1. For MAKE TRANSACTION
    static void makeTransaction(Connection con) {

        try{
            con.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER YOUR ID : ");
        int userId = sc.nextInt();

        System.out.print("ENTER AMOUNT : ");
        double userAmount = sc.nextDouble();

        System.out.print("ENTER ID FOR SEND MONEY : ");
        int userId2 = sc.nextInt();

        try {
            String debitQuery = "UPDATE bank SET balance = balance - ? WHERE id = ?";
            String creditQuery = "UPDATE bank SET balance = balance + ? WHERE id = ?";
            PreparedStatement debitPreparedStatement = con.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement = con.prepareStatement(creditQuery);

            debitPreparedStatement.setDouble(1,userAmount);
            debitPreparedStatement.setInt(2,userId);
            creditPreparedStatement.setDouble(1,userAmount);
            creditPreparedStatement.setInt(2,userId2);

            int debitResult = debitPreparedStatement.executeUpdate();
            int creditResult = creditPreparedStatement.executeUpdate();

            if(isSufficint(userId, userAmount, con) && debitResult == 1 && creditResult == 1){ //!
                con.commit();
                System.out.println("Transaction successful.");
            }else{
                con.rollback();
                System.out.println("Transaction failed");
            }
        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
    }

    // 2. For ADD BALANCE
    static void addBalance(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter ID : ");
        int userId = sc.nextInt();

        System.out.print("Enter AMOUNT : ");
        double amount = sc.nextDouble();

        try{
            String AddQuery = "UPDATE bank SET balance = balance + ? WHERE id = ?";
            PreparedStatement ps4 = con.prepareStatement(AddQuery);
            ps4.setDouble(1,amount);
            ps4.setInt(2,userId);

            int addedBalance = ps4.executeUpdate();
            if(addedBalance == 1) System.out.println("AMOUNT ADDED SUCCESSFULLY");
            else System.out.println("SOMETHING WENT WRONG");

        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
    }

    // 3. For WITHDRAW BALANCE
    static void withdrawBalance(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER ID : ");
        int userId = sc.nextInt();

        System.out.print("Enter WITHDRAW AMOUNT : ");
        double amount = sc.nextDouble();

        try{
            String withdrawQuery = "UPDATE bank SET balance = balance - ? WHERE id = ?";
            PreparedStatement ps5 = con.prepareStatement(withdrawQuery);
            ps5.setDouble(1,amount);
            ps5.setInt(2,userId);

            int withdrawBalance = ps5.executeUpdate();
            if(withdrawBalance == 1) System.out.println("AMOUNT WITHDRAW SUCCESSFULLY");
            else System.out.println("SOMETHING WENT WRONG");

        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
    }

    // 4. For CHECK BALANCE
    static void checkBalance(Connection con){
        Scanner sc = new Scanner(System.in);
        System.out.print("ENTER ID : ");
        int userId = sc.nextInt();

        try{
            String balanceQuery = "SELECT balance from bank WHERE id = ?";
            PreparedStatement ps6 = con.prepareStatement(balanceQuery);
            ps6.setDouble(1,userId);

            ResultSet rs = ps6.executeQuery();
            if(rs.next()) System.out.println(rs.getDouble("balance"));
            else System.out.println("SOMETHING WENT WRONG");

        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }
    }

    // USER SECTION
    static void userSection(Connection con){
        int userChoice = UserMenu();
        if(userChoice == 1) makeTransaction(con);
        else if(userChoice == 2) addBalance(con);
        else if(userChoice == 3) withdrawBalance(con);
        else if(userChoice == 4) checkBalance(con);
        else System.out.println("INVALID INPUT");
    }

    // USER CODES ENDS HERE

    // Connection details
    private static String url = "jdbc:postgresql://localhost:5432/demo";
    private static String user = "postgres";
    private static String pass = "0000";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("Class Not Found" + e);
        }

        try{
            Connection con = DriverManager.getConnection(url,user,pass);
            Statement statement = con.createStatement();

            int who = whoYouAre(); // ADMIN OR USER
            if(who == 1) adminSection(con);
            else if(who == 2) userSection(con);
            else System.out.println("INVALID INPUT");


        }catch (SQLException e){
            System.out.println("SQLException " + e);
        }

    }
}
