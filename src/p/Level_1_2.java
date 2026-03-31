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
    //private NewProduct oven;
    private Tool oven;
    //private NewProduct plate;
    private Tool plate;
    private NewProduct knife;
    private NewProduct basil;
    private NewProduct oil;
    //private NewProduct pizza;
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
    //private boolean ovenUsed = false;
    //private boolean pizzaOnPlate = false;
    //private boolean oilAdded = false;
    //private boolean basilAdded = false;
    //private boolean knifeUsed = false;
    //private boolean quantityPopupShown = false;
    //private boolean gameEnded = false;
    //private boolean cooking = false;

    private static enum State {
        RAW_PIZZA,
        COOKING,
        COOKED_PIZZA,
        PIZZA_ON_PLATE,
        OIL_ADDED,
        BASIL_ADDED,
        CUT,
    }
    private State state = State.RAW_PIZZA;
    private QuantityPopup temperaturePopup;
    private final Tool daleeArrow;

    public Level_1_2() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/level1background.png"));

        trayWithPizza = new NewProduct("tray_cheese2", true,
                "img/tray_cheese2.png", "img/tray_cheese2.png",
                "img/tray_cheese2.png", "img/tray_cheese2.png");

        //oven = new NewProduct("oven", false,
        //    "img/oven.png", "img/oven.png",
        //    "img/oven.png", "img/oven.png");
        oven = new Tool("oven", false,
                "img/oven.png",
                "img/oven.png",
                "img/oven_green.png");

        //plate = new NewProduct("plate", false,
        //    "img/plate.png", "img/plate.png",
        //    "img/plate.png", "img/plate.png");
        plate = new Tool("plate", false,
                "img/plate.png",
                "img/plate.png",
                "img/plate_green.png");

        knife = new NewProduct("knife2", false,
                "img/knife2.png", "img/knife2.png",
                "img/knife2.png", "img/knife2.png");

        basil = new NewProduct("basil", false,
                "img/basil.png", "img/basil.png",
                "img/basil.png", "img/basil.png");

        oil = new NewProduct("oil", false,
                "img/oil.png", "img/oil_selected.png",
                "img/oil.png", "img/oil_selected.png");

        //pizza = null;

        temperaturePopup = new QuantityPopup("temperature", 0, 300, 180,
                "img/oven_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this);

        endLabel = new JLabel("Конец!" /*+ mist*/);
        endLabel.setFont(new Font("Arial", Font.BOLD, 72));
        endLabel.setForeground(Color.RED);
        endLabel.setSize(300, 100);
        endLabel.setLocation(530, 300);
        endLabel.setVisible(false);

        timerLabel = new JLabel("");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        timerLabel.setForeground(Color.RED);
        timerLabel.setSize(200, 100);
        timerLabel.setLocation(1220, 350);
        //timerLabel.setVisible(false);

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

        add(plate);
        plate.setBounds(platePos.x, platePos.y, plate.getPreferredSize().width, plate.getPreferredSize().height);

        add(knife);
        knife.setBounds(knifePos.x, knifePos.y, knife.getPreferredSize().width, knife.getPreferredSize().height);
        //knife.addMouseListener(this);

        add(basil);
        basil.setBounds(basilPos.x, basilPos.y, basil.getPreferredSize().width, basil.getPreferredSize().height);
        //basil.addMouseListener(this);

        add(oil);
        oil.setBounds(oilPos.x, oilPos.y, oil.getPreferredSize().width, oil.getPreferredSize().height);
        //oil.addMouseListener(this);

        add(endLabel);
        add(timerLabel);
        moveToFront(timerLabel);

        daleeArrow = new Tool("dalee", false,
                "img/dalee2_bw.png",
                "img/dalee2_bw.png",
                "img/dalee2.png");
        daleeArrow.addMouseListener(this);

        add(daleeArrow);
        daleeArrow.setBounds(1100, 30, 150, 71);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == temperaturePopup) {
            remove(temperaturePopup);
            //quantityPopupShown = false;
            repaint();
            startCooking();
        }
        else if (source == cookingTimer) {
            timeLeft--;
            timerLabel.setText(String.valueOf(timeLeft));
            if (timeLeft <= 0) {
                cookingTimer.stop();
                timerLabel.setVisible(false);
                NewProduct cookedPizza = new NewProduct("pizza", true,
                        "img/pizza.png",
                        "img/pizza.png",
                        "img/pizza.png",
                        "img/pizza.png");
                trayWithPizza.returnToOriginalLocation();
                cookedPizza.setBounds(trayWithPizza.getBounds());
                remove(trayWithPizza);
                trayWithPizza = cookedPizza;
                add(trayWithPizza);
                trayWithPizza.addMouseListener(this);
                //this.trayWithPizza = pizza;
                state = State.COOKED_PIZZA;
                //ovenUsed = true;
                //cooking = false;

                repaint();
            }
        }
    }

    private void startCooking() {
        state = State.COOKING;
        //cooking = true;
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
        //super.paintComponent(g);
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        //if (gameEnded) return;

        int x = event.getX();
        int y = event.getY();
        Object source = event.getSource();
        if(source instanceof JComponent) {
            JComponent component = (JComponent) source;
            x += component.getX();
            y += component.getY();
        }

        if (source == trayWithPizza /*&& !ovenUsed && !cooking && trayWithPizza.getName().equals("tray_cheese2")*/ ) {
            if(state == State.RAW_PIZZA) {
                oven.setTargeted(false);

                //Rectangle pizzaBounds = trayWithPizza.getBounds();
                Rectangle ovenBounds = oven.getBounds();
                //if (pizzaBounds.intersects(ovenBounds) && !quantityPopupShown) {
                if (ovenBounds.contains(x, y)) {
                    temperaturePopup.setLocation(300, 150);
                    add(temperaturePopup);
                    moveToFront(temperaturePopup);
                    //quantityPopupShown = true;
                    event.consume();
                }
            }
            else if(state == State.COOKED_PIZZA) {
                plate.setTargeted(false);

                //Rectangle pizzaBounds = pizza.getBounds();
                Rectangle plateBounds = plate.getBounds();

                //if (pizzaBounds.intersects(plateBounds)) {
                if (plateBounds.contains(x, y)) {
                    trayWithPizza.removeMouseListener(this);
                    remove(trayWithPizza);

                    remove(plate);
                    //NewProduct pizzaOnPlate_ = new NewProduct("plate_pizza", false,
                    //    "img/plate_pizza.png", "img/plate_pizza.png",
                    //    "img/plate_pizza.png", "img/plate_pizza.png");
                    Tool pizzaOnPlate = new Tool("plate_pizza", false,
                            "img/plate_pizza.png",
                            "img/plate_pizza.png",
                            "img/plate_pizza_green.png");
                    //pizzaOnPlate.setBounds(platePos.x, platePos.y, pizzaOnPlate_.getPreferredSize().width, pizzaOnPlate_.getPreferredSize().height);
                    pizzaOnPlate.setBounds(plate.getBounds());
                    this.plate = pizzaOnPlate;
                    add(pizzaOnPlate);

                    //this.pizzaOnPlate = true;
                    state = State.PIZZA_ON_PLATE;
                    oil.addMouseListener(this);
                    oil.setDraggable(true);

                    repaint();
                }
            }
        }

        else if (source == oil /*&& pizzaOnPlate && !oilAdded && plate.getName().equals("plate_pizza")*/) {
            plate.setTargeted(false);

            //Rectangle oilBounds = oil.getBounds();
            Rectangle plateBounds = plate.getBounds();

            //if (oilBounds.intersects(plateBounds)) {
            if (plateBounds.contains(x, y)) {
                remove(plate);
                //NewProduct plateWithOil = new NewProduct("plate_oil", false,
                //    "img/plate_oil.png", "img/plate_oil.png",
                //    "img/plate_oil.png", "img/plate_oil.png");
                Tool plateWithOil = new Tool("plate_oil", false,
                        "img/plate_oil.png",
                        "img/plate_oil.png",
                        "img/plate_oil_green.png");
                plateWithOil.setBounds(plate.getBounds());
                add(plateWithOil);
                oil.removeMouseListener(this);
                oil.setDraggable(false);
                this.plate = plateWithOil;

                //oilAdded = true;
                state = State.OIL_ADDED;
                basil.addMouseListener(this);
                basil.setDraggable(true);
                //oil.setLocation(oilPos);
                oil.returnToOriginalLocation();

                repaint();
            }
        }

        if (source == basil /*&& oilAdded && !basilAdded && plate.getName().equals("plate_oil")*/) {
            plate.setTargeted(false);

            //Rectangle basilBounds = basil.getBounds();
            Rectangle plateBounds = plate.getBounds();

            //if (basilBounds.intersects(plateBounds)) {
            if (plateBounds.contains(x, y)) {
                remove(plate);
                //NewProduct plateWithBasil = new NewProduct("plate_green", false,
                //    "img/plate_green.png", "img/plate_green.png",
                //    "img/plate_green.png", "img/plate_green.png");
                Tool plateWithBasil = new Tool("plate_basil", false,
                        "img/plate_basil.png",
                        "img/plate_basil.png",
                        "img/plate_basil_green.png");
                //plateWithBasil.setBounds(platePos.x, platePos.y, plateWithBasil.getPreferredSize().width, plateWithBasil.getPreferredSize().height);
                plateWithBasil.setBounds(plate.getBounds());
                add(plateWithBasil);
                this.plate = plateWithBasil;
                //basilAdded = true;
                state = State.BASIL_ADDED;
                remove(basil);
                basil.removeMouseListener(this);
                knife.addMouseListener(this);
                knife.setDraggable(true);

                repaint();
            }
        }

        else if (source == knife /*&& basilAdded && !knifeUsed && plate.getName().equals("plate_green")*/) {
            plate.setTargeted(false);

            //Rectangle knifeBounds = knife.getBounds();
            Rectangle plateBounds = plate.getBounds();

            //if (knifeBounds.intersects(plateBounds)) {
            if (plateBounds.contains(x, y)) {
                remove(plate);
                //NewProduct finalPlate = new NewProduct("thatsALL", false,
                //    "img/thatsALL.png", "img/thatsALL.png",
                //    "img/thatsALL.png", "img/thatsALL.png");
                Tool finalPlate = new Tool("thatsALL", false,
                        "img/thatsALL.png",
                        "img/thatsALL.png",
                        "img/thatsALL.png");
                finalPlate.setBounds(plate.getBounds());
                add(finalPlate);
                this.plate = finalPlate;
                //knifeUsed = true;
                state = State.CUT;
                daleeArrow.setTargeted(true);
                knife.setLocation(knifePos);
                //gameEnded = true;
                //state = State.;
                endLabel.setVisible(true);
                repaint();
                // Создаем и добавляем слой Over
                /*Over overLayer = new Over(layersContainer); // Передайте ваш контейнер
                overLayer.setBounds(0, 0, getWidth(), getHeight());
                add(overLayer);
                overLayer.revalidate();
                overLayer.repaint();*/
                //if(gameEnded = true)
                {
                    MistakeCounter mistakeCounter = MistakeCounter.getSharedInstance();
                    int owenTemperature = temperaturePopup.getValue();
                    mistakeCounter.setOwenTemperature(owenTemperature);

                    //layersContainer.showLayer(LayersContainer.Layer.OVER);
                }
            }
        }
    }

    private final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    private final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    @Override
    public void mouseEntered(MouseEvent event) {
        Object source = event.getSource();
        if(source == daleeArrow) {
            boolean daleeAvailable = state == State.CUT;
            if(daleeAvailable) {
                daleeArrow.setCursor(HAND_CURSOR);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        Object source = event.getSource();
        if(source == daleeArrow) {
            boolean daleeAvailable = state == State.CUT;
            if(daleeAvailable) {
                daleeArrow.setCursor(DEFAULT_CURSOR);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {
        Object source = event.getSource();
        if (source == trayWithPizza) {
            if (state == State.RAW_PIZZA) {
               // System.out.println("pressed on trayWith raw Pizza");
                oven.setTargeted(true);
            }
            else if(state == State.COOKED_PIZZA) {
            //    System.out.println("pressed on trayWith cooked Pizza");
                plate.setTargeted(true);
            }
        }
        else if (source == oil) {
          //  System.out.println("pressed on oil");
            //if(state == State.PIZZA_ON_PLATE) {
            plate.setTargeted(true);
            //}
        }
        else if (source == basil) {
           // System.out.println("pressed on basil");
            //if(state == State.OIL_ADDED) {
            plate.setTargeted(true);
            //}
        }
        else if (source == knife) {
          //  System.out.println("pressed on knife");
            //if(state == State.BASIL_ADDED) {
            plate.setTargeted(true);
            //}
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Object source = event.getSource();
        if(source == daleeArrow) {
            boolean daleeAvailable = state == State.CUT;
            if(daleeAvailable) {
                layersContainer.showLayer(LayersContainer.Layer.OVER);
            }
        }
    }

    public static void main(String...args) {
        JFrame f = new JFrame();
        f.add(new Level_1_2());
        f.setSize(1360, 770);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}