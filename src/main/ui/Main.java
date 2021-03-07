package ui;

import javax.sound.sampled.Port;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new PortfolioApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
