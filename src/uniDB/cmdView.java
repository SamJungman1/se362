package uniDB;

import java.util.Scanner;

public class cmdView {
    Controller controller = new Controller();

    public void processCommands(){
        while(true) {
            Scanner scan = new Scanner(System.in);
            display("enter command:");
            display(controller.parseCommand(scan.nextLine()));
        }
    }

    public void display(Object message) {
        System.out.println(message);
    }
}
