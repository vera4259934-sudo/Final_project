package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Level_2 extends JLayeredPane implements MouseListener, ActionListener {

    // Иконки для стакана и фона
    //private final ImageIcon cupIcon;
    //private final ImageIcon backgroundIcon;


    // Компонент, отвечающий за генерацию и отображение ингредиентов напитка
    private Drunks generatedDrink;

    // Кнопка для генерации нового напитка
    private JButton generateButton;


    // public static  int mist;
    private Map<NewProduct, Integer> amountEntered = new HashMap<>();

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    private final ImageIcon cupIcon;

   // private final NewProduct cup;
    private final NewProduct ice;
    private final NewProduct milk;
    private final NewProduct sugar;
    private final NewProduct tea;
    private final NewProduct topioka;
    private final NewProduct vanil;
    private final NewProduct spoon;
    //private final NewProduct oil;
    private NewProduct cup;

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

    public Level_2() {

        setLayout(null);
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cafe_screen.png"));
        cupIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cup.png"));

        //---------------------------------------------------------------
        // Размер компонента Drunks.
        int drinkComponentWidth = 300;
        int drinkComponentHeight = 300;
        generatedDrink.setPreferredSize(new Dimension(drinkComponentWidth, drinkComponentHeight));

        // Позиция стакана.
        int cupX = (getWidth() - cupIcon.getIconWidth()) / 2;
        int cupY = getHeight() - cupIcon.getIconHeight() - 50;

        // Позиция напитка внутри стакана.
        // Используем фиксированные смещения, как требовалось.
        int drinkX = cupX + 15;
        int drinkY = cupY + 25;

        // Установка позиции и размера для Drunks.
        generatedDrink.setBounds(drinkX, drinkY, drinkComponentWidth, drinkComponentHeight);
        add(generatedDrink);
        //---------------------------------------------------------------


        ice = new NewProduct("ice", true,
                "img/kib.png", "img/ice_selected.png",
                "img/ice.png", "img/ice.png");
        milk = new NewProduct("milk", true,
                "img/milkFull.png", "img/milkFullSelected.png",
                "img/milkPartial.png", "img/milkPartialSelected.png");
        sugar = new NewProduct("sugar", true,
                "img/sugar.png", "img/sugar_selected.png",
                "img/sugar.png", "img/sugar_selected.png");
        tea = new NewProduct("tea", true,
                "img/tea.png", "img/tea_selected.png",
                "img/tea.png", "img/tea_selected.png");
        topioka = new NewProduct("topioka", true,
                "img/topioka.png", "img/topioka_selected.png",
                "img/topioka.png", "img/topioka_selected.png");
        vanil = new NewProduct("vanil", true,
                "img/vanil.png", "img/vanil_selected.png",
                "img/vanil.png", "img/vanil_selected.png");

        cup = new NewProduct("cup", false,
                "img/cup.png", "img/cup.png",
                "img/cup.png", "img/cup.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");


        productsWithQuantityPopup.add(ice);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(sugar);
        productsWithQuantityPopup.add(vanil);
        productsWithQuantityPopup.add(topioka);
        productsWithQuantityPopup.add(tea);

        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 200,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(ice, new QuantityPopup("flour", 10, 1000, 200,
                "img/flour_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(topioka, new QuantityPopup("eggs", 1, 6, 1,
                "img/egg_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(tea, new QuantityPopup("cheese", 1, 1000, 150,
                "img/cheese_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(sugar, new QuantityPopup("cheese2", 1, 1000, 125,
                "img/cheese2_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(vanil, new QuantityPopup("tomato", 1, 20, 1,
                "img/tomato_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);
        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        add(ice);
        ice.setBounds(50, 470, 250, 250);
        ice.addMouseListener(this);
        add(milk);
        milk.setBounds(200, 340, 170, 192);
        milk.addMouseListener(this);
        add(sugar);
        sugar.setBounds(950, 400, 300, 116);
        sugar.addMouseListener(this);
        add(topioka);
        topioka.setBounds(370, 390, 240, 150);
        topioka.addMouseListener(this);
        add(vanil);
        vanil.setBounds(725, 340, 400, 230);
        vanil.addMouseListener(this);
        add(tea);
        tea.setBounds(1150, 410, 222, 208);
        tea.addMouseListener(this);
        add(cup);
        cup.setBounds(1000, 10, 250, 300);//*********************************************************
        add(spoon);
        spoon.setBounds(830, 600, 160, 160);




        generatedDrink = new Drunks();
       // cupIcon.setImage(1000, 10, 250, 300); // Предварительная отрисовка стакана

        // Компонент generatedDrink размещается внутри стакана
        // Позиция и размер настроены так, чтобы имитировать заполнение стакана
        generatedDrink.setBounds(1000, 10, 250, 300); // Корректируйте по необходимости
        add(generatedDrink);
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

               /* if (product == cheese2) {
                    remove(board);
                    boardWithCheese = new NewProduct("board_cheese_1", false,
                            "img/board_cheese_1.png", "img/board_cheese_1.png",
                            "img/board_cheese_1.png", "img/board_cheese_1.png");
                    boardWithCheese.setBounds(board.getBounds());
                    add(boardWithCheese);
                    this.board = boardWithCheese;
                    repaint();
                }*/

                if (product == sugar) {
                    remove(cup);
                    NewProduct graterWithCheese = new NewProduct("grater_cheese", false,
                            "img/cup.png", "img/cup.png",
                            "img/cup.png", "img/cup.png");
                    graterWithCheese.setBounds(cup.getBounds());
                    add(graterWithCheese);
                    this.cup = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

               /* if (product == tomato) {
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
                }*/
                checkAndSwitchLevel();
                return;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        backgroundIcon.paintIcon(this, g, 0, 0);

        generatedDrink = new Drunks();
        // cupIcon.setImage(1000, 10, 250, 300); // Предварительная отрисовка стакана

        // Компонент generatedDrink размещается внутри стакана
        // Позиция и размер настроены так, чтобы имитировать заполнение стакана
        generatedDrink.setBounds(1000, 10, 250, 300); // Корректируйте по необходимости
        add(generatedDrink);
        generatedDrink.setPreferredSize(new Dimension(1000, 10));
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
        /*if (source == knife) {
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
        }*/

        if (source instanceof NewProduct) {
            NewProduct product = (NewProduct) source;
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if (source == ice && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(ice, x, y, event);
                eggsInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == tea && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(tea, x, y, event);
                flourInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == milk && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == vanil && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(vanil, x, y, event);
                return;
            }

            if (source == topioka && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topioka, x, y, event);
                return;
            }

            if (source == sugar && cup.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(sugar, x, y, event);
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
//*************************************************************************************
            int tomatoAmount = amountEntered.get(tea);
            mistakeCounter.setTomatoAmount(tomatoAmount);

            int eggsAmount = amountEntered.get(vanil);
            mistakeCounter.setEggAmount(eggsAmount);

            int cheese1Amount = amountEntered.get(sugar);
            mistakeCounter.setCheese1Amount(cheese1Amount);

            int cheese2Amount = amountEntered.get(topioka);
            mistakeCounter.setCheese2Amount(cheese2Amount);

            int flourAmount = amountEntered.get(ice);
            mistakeCounter.setFlourAmount(flourAmount);
            //****************************************************************************
              if (layersContainer != null) {
                layersContainer.showLayer(LayersContainer.Layer.LEVEL_1_1);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {}
}


