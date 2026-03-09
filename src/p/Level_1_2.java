package p;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Level_1_2 extends JLayeredPane implements MouseListener, ActionListener {
    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private NewProduct trayWithPizza;
    private NewProduct oven;
    private NewProduct plate;
    private NewProduct knife;
    private NewProduct basil;
    private NewProduct oil;
    private NewProduct pizza;
    private JLabel endLabel;
    private JLabel timerLabel;
    private Timer cookingTimer;
    private int timeLeft = 5;
    private Point trayPos;
    private Point ovenPos;
    private Point platePos;
    private Point knifePos;
    private Point basilPos;
    private Point oilPos;
    private boolean ovenUsed = false;
    private boolean pizzaOnPlate = false;
    private boolean oilAdded = false;
    private boolean basilAdded = false;
    private boolean knifeUsed = false;
    private boolean quantityPopupShown = false;
    private boolean gameEnded = false;
    private boolean cooking = false;
    private QuantityPopup temperaturePopup;

    public Level_1_2() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/level1background.png"));

        trayWithPizza = new NewProduct("tray_cheese2", true,
                "img/tray_cheese2.png", "img/tray_cheese2.png",
                "img/tray_cheese2.png", "img/tray_cheese2.png");

        oven = new NewProduct("oven", false,
                "img/oven.png", "img/oven.png",
                "img/oven.png", "img/oven.png");

        plate = new NewProduct("plate", false,
                "img/plate.png", "img/plate.png",
                "img/plate.png", "img/plate.png");

        knife = new NewProduct("knife2", true,
                "img/knife2.png", "img/knife2.png",
                "img/knife2.png", "img/knife2.png");

        basil = new NewProduct("basil", true,
                "img/basil.png", "img/basil.png",
                "img/basil.png", "img/basil.png");

        oil = new NewProduct("oil", true,
                "img/oil.png", "img/oil_selected.png",
                "img/oil.png", "img/oil_selected.png");

        pizza = null;

        temperaturePopup = new QuantityPopup("temperature", 0, 300, 180,
                "img/oven_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this);

        endLabel = new JLabel("Конец!");
        endLabel.setFont(new Font("Arial", Font.BOLD, 72));
        endLabel.setForeground(Color.RED);
        endLabel.setSize(300, 100);
        endLabel.setLocation(530, 300);
        endLabel.setVisible(false);

        timerLabel = new JLabel("");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 25));
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setSize(200, 100);
        timerLabel.setLocation(1200, 540);
        timerLabel.setVisible(false);

        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));
        setLayout(null);

        trayPos = new Point(250, 290);
        ovenPos = new Point(900, 300);
        platePos = new Point(540, 450);
        knifePos = new Point(800, 600);
        basilPos = new Point(50, 500);
        oilPos = new Point(200, 500);

        add(trayWithPizza);
        trayWithPizza.setBounds(trayPos.x, trayPos.y, trayWithPizza.getPreferredSize().width, trayWithPizza.getPreferredSize().height);
        trayWithPizza.addMouseListener(this);

        add(oven);
        oven.setBounds(ovenPos.x, ovenPos.y, oven.getPreferredSize().width, oven.getPreferredSize().height);
        oven.addMouseListener(this);

        add(plate);
        plate.setBounds(platePos.x, platePos.y, plate.getPreferredSize().width, plate.getPreferredSize().height);
        plate.addMouseListener(this);

        add(knife);
        knife.setBounds(knifePos.x, knifePos.y, knife.getPreferredSize().width, knife.getPreferredSize().height);
        knife.addMouseListener(this);

        add(basil);
        basil.setBounds(basilPos.x, basilPos.y, basil.getPreferredSize().width, basil.getPreferredSize().height);
        basil.addMouseListener(this);

        add(oil);
        oil.setBounds(oilPos.x, oilPos.y, oil.getPreferredSize().width, oil.getPreferredSize().height);
        oil.addMouseListener(this);

        add(endLabel);
        add(timerLabel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == temperaturePopup) {
            remove(temperaturePopup);
            quantityPopupShown = false;
            repaint();
            startCooking();
        } else if (source == cookingTimer) {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            if (timeLeft <= 0) {
                cookingTimer.stop();
                timerLabel.setVisible(false);
                remove(trayWithPizza);
                pizza = new NewProduct("pizza", true,
                        "img/pizza.png", "img/pizza.png",
                        "img/pizza.png", "img/pizza.png");
                pizza.setBounds(trayPos.x, trayPos.y, pizza.getPreferredSize().width, pizza.getPreferredSize().height);
                add(pizza);
                pizza.addMouseListener(this);
                this.trayWithPizza = pizza;
                ovenUsed = true;
                cooking = false;
                repaint();
            }
        }
    }

    private void startCooking() {
        cooking = true;
        timeLeft = 5;
        timerLabel.setText("5");
        timerLabel.setVisible(true);
        remove(trayWithPizza);
        repaint();
        cookingTimer = new Timer(1000, this);
        cookingTimer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (gameEnded) return;

        Object source = event.getSource();

        if (source == trayWithPizza && !ovenUsed && !cooking && trayWithPizza.getName().equals("tray_cheese2")) {
            Rectangle pizzaBounds = trayWithPizza.getBounds();
            Rectangle ovenBounds = oven.getBounds();

            if (pizzaBounds.intersects(ovenBounds) && !quantityPopupShown) {
                temperaturePopup.setLocation(300, 150);
                add(temperaturePopup);
                moveToFront(temperaturePopup);
                quantityPopupShown = true;
                event.consume();
            }
        }

        if (source == pizza && ovenUsed && !pizzaOnPlate && !cooking) {
            Rectangle pizzaBounds = pizza.getBounds();
            Rectangle plateBounds = plate.getBounds();

            if (pizzaBounds.intersects(plateBounds)) {
                remove(pizza);
                NewProduct pizzaOnPlate_ = new NewProduct("plate_pizza", false,
                        "img/plate_pizza.png", "img/plate_pizza.png",
                        "img/plate_pizza.png", "img/plate_pizza.png");
                pizzaOnPlate_.setBounds(platePos.x, platePos.y, pizzaOnPlate_.getPreferredSize().width, pizzaOnPlate_.getPreferredSize().height);
                add(pizzaOnPlate_);
                remove(plate);
                this.plate = pizzaOnPlate_;
                this.pizza = null;
                pizzaOnPlate = true;
                repaint();
            }
        }

        if (source == oil && pizzaOnPlate && !oilAdded && plate.getName().equals("plate_pizza")) {
            Rectangle oilBounds = oil.getBounds();
            Rectangle plateBounds = plate.getBounds();

            if (oilBounds.intersects(plateBounds)) {
                remove(plate);
                NewProduct plateWithOil = new NewProduct("plate_oil", false,
                        "img/plate_oil.png", "img/plate_oil.png",
                        "img/plate_oil.png", "img/plate_oil.png");
                plateWithOil.setBounds(platePos.x, platePos.y, plateWithOil.getPreferredSize().width, plateWithOil.getPreferredSize().height);
                add(plateWithOil);
                this.plate = plateWithOil;
                oilAdded = true;
                oil.setLocation(oilPos);
                repaint();
            }
        }

        if (source == basil && oilAdded && !basilAdded && plate.getName().equals("plate_oil")) {
            Rectangle basilBounds = basil.getBounds();
            Rectangle plateBounds = plate.getBounds();

            if (basilBounds.intersects(plateBounds)) {
                remove(plate);
                NewProduct plateWithBasil = new NewProduct("plate_green", false,
                        "img/plate_green.png", "img/plate_green.png",
                        "img/plate_green.png", "img/plate_green.png");
                plateWithBasil.setBounds(platePos.x, platePos.y, plateWithBasil.getPreferredSize().width, plateWithBasil.getPreferredSize().height);
                add(plateWithBasil);
                this.plate = plateWithBasil;
                basilAdded = true;
                remove(basil);
                repaint();
            }
        }

        if (source == knife && basilAdded && !knifeUsed && plate.getName().equals("plate_green")) {
            Rectangle knifeBounds = knife.getBounds();
            Rectangle plateBounds = plate.getBounds();

            if (knifeBounds.intersects(plateBounds)) {
                remove(plate);
                NewProduct finalPlate = new NewProduct("thatsALL", false,
                        "img/thatsALL.png", "img/thatsALL.png",
                        "img/thatsALL.png", "img/thatsALL.png");
                finalPlate.setBounds(platePos.x, platePos.y, finalPlate.getPreferredSize().width, finalPlate.getPreferredSize().height);
                add(finalPlate);
                this.plate = finalPlate;
                knifeUsed = true;
                knife.setLocation(knifePos);
                gameEnded = true;
                endLabel.setVisible(true);
                repaint();
            }
        }
    }

    @Override public void mouseEntered(MouseEvent event) {}
    @Override public void mouseExited(MouseEvent event) {}
    @Override public void mousePressed(MouseEvent event) {}
    @Override public void mouseClicked(MouseEvent event) {}
}