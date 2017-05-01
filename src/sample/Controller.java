package sample;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static sample.Cache.ChatroomsArrayList;


public class Controller extends ListCell implements Initializable {
    //    final static String source = "/home/" + "mehdi" + "/SabaAplicationSource/";
//    final static String sourceuser = source + "Users/";
//    final static String userMainCash = sourceuser + "Maincash/";
//    final static String sourceuserProfiles = sourceuser + "Profiles";
    @FXML
    private VBox signuppage;
    @FXML
    private Pane BackPane;
    @FXML
    private Pane BackPane2;
    @FXML
    private Label statussignup;
    @FXML
    private TextField namesignup;
    @FXML
    private TextField numbersignup;
    @FXML
    private PasswordField passsignup1;
    @FXML
    private PasswordField passsignup2;
    @FXML
    private VBox loginpage;
    @FXML
    private Pane BackPaneLog;
    @FXML
    private Label statuslogin;
    @FXML
    private TextField userlogin;
    @FXML
    private PasswordField passlogin;
    @FXML
    private Button signin;
    @FXML
    private JFXDrawer drawerprofile;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private VBox drawpage;
    @FXML
    private JFXListView<Chat> chatsTable;
    @FXML
    private Label FriendRequestLabel;
    @FXML
    private JFXTextField FriendRequestText;
    @FXML
    private JFXButton FriendRequestButton;
    @FXML
    private TextField searchChatrooms;
    @FXML
    private Pane gridPane;
    @FXML
    private Label ChatName;
    @FXML
    private ImageView typeuser;
    @FXML
    private Label nameProfile;
    @FXML
    private Label numberProfile;
    @FXML
    private ContextMenu contextMenu;
    MenuItem soundDisable = new MenuItem("Disable notification");
    MenuItem soundEnable = new MenuItem("Enable notification");
    MenuItem removehistory = new MenuItem("Clear history");
    MenuItem removegroup = new MenuItem("Remove group");
    MenuItem leavaGroup = new MenuItem("Leave group");
    MenuItem leavaChannel = new MenuItem("Leave Channel");
    MenuItem removeChannel = new MenuItem("Remove Channel");

    Cache cache = new Cache();

    public void SighnIn() throws Exception {
        String string = userlogin.getText() + passlogin.getText();
        if (string.indexOf(" ") > 0) {
            statuslogin.setText("Incorrect character");
            statuslogin.setTextFill(Color.RED);
        } else if (userlogin.getText().equals("") || passlogin.equals("")) {
            statuslogin.setText("Please  fill all of fields ");
            statuslogin.setTextFill(Color.RED);
        } else {
            String s = Conection.Ping("Signin " + userlogin.getText() + " " + passlogin.getText());//
            if (s.equals("Connection refused")) {
                System.out.println(s);
                statuslogin.setText("You are not Conected!");
                statuslogin.setTextFill(Color.RED);
            } else if (s.equals("false")) {
                statuslogin.setText("Login Failed");
                statuslogin.setTextFill(Color.RED);
            } else if (s.equals("true")) {
                cache.setNumber(userlogin.getText());
                cache.setName(passlogin.getText());
                Main.ChangeScene("MainPage.fxml");
            }

        }
    }


    public void logout() throws IOException {
        ChatroomsArrayList.clear();
        cache.setNumber("");
        Main.ChangeScene("Login.fxml");

    }

