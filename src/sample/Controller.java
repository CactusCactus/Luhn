package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    TextField peselInput;
    @FXML
    Label outputLabel;
    @FXML
    Button checkButton;
    PrintWriter out;

    @FXML
    private void OnCheckBtnClick() {

        if(peselInput.getText().length() < 11) {
            outputLabel.setText("PESEL za krótki");
        } else {
            if(checkPESEL(peselInput.getText())) {
                outputLabel.setText("PESEL poprawny");
                try {
                    FileWriter writer = new FileWriter("PESEL.txt", true);
                    writer.write(peselInput.getText() + "\n");
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else outputLabel.setText("PESEL niepoprawny");
        }
    }
    private boolean checkPESEL(String pesel) {
        String controlCheck = "1379";
        int sum = 0;
        for(int i = 0; i < pesel.length() - 1; i ++) {
            int contolIndex = Character.getNumericValue(controlCheck.charAt(i%4));
            sum += Character.getNumericValue(pesel.charAt(i)) * contolIndex;
        }
        sum = sum % 10;
        sum = 10 - sum;
        sum = sum % 10;
        return sum == Character.getNumericValue(pesel.charAt(pesel.length() - 1));

    }
}
