/*package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Level_2_2 extends JLayeredPane implements MouseListener, ActionListener {

    // Иконки для стакана и фона
    //private final ImageIcon cupIcon;
    //private final ImageIcon backgroundIcon;


    // Компонент, отвечающий за генерацию и отображение ингредиентов напитка
    // private Drunks generatedDrink;

    // Кнопка для генерации нового напитка
    //  private JButton generateButton;
    // Стек для хранения истории действий пользователя
    private Stack<Map<NewProduct, Integer>> history = new Stack<>();

    // public static  int mist;
    private Map<NewProduct, Integer> amountEntered = new HashMap<>();

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
    }

    private final Icon backgroundIcon;
    //private final ImageIcon cupIcon;
    Icon againButtonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/again.png"));
    JLabel playButton = new JLabel(againButtonIcon);


    // private final NewProduct cup;
    private final NewProduct ice;
    private final NewProduct ice2;
    private final NewProduct ice3;
    private final NewProduct trub1;
    private final NewProduct trub2;
    private final NewProduct trub3;
    private final NewProduct milk;
    private final NewProduct sugar;
    private final NewProduct tea;
    private final NewProduct tea2;
    private final NewProduct topioka;
    private final NewProduct topioka2;
    private final NewProduct topioka3;
    private final NewProduct vanil;
    private final NewProduct spoon;
    private final NewProduct random_ice;
    private final NewProduct random_topioka;
    private final NewProduct random_trub;
    private final NewProduct random_tea;
    //private final NewProduct oil;
    private NewProduct cup;
    private NewProduct cup1;

    private final Tool againArrow;
    private final Tool popitka;

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

    public Level_2_2() {

        setLayout(null);
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cafe_screen_1.png"));
        //cupIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cup.png"));



        Random random = new Random();


        ice = new NewProduct("ice", true,
                "img/star.png", "img/star.png",
                "img/star.png", "img/star.png");
        ice2 = new NewProduct("ice2", true,
                "img/kib.png", "img/kib.png",
                "img/kib.png", "img/kib.png");
        ice3= new NewProduct("ice3", true,
                "img/heart.png", "img/heart.png",
                "img/heart.png", "img/heart.png");
        trub1= new NewProduct("trub1", true,
                "img/trub1.png", "img/trub1.png",
                "img/trub1.png", "img/trub1.png");
        trub2= new NewProduct("trub2", true,
                "img/trub2.png", "img/trub2.png",
                "img/trub2.png", "img/trub2.png");
        trub3= new NewProduct("trub3", true,
                "img/trub3.png", "img/trub3.png",
                "img/trub3.png", "img/trub3.png");
        milk = new NewProduct("milk", true,
                "img/milkFull.png", "img/milkFullSelected.png",
                "img/milkPartial.png", "img/milkPartialSelected.png");
        sugar = new NewProduct("sugar", true,
                "img/sugar.png", "img/sugar_selected.png",
                "img/sugar.png", "img/sugar_selected.png");
        tea = new NewProduct("tea", true,
                "img/blackTea.png", "img/blackTea.png",
                "img/blackTea.png", "img/blackTea.png");
        tea2 = new NewProduct("tea", true,
                "img/greenTea.png", "img/greenTea.png",
                "img/greenTea.png", "img/greenTea.png");
        topioka = new NewProduct("topioka", true,
                "img/topioka_brown.png", "img/topioka_brown.png",
                "img/topioka_brown.png", "img/topioka_brown.png");
        topioka2 = new NewProduct("topioka2", true,
                "img/topioka_pink.png", "img/topioka_pink.png",
                "img/topioka_pink.png", "img/topioka_pink.png");
        topioka3 = new NewProduct("topioka3", true,
                "img/topioka_yellow.png", "img/topioka_yellow.png",
                "img/topioka_yellow.png", "img/topioka_yellow.png");
        vanil = new NewProduct("vanil", true,
                "img/vanil.png", "img/vanil_selected.png",
                "img/vanil.png", "img/vanil_selected.png");
        cup1 = new NewProduct("cup", false,
                "img/cup.png", "img/cup.png",
                "img/cup.png", "img/cup.png");

        cup = new NewProduct("cup", false,
                "img/cup.png", "img/cup.png",
                "img/cup.png", "img/cup.png");
        spoon = new NewProduct("spoon", true,
                "img/spoon.png", "img/spoon_selected.png",
                "img/spoon.png", "img/spoon_selected.png");
        int a = random.nextInt(3) + 1;
        int b = random.nextInt(3) + 1;
        int c =  random.nextInt(3) + 1;
        int d =  random.nextInt(2) + 1;
        //****************************RANDOM
        random_ice = new NewProduct("random_ice", false,
                "img/ice"+ a+ ".png", "img/ice"+a+ ".png",
                "img/ice"+a+ ".png", "img/ice"+ a+ ".png");
        random_topioka = new NewProduct("random_topioka", false,
                "img/topioka"+b+ ".png", "img/topioka"+b+ ".png",
                "img/topioka"+b+ ".png", "img/topioka"+b+ ".png");
        random_trub = new NewProduct("random_trub", false,
                "img/trubochka"+c+ ".png", "img/trubochka"+c+ ".png",
                "img/trubochka"+c+ ".png", "img/trubochka"+c+ ".png");
        random_tea = new NewProduct("random_trub", false,
                "img/tea"+d+ ".png", "img/tea"+d+ ".png",
                "img/tea"+d+ ".png", "img/tea"+d+ ".png");
        //****************************RANDOM


        productsWithQuantityPopup.add(ice);
        productsWithQuantityPopup.add(ice2);
        productsWithQuantityPopup.add(ice3);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(sugar);
        productsWithQuantityPopup.add(vanil);
        productsWithQuantityPopup.add(topioka);
        productsWithQuantityPopup.add(topioka2);
        productsWithQuantityPopup.add(topioka3);
        productsWithQuantityPopup.add(tea2);

        productsWithQuantityPopup.add(tea);

        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 200,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(sugar, new QuantityPopup("sugar", 10, 1000, 200,
                "img/sugar_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(ice, new QuantityPopup("flour", 10, 1000, 200,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(ice2, new QuantityPopup("flour", 10, 1000, 200,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(ice3, new QuantityPopup("flour", 10, 1000, 200,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));



        quantityPopupsMap.put(topioka, new QuantityPopup("eggs", 1, 6, 1,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));


        quantityPopupsMap.put(topioka2, new QuantityPopup("eggs", 1, 6, 1,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(topioka3, new QuantityPopup("eggs", 1, 6, 1,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(tea, new QuantityPopup("cheese", 1, 1000, 150,
                "img/tea_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(tea2, new QuantityPopup("cheese", 1, 1000, 150,
                "img/tea_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
/*
        quantityPopupsMap.put(sugar, new QuantityPopup("cheese2", 1, 1000, 125,
                "img/cheese2_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));*/
     /*   quantityPopupsMap.put(vanil, new QuantityPopup("tomato", 1, 20, 1,
                "img/vanil_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);


        againArrow = new Tool("again", false,
                "img/again.png",
                "img/again.png",
                "img/again.png");
        popitka = new Tool("popitka", false,
                "img/popitka2.png",
                "img/popitka2.png",
                "img/popitka2.png");
        againArrow.addMouseListener(this);


        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        add(ice);
        ice.setBounds(670, 120, 250, 250);
        ice.addMouseListener(this);
        add(ice2);
        ice2.setBounds(820, 120, 250, 250);
        ice2.addMouseListener(this);
        add(ice3);
        ice3.setBounds(957, 120, 250, 250);
        ice3.addMouseListener(this);
        add(milk);
        milk.setBounds(200, 340, 170, 192);
        milk.addMouseListener(this);
        add(sugar);
        sugar.setBounds(950, 400, 300, 116);
        sugar.addMouseListener(this);
        add(topioka);
        topioka.setBounds(670, 2, 240, 150);
        topioka.addMouseListener(this);

        add(trub1);
        trub1.setBounds(40, 173, 200, 16);
        trub1.addMouseListener(this);
        add(trub2);
        trub2.setBounds(60, 223, 200, 16);
        trub2.addMouseListener(this);
        add(trub3);
        trub3.setBounds(50, 198, 200, 16);
        trub3.addMouseListener(this);


        add(topioka2);
        topioka2.setBounds(820, 2, 240, 150);
        topioka2.addMouseListener(this);
        add(topioka3);
        topioka3.setBounds(957, 2, 240, 150);
        topioka3.addMouseListener(this);

        add(vanil);
        vanil.setBounds(725, 340, 400, 230);
        vanil.addMouseListener(this);
        add(tea);
        tea.setBounds(10, 262, 222, 208);
        tea.addMouseListener(this);
        add(tea2);
        tea2.setBounds(170, 260, 222, 208);
        tea2.addMouseListener(this);

        add(cup1);
        cup1.setBounds(500, 140, 250, 300);
        //----------------------------------------------RANDOM
        add(cup);
        cup.setBounds(1100, 10, 250, 300);
        add(random_ice);
        random_ice.setBounds(1100, 10, 250, 300);
        add(random_topioka);
        random_topioka.setBounds(1100, 10, 250, 300);
        add(random_trub);
        random_trub.setBounds(1100, 10, 250, 300);
        add(random_tea);
        random_tea.setBounds(1100, 10, 250, 300);
        //***********************************************RANDOM
        add(spoon);
        spoon.setBounds(830, 600, 160, 160);

        add(againArrow);
        againArrow.setBounds(0, 0,  70, 70);
        add(popitka);
        popitka.setBounds(80, 12, 100, 30);



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

           /*     if (product == sugar) {
                    remove(cup1);
                    NewProduct graterWithCheese = new NewProduct("grater_cheese", false,
                            "img/cup.png", "img/cup.png",
                            "img/cup.png", "img/cup.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == ice) {
                    //remove(cup1);
                    NewProduct graterWithCheese = new NewProduct("ice_in_cup", false,
                            "img/ice3.png", "img/ice3.png",
                            "img/ice3.png", "img/ice3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == ice2) {
                    //   eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("ice2_in_cup", false,
                            "img/ice1.png", "img/ice1.png",
                            "img/ice1.png", "img/ice1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    //  cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == ice3) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("ice3_in_cup", false,
                            "img/ice2.png", "img/ice2.png",
                            "img/ice2.png", "img/ice2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();

                }

                if (product == tea) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("tea1_in_cup", false,
                            "img/tea1.png", "img/tea1.png",
                            "img/tea1.png", "img/tea1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == tea2) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("tea2_in_cup", false,
                            "img/tea2.png", "img/tea2.png",
                            "img/tea2.png", "img/tea2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == topioka) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka1_in_cup", false,
                            "img/topioka1.png", "img/topioka1.png",
                            "img/topioka1.png", "img/topioka1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == topioka2) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka2_in_cup", false,
                            "img/topioka2.png", "img/topioka2.png",
                            "img/topioka2.png", "img/topioka2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }


                if (product == topioka3) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka3_in_cup", false,
                            "img/topioka3.png", "img/topioka3.png",
                            "img/topioka3.png", "img/topioka3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

//********ТРУБОЧКИ**************************************************************
                if (product == trub1) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub1_in_cup", false,
                            "img/trubochka1.png", "img/trubochka1.png",
                            "img/trubochka1.png", "img/trubochka1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == trub2) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub2_in_cup", false,
                            "img/trubochka2.png", "img/trubochka2.png",
                            "img/trubochka2.png", "img/trubochka2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == trub3) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub3_in_cup", false,
                            "img/trubochka3.png", "img/trubochka3.png",
                            "img/trubochka3.png", "img/trubochka3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

// ********ТРУБОЧКИ**************************************************************

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
    /*            checkAndSwitchLevel();
                return;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        backgroundIcon.paintIcon(this, g, 0, 0);

        //generatedDrink = new Drunks();
        // cupIcon.setImage(1000, 10, 250, 300); // Предварительная отрисовка стакана

        // Компонент generatedDrink размещается внутри стакана
        // Позиция и размер настроены так, чтобы имитировать заполнение стакана
        //  generatedDrink.setBounds(1000, 10, 250, 300); // Корректируйте по необходимости
        //  add(generatedDrink);
        //generatedDrink.setPreferredSize(new Dimension(1000, 10));
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

    /*    if (source instanceof NewProduct) {
            NewProduct product = (NewProduct) source;
            int x = event.getX() + product.getX();
            int y = event.getY() + product.getY();

            if (source == ice && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(ice, x, y, event);

                return;
            }

            if (source == ice2 && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(ice2, x, y, event);

                return;
            }

            if (source == ice3 && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(ice3, x, y, event);

                return;
            }

            if (source == tea && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(tea, x, y, event);

                return;
            }

            if (source == tea2 && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(tea2, x, y, event);

                return;
            }

            if (source == milk && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInDish = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == vanil && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(vanil, x, y, event);
                return;
            }

            if (source == topioka && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topioka, x, y, event);

                return;
            }

            if (source == topioka2 && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topioka2, x, y, event);

                return;
            }

            if (source == topioka3 && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topioka3, x, y, event);

                return;
            }

            if (source == sugar && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(sugar, x, y, event);
                return;
            }


            //********ТРУБОЧКИ**************************************************************
            if (source == trub1 && cup1.getBounds().contains(x, y) ) {
                // if (product == trub1) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub1_in_cup", false,
                        "img/trubochka1.png", "img/trubochka1.png",
                        "img/trubochka1.png", "img/trubochka1.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                cheeseInGrater = true;
                //checkAndSwitchLevel();
                repaint();
                //checkAndSwitchLevel();
            }
            //}

            if (source == trub2 && cup1.getBounds().contains(x, y) ) {
                // if (product == trub2) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub2_in_cup", false,
                        "img/trubochka2.png", "img/trubochka2.png",
                        "img/trubochka2.png", "img/trubochka2.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                cheeseInGrater = true;
                //checkAndSwitchLevel();
                repaint();
                //checkAndSwitchLevel();
            }

            if (source == trub3 && cup1.getBounds().contains(x, y) ) {
                //  if (product == trub3) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub3_in_cup", false,
                        "img/trubochka3.png", "img/trubochka3.png",
                        "img/trubochka3.png", "img/trubochka3.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                cheeseInGrater = true;
                //checkAndSwitchLevel();
                repaint();
                //checkAndSwitchLevel();
            }

// ********ТРУБОЧКИ**************************************************************


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
    public void mouseClicked(MouseEvent event) {
        Object source = event.getSource();
        if(source == againArrow) {

            layersContainer.showLayer(LayersContainer.Layer.LEVEL_2_3);

        }
    }
    /*@Override
    public void mouseClicked(MouseEvent event) {}*/
//****************************************************************************************************************************************************************************
    // Метод для добавления продукта в стакан (или выполнения другого действия)
/*    public void addProduct(NewProduct product, int quantity) {
        // Сохраняем текущее состояние перед добавлением
        saveCurrentState();
        // Добавляем продукт (реализация зависит от игровой логики)
        amountEntered.put(product, quantity);
        // Обновляем отображение (если необходимо)
        repaint();
    }

    // Метод для отмены последнего действия (шаг назад)
    public void performBackStep() {
        if (!history.isEmpty()) {
            // Восстанавливаем предыдущее состояние из стека
            amountEntered = history.pop();
            // Обновляем отображение
            repaint();
        }
    }

    // Метод для сохранения текущего состояния в стек истории
    private void saveCurrentState() {
        // Создаем копию текущего состояния amountEntered
        Map<NewProduct, Integer> currentState = new HashMap<>(amountEntered);
        history.push(currentState);
    }
    //*************************************************************************************************************************************************************************************
}*/