    public void SignUp() throws Exception {
        String string = numbersignup.getText() + passsignup2.getText() + namesignup.getText();
        if (string.indexOf(" ") > 0) {
            statussignup.setText("Incorrect character");
            statussignup.setTextFill(Color.RED);
        } else if (!passsignup1.getText().equals(passsignup2.getText())) {
            statussignup.setText("These passwords are not same!");
            statussignup.setTextFill(Color.RED);
        } else if (passsignup1.getText().equals("") || passsignup2.equals("") || namesignup.getText().equals("") || numbersignup.getText().equals("")) {
            statussignup.setText("Please  fill all of fields ");
            statussignup.setTextFill(Color.RED);
        }
        String conection = Conection.Ping("SignUp " + numbersignup.getText() + " " + passsignup1.getText() + " " + namesignup.getText() + " 1");
        if (conection.equals("false")) {
            statussignup.setText("This number is available");
            statussignup.setTextFill(Color.RED);
        } else if (conection.equals("Connection refused")) {
            statussignup.setText("You are not connected");
            statussignup.setTextFill(Color.RED);
        } else {
//            new File(sourceuser + numbersignup.getText()).mkdir();
            try {
//                FileWriter sign = new FileWriter(sourceuserProfiles, true);
//                sign.write(numbersignup.getText() + " " + passsignup1.getText() + " " + namesignup.getText() + " " + "1 ");
//                sign.close();
                Main.ChangeScene("Login.fxml");
                statuslogin.setText("Sign up  Succesfuuly");
                statuslogin.setTextFill(Color.GREEN);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    public void SigninPage() throws Exception {
        Main.ChangeScene("Login.fxml");
    }

    public void SignUpPage() throws Exception {
        Main.ChangeScene("SignUp.fxml");
    }

    public void updateChats() throws IOException, ClassNotFoundException {
        String Chatname = Conection.Ping("getChats " + cache.getNumber());
        String[] str = Chatname.split("\\s+");
        if (!Chatname.equals("")) {
            for (int i = 0; i < str.length; i += 2) {
                ChatroomsArrayList.add(new Chat(str[i], str[i + 1]));
            }

            for (int i = 0; i < ChatroomsArrayList.size(); i += 5) {
                String m = "";
                if (ChatroomsArrayList.get(i).getType().equals("1"))
                    m = Conection.Ping("getHistoryContacts " + cache.getNumber() + " " + ChatroomsArrayList.get(i).getNumber());
                else if (ChatroomsArrayList.get(i).getType().equals("2"))
                    m = Conection.Ping("getHistoryGroup   " + cache.getNumber() + " " + ChatroomsArrayList.get(i).getName());
                else if (ChatroomsArrayList.get(i).getType().equals("3"))
                    m = Conection.Ping("getHistoryGroupChannel " + cache.getNumber() + " " + ChatroomsArrayList.get(i).getName());
                String[] arr = m.split("\\s+");
                int j = 0;
                System.out.println(m);
                if (!m.equals("")) {
                    while (j < arr.length) {
                        ArrayList<ArrayList<String>> ms = new ArrayList<>();
                        ArrayList<String> date = new ArrayList<>();
                        ArrayList<String> forward = new ArrayList<>();
                        ArrayList<String> name = new ArrayList<>();
                        ArrayList<String> msj = new ArrayList<>();
                        date.add(arr[j]);
                        forward.add(arr[j + 1]);
                        name.add(arr[j + 2]);
                        String string = "";
                        while (!arr[j + 4].equals("(oO~@~+-`o~O~o~O~o~O~o`~+~_~-~*~&~^~%~$~#`~!~?~Oo)")) {
                            string += arr[j];
                        }
                        msj.add(string);
                        ms.add(date);
                        ms.add(forward);
                        ms.add(name);
                        ms.add(msj);
                        ChatroomsArrayList.get(i).getMsg().add(ms);
                        j++;
                    }
                }
            }
            String string = "";
            for (int i = 0; i < ChatroomsArrayList.size(); i++) {
                if (ChatroomsArrayList.get(i).getType().equals("1"))
                    string += ChatroomsArrayList.get(i).getNumber() + " ";
            }

            string = Conection.Ping("getNameNumber " + cache.getNumber() + " " + string);
            String[] arr = string.split("\\s+");
            for (int i = 0; i < arr.length; i += 2) {
                for (int j = 0; j < ChatroomsArrayList.size(); j++) {
                    if (ChatroomsArrayList.get(j).getNumber().equals(arr[i]))
                        ChatroomsArrayList.get(j).setName(arr[i + 1]);
                }
            }
            for (int i = 0; i < ChatroomsArrayList.size(); i++) {
                chatsTable.getItems().add(ChatroomsArrayList.get(i));
                chatsTable.setFixedCellSize(50);
            }
        }
    }

    public void FriendRequest() {
        if (FriendRequestText.getText().equals("") || FriendRequestText.getText().indexOf(" ") > 0 || FriendRequestText.getText().equals(cache.getNumber())) {
            FriendRequestLabel.setText("Number is not valid");
            FriendRequestLabel.setTextFill(Color.RED);
        } else {
            String s = Conection.Ping("FriendRequest " + cache.getNumber() + " " + FriendRequestText.getText());
            if (s.equals("UserNotFound")) {
                FriendRequestLabel.setText("Not found");
                FriendRequestLabel.setTextFill(Color.RED);
            } else if (s.equals("YouFriended")) {
                FriendRequestLabel.setText("You are Friended");
                FriendRequestLabel.setTextFill(Color.RED);
            } else if (s.equals("Connection refused")) {
                FriendRequestLabel.setText("You are not Conected!");
                FriendRequestLabel.setTextFill(Color.RED);

            } else {
                ChatroomsArrayList.add(new Chat(FriendRequestText.getText(), "1"));
                for (int i = 0; i < ChatroomsArrayList.size(); i++) {
                    if (ChatroomsArrayList.get(i).getNumber().equals(FriendRequestText.getText())) {
                        FriendRequestLabel.setText("Succesfully");
                        FriendRequestLabel.setTextFill(Color.GREEN);
                        ChatroomsArrayList.get(i).setName(s);
                        break;
                    }
                }
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resource) {
        String FileName = location.getFile().substring(location.getFile().lastIndexOf("/") + 1, location.getFile().length());
        if (FileName.equals("SignUp.fxml"))
            fadaTrans(signuppage);
        else if (FileName.equals("Login.fxml"))
            fadaTrans(loginpage);
        else if (FileName.equals("MainPage.fxml")) {

            chatsTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    contextMenu.getItems().removeAll();
                    if (event.getButton() == MouseButton.SECONDARY) {
                        if (chatsTable.getSelectionModel().getSelectedItem().getNotification() == null)
                            contextMenu.getItems().add(soundDisable);
                        else if (chatsTable.getSelectionModel().getSelectedItem().getNotification().equals(Chat.sound.Enable))
                            contextMenu.getItems().add(soundDisable);
                        else
                            contextMenu.getItems().add(soundEnable);
                        contextMenu.getItems().add(removehistory);


                        if (chatsTable.getSelectionModel().getSelectedItem().getType().equals("2")) {
                            if (chatsTable.getSelectionModel().getSelectedItem().getAdmin().equals("1"))
                                contextMenu.getItems().add(removegroup);
                            contextMenu.getItems().add(leavaGroup);
                        } else if (chatsTable.getSelectionModel().getSelectedItem().getType().equals("3")) {
                            if (chatsTable.getSelectionModel().getSelectedItem().getAdmin().equals("1"))
                                contextMenu.getItems().add(removeChannel);
                            contextMenu.getItems().add(leavaChannel);
                        }

                    }
                }
            });

//            searchChatrooms.textProperty().addListener(new ChangeListener<Chat>() {
//                @Override
//                public void changed(ObservableValue<? extends Chat> observable, Chat oldVal, Chat newVal) {
//                    if (oldVal != null && (newVal.getName().length() < oldVal.getName().length())) {
//                        chatsTable.getItems().clear();
//                        for (int i = 0; i < ChatroomsArrayList.size(); i++) {
//                            chatsTable.getItems().add(ChatroomsArrayList.get(i));
//                        }
//                    }
//                    String value = newVal.getName();
//                    ObservableList<Chat> subentries = FXCollections.observableArrayList();
//                    for (Chat entry : chatsTable.getItems()) {
//                        boolean match = true;
//                        subentries.add(entry);
//                        Chat entryText = new Chat(entry.getName(), entry.getType());
//                        if (!entry.getName().contains(value)) {
//                            match = false;
//                        }
//                        if (match) {
//                            subentries.add(entry);
//                        }
//                    }
//                    chatsTable.setItems(subentries);
//                }
//            });

            chatsTable.setCellFactory(param -> {
                return new ListCell<Chat>() {
                    @Override
                    protected void updateItem(Chat item, boolean empty) {
                        super.updateItem(item, empty);

                        if (empty || item == null || item.getNumber() == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(null);
                            GridPane grid = new GridPane();
                            grid.setHgap(10);
                            grid.setVgap(4);
                            grid.setPadding(new Insets(0, 10, 0, 10));

                            Label name = new Label(item.getName());
                            name.setAlignment(Pos.CENTER);
                            name.setFont(Font.font("Garuda", 14));
                            grid.add(name, 4, 2);

                            Image image1 = new Image(Main.class.getResourceAsStream("Type1.png"));
                            if (item.getType().equals("1")) {
                                image1 = new Image(Main.class.getResourceAsStream("Type1.png"));
                            } else if (item.getType().equals("2")) {
                                image1 = new Image(Main.class.getResourceAsStream("Type2.png"));
                            } else if (item.getType().equals("3")) {
                                image1 = new Image(Main.class.getResourceAsStream("Type3.png"));
                            }

                            ImageView imageView = new ImageView(image1);
                            imageView.setFitHeight(40);
                            imageView.setFitWidth(40);
                            imageView.setPreserveRatio(true);
                            Label icon = new Label();
                            icon.setFont(Font.font("FontAwesome", FontWeight.BOLD, 18));
                            icon.setGraphic(imageView);
                            grid.add(icon, 1, 0, 1, 8);

                            if (item.getSeen() != null) {
                                Image image2 = new Image(Main.class.getResourceAsStream("new1.png"));
                                ImageView imageView2 = new ImageView(image2);
                                imageView2.setFitHeight(15);
                                imageView2.setFitWidth(15);
                                imageView2.setPreserveRatio(true);
                                Label icon2 = new Label();
                                icon2.setFont(Font.font("FontAwesome", FontWeight.BOLD, 18));
                                icon2.setGraphic(imageView2);
                                grid.add(icon2, 0, 4, 4, 4);
                            }
                            setGraphic(grid);
                        }
                    }
                };
            });






            Pane pane = null;
            try {
                pane = FXMLLoader.load(getClass().getResource("DrawPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            drawerprofile.setSidePane(pane);
            HamburgerSlideCloseTransition burgerTask = new HamburgerSlideCloseTransition(hamburger);
            burgerTask.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);
                burgerTask.play();
                if (drawerprofile.isShown()) {
                    drawerprofile.close();
                    chatsTable.getItems().clear();
                    for (int i = 0; i < ChatroomsArrayList.size(); i++) {
                        chatsTable.getItems().add(ChatroomsArrayList.get(i));
                    }
                } else {
                    drawerprofile.open();
                }
            });
            try {
                updateChats();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            } catch (ClassNotFoundException e) {
                e.getMessage();
            }

        } else if (FileName.equals("FriendsManage.fxml")) {
//            fadaTrans(FriendsManage);
        } else if (FileName.equals("DrawPage.fxml")) {
            fadaTrans(drawpage);
        }

    }


    private void fadaTrans(Node e) {
        FadeTransition x = new FadeTransition(new Duration(3000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

}
