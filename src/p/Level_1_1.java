/*package p;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Level_1_1 extends JLayeredPane implements MouseListener {
    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private NewProduct mixer;
    private NewProduct board;
    private NewProduct grater;
    private NewProduct dish;
    private NewProduct spoon;
    private NewProduct tray;
    private Point mixerPos;
    private Point boardPos;
    private Point graterPos;
    private Point dishPos;
    private Point spoonPos;
    private boolean trayWithDough = false;
    private boolean trayWithTomato = false;
    private boolean trayWithCheese1 = false;
    private boolean trayWithCheese2 = false;

    public Level_1_1() {
      //  backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/level1background.png"));
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/Main_screen.png"));

        mixer = new NewProduct("mixer", true,
                "img/mixer_tomato.png", "img/mixer_tomato.png",
                "img/mixer.png", "img/mixer.png");
        board = new NewProduct("board", true,
                "img/board_cheese_12.png", "img/board_cheese_12.png",
                "img/board.png", "img/board.png");
        dish = new NewProduct("dish", true,
                "img/dish_dough.png", "img/dish_dough.png",
                "img/dish.png", "img/dish.png");
        grater = new NewProduct("grater", true,
                "img/grater_cheese.png", "img/grater_cheese.png",
                "img/grater.png", "img/grater.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");
        tray = new NewProduct("tray", false,
                "img/tray.png", "img/tray.png",
                "img/tray.png", "img/tray.png");

        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        mixerPos = new Point(25, 300);
        boardPos = new Point(1000, 570);
        graterPos = new Point(750, 300);
        dishPos = new Point(540, 505);
        spoonPos = new Point(830, 600);

        add(mixer);
        mixer.setBounds(mixerPos.x, mixerPos.y, 220, 290);
        mixer.addMouseListener(this);

        add(board);
        board.setBounds(boardPos.x, boardPos.y, 370, 230);
        board.addMouseListener(this);

        add(grater);
        grater.setBounds(graterPos.x, graterPos.y, 370, 230);
        grater.addMouseListener(this);

        add(dish);
        dish.setBounds(dishPos.x, dishPos.y, 245, 230);
        dish.addMouseListener(this);

        add(spoon);
        spoon.setBounds(spoonPos.x, spoonPos.y, 160, 160);
        spoon.addMouseListener(this);

        add(tray);
        tray.setBounds(250, 290, 367, 226);
        tray.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();

        if (source == dish && !trayWithDough) {
            Rectangle dishBounds = dish.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (dishBounds.intersects(trayBounds)) {
                remove(dish);
                NewProduct emptyDish = new NewProduct("dish", false,
                        "img/dish.png", "img/dish.png",
                        "img/dish.png", "img/dish.png");
                emptyDish.setBounds(dishPos.x, dishPos.y, 245, 230);
                add(emptyDish);
                this.dish = emptyDish;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_doagh_n", false,
                        "img/tray_doagh_n.png", "img/tray_doagh_n.png",
                        "img/tray_doagh_n.png", "img/tray_doagh_n.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithDough = true;
                repaint();
            }
        }

        if (source == spoon && trayWithDough && tray.getName().equals("tray_doagh_n")) {
            Rectangle spoonBounds = spoon.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (spoonBounds.intersects(trayBounds)) {
                remove(tray);
                NewProduct newTray = new NewProduct("tray_doagh", false,
                        "img/tray_doagh.png", "img/tray_doagh.png",
                        "img/tray_doagh.png", "img/tray_doagh.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                spoon.setLocation(spoonPos);
                repaint();
            }
        }

        if (source == mixer && trayWithDough && !trayWithTomato && tray.getName().equals("tray_doagh")) {
            Rectangle mixerBounds = mixer.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (mixerBounds.intersects(trayBounds)) {
                remove(mixer);
                NewProduct emptyMixer = new NewProduct("mixer", false,
                        "img/mixer.png", "img/mixer.png",
                        "img/mixer.png", "img/mixer.png");
                emptyMixer.setBounds(mixerPos.x, mixerPos.y, 220, 290);
                add(emptyMixer);
                this.mixer = emptyMixer;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_tomato_n", false,
                        "img/tray_tomato_n.png", "img/tray_tomato_n.png",
                        "img/tray_tomato_n.png", "img/tray_tomato_n.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithTomato = true;
                repaint();
            }
        }

        if (source == spoon && trayWithTomato && tray.getName().equals("tray_tomato_n")) {
            Rectangle spoonBounds = spoon.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (spoonBounds.intersects(trayBounds)) {
                remove(tray);
                NewProduct newTray = new NewProduct("tray_tomato", false,
                        "img/tray_tomato.png", "img/tray_tomato.png",
                        "img/tray_tomato.png", "img/tray_tomato.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                spoon.setLocation(spoonPos);
                repaint();
            }
        }

        if (source == grater && trayWithTomato && !trayWithCheese1 && tray.getName().equals("tray_tomato")) {
            Rectangle graterBounds = grater.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (graterBounds.intersects(trayBounds)) {
                remove(grater);
                NewProduct emptyGrater = new NewProduct("grater", false,
                        "img/grater.png", "img/grater.png",
                        "img/grater.png", "img/grater.png");
                emptyGrater.setBounds(graterPos.x, graterPos.y, 370, 230);
                add(emptyGrater);
                this.grater = emptyGrater;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_cheese1", false,
                        "img/tray_cheese1.png", "img/tray_cheese1.png",
                        "img/tray_cheese1.png", "img/tray_cheese1.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithCheese1 = true;
                repaint();
            }
        }

        if (source == board && trayWithCheese1 && !trayWithCheese2 && tray.getName().equals("tray_cheese1")) {
            Rectangle boardBounds = board.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (boardBounds.intersects(trayBounds)) {
                remove(board);
                NewProduct emptyBoard = new NewProduct("board", false,
                        "img/board.png", "img/board.png",
                        "img/board.png", "img/board.png");
                emptyBoard.setBounds(boardPos.x, boardPos.y, 370, 230);
                add(emptyBoard);
                this.board = emptyBoard;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_cheese2", false,
                        "img/tray_cheese2.png", "img/tray_cheese2.png",
                        "img/tray_cheese2.png", "img/tray_cheese2.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithCheese2 = true;
                repaint();
            }
        }

        if (trayWithCheese2 && tray.getName().equals("tray_cheese2")) {
            if (layersContainer != null) {
                layersContainer.showLayer(LayersContainer.Layer.LEVEL_1_2);
            }
        }
    }

    @Override public void mouseEntered(MouseEvent event) {}
    @Override public void mouseExited(MouseEvent event) {}
    @Override public void mousePressed(MouseEvent event) {}
    @Override public void mouseClicked(MouseEvent event) {}
}*/

package p;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Level_1_1 extends JLayeredPane implements MouseListener {
    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private NewProduct mixer;
    private NewProduct board;
    private NewProduct grater;
    private NewProduct dish;
    private NewProduct spoon;
    private NewProduct tray;
    private Point mixerPos;
    private Point boardPos;
    private Point graterPos;
    private Point dishPos;
    private Point spoonPos;
    private boolean trayWithDough = false;
    private boolean trayWithTomato = false;
    private boolean trayWithCheese1 = false;
    private boolean trayWithCheese2 = false;

    public Level_1_1() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/Main_screen.png"));

        mixer = new NewProduct("mixer", true,
                "img/mixer_tomato.png", "img/mixer_tomato.png",
                "img/mixer.png", "img/mixer.png");
        board = new NewProduct("board", true,
                "img/board_cheese_12.png", "img/board_cheese_12.png",
                "img/board.png", "img/board.png");
        dish = new NewProduct("dish", true,
                "img/dish_dough.png", "img/dish_dough.png",
                "img/dish.png", "img/dish.png");
        grater = new NewProduct("grater", true,
                "img/grater_cheese.png", "img/grater_cheese.png",
                "img/grater.png", "img/grater.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");
        tray = new NewProduct("tray", false,
                "img/tray.png", "img/tray.png",
                "img/tray.png", "img/tray.png");

        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        mixerPos = new Point(25, 300);
        boardPos = new Point(1000, 570);
        graterPos = new Point(750, 300);
        dishPos = new Point(540, 505);
        spoonPos = new Point(830, 600);

        add(mixer);
        mixer.setBounds(mixerPos.x, mixerPos.y, 220, 290);
        mixer.addMouseListener(this);

        add(board);
        board.setBounds(boardPos.x, boardPos.y, 370, 230);
        board.addMouseListener(this);

        add(grater);
        grater.setBounds(graterPos.x, graterPos.y, 370, 230);
        grater.addMouseListener(this);

        add(dish);
        dish.setBounds(dishPos.x, dishPos.y, 245, 230);
        dish.addMouseListener(this);

        add(spoon);
        spoon.setBounds(spoonPos.x, spoonPos.y, 160, 160);
        spoon.addMouseListener(this);

        add(tray);
        tray.setBounds(250, 290, 367, 226);
        tray.addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();

        if (source == dish && !trayWithDough) {
            Rectangle dishBounds = dish.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (dishBounds.intersects(trayBounds)) {
                remove(dish);
                NewProduct emptyDish = new NewProduct("dish", false,
                        "img/dish.png", "img/dish.png",
                        "img/dish.png", "img/dish.png");
                emptyDish.setBounds(dishPos.x, dishPos.y, 245, 230);
                add(emptyDish);
                this.dish = emptyDish;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_doagh_n", false,
                        "img/tray_doagh_n.png", "img/tray_doagh_n.png",
                        "img/tray_doagh_n.png", "img/tray_doagh_n.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithDough = true;
                repaint();
            }
        }

        if (source == spoon && trayWithDough && tray.getName().equals("tray_doagh_n")) {
            Rectangle spoonBounds = spoon.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (spoonBounds.intersects(trayBounds)) {
                remove(tray);
                NewProduct newTray = new NewProduct("tray_doagh", false,
                        "img/tray_doagh.png", "img/tray_doagh.png",
                        "img/tray_doagh.png", "img/tray_doagh.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                spoon.setLocation(spoonPos);
                repaint();
            }
        }

        if (source == mixer && trayWithDough && !trayWithTomato && tray.getName().equals("tray_doagh")) {
            Rectangle mixerBounds = mixer.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (mixerBounds.intersects(trayBounds)) {
                remove(mixer);
                NewProduct emptyMixer = new NewProduct("mixer", false,
                        "img/mixer.png", "img/mixer.png",
                        "img/mixer.png", "img/mixer.png");
                emptyMixer.setBounds(mixerPos.x, mixerPos.y, 220, 290);
                add(emptyMixer);
                this.mixer = emptyMixer;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_tomato_n", false,
                        "img/tray_tomato_n.png", "img/tray_tomato_n.png",
                        "img/tray_tomato_n.png", "img/tray_tomato_n.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithTomato = true;
                repaint();
            }
        }

        if (source == spoon && trayWithTomato && tray.getName().equals("tray_tomato_n")) {
            Rectangle spoonBounds = spoon.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (spoonBounds.intersects(trayBounds)) {
                remove(tray);
                NewProduct newTray = new NewProduct("tray_tomato", false,
                        "img/tray_tomato.png", "img/tray_tomato.png",
                        "img/tray_tomato.png", "img/tray_tomato.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                spoon.setLocation(spoonPos);
                repaint();
            }
        }

        if (source == grater && trayWithTomato && !trayWithCheese1 && tray.getName().equals("tray_tomato")) {
            Rectangle graterBounds = grater.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (graterBounds.intersects(trayBounds)) {
                remove(grater);
                NewProduct emptyGrater = new NewProduct("grater", false,
                        "img/grater.png", "img/grater.png",
                        "img/grater.png", "img/grater.png");
                emptyGrater.setBounds(graterPos.x, graterPos.y, 370, 230);
                add(emptyGrater);
                this.grater = emptyGrater;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_cheese1", false,
                        "img/tray_cheese1.png", "img/tray_cheese1.png",
                        "img/tray_cheese1.png", "img/tray_cheese1.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithCheese1 = true;
                repaint();
            }
        }

        if (source == board && trayWithCheese1 && !trayWithCheese2 && tray.getName().equals("tray_cheese1")) {
            Rectangle boardBounds = board.getBounds();
            Rectangle trayBounds = tray.getBounds();

            if (boardBounds.intersects(trayBounds)) {
                remove(board);
                NewProduct emptyBoard = new NewProduct("board", false,
                        "img/board.png", "img/board.png",
                        "img/board.png", "img/board.png");
                emptyBoard.setBounds(boardPos.x, boardPos.y, 370, 230);
                add(emptyBoard);
                this.board = emptyBoard;

                remove(tray);
                NewProduct newTray = new NewProduct("tray_cheese2", false,
                        "img/tray_cheese2.png", "img/tray_cheese2.png",
                        "img/tray_cheese2.png", "img/tray_cheese2.png");
                newTray.setBounds(trayBounds);
                add(newTray);
                this.tray = newTray;
                trayWithCheese2 = true;
                repaint();
            }
        }

        if (trayWithCheese2 && tray.getName().equals("tray_cheese2")) {
            if (layersContainer != null) {
                layersContainer.showLayer(LayersContainer.Layer.LEVEL_1_2);
            }
        }
    }

    @Override public void mouseEntered(MouseEvent event) {}
    @Override public void mouseExited(MouseEvent event) {}
    @Override public void mousePressed(MouseEvent event) {}
    @Override public void mouseClicked(MouseEvent event) {}
}