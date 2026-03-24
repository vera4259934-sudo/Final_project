package p;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Over extends JPanel {
    private LayersContainer container;
    private Image backgroundImage;
    private JLabel nextButton;

    private MistakeCounter mistakeCounter = MistakeCounter.getSharedInstance();

    @Override
    public void addNotify() {
        super.addNotify();
        this.container = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    public Over() {
        this.container = container;

        // Загружаем фоновое изображение "overgame"
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("img/Over_screen.png")).getImage();

        // Кнопка "Далее" (можно заменить на JButton с изображением)
        Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/dalee.png"));
        nextButton = new JLabel(buttonIcon);
        nextButton.setBounds(800, 450, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());

        setLayout(null);
        add(nextButton);

        // Обработка клика по кнопке "Далее"
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Переход к слою Recipe
                container.showLayer(LayersContainer.Layer.FACT_2);
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if(b) {
            int mistakeAmount = mistakeCounter.getMistakeAmount();
            System.out.println(mistakeCounter);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        int mistakeAmount = mistakeCounter.getMistakeAmount();

        Font name;
        try {

            name = Font.createFont(Font.TRUETYPE_FONT, new File("C:/Users/gavsp/OneDrive/Документы/Кулинарный вызов/Game_for_me/IlayGame/src/Caveat.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(name);

        }  catch (IOException | FontFormatException e) {
            name = Font.getFont("Arial");
        }
        g.setFont(new Font(name.getName(), Font.BOLD, 100));
        g.setColor(Color.RED);
        String mistakeText = "  " + mistakeAmount;
        g.drawString(mistakeText, 965, 260);
    }

    public static void main(String...args) {
        JFrame f = new JFrame();
        f.add(new Over());
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}