package p;

import javax.swing.*;
import java.awt.*;

public class Launcher implements Runnable {
    public static void main(String... args) {
        new Launcher(args).execute();
    }

    private final String[] args;

    public Launcher(String[] args) {
        this.args = args;
    }

    public void execute() {
        SwingUtilities.invokeLater(this);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Best game ever");
        frame.getContentPane().add(new LayersContainer());
        frame.setSize(1360, 770);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
