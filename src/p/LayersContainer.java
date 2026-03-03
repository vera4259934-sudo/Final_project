package p;


//import java.util.*;
import java.awt.*;
import javax.swing.*;

public class LayersContainer extends JPanel {
    public enum Layer {
        WELCOME, FACT, RECIPE, LEVEL_1, LEVEL_1_1, LEVEL_1_2
    }

    protected final CardLayout cardLayout = new CardLayout();

    public LayersContainer() {
        setLayout(cardLayout);
        add(new WelcomeLayer(this), String.valueOf(Layer.WELCOME));
        add(new FactLayer(this), String.valueOf(Layer.FACT));
        add(new RecipeLayer(this), String.valueOf(Layer.RECIPE));
        add(new Level_1(), String.valueOf(Layer.LEVEL_1));
        add(new Level_1_1(), String.valueOf(Layer.LEVEL_1_1));
        add(new Level_1_2(), String.valueOf(Layer.LEVEL_1_2));
    }

    public void showLayer(Layer id) {
        cardLayout.show(this, String.valueOf(id));
    }

    private class WelcomeLayer extends JPanel {
        private final Icon backgroundIcon;
        private final LayersContainer container;

        public WelcomeLayer(LayersContainer container) {
            this.container = container;
            backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/background.png"));
            Icon playButtonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/play.png"));
            JLabel playButton = new JLabel(playButtonIcon);
            int x = (backgroundIcon.getIconWidth() - playButtonIcon.getIconWidth()) / 2;
            playButton.setBounds(x, 230, playButtonIcon.getIconWidth(), playButtonIcon.getIconHeight());
            setLayout(null);
            add(playButton);

            playButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        container.showLayer(Layer.FACT);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            backgroundIcon.paintIcon(this, g, 0, 0);
        }
    }

    private class FactLayer extends JPanel {
        private final Image backgroundImage;
        private final JLabel recipeButton;
        private final LayersContainer container;

        public FactLayer(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/Screen_fact.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/To_the_recipe.png"));
            recipeButton = new JLabel(buttonIcon);
            recipeButton.setBounds(1000, 400, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(recipeButton);

            recipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    container.showLayer(Layer.RECIPE);
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    private class RecipeLayer extends JPanel {
        private final Image backgroundImage;
        private final JLabel readyButton;
        private Timer timer;
        private int timeLeft = 120;
        private final LayersContainer container;

        public RecipeLayer(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/ScreenRecipe.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/knopkaReady.png"));
            readyButton = new JLabel(buttonIcon);
            readyButton.setBounds(1015, 500, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(readyButton);
            startTimer();

            readyButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (timer != null) {
                        timer.stop();
                    }
                    container.showLayer(Layer.LEVEL_1);
                }
            });
        }

        private void startTimer() {
            timer = new Timer(1000, e -> {
                timeLeft--;
                repaint();
                if (timeLeft < 0) {
                    timer.stop();
                    container.showLayer(Layer.LEVEL_1);
                }
            });
            timer.start();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 35));
            g.setColor(java.awt.Color.RED);
            g.drawString("Время: " + timeLeft, 1085, 300);
        }
    }
}