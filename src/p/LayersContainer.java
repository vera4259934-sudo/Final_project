package p;


//import java.util.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class LayersContainer extends JPanel {
    public enum Layer {
        WELCOME, HOW_PLAY, FACT, RECIPE, LEVEL_1, LEVEL_1_1, LEVEL_1_2, OVER, FACT_2, RECIPE_2, LEVEL_2, LEVEL_2_2, LEVEL_2_3, OVER_2, THANKS
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
        add(new Over(), String.valueOf(Layer.OVER));
        add(new FactLayer_2(this), String.valueOf(Layer.FACT_2));
        add(new RecipeLayer_2(this), String.valueOf(Layer.RECIPE_2));
        add(new Level_2(), String.valueOf(Layer.LEVEL_2));
        add(new How_play(this), String.valueOf(Layer.HOW_PLAY));
        add(new Level_2_2(), String.valueOf(Layer.LEVEL_2_2));
        add(new Level_2_3(), String.valueOf(Layer.LEVEL_2_3));
        add(new Over_2(), String.valueOf(Layer.OVER_2));
        add(new Thanks(), String.valueOf(Layer.THANKS));


    }

    public void showLayer(Layer id) {
        cardLayout.show(this, String.valueOf(id));
    }

    private class WelcomeLayer extends JPanel {
        private final Icon backgroundIcon;
        private final LayersContainer container;

        public WelcomeLayer(LayersContainer container) {
            this.container = container;
            backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/Welcome_screen.png"));
            Icon playButtonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/Play.png"));
            Icon playButtonIcon2 = new ImageIcon(getClass().getClassLoader().getResource("img/How_play.png"));
            JLabel playButton = new JLabel(playButtonIcon);
            JLabel playButton2 = new JLabel(playButtonIcon2);
           // int x = (backgroundIcon.getIconWidth() - playButtonIcon.getIconWidth()) / 2;
            playButton.setBounds(307, 160, playButtonIcon.getIconWidth(), playButtonIcon.getIconHeight());
            playButton2.setBounds(675, 160, playButtonIcon2.getIconWidth(), playButtonIcon.getIconHeight()); //     2
            setLayout(null);
            add(playButton);
            add(playButton2);



            playButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {

                        container.showLayer(Layer.FACT);//****************************************************************************************
                    }
                }
            });

            //-------------------------------
            playButton2.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        container.showLayer(Layer.HOW_PLAY);
                    }
                }
            });
            //------------------------------
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            backgroundIcon.paintIcon(this, g, 0, 0);
        }
    }

    private class How_play extends JPanel {
        private final Image backgroundImage;
        private final JLabel recipeButton;
        private final LayersContainer container;

        public How_play(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/How_play_screen.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/dalee.png"));
            recipeButton = new JLabel(buttonIcon);
            recipeButton.setBounds(950, 550, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(recipeButton);

            recipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    container.showLayer(Layer.WELCOME);
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }

    private class FactLayer extends JPanel {
        private final Image backgroundImage;
        private final JLabel recipeButton;
        private final LayersContainer container;

        public FactLayer(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/Fact_screen.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/for_recipe.png"));
            recipeButton = new JLabel(buttonIcon);
            recipeButton.setBounds(100, 400, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
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




    //******************************************************************************************

    private class FactLayer_2 extends JPanel {
        private final Image backgroundImage;
        private final JLabel recipeButton;
        private final LayersContainer container;

        public FactLayer_2(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/Fact_2_screen.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/for_recipe.png"));
            recipeButton = new JLabel(buttonIcon);
            recipeButton.setBounds(865, 400, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(recipeButton);

            recipeButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    container.showLayer(Layer.RECIPE_2);
                }
            });
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
    //******************************************************************************************

    private class RecipeLayer extends JPanel {
        private final Image backgroundImage;
        private final JLabel readyButton;
        private Timer timer;
        private int timeLeft = 120;
        private final LayersContainer container;

        public RecipeLayer(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/Recipe_screen.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/dalee.png"));
            readyButton = new JLabel(buttonIcon);
            readyButton.setBounds(1015, 500, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(readyButton);

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

        @Override
        public void setVisible(boolean b) {
            super.setVisible(b);
            if(b) {
                startTimer();
            }
            else {
                // stopTimer();
            }
        }

        private void startTimer() {
            timeLeft = 120;
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
            g.drawString("Время: " + timeLeft, 1078, 213);
        }
    }

    //-------------------------------------------------------------

    private class RecipeLayer_2 extends JPanel {
        private final Image backgroundImage;
        private final JLabel readyButton;
        private Timer timer2;
        private int timeLeft = 80;
        private final LayersContainer container;

        public RecipeLayer_2(LayersContainer container) {
            this.container = container;
            backgroundImage = Toolkit.getDefaultToolkit().createImage(getClass().getClassLoader().getResource("img/Recipe_2_screen.png"));
            Icon buttonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/dalee.png"));
            readyButton = new JLabel(buttonIcon);
            readyButton.setBounds(150, 500, buttonIcon.getIconWidth(), buttonIcon.getIconHeight());
            setLayout(null);
            add(readyButton);
            //startTimer();

            readyButton.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (timer2 != null) {
                        timer2.stop();
                    }
                    container.showLayer(Layer.LEVEL_2);
                }
            });
        }

        @Override
        public void setVisible(boolean b) {
            super.setVisible(b);
            if(b) {
                startTimer();
            }
            else {
                // stopTimer();
            }
        }

        private void startTimer() {
            timeLeft = 80;
            timer2 = new Timer(1000, e -> {
                timeLeft--;
                repaint();
                if (timeLeft < 0) {
                    timer2.stop();
                    container.showLayer(Layer.LEVEL_2);
                }
            });
            timer2.start();
        }

        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this);
            g.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 35));
            g.setColor(java.awt.Color.RED);
            g.drawString("Время: " + timeLeft, 85, 185);
        }
    }
    //-------------------------------------------------------------
}