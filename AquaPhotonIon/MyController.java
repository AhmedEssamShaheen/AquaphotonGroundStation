
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import jssc.SerialPort;
import jssc.SerialPortException;

public class MyController {
private static SerialConection conections;
private static ObservableList<String>port;
private static Controlling xboxs;

    public static SerialConection getConections() {
        return conections;
    }

    public static Controlling getXboxs() {
        return xboxs;
    }

    @FXML
    private ChoiceBox<String> choiceBox;


    @FXML
    void onClose(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private MenuItem xbox;
    @FXML
    void startController(ActionEvent event) {
        try {

            if(XboxController.isConnected())
            {
                new Thread(
                        new Runnable() {

                            public void run() {try{SerialConection.getConnection()
                                    .getPort().addEventListener(SerialConection.getConnection(), SerialPort.MASK_RXCHAR);}
                            catch (SerialPortException e) {System.out.println("Error in Resciving Data");
                            }

                                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

                                while(XboxController.isConnected())
                                {

                                 XboxController.getController().actionRespond();
                                }
                            }
                        }
                ).start();
            }

        /*    else if()
            {
                toggleButton.setSelected(false);
            }*/

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    void selectedPort(MouseEvent event) {

      choiceBox.setValue(port.get(0));
        choiceBox.setItems(port);
    }
    @FXML
    void startConnection(ActionEvent event) {
        conections=SerialConection.getConnection( choiceBox.getValue());
        xboxs= XboxController.getController(conections);
        xboxs.onStart();
        conections.onStart();
        //System.out.println(conections);

    }
   static void setChoiseBox(){
       port= FXCollections.observableArrayList(SerialConection.availablePorts());
    }

}