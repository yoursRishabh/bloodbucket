import java.sql.*;
import java.io.*;
import java.net.*;
public class Server
{
  public static void main(String [] args) throws Exception
  {
while(true)
 {
   ServerSocket ss = new ServerSocket(9999);
   System.out.println("Server is waiting for client request");
   Socket s = ss.accept();
   System.out.println("Connected with client...");
   BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
   String query1 = br.readLine();
    if(query1.equals("a")) 
       {
         String query2 = br.readLine();          
         //------------Database Connectivity------------------------------------------------------
         Class.forName("com.mysql.cj.jdbc.Driver");  
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","root");
         Statement st = con.createStatement();
         st.executeUpdate(query2);
         st.close();   con.close();
         System.out.println(" "+query2);
         System.out.println("Data is Submitted");    
       }
    else if(query1.equals("b"))
         {
             String query3 = br.readLine();
             //------------Database Connectivity------------------------------------------------------
             Class.forName("com.mysql.cj.jdbc.Driver");  
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BB","root","root");
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query3);
             rs.next();
             String name1 = rs.getString("Fname");
             String name2 = rs.getString("Lname");
             String number = rs.getString("Phone");
             st.close() ;     con.close();
              
             OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());
             PrintWriter out = new PrintWriter(os);
             out.println(name1); 
             out.flush();
             out.println(name2); 
             out.flush();
             out.println(number); 
             out.flush();
             System.out.println("Data sent to client :"+name1);
         }
    ss.close();
  } //closing while
  }                //closing main()
}                  //closing class 