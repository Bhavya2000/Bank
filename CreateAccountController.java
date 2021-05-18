
package account;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import login.Banking;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class CreateAccountController implements Initializable {
 
    private final FileChooser filechooser = new FileChooser();
    private File file;
    private FileInputStream fis;
    @FXML
    private ImageView pic;
    @FXML
    private TextField name;
    @FXML
    private TextField idcardno;
    @FXML
    private TextField mobileno;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField accountno;
    @FXML
    private TextField pin;
    @FXML
    private TextField balance;
    @FXML
    private TextField answer;
    @FXML
    private TextField email;
    @FXML
    private TextField otpfield;
    @FXML
    private DatePicker dob;
    @FXML
    private ComboBox<String> gender;
    @FXML
    private ComboBox<String> maritialstatus;
    @FXML
    private ComboBox<String> religion;
    @FXML
    private ComboBox<String> accounttype;
    @FXML
    private ComboBox<String> questions;
    private int otp;
    
    ObservableList<String> list = FXCollections.observableArrayList("Male", "Female", "Other");
    ObservableList<String> list1 = FXCollections.observableArrayList("Un-Married", "Married");
    ObservableList<String> list2 = FXCollections.observableArrayList("Hindu", "Christian", "Islam", "Other");
    ObservableList<String> list3 = FXCollections.observableArrayList("Current", "Savings");
    ObservableList<String> list4 = FXCollections.observableArrayList("What is your pet name?", "What is your childhood town?", "What is your nick name?");
    
   
   
   
    
    
    public boolean validateName()
    {
        Pattern p = Pattern.compile("[a-zA-Z ]+");
        Matcher m = p.matcher(name.getText());
        if(m.find()&&m.group().equals(name.getText()))
        {
            return true;
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Incorrect Name");
            a.setHeaderText("Your Name is wrong");
            a.setContentText("Only characters are allowed in name");
            a.showAndWait();
            return false;
        }
    }
     
    public boolean validateMobileNo()
    {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(mobileno.getText());
        if(m.find()&&m.group().equals(mobileno.getText()))
        {
            return true;
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Incorrect MobileNo");
            a.setHeaderText("Your MobileNo is wrong");
            a.setContentText("Only numbers are allowed in mobile no");
            a.showAndWait();
            return false;
        }
    }
    public void generateOtp()throws Exception
    {
        otp = account.JoiningMail.sendMail(email.getText());
        Alert a=new Alert(AlertType.CONFIRMATION);
        a.setTitle("OTP SENT");
        a.setHeaderText("OTP SENT");
        a.setContentText("OTP is sent on the email address  "+email.getText());
        a.showAndWait();
        otpfield.setEditable(true);
        
    }
    public boolean validateOtp()
    {
        if(otp==Integer.parseInt(otpfield.getText()))
        {
            return true;
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Incorrect OTP");
            a.setHeaderText("Incorrect OTP");
            a.setContentText("Please enter  correct OTP.");
            a.showAndWait();
            return false;    
        
        }
    }
     public boolean validateIdNo()
    {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(idcardno.getText());
        if(m.find()&&m.group().equals(idcardno.getText()))
        {
            return true;
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Incorrect ID No");
            a.setHeaderText("Your ID No is wrong");
            a.setContentText("Only numbers are allowed in ID no");
            a.showAndWait();
            return false;
        }
    }
     public void clearAllFields()
     {
         name.clear();
         idcardno.clear();
         mobileno.clear();
         gender.getSelectionModel().clearSelection();
         religion.getSelectionModel().clearSelection();
         maritialstatus.getSelectionModel().clearSelection();
         dob.getEditor().clear();
         city.clear();
         address.clear();
         pin.clear();
         accounttype.getSelectionModel().clearSelection();
         balance.clear();
         questions.getSelectionModel().clearSelection();
         answer.clear();
         Image img=new Image("/Images/150.png");
         pic.setImage(img);
         accountno.setText((String.valueOf((generateAccuntNo()))));
     }
     public int generateAccuntNo()
     {
         Random ran=new Random();
         int num = ran.nextInt(89999999) + 1000000;
         return num;
     }
     public boolean validatebalance()
    {
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(balance.getText());
        if(m.find()&&m.group().equals(balance.getText()))
        {
            return true;
        }
        else
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Incorrect Balance Format");
            a.setHeaderText("Your Balance is wrong");
            a.setContentText("Only numbers are allowed in Balance");
            a.showAndWait();
            return false;
        }
    }
    public void backToLogin(MouseEvent event) throws IOException
    {
        Banking.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Login/LoginScreen.fxml")));
        
    }
    public void setUpPic(MouseEvent e)
    {
        filechooser.getExtensionFilters().add(new ExtensionFilter("Images Files", "*.png", "*.jpg"));
        file= filechooser.showOpenDialog(null);
        if(file!=null)
        {
            Image img = new Image(file.toURI().toString(), 150, 150, true, true);
            pic.setImage(img);
            pic.setPreserveRatio(true);
            
        }
        
    }
    public void newAccount(MouseEvent event)
    {
        Connection con=null;
        PreparedStatement ps=null;
        if(validateName()&&validateMobileNo()&&validateIdNo()&&validatebalance()&&validateOtp())
        {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","");
            String sql ="INSERT INTO userdata (Name, ICN, MobileNo, Gender, Religion, MaritialStatus,DOB,City,Address, AccountNo, PIN, AccountType, Balance, SecurityQuestion, Answer, ProfilePicture, Email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, name.getText());
            ps.setString(2, idcardno.getText());
            ps.setString(3, mobileno.getText());
            ps.setString(4, gender.getValue());
            ps.setString(5, religion.getValue());
            ps.setString(6, maritialstatus.getValue());
            ps.setString(7, ((TextField)dob.getEditor()).getText());
            ps.setString(8, city.getText());
            ps.setString(9, address.getText());
            ps.setString(10, accountno.getText());
            ps.setString(11, pin.getText());
            ps.setString(12, accounttype.getValue());
            ps.setString(13, balance.getText());
            ps.setString(14, questions.getValue());
            ps.setString(15, answer.getText());
            ps.setString(17, email.getText());
            
            
            
            fis=new FileInputStream(file);
            ps.setBinaryStream(16, (InputStream)fis, (int)file.length());
            
            int i = ps.executeUpdate();
            if(i>0)
            {
            Alert a=new Alert(AlertType.INFORMATION);
            a.setTitle("Account Created");
            a.setHeaderText("Welcome To Janta Bank.");
            a.setContentText("Your Account has been  created sucessfully. You can now Login with your account no and pin.");
            JoiningMail.sendMail(email.getText());
            a.showAndWait();  
            clearAllFields();
             Banking.stage.getScene().setRoot(FXMLLoader.load(getClass().getResource("/Login/LoginScreen.fxml")));
            }
            else
            {
             
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Account not created ");
            a.setContentText("Your Account is not created there is some error .");
            a.showAndWait();
           
            }
            
            
            
            
        }catch(Exception e)
        {
            Alert a=new Alert(AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error in creating Account.");
            a.setContentText("Your Account is not created there is some technical issue."+ e.getMessage());
            a.showAndWait();
            
        }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gender.setItems(list);
        maritialstatus.setItems(list1);
        religion.setItems(list2);
        accounttype.setItems(list3);
        questions.setItems(list4);
        
        
        
        accountno.setText((String.valueOf((generateAccuntNo()))));
        accountno.setEditable(false);
        otpfield.setEditable(false);
        
        
     
    }    
    
}
