package p;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Level_2 extends JLayeredPane implements MouseListener, ActionListener {

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
        if (layersContainer != null) {
            layersContainer.over_2.setMistakeCounter2(mistakeCounter2);
        }
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
        Recipe_2.IceKind iceKind = null;
        switch (a) {
            case 1:
                iceKind = Recipe_2.IceKind.ICE1;
                break;
            case 2:
                iceKind = Recipe_2.IceKind.ICE2;
                break;
            case 3:
                iceKind = Recipe_2.IceKind.ICE3;
                break;
        }
        Recipe_2.TapiokaKind tapiokaKind = null;
        switch (b) {
            case 1:
                tapiokaKind = Recipe_2.TapiokaKind.TAPIOKA1;
                break;
            case 2:
                tapiokaKind = Recipe_2.TapiokaKind.TAPIOKA2;
                break;
            case 3:
                tapiokaKind = Recipe_2.TapiokaKind.TAPIOKA3;
                break;
        }
        Recipe_2.TrubKind trubKind = null;
        switch (c) {
            case 1:
                trubKind = Recipe_2.TrubKind.TRUB1;
                break;
            case 2:
                trubKind = Recipe_2.TrubKind.TRUB2;
                break;
            case 3:
                trubKind = Recipe_2.TrubKind.TRUB3;
                break;
        }
        Recipe_2.TeaKind teaKind = null;
        switch (c) {
            case 1:
                teaKind = Recipe_2.TeaKind.TEA1;
                break;
            case 2:
                teaKind = Recipe_2.TeaKind.TEA2;
                break;
        }
        mistakeCounter2 = new MistakeCounter2(iceKind, teaKind, tapiokaKind, trubKind);

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
        quantityPopupsMap.put(milk, new QuantityPopup("milk", 10, 1000, 230,
                "img/milk_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(sugar, new QuantityPopup("sugar", 0, 5, 1,
                "img/sugar_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(ice, new QuantityPopup("ice", 0, 10, 5,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(ice2, new QuantityPopup("ice2", 0, 10, 5,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(ice3, new QuantityPopup("ice3", 0, 10, 5,
                "img/ice_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(topioka, new QuantityPopup("topioka", 0, 200, 110,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));


        quantityPopupsMap.put(topioka2, new QuantityPopup("topioka2", 0, 200, 110,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));
        quantityPopupsMap.put(topioka3, new QuantityPopup("topioka3", 0, 200, 110,
                "img/tapioka_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(tea, new QuantityPopup("tea", 0, 5, 1,
                "img/tea_quantity.png",
                new Rectangle(329, 104, 362 - 329 + 1, 140 - 104 + 1),
                new Rectangle(28, 104, 54 - 28 + 1, 140 - 104 + 1),
                new Rectangle(177, 152, 219 - 177 + 1, 174 - 152 + 1), this));

        quantityPopupsMap.put(tea2, new QuantityPopup("tea2", 0, 5, 1,
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
        add(spoon);
        spoon.setBounds(830, 600, 160, 160);

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
          //    System.out.println(product.getName() + ": " + quantityPopup.getValue());
                int newAmount;
                if(amountEntered.containsKey(product)) {
                    newAmount = amountEntered.get(product);
                    newAmount += quantityPopup.getValue();
                }
                else {
                    newAmount = quantityPopup.getValue();
                }
                amountEntered.put(product, newAmount);
      //        System.out.println("\t" + product.getName() + ": " + quantityPopup.getValue());

                if (product == sugar) {
                    remove(cup1);
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

                if (product == ice) {
                    //remove(cup1);
                    NewProduct graterWithCheese = new NewProduct("ice_in_cup", false,
                            "img/ice3.png", "img/ice3.png",
                            "img/ice3.png", "img/ice3.png");
                    graterWithCheese.setBounds(cup1.getBounds());
                    add(graterWithCheese);
                    this.cup1 = graterWithCheese;
                    iceInCup = true;
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
                    iceInCup = true;
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
                    iceInCup = true;
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
                    teaInCup = true;
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
                    teaInCup = true;
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
                    tapiokaInCup = true;
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
                    tapiokaInCup = true;
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
                    tapiokaInCup = true;
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
                    trubInCup = true;
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
                    trubInCup = true;
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
         // System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
       //           iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
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
         // System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
          //        iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
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
                milkInCup = true;
                //checkAndSwitchLevel();
                return;
            }

            if (source == vanil && cup1.getBounds().contains(x, y) && productsWithQuantityPopup.contains(product)) {
                showQuantityPopup(vanil, x, y, event);
                vanilInCup = true;
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
                sugarInCup = true;
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
                trubInCup = true;
                amountEntered.put(trub1, 1);
                checkDaleeAvailable();
                repaint();
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
                trubInCup = true;
                amountEntered.put(trub2, 1);
                checkDaleeAvailable();
                repaint();
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
                trubInCup = true;
                amountEntered.put(trub3, 1);
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
    //  System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
      //        iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
        boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
        if (daleeAvailable) {
            if(amountEntered.containsKey(ice)) {
                int iceAmount = amountEntered.get(ice);
                mistakeCounter2.setIceAmount(iceAmount, Recipe_2.IceKind.ICE1);
            }

            if(amountEntered.containsKey(ice2)) {
                int ice2Amount = amountEntered.get(ice2);
                mistakeCounter2.setIceAmount(ice2Amount, Recipe_2.IceKind.ICE2);
            }

            if(amountEntered.containsKey(ice3)) {
                int ice3Amount = amountEntered.get(ice3);
                mistakeCounter2.setIceAmount(ice3Amount, Recipe_2.IceKind.ICE3);
            }

            if(amountEntered.containsKey(tea)) {
                int teaAmount = amountEntered.get(tea);
                mistakeCounter2.setTeaAmount(teaAmount, Recipe_2.TeaKind.TEA1);
            }

            if(amountEntered.containsKey(tea2)) {
                int tea2Amount = amountEntered.get(tea2);
                mistakeCounter2.setTeaAmount(tea2Amount, Recipe_2.TeaKind.TEA2);
            }

            if(amountEntered.containsKey(topioka)) {
                int tapiokaAmount = amountEntered.get(topioka);
                mistakeCounter2.setTapiokaAmount(tapiokaAmount, Recipe_2.TapiokaKind.TAPIOKA1);
            }

            if(amountEntered.containsKey(topioka2)) {
                int tapioka2Amount = amountEntered.get(topioka2);
                mistakeCounter2.setTapiokaAmount(tapioka2Amount, Recipe_2.TapiokaKind.TAPIOKA2);
            }

            if(amountEntered.containsKey(topioka3)) {
                int tapioka3Amount = amountEntered.get(topioka3);
                mistakeCounter2.setTapiokaAmount(tapioka3Amount, Recipe_2.TapiokaKind.TAPIOKA3);
            }

            int vanillaAmount = amountEntered.get(vanil);
            mistakeCounter2.setVanillaAmount(vanillaAmount);

            int sugarAmount = amountEntered.get(sugar);
            mistakeCounter2.setSugarAmount(sugarAmount);

            int milkAmount = amountEntered.get(milk);
            mistakeCounter2.setMilkAmount(milkAmount);

            if(amountEntered.containsKey(trub1)) {
                int trub1Amount = amountEntered.get(trub1);
                mistakeCounter2.setTrubAmount(trub1Amount, Recipe_2.TrubKind.TRUB1);
            }

            if(amountEntered.containsKey(trub2)) {
                int trub2Amount = amountEntered.get(trub2);
                mistakeCounter2.setTrubAmount(trub2Amount, Recipe_2.TrubKind.TRUB2);
            }

            if(amountEntered.containsKey(trub3)) {
                int trub3Amount = amountEntered.get(trub3);
                mistakeCounter2.setTrubAmount(trub3Amount, Recipe_2.TrubKind.TRUB3);
            }

            daleeArrow.setTargeted(true);
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Object source = event.getSource();
        if(source == againArrow) {
         // System.out.println("Reset!");
            layersContainer.remove(this);
            layersContainer.add(new Level_2(), String.valueOf(LayersContainer.Layer.LEVEL_2));
            layersContainer.showLayer(LayersContainer.Layer.LEVEL_2);
        }
        else if(source == daleeArrow) {
        //  System.out.println(String.format("iceInCup:%b, teaInCup:%b, tapiokaInCup:%b, trubInCup:%b, vanilInCup:%b, sugarInCup :%b, milkInCup:%b",
      //            iceInCup, teaInCup, tapiokaInCup, trubInCup, vanilInCup, sugarInCup, milkInCup));
            boolean daleeAvailable = iceInCup && teaInCup && tapiokaInCup && trubInCup && vanilInCup && sugarInCup && milkInCup;
            if (daleeAvailable) {
                layersContainer.showLayer(LayersContainer.Layer.OVER_2);
            }
        }
    }



    public static void main(String...args) {
        JFrame f = new JFrame();
        f.add(new Level_2());
        f.setSize(1360, 770);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}