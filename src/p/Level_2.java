package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Level_2 extends JLayeredPane implements MouseListener, ActionListener {

    private Stack<Map<NewProduct, Integer>> history = new Stack<>();

    private Map<NewProduct, Integer> amountEntered = new HashMap<>();

    private LayersContainer layersContainer;

    @Override
    public void addNotify() {
        super.addNotify();
        this.layersContainer = (LayersContainer) SwingUtilities.getAncestorOfClass(LayersContainer.class, this);
        if (layersContainer != null) {
            layersContainer.over_2.setMistakeCounter2(mistakeCounter2);
        }
    }

    private final Icon backgroundIcon;
    //private final ImageIcon cupIcon;
    Icon againButtonIcon = new ImageIcon(getClass().getClassLoader().getResource("img/again.png"));
    JLabel playButton = new JLabel(againButtonIcon);


    // private final NewProduct cup;
    private final NewProduct iceCubes;
    private final NewProduct iceHearts;
    private final NewProduct iceStars;
    private final NewProduct trubBlue;
    private final NewProduct trubRed;
    private final NewProduct trubGreen;
    private final NewProduct milk;
    private final NewProduct sugar;
    private final NewProduct teaBlack;
    private final NewProduct teaGreen;
    private final NewProduct topiokaBrown;
    private final NewProduct topiokaPink;
    private final NewProduct topiokaYellow;
    private final NewProduct vanil;
   //rivate final NewProduct spoon;
    private final NewProduct random_ice;
    private final NewProduct random_topioka;
    private final NewProduct random_trub;
    private final NewProduct random_tea;
    //private final NewProduct oil;
    private NewProduct cup;
    private NewProduct cup1;

    private final Tool againArrow;
    //private final Tool popitka;
    private final Tool daleeArrow;
    private boolean trubInCup = false;
    private NewProduct boardWithCheese;
    private NewProduct boardWithCutCheese;
    private boolean iceInCup = false;
    private boolean tapiokaInCup = false;
    private boolean teaInCup = false;
    private boolean milkInCup= false;
    private boolean sugarInCup = false;
    private boolean vanilInCup = false;
    private final Set<NewProduct> productsWithQuantityPopup = new HashSet<>();
    private final Map<NewProduct, QuantityPopup> quantityPopups;
    boolean quantityPopupShown;
    private final MistakeCounter2 mistakeCounter2;

    public Level_2() {

        setLayout(null);
        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cafe_screen_1.png"));
        //cupIcon = new ImageIcon(getClass().getClassLoader().getResource("img/cup.png"));



        Random random = new Random();


        iceStars = new NewProduct("ice", true,
                "img/star.png", "img/star_selected.png",
                "img/star.png", "img/star_selected.png");
        iceCubes = new NewProduct("ice2", true,
                "img/kib.png", "img/kib_selected.png",
                "img/kib.png", "img/kib_selected.png");
        iceHearts = new NewProduct("ice3", true,
                "img/heart.png", "img/heart_selected.png",
                "img/heart.png", "img/heart_selected.png");
        trubBlue = new NewProduct("trub1", true,
                "img/trub1.png", "img/trub1.png",
                "img/trub1.png", "img/trub1.png");
        trubRed = new NewProduct("trub2", true,
                "img/trub2.png", "img/trub2.png",
                "img/trub2.png", "img/trub2.png");
        trubGreen = new NewProduct("trub3", true,
                "img/trub3.png", "img/trub3.png",
                "img/trub3.png", "img/trub3.png");
        milk = new NewProduct("milk", true,
                "img/milk.png", "img/milk_selected.png",
                "img/milkPartial.png", "img/milk1s3_selected.png");
        sugar = new NewProduct("sugar", true,
                "img/sugar.png", "img/sugar_selected.png",
                "img/Partical_sugar.png", "img/Partical_sugar_selected.png");
        teaBlack = new NewProduct("tea", true,
                "img/blackTea.png", "img/blackTea_selected.png",
                "img/blackTea.png", "img/blackTea_selected.png");
        teaGreen = new NewProduct("tea", true,
                "img/greenTea.png", "img/greenTea_selected.png",
                "img/greenTea.png", "img/greenTea_selected.png");
        topiokaBrown = new NewProduct("topioka", true,
                "img/topioka_brown.png", "img/topioka_brown_selected.png",
                "img/topioka_brown.png", "img/topioka_brown_selected.png");
        topiokaPink = new NewProduct("topioka2", true,
                "img/topioka_pink.png", "img/topioka_pink_selected.png",
                "img/topioka_pink.png", "img/topioka_pink_selected.png");
        topiokaYellow = new NewProduct("topioka3", true,
                "img/topioka_yellow.png", "img/topioka_yellow_selected.png",
                "img/topioka_yellow.png", "img/topioka_yellow_selected.png");
        vanil = new NewProduct("vanil", true,
                "img/vanil.png", "img/vanil_selected.png",
                "img/Partical_vanile.png", "img/Partical_vanile_selected.png");
        cup1 = new NewProduct("cup", false,
                "img/cup.png", "img/cup.png",
                "img/cup.png", "img/cup.png");

        cup = new NewProduct("cup", false,
                "img/cup.png", "img/cup.png",
                "img/cup.png", "img/cup.png");

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
        Recipe_2.IceKind iceKind = Recipe_2.IceKind.findByCode(a);
        System.out.println(String.format("ice-kind: %d -> %s", a, iceKind));

        Recipe_2.TapiokaKind tapiokaKind = Recipe_2.TapiokaKind.findByCode(b);
        System.out.println(String.format("tapioka-kind: %d -> %s", b, tapiokaKind));

        Recipe_2.TrubKind trubKind = Recipe_2.TrubKind.findByCode(c);
        System.out.println(String.format("trub-kind: %d -> %s", c, trubKind));

        Recipe_2.TeaKind teaKind = Recipe_2.TeaKind.findByCode(d);
        System.out.println(String.format("tea-kind: %d -> %s", d, teaKind));

        mistakeCounter2 = new MistakeCounter2(iceKind, teaKind, tapiokaKind, trubKind);

        productsWithQuantityPopup.add(iceCubes);
        productsWithQuantityPopup.add(iceHearts);
        productsWithQuantityPopup.add(iceStars);
        productsWithQuantityPopup.add(milk);
        productsWithQuantityPopup.add(sugar);
        productsWithQuantityPopup.add(vanil);
        productsWithQuantityPopup.add(topiokaBrown);
        productsWithQuantityPopup.add(topiokaPink);
        productsWithQuantityPopup.add(topiokaYellow);
        productsWithQuantityPopup.add(teaGreen);

        productsWithQuantityPopup.add(teaBlack);

        Map<NewProduct, QuantityPopup> quantityPopupsMap = new HashMap<>();
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 230,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(sugar, new QuantityPopup("sugar", 0, 5, 3,
                "img/sugar_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(iceCubes, new QuantityPopup("ice", 0, 10, 4,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(iceHearts, new QuantityPopup("ice2", 0, 10, 3,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(iceStars, new QuantityPopup("ice3", 0, 10, 1,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(topiokaBrown, new QuantityPopup("topioka", 0, 200, 104,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));


        quantityPopupsMap.put(topiokaPink, new QuantityPopup("topioka2", 0, 200, 109,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(topiokaYellow, new QuantityPopup("topioka3", 0, 200, 111,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(teaBlack, new QuantityPopup("tea", 0, 5, 2,
                "img/tea_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(teaGreen, new QuantityPopup("tea2", 0, 5, 3,
                "img/tea_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(vanil, new QuantityPopup("vanilla", 0, 5, 2,
                "img/vanil_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        this.quantityPopups = Collections.unmodifiableMap(quantityPopupsMap);

        againArrow = new Tool("again", false,
                "img/again.png",
                "img/again.png",
                "img/again.png");
//        popitka = new Tool("popitka", false,
//            "img/popitka1.png",
//            "img/popitka1.png",
//            "img/popitka1.png");
        againArrow.addMouseListener(this);

        daleeArrow = new Tool("dalee", false,
                "img/dalee2_bw.png",
                "img/dalee2_bw.png",
                "img/dalee2.png");
        daleeArrow.addMouseListener(this);

        add(daleeArrow);
        daleeArrow.setBounds(1100, 650, 150, 71);

        setPreferredSize(new Dimension(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight()));

        add(iceCubes);
        iceCubes.setBounds(670, 120, 250, 250);
        iceCubes.addMouseListener(this);
        add(iceHearts);
        iceHearts.setBounds(820, 120, 250, 250);
        iceHearts.addMouseListener(this);
        add(iceStars);
        iceStars.setBounds(957, 120, 250, 250);
        iceStars.addMouseListener(this);
        add(milk);
        milk.setBounds(785, 270, 170, 192);
        milk.addMouseListener(this);
        add(sugar);
        sugar.setBounds(950, 355, 300, 116);
        sugar.addMouseListener(this);
        add(topiokaBrown);
        topiokaBrown.setBounds(670, 2, 240, 150);
        topiokaBrown.addMouseListener(this);

        add(trubBlue);
        trubBlue.setBounds(40, 173, 200, 16);
        trubBlue.addMouseListener(this);
        add(trubRed);
        trubRed.setBounds(60, 223, 200, 16);
        trubRed.addMouseListener(this);
        add(trubGreen);
        trubGreen.setBounds(50, 198, 200, 16);
        trubGreen.addMouseListener(this);


        add(topiokaPink);
        topiokaPink.setBounds(820, 2, 240, 150);
        topiokaPink.addMouseListener(this);
        add(topiokaYellow);
        topiokaYellow.setBounds(957, 2, 240, 150);
        topiokaYellow.addMouseListener(this);

        add(vanil);
        vanil.setBounds(275, 245, 400, 230);
        vanil.addMouseListener(this);
        add(teaBlack);
        teaBlack.setBounds(10, 262, 222, 208);
        teaBlack.addMouseListener(this);
        add(teaGreen);
        teaGreen.setBounds(170, 260, 222, 208);
        teaGreen.addMouseListener(this);

        add(cup1);
        cup1.setBounds(500, 180, 250, 300);
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


        add(againArrow);
        againArrow.setBounds(0, 0,  70, 70);
        //add(popitka);
        //popitka.setBounds(80, 12, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        //System.out.println("actionPerformed from " + source);
        for (Map.Entry<NewProduct, QuantityPopup> entry : quantityPopups.entrySet()) {
            NewProduct product = entry.getKey();
            QuantityPopup quantityPopup = entry.getValue();
            if (source == quantityPopup) {
                remove(quantityPopup);
                quantityPopupShown = false;
                repaint();
                product.setPartialSize();
                product.returnToOriginalLocation();
                //System.out.println(product.getName() + ": " + quantityPopup.getValue());
                int newAmount;
                if(amountEntered.containsKey(product)) {
                    newAmount = amountEntered.get(product);
                    newAmount += quantityPopup.getValue();
                }
                else {
                    newAmount = quantityPopup.getValue();
                }
                amountEntered.put(product, newAmount);
                //System.out.println("\t" + product.getName() + ": " + quantityPopup.getValue());

                if (product == sugar) {
                 // remove(cup1);
                    NewProduct graterWithCheese = new NewProduct("grater_cheese", false,
                            "img/cup.png", "img/cup.png",
                            "img/cup.png", "img/cup.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    sugarInCup=true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == iceCubes) {
                    //remove(cup1);
                    NewProduct graterWithCheese = new NewProduct("ice_in_cup", false,
                            "img/ice1.png", "img/ice1.png",
                            "img/ice1.png", "img/ice1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    iceInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                }

                if (product == iceHearts) {
                    //   eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("ice2_in_cup", false,
                            "img/ice2.png", "img/ice2.png",
                            "img/ice2.png", "img/ice2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    //  cheeseInGrater = true;
                    //checkAndSwitchLevel();
                    iceInCup = true;
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == iceStars) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("ice3_in_cup", false,
                            "img/ice3.png", "img/ice3.png",
                            "img/ice3.png", "img/ice3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    iceInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();

                }

                if (product == teaBlack) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("tea1_in_cup", false,
                            "img/tea1.png", "img/tea1.png",
                            "img/tea1.png", "img/tea1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    teaInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == teaGreen) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("tea2_in_cup", false,
                            "img/tea2.png", "img/tea2.png",
                            "img/tea2.png", "img/tea2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    teaInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == topiokaBrown) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka1_in_cup", false,
                            "img/topioka1.png", "img/topioka1.png",
                            "img/topioka1.png", "img/topioka1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    tapiokaInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == topiokaPink) {
                    //  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka2_in_cup", false,
                            "img/topioka2.png", "img/topioka2.png",
                            "img/topioka2.png", "img/topioka2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    tapiokaInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }


                if (product == topiokaYellow) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("topioka3_in_cup", false,
                            "img/topioka3.png", "img/topioka3.png",
                            "img/topioka3.png", "img/topioka3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    tapiokaInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

//********ТРУБОЧКИ**************************************************************
                if (product == trubBlue) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub1_in_cup", false,
                            "img/trubochka1.png", "img/trubochka1.png",
                            "img/trubochka1.png", "img/trubochka1.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    trubInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == trubRed) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub2_in_cup", false,
                            "img/trubochka2.png", "img/trubochka2.png",
                            "img/trubochka2.png", "img/trubochka2.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    trubInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

                if (product == trubGreen) {
//  eggsInDish = true;
                    NewProduct graterWithCheese = new NewProduct("trub3_in_cup", false,
                            "img/trubochka3.png", "img/trubochka3.png",
                            "img/trubochka3.png", "img/trubochka3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    trubInCup = true;
                    //checkAndSwitchLevel();
                    repaint();
                    //checkAndSwitchLevel();
                }

// ********ТРУБОЧКИ**************************************************************

                checkDaleeAvailable();
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

    private final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    private final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);

    @Override
    public void mouseEntered(MouseEvent event) {
        Object source = event.getSource();
        if(source == daleeArrow) {
            //System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
            //    iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
            boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
            if(daleeAvailable) {
                daleeArrow.setCursor(HAND_CURSOR);
            }
        }
        else if(source == againArrow) {
            againArrow.setCursor(HAND_CURSOR);
        }
    }

    @Override
    public void mouseExited(MouseEvent event) {
        Object source = event.getSource();
        if(source == daleeArrow) {
            //System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
            //    iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
            boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
            if(daleeAvailable) {
                daleeArrow.setCursor(DEFAULT_CURSOR);
            }
        }
        else if(source == againArrow) {
            againArrow.setCursor(DEFAULT_CURSOR);
        }
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        Object source = event.getSource();
        //System.out.println("mouseReleased from " + source);
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

            if (source == iceCubes && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(iceCubes, x, y, event);

                return;
            }

            if (source == iceHearts && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(iceHearts, x, y, event);

                return;
            }

            if (source == iceStars && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(iceStars, x, y, event);

                return;
            }

            if (source == teaBlack && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(teaBlack, x, y, event);

                return;
            }

            if (source == teaGreen && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(teaGreen, x, y, event);

                return;
            }

            if (source == milk && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(milk, x, y, event);
                milkInCup = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == vanil && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(vanil, x, y, event);
                vanilInCup = true;
                return;
            }

            if (source == topiokaBrown && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topiokaBrown, x, y, event);

                return;
            }

            if (source == topiokaPink && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topiokaPink, x, y, event);

                return;
            }

            if (source == topiokaYellow && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(topiokaYellow, x, y, event);

                return;
            }

            if (source == sugar && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(sugar, x, y, event);
                sugarInCup = true;
                return;
            }


            //********ТРУБОЧКИ**************************************************************
            if (source == trubBlue && cup1.getBounds().contains(x, y) ) {
                // if (product == trub1) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub1_in_cup", false,
                        "img/trubochka1.png", "img/trubochka1.png",
                        "img/trubochka1.png", "img/trubochka1.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                trubInCup = true;
                amountEntered.put(trubBlue, 1);
                checkDaleeAvailable();
                repaint();
            }
            //}

            if (source == trubRed && cup1.getBounds().contains(x, y) ) {
                // if (product == trub2) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub2_in_cup", false,
                        "img/trubochka2.png", "img/trubochka2.png",
                        "img/trubochka2.png", "img/trubochka2.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                trubInCup = true;
                amountEntered.put(trubRed, 1);
                checkDaleeAvailable();
                repaint();
            }

            if (source == trubGreen && cup1.getBounds().contains(x, y) ) {
                //  if (product == trub3) {
//  eggsInDish = true;
                NewProduct graterWithCheese = new NewProduct("trub3_in_cup", false,
                        "img/trubochka3.png", "img/trubochka3.png",
                        "img/trubochka3.png", "img/trubochka3.png");
                graterWithCheese.setBounds(cup1.getBounds());
                add(graterWithCheese);
                this.cup1 = graterWithCheese;
                trubInCup = true;
                amountEntered.put(trubGreen, 1);
                checkDaleeAvailable();
                repaint();
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

    private void checkDaleeAvailable() {
        //System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
        //    iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
        boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
        if (daleeAvailable) {
            if(amountEntered.containsKey(iceCubes)) {
                int iceAmount = amountEntered.get(iceCubes);
                mistakeCounter2.setIceAmount(iceAmount, Recipe_2.IceKind.ICE_CUBES);
            }

            if(amountEntered.containsKey(iceHearts)) {
                int ice2Amount = amountEntered.get(iceHearts);
                mistakeCounter2.setIceAmount(ice2Amount, Recipe_2.IceKind.ICE_HEARTS);
            }

            if(amountEntered.containsKey(iceStars)) {
                int ice3Amount = amountEntered.get(iceStars);
                mistakeCounter2.setIceAmount(ice3Amount, Recipe_2.IceKind.ICE_STARS);
            }

            if(amountEntered.containsKey(teaBlack)) {
                int teaAmount = amountEntered.get(teaBlack);
                mistakeCounter2.setTeaAmount(teaAmount, Recipe_2.TeaKind.BLACK_TEA);
            }

            if(amountEntered.containsKey(teaGreen)) {
                int tea2Amount = amountEntered.get(teaGreen);
                mistakeCounter2.setTeaAmount(tea2Amount, Recipe_2.TeaKind.GREEN_TEA);
            }

            if(amountEntered.containsKey(topiokaBrown)) {
                int tapiokaAmount = amountEntered.get(topiokaBrown);
                mistakeCounter2.setTapiokaAmount(tapiokaAmount, Recipe_2.TapiokaKind.TAPIOKA_BROWN);
            }

            if(amountEntered.containsKey(topiokaPink)) {
                int tapioka2Amount = amountEntered.get(topiokaPink);
                mistakeCounter2.setTapiokaAmount(tapioka2Amount, Recipe_2.TapiokaKind.TAPIOKA_PINK);
            }

            if(amountEntered.containsKey(topiokaYellow)) {
                int tapioka3Amount = amountEntered.get(topiokaYellow);
                mistakeCounter2.setTapiokaAmount(tapioka3Amount, Recipe_2.TapiokaKind.TAPIOKA_YELLOW);
            }

            int vanillaAmount = amountEntered.get(vanil);
            mistakeCounter2.setVanillaAmount(vanillaAmount);

            int sugarAmount = amountEntered.get(sugar);
            mistakeCounter2.setSugarAmount(sugarAmount);

            int milkAmount = amountEntered.get(milk);
            mistakeCounter2.setMilkAmount(milkAmount);

            if(amountEntered.containsKey(trubBlue)) {
                int trub1Amount = amountEntered.get(trubBlue);
                mistakeCounter2.setTrubAmount(trub1Amount, Recipe_2.TrubKind.TRUB_BLUE);
            }

            if(amountEntered.containsKey(trubRed)) {
                int trub2Amount = amountEntered.get(trubRed);
                mistakeCounter2.setTrubAmount(trub2Amount, Recipe_2.TrubKind.TRUB_RED);
            }

            if(amountEntered.containsKey(trubGreen)) {
                int trub3Amount = amountEntered.get(trubGreen);
                mistakeCounter2.setTrubAmount(trub3Amount, Recipe_2.TrubKind.TRUB_GREEN);
            }

            daleeArrow.setTargeted(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Object source = event.getSource();
        if(source == againArrow) {
            //System.out.println("Reset!");
            layersContainer.remove(this);
            layersContainer.add(new Level_2(), String.valueOf(LayersContainer.Layer.LEVEL_2));
            layersContainer.showLayer(LayersContainer.Layer.LEVEL_2);
        }
        else if(source == daleeArrow) {
            //System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
            //    iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
            boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
            if (daleeAvailable) {
                //System.out.println(mistakeCounter2);
                layersContainer.showLayer(LayersContainer.Layer.OVER_2);
            }
        }
    }

    /*@Override
    public void mouseClicked(MouseEvent event) {}*/
//****************************************************************************************************************************************************************************
    // Метод для добавления продукта в стакан (или выполнения другого действия)
    public void addProduct(NewProduct product, int quantity) {
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

    public static void main(String...args) {
        JFrame f = new JFrame();
        f.add(new Level_2());
        f.setSize(1360, 770);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}