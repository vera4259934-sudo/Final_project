package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Level_1 extends JLayeredPane implements MouseListener, ActionListener {

    public static  int mist;
    private Map<NewProduct, Integer> amountEntered = new HashMap<>();

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private final NewProduct flour;
    private final NewProduct milk;
    private final NewProduct eggs;
    private final NewProduct cheese;
    private final NewProduct cheese2;
    private final NewProduct tomato;
    private final NewProduct ketchup;
    private final NewProduct oil;
    private Tool mixer;
    private Tool board;
    private Tool grater;
    private NewProduct dish;
    private NewProduct spoon;
    private NewProduct knife;
    //private NewProduct boardWithCheese;
    private Tool boardWithCheese;
    //private NewProduct boardWithCutCheese;
    private Tool boardWithCutCheese;
    private boolean tomatoInMixer = false;
    private boolean cheeseCutOnBoard = false;
    private boolean cheeseInGrater = false;
    private boolean milkInDish = false;
    private boolean eggsInDish = false;
    private boolean flourInDish = false;
    private final Set<NewProduct> productsWithQuantityPopup = new HashSet<>();
    private final Map<NewProduct, QuantityPopup> quantityPopups;
    boolean quantityPopupShown;

    public Level_1() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/level1background.png"));

        flour = new NewProduct("flour", true,
                "img/flour.png", "img/flour_selected.png",
                "img/flourPartial.png", "img/flourPartial_selected.png");
        milk = new NewProduct("milk", true,
                "img/milkFull.png", "img/milkFullSelected.png",
                "img/milkPartial.png", "img/milkPartialSelected.png");
        eggs = new NewProduct("eggs", true,
                "img/egg.png", "img/egg_selected.png",
                "img/eggPartical.png", "img/eggPartical_selected.png");
        cheese = new NewProduct("cheese", true,
                "img/cheese_1.png", "img/cheese_1_selected.png",
                "img/cheese_1.png", "img/cheese_1_selected.png");
        cheese2 = new NewProduct("cheese2", true,
                "img/cheese_2.png", "img/cheese_2_selected.png",
                "img/cheese_2.png", "img/cheese_2_selected.png");
        tomato = new NewProduct("tomato", true,
                "img/tomato.png", "img/tomato_selected.png",
                "img/tomato.png", "img/tomato_selected.png");
        ketchup = new NewProduct("ketchup", true,
                "img/ketchup.png", "img/ketchup_selected.png",
                "img/ketchup.png", "img/ketchup_selected.png");
        oil = new NewProduct("oliveOil", true,
                "img/oil.png", "img/oil_selected.png",
                "img/oil.png", "img/oil_selected.png");
        //mixer = new NewProduct("mixer", false,
        //        "img/mixer.png", "img/mixer.png",
        //        "img/mixer.png", "img/mixer.png");
        mixer = new Tool("mixer", false,
                "img/mixer.png",
                "img/mixer.png",
                "img/mixer_green.png");
        //board = new NewProduct("board", false,
        //        "img/board.png", "img/board.png",
        //        "img/board.png", "img/board.png");
        board = new Tool("board", false,
                "img/board.png",
                "img/board_selected.png",
                "img/board_green.png");
        dish = new NewProduct("dish", false,
                "img/dish.png", "img/dish.png",
                "img/dish.png", "img/dish.png");
        //grater = new NewProduct("grater", false,
        //        "img/grater.png", "img/grater.png",
        //        "img/grater.png", "img/grater.png");
        grater = new Tool("grater", false,
                "img/grater.png",
                "img/grater_selected.png",
                "img/grater_green.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");
        knife = new NewProduct("knife", true,
                "img/kneef.png", "img/kneef_selected.png",
                "img/kneef.png", "img/kneef_selected.png");

        productsWithQuantityPopup.add(flour);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(eggs);
        productsWithQuantityPopup.add(cheese);
        productsWithQuantityPopup.add(cheese2);
        productsWithQuantityPopup.add(tomato);

        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 200,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(flour, new QuantityPopup("flour", 10, 1000, 200,
                "img/flour_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(eggs, new QuantityPopup("eggs", 1, 6, 1,
                "img/egg_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(cheese, new QuantityPopup("cheese", 1, 1000, 150,
                "img/cheese_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(cheese2, new QuantityPopup("cheese2", 1, 1000, 125,
                "img/cheese2_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(tomato, new QuantityPopup("tomato", 1, 20, 1,
                "img/tomato_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);
        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        add(flour);
        flour.setBounds(50, 470, 250, 250);
        flour.addMouseListener(this);
        add(milk);
        milk.setBounds(230, 360, 170, 192);
        milk.addMouseListener(this);
        add(eggs);
        eggs.setBounds(950, 400, 300, 116);
        eggs.addMouseListener(this);
        add(cheese);
        cheese.setBounds(370, 390, 200, 108);
        cheese.addMouseListener(this);
        add(cheese2);
        cheese2.setBounds(725, 390, 170, 150);
        cheese2.addMouseListener(this);
        add(tomato);
        tomato.setBounds(1150, 370, 222, 208);
        tomato.addMouseListener(this);
        add(ketchup);
        ketchup.setBounds(500, 310, 155, 220);
        add(oil);
        oil.setBounds(550, 290, 220, 226);
        add(mixer);
        mixer.setBounds(25, 300, 220, 290);
        add(board);
        board.setBounds(1000, 570, 370, 230);
        add(grater);
        grater.setBounds(750, 300, 370, 230);
        add(dish);
        dish.setBounds(540, 505, 245, 230);
        add(spoon);
        spoon.setBounds(830, 600, 160, 160);
        add(knife);
        knife.setBounds(305, 630, 260, 50);
        knife.addMouseListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        System.out.println("actionPerformed from " + source);
        for (Map.Entry<NewProduct, QuantityPopup> entry : quantityPopups.entrySet()) {
            NewProduct product = entry.getKey();
            QuantityPopup quantityPopup = entry.getValue();
            if (source == quantityPopup) {
                remove(quantityPopup);
                quantityPopupShown = false;
                repaint();
                product.setPartialSize();
                product.returnToOriginalLocation();
                System.out.println(product.getName() + ": " + quantityPopup.getValue());
                int newAmount;
                if(amountEntered.containsKey(product)) {
                    newAmount = amountEntered.get(product);
                    newAmount += quantityPopup.getValue();
                }
                else {
                    newAmount = quantityPopup.getValue();
                }
                amountEntered.put(product, newAmount);
                System.out.println("\t" + product.getName() + ": " + quantityPopup.getValue());

                if (product == cheese2) {
                    remove(board);
                    boardWithCheese = new Tool("board_cheese_1", false,
                            "img/board_cheese_1.png",
                            "img/board_cheese_1.png",
                            "img/board"); //*******************
                    boardWithCheese.setBounds(board.getBounds());
                    add(boardWithCheese);
                    this.board = boardWithCheese;
                    repaint();
                }

                if (product == cheese) {
                    remove(grater);
                    Tool graterWithCheese = new Tool("grater_cheese", false,
                            "img/grater_cheese.png",
                            "img/grater_cheese_selected.png",
                            "img/grater_cheese_selected.png");//*****************************
                    graterWithCheese.setBounds(grater.getBounds());
                    add(graterWithCheese);
                    this.grater = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == tomato) {
                    remove(mixer);
                    Tool mixerWithTomato = new Tool("mixer_tomato", false,
                            "img/mixer_tomato.png",
                            "img/mixer_tomato_selected.png",
                            "img/mixer_tomato_green.png");
                    mixerWithTomato.setBounds(mixer.getBounds());
                    add(mixerWithTomato);
                    this.mixer = mixerWithTomato;
                    tomatoInMixer = true;
                    //checkAndSwitchLevel();
                    repaint();
                }
                checkAndSwitchLevel();
                return;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        Object source = event.getSource();
        if (source == tomato) {
            mixer.setTargeted(true);
        }
        else if (source == cheese) {
            grater.setTargeted(true);
        }
        else if (source == cheese2) {
            board.setTargeted(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();
        System.out.println("mouseReleased from " + source);
        if (source == knife) {
            if (boardWithCheese != null && boardWithCheese.getName().equals("board_cheese_1")) {
                Rectangle knifeBounds = knife.getBounds();
                Rectangle boardBounds = boardWithCheese.getBounds();

                if (knifeBounds.intersects(boardBounds)) {
                    remove(boardWithCheese);
                    boardWithCutCheese = new Tool("board_cheese_12", false,
                            "img/board_cheese_12.png",
                            "img/board_cheese_12_selected.png",
                            "img/board_cheese_12_green.png");
                    boardWithCutCheese.setBounds(boardBounds);
                    add(boardWithCutCheese);
                    this.board = boardWithCutCheese;
                    this.boardWithCheese = null;
                    cheeseCutOnBoard = true;
                    checkAndSwitchLevel();
                    repaint();
                } else {
                    knife.returnToOriginalLocation();
                    repaint();
                }
            } else {
                knife.returnToOriginalLocation();
                repaint();
            }
            return;
        }

        if (source instanceof NewProduct) {
            NewProduct product = (NewProduct) source;
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if (source == eggs && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(eggs, x, y, event);
                eggsInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == flour && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(flour, x, y, event);
                flourInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == milk && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == tomato) {
                mixer.setTargeted(false);
                if(mixer.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                    showQuantityPopup(tomato, x, y, event);
                    return;
                }
            }

            if (source == cheese) {
                grater.setTargeted(false);
                if(grater.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                    showQuantityPopup(cheese, x, y, event);
                    return;
                }
            }

            if (source == cheese2) {
                board.setTargeted(false);
                if(board.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                    showQuantityPopup(cheese2, x, y, event);
                    return;
                }
            }
        }
    }

    private void showQuantityPopup(NewProduct product, int x, int y, MouseEvent event) {
        if (!quantityPopupShown) {
            QuantityPopup quantityPopup = quantityPopups.get(product);
            quantityPopup.setLocation(300, 150);
            add(quantityPopup);
            moveToFront(quantityPopup);
            quantityPopupShown = true;
            event.consume();
        }
    }

    private void checkAndSwitchLevel() {
        MistakeCounter mistakeCounter = MistakeCounter.getSharedInstance();
        if (tomatoInMixer && cheeseCutOnBoard && cheeseInGrater && milkInDish && flourInDish && eggsInDish) {
            int milkAmount = amountEntered.get(milk);
            mistakeCounter.setMilkAmount(milkAmount);

            int tomatoAmount = amountEntered.get(tomato);
            mistakeCounter.setTomatoAmount(tomatoAmount);

            int eggsAmount = amountEntered.get(eggs);
            mistakeCounter.setEggAmount(eggsAmount);

            int cheese1Amount = amountEntered.get(cheese);
            mistakeCounter.setCheese1Amount(cheese1Amount);

            int cheese2Amount = amountEntered.get(cheese2);
            mistakeCounter.setCheese2Amount(cheese2Amount);

            int flourAmount = amountEntered.get(flour);
            mistakeCounter.setFlourAmount(flourAmount);
          /*
            int mistakes = 0;
            if(flourAmount == FLOUR_RECIPE_AMOUNT) {
                System.out.println("Flour alright!");
                            }
            else {
                mistakes+=1;
                System.out.println("Flour failure!");
            }

            if(tomatoAmount == TOMATO_RECIPE_AMOUNT) {
                System.out.println("Tomato alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Tomato failure!");
            }

            if(eggsAmount == EGGS_RECIPE_AMOUNT) {
                System.out.println("Eggs alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Eggs failure!");
            }

            if(cheese1Amount == CHEESE1_RECIPE_AMOUNT) {
                System.out.println("Cheese1 alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Cheese1 failure!");
            }

            if(cheese2Amount == CHEESE2_RECIPE_AMOUNT) {
                System.out.println("Cheese2 alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Cheese2 failure!");
            }

            if(milkAmount == MILK_RECIPE_AMOUNT) {
                System.out.println("Milk alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Milk failure!");
            }
            System.out.println("Всего совершено ошибок: " + mistakes);
            mist = mistakes;*/

           if (layersContainer != null) {
                layersContainer.showLayer(LayersContainer.Layer.LEVEL_1_1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {}
}





/*
package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Level_1 extends JLayeredPane implements MouseListener, ActionListener {

    public static  int mist;
    private Map<NewProduct, Integer> amountEntered = new HashMap<>();

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private final NewProduct flour;
    private final NewProduct milk;
    private final NewProduct eggs;
    private final NewProduct cheese;
    private final NewProduct cheese2;
    private final NewProduct tomato;
    private final NewProduct ketchup;
    private final NewProduct oil;
    private NewProduct mixer;
    private NewProduct board;
    private NewProduct grater;
    private NewProduct dish;
    private NewProduct spoon;
    private NewProduct knife;
    private NewProduct boardWithCheese;
    private NewProduct boardWithCutCheese;
    private boolean tomatoInMixer = false;
    private boolean cheeseCutOnBoard = false;
    private boolean cheeseInGrater = false;
    private boolean milkInDish = false;
    private boolean eggsInDish = false;
    private boolean flourInDish = false;
    private final Set<NewProduct> productsWithQuantityPopup = new HashSet<>();
    private final Map<NewProduct, QuantityPopup> quantityPopups;
    boolean quantityPopupShown;

    public Level_1() {
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/level1background.png"));

        flour = new NewProduct("flour", true,
                "img/flour.png", "img/flour_selected.png",
                "img/flourPartial.png", "img/flourPartial_selected.png");
        milk = new NewProduct("milk", true,
                "img/milkFull.png", "img/milkFullSelected.png",
                "img/milkPartial.png", "img/milkPartialSelected.png");
        eggs = new NewProduct("eggs", true,
                "img/egg.png", "img/egg_selected.png",
                "img/eggPartical.png", "img/eggPartical_selected.png");
        cheese = new NewProduct("cheese", true,
                "img/cheese_1.png", "img/cheese_1_selected.png",
                "img/cheese_1.png", "img/cheese_1_selected.png");
        cheese2 = new NewProduct("cheese2", true,
                "img/cheese_2.png", "img/cheese_2_selected.png",
                "img/cheese_2.png", "img/cheese_2_selected.png");
        tomato = new NewProduct("tomato", true,
                "img/tomato.png", "img/tomato_selected.png",
                "img/tomato.png", "img/tomato_selected.png");
        ketchup = new NewProduct("ketchup", true,
                "img/ketchup.png", "img/ketchup_selected.png",
                "img/ketchup.png", "img/ketchup_selected.png");
        oil = new NewProduct("oliveOil", true,
                "img/oil.png", "img/oil_selected.png",
                "img/oil.png", "img/oil_selected.png");
        mixer = new NewProduct("mixer", false,
                "img/mixer.png", "img/mixer.png",
                "img/mixer.png", "img/mixer.png");
        board = new NewProduct("board", false,
                "img/board.png", "img/board.png",
                "img/board.png", "img/board.png");
        dish = new NewProduct("dish", false,
                "img/dish.png", "img/dish.png",
                "img/dish.png", "img/dish.png");
        grater = new NewProduct("grater", false,
                "img/grater.png", "img/grater.png",
                "img/grater.png", "img/grater.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");
        knife = new NewProduct("knife", true,
                "img/kneef.png", "img/kneef_selected.png",
                "img/kneef.png", "img/kneef_selected.png");

        productsWithQuantityPopup.add(flour);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(eggs);
        productsWithQuantityPopup.add(cheese);
        productsWithQuantityPopup.add(cheese2);
        productsWithQuantityPopup.add(tomato);

        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 200,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(flour, new QuantityPopup("flour", 10, 1000, 200,
                "img/flour_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(eggs, new QuantityPopup("eggs", 1, 6, 1,
                "img/egg_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(cheese, new QuantityPopup("cheese", 1, 1000, 150,
                "img/cheese_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(cheese2, new QuantityPopup("cheese2", 1, 1000, 125,
                "img/cheese2_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(tomato, new QuantityPopup("tomato", 1, 20, 1,
                "img/tomato_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);
        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        add(flour);
        flour.setBounds(50, 470, 250, 250);
        flour.addMouseListener(this);
        add(milk);
        milk.setBounds(230, 360, 170, 192);
        milk.addMouseListener(this);
        add(eggs);
        eggs.setBounds(950, 400, 300, 116);
        eggs.addMouseListener(this);
        add(cheese);
        cheese.setBounds(370, 390, 200, 108);
        cheese.addMouseListener(this);
        add(cheese2);
        cheese2.setBounds(725, 390, 170, 150);
        cheese2.addMouseListener(this);
        add(tomato);
        tomato.setBounds(1150, 370, 222, 208);
        tomato.addMouseListener(this);
        add(ketchup);
        ketchup.setBounds(500, 310, 155, 220);
        add(oil);
        oil.setBounds(550, 290, 220, 226);
        add(mixer);
        mixer.setBounds(25, 300, 220, 290);
        add(board);
        board.setBounds(1000, 570, 370, 230);
        add(grater);
        grater.setBounds(750, 300, 370, 230);
        add(dish);
        dish.setBounds(540, 505, 245, 230);
        add(spoon);
        spoon.setBounds(830, 600, 160, 160);
        add(knife);
        knife.setBounds(305, 630, 260, 50);
        knife.addMouseListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        System.out.println("actionPerformed from " + source);
        for (Map.Entry<NewProduct, QuantityPopup> entry : quantityPopups.entrySet()) {
            NewProduct product = entry.getKey();
            QuantityPopup quantityPopup = entry.getValue();
            if (source == quantityPopup) {
                remove(quantityPopup);
                quantityPopupShown = false;
                repaint();
                product.setPartialSize();
                product.returnToOriginalLocation();
                System.out.println(product.getName() + ": " + quantityPopup.getValue());
                int newAmount;
                if(amountEntered.containsKey(product)) {
                    newAmount = amountEntered.get(product);
                    newAmount += quantityPopup.getValue();
                }
                else {
                    newAmount = quantityPopup.getValue();
                }
                amountEntered.put(product, newAmount);
                System.out.println("\t" + product.getName() + ": " + quantityPopup.getValue());

                if (product == cheese2) {
                    remove(board);
                    boardWithCheese = new NewProduct("board_cheese_1", false,
                            "img/board_cheese_1.png", "img/board_cheese_1.png",
                            "img/board_cheese_1.png", "img/board_cheese_1.png");
                    boardWithCheese.setBounds(board.getBounds());
                    add(boardWithCheese);
                    this.board = boardWithCheese;
                    repaint();
                }

                if (product == cheese) {
                    remove(grater);
                    NewProduct graterWithCheese = new NewProduct("grater_cheese", false,
                            "img/grater_cheese.png", "img/grater_cheese.png",
                            "img/grater_cheese.png", "img/grater_cheese.png");
                    graterWithCheese.setBounds(grater.getBounds());
                    add(graterWithCheese);
                    this.grater = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == tomato) {
                    remove(mixer);
                    NewProduct mixerWithTomato = new NewProduct("mixer_tomato", false,
                            "img/mixer_tomato.png", "img/mixer_tomato.png",
                            "img/mixer_tomato.png", "img/mixer_tomato.png");
                    mixerWithTomato.setBounds(mixer.getBounds());
                    add(mixerWithTomato);
                    this.mixer = mixerWithTomato;
                    tomatoInMixer = true;
                    //checkAndSwitchLevel();
                    repaint();
                }
                checkAndSwitchLevel();
                return;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();
        System.out.println("mouseReleased from " + source);
        if (source == knife) {
            if (boardWithCheese != null && boardWithCheese.getName().equals("board_cheese_1")) {
                Rectangle knifeBounds = knife.getBounds();
                Rectangle boardBounds = boardWithCheese.getBounds();

                if (knifeBounds.intersects(boardBounds)) {
                    remove(boardWithCheese);
                    boardWithCutCheese = new NewProduct("board_cheese_12", false,
                            "img/board_cheese_12.png", "img/board_cheese_12.png",
                            "img/board_cheese_12.png", "img/board_cheese_12.png");
                    boardWithCutCheese.setBounds(boardBounds);
                    add(boardWithCutCheese);
                    this.board = boardWithCutCheese;
                    this.boardWithCheese = null;
                    cheeseCutOnBoard = true;
                    checkAndSwitchLevel();
                    repaint();
                } else {
                    knife.returnToOriginalLocation();
                    repaint();
                }
            } else {
                knife.returnToOriginalLocation();
                repaint();
            }
            return;
        }

        if (source instanceof NewProduct) {
            NewProduct product = (NewProduct) source;
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if (source == eggs && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(eggs, x, y, event);
                eggsInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == flour && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(flour, x, y, event);
                flourInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == milk && dish.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == tomato && mixer.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(tomato, x, y, event);
                return;
            }

            if (source == cheese && grater.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(cheese, x, y, event);
                return;
            }

            if (source == cheese2 && board.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(cheese2, x, y, event);
                return;
            }
        }
    }

    private void showQuantityPopup(NewProduct product, int x, int y, MouseEvent event) {
        if (!quantityPopupShown) {
            QuantityPopup quantityPopup = quantityPopups.get(product);
            quantityPopup.setLocation(300, 150);
            add(quantityPopup);
            moveToFront(quantityPopup);
            quantityPopupShown = true;
            event.consume();
        }
    }

    private void checkAndSwitchLevel() {
        MistakeCounter mistakeCounter = MistakeCounter.getSharedInstance();
        if (tomatoInMixer && cheeseCutOnBoard && cheeseInGrater && milkInDish && flourInDish && eggsInDish) {
            int milkAmount = amountEntered.get(milk);
            mistakeCounter.setMilkAmount(milkAmount);

            int tomatoAmount = amountEntered.get(tomato);
            mistakeCounter.setTomatoAmount(tomatoAmount);

            int eggsAmount = amountEntered.get(eggs);
            mistakeCounter.setEggAmount(eggsAmount);

            int cheese1Amount = amountEntered.get(cheese);
            mistakeCounter.setCheese1Amount(cheese1Amount);

            int cheese2Amount = amountEntered.get(cheese2);
            mistakeCounter.setCheese2Amount(cheese2Amount);

            int flourAmount = amountEntered.get(flour);
            mistakeCounter.setFlourAmount(flourAmount);
            /*
            int mistakes = 0;
            if(flourAmount == FLOUR_RECIPE_AMOUNT) {
                System.out.println("Flour alright!");
                            }
            else {
                mistakes+=1;
                System.out.println("Flour failure!");
            }

            if(tomatoAmount == TOMATO_RECIPE_AMOUNT) {
                System.out.println("Tomato alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Tomato failure!");
            }

            if(eggsAmount == EGGS_RECIPE_AMOUNT) {
                System.out.println("Eggs alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Eggs failure!");
            }

            if(cheese1Amount == CHEESE1_RECIPE_AMOUNT) {
                System.out.println("Cheese1 alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Cheese1 failure!");
            }

            if(cheese2Amount == CHEESE2_RECIPE_AMOUNT) {
                System.out.println("Cheese2 alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Cheese2 failure!");
            }

            if(milkAmount == MILK_RECIPE_AMOUNT) {
                System.out.println("Milk alright!");
            }
            else {
                mistakes+=1;
                System.out.println("Milk failure!");
            }
            System.out.println("Всего совершено ошибок: " + mistakes);
            mist = mistakes;
             */
      /*      if (layersContainer != null) {
                layersContainer.showLayer(LayersContainer.Layer.LEVEL_1_1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {}
}*/