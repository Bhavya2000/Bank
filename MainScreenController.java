
package Dashboard;

import login.LoginScreenController;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;


public class MainScreenController implements Initializable {
     
     @FXML
     private Label body;
     @FXML
     private Label name;
    public void setInfo()
    {
         Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
           String sql = "SELECT * FROM userdata WHERE AccountNo=?";
            ps = con.prepareStatement(sql);
            
            
            ps.setString(1, LoginScreenController.acc);
         
            rs=ps.executeQuery();

            
            
            if(rs.next())
            {
               
                name.setText(rs.getString("Name"));
              
           
            }
            else
            {
             
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error in Login");
            a.setContentText("Your Account no or Pin is incorrect.");
            a.showAndWait();
           
            }
            
            
            
            
        }catch(Exception e)
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error in creating Account.");
            a.setContentText("1.Your Account is not created there is some technical issue.here"+ e.getMessage());
            a.showAndWait();
            
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        body.setText("Janta Bank is one of the largest Indian government owned banks. It is under the ownership of Ministry of Finance,\n Government of India. It is headquartered in Bengaluru. It was established at Mangalore in 1906 by Ammembal \nSubba Rao Pai and later the government nationalized the bank in 1969");
        setInfo();
    }
    
}
