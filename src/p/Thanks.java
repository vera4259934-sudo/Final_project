package p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Thanks extends JPanel {
    private LayersContainer container;
    private Image backgroundImage;
    private JLabel nextButton;

    private MistakeCounter mistakeCounter = MistakeCounter.getSharedInstance();

    @Override
    public void addNotify() {
        super.addNotify();
        this.container = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    public Thanks() {
        this.container = container;

        // Загружаем фоновое изображение "overgame"
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("img/ALL.png")).getImage();

        // Кнопка "Далее" (можно заменить на JButton с изображением)
        Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/Home.png"));
        nextButton = new JLabel(buttonIcon);
        nextButton.setBounds(900, 500, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());

        setLayout(null);
        add(nextButton);

        // Обработка клика по кнопке "Далее"
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Переход к слою Recipe
                container.showLayer(LayersContainer.Layer.WELCOME);
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

    }

    public static void main(String...args) {
        JFrame f = new JFrame();
        f.add(new Over());
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}