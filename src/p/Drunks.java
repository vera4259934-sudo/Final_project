package p;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Класс Drunks представляет собой контейнер для ингредиентов напитка:
 * льда, тапиоки и трубочки. Он будет использоваться для создания
 * "образца" напитка, который затем будет отображаться в стакане.
 */
public class Drunks extends JLabel {

    // Пути к изображениям различных вариаций льда, тапиоки и трубочки
    private final String[] icePaths = {"img/ice1.png", "img/ice2.png", "img/ice3.png"};
    private final String[] tapiocaPaths = {"img/topioka1.png", "img/topioka2.png", "img/topioka3.png"};
    private final String[] strawPaths = {"img/trubochka1.png", "img/trubochka2.png", "img/trubochka3.png"};

    private Random random = new Random(); // Генератор случайных чисел

    private ImageIcon currentIceIcon;
    private ImageIcon currentTapiocaIcon;
    private ImageIcon currentStrawIcon;

    /**
     * Конструктор класса Drunks.
     * Инициализирует случайными изображениями лед, тапиоку и трубочку.
     */
  public Drunks() {
        // Устанавливаем начальные случайные иконки для каждого ингредиента
        randomizeIngredients();
        // Устанавливаем общий размер для этого компонента (может быть скорректирован)
        setPreferredSize(new Dimension(200, 200)); // Примерный размер, может потребоваться изменение
    }

    /**
     * Выбирает случайные изображения для льда, тапиоки и трубочки.
     */
  public void randomizeIngredients() {
        // Случайный выбор изображения льда
        currentIceIcon = new ImageIcon(getClass().getClassLoader().getResource(icePaths[random.nextInt(icePaths.length)]));
        // Случайный выбор изображения тапиоки
        currentTapiocaIcon = new ImageIcon(getClass().getClassLoader().getResource(tapiocaPaths[random.nextInt(tapiocaPaths.length)]));
        // Случайный выбор изображения трубочки
        currentStrawIcon = new ImageIcon(getClass().getClassLoader().getResource(strawPaths[random.nextInt(strawPaths.length)]));
        // Перерисовываем компонент, чтобы отобразить новые иконки
        repaint();
    }

    /**
     * Отрисовывает выбранные ингредиенты.
     * В данной версии ингредиенты отрисовываются вместе, имитируя вид напитка.
     *
     * @param g Объект Graphics для рисования.
     */
  @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (currentIceIcon != null) {
            // Примерное расположение льда (можно настроить)
            currentIceIcon.paintIcon(this, g, 1000, 10);
        }
        if (currentTapiocaIcon != null) {
            // Примерное расположение тапиоки (можно настроить)
            currentTapiocaIcon.paintIcon(this, g, 1000, 10);
        }
        if (currentStrawIcon != null) {
            // Примерное расположение трубочки (можно настроить)
            currentStrawIcon.paintIcon(this, g, 1000, 10);
        }
    }

    // Дополнительные методы, если потребуются для получения отдельных иконок или имен ингредиентов
    public ImageIcon getCurrentIceIcon() { return currentIceIcon; }
    public ImageIcon getCurrentTapiocaIcon() { return currentTapiocaIcon; }
    public ImageIcon getCurrentStrawIcon() { return currentStrawIcon; }
}

/*
import javax.swing.*;
import java.awt.*;

class Drunks extends JPanel {
    // Объявление окончательных (final) полей для изображений.
    private final ImageIcon iceIcon;
    private final ImageIcon tapiocaIcon;
    private final ImageIcon strawIcon;

    public Drunks() {
        // Инициализация полей ImageIcon происходит в конструкторе.
        // Если загрузка изображения не удалась, то ImageIcon может остаться null
        // или может быть установлен какой-либо индикатор ошибки.
        // В данном случае, если ресурс не найден, конструктор ImageIcon выбросит исключение
        // или вернет ImageIcon с пустым изображением, но само поле будет инициализировано.

        try {
            iceIcon = new ImageIcon(getClass().getClassLoader().getResource("img/ice.png"));
            tapiocaIcon = new ImageIcon(getClass().getClassLoader().getResource("img/tapioca.png"));
            strawIcon = new ImageIcon(getClass().getClassLoader().getResource("img/straw.png"));
        } catch (Exception e) {
            // Обработка ошибок загрузки ресурсов.
            System.err.println("Ошибка загрузки изображений!");
            e.printStackTrace();

            // Важно: Если изображения не загружены, для final полей нужно присвоить
            // какое-то значение, иначе будет ошибка компиляции или NullPointerException.
            // Здесь мы инициализируем их пустыми ImageIcon, чтобы избежать ошибки.
            ImageIcon errorIcon = new ImageIcon(); // Пустой ImageIcon
            iceIcon = errorIcon;
            tapiocaIcon = errorIcon;
            strawIcon = errorIcon;

            // Опционально: Добавить метку на панель, чтобы пользователь видел ошибку.
            add(new JLabel("Ошибка загрузки изображений"));
            return; // Прерываем дальнейшую инициализацию, если произошла ошибка.
        }

        // Проверка успешности загрузки изображений перед их использованием.
        // Если изображения были успешно загружены, их status не будет ERRORED.
        if (iceIcon.getImageLoadStatus() != MediaTracker.ERRORED) {
            addIngredient(iceIcon, 0.6, 0.3, 1000, 10);
        }
        if (tapiocaIcon.getImageLoadStatus() != MediaTracker.ERRORED) {
            addIngredient(tapiocaIcon, 0.7, 0.4, 1000, 10);
        }
        if (strawIcon.getImageLoadStatus() != MediaTracker.ERRORED) {
            addIngredient(strawIcon, 1.0, 0.2, 1000, 10);
        }
    }

    private void addIngredient(ImageIcon icon, double probability, double sizeFactor, int defaultX, int defaultY) {
        if (Math.random() < probability) {
            JLabel ingredientLabel = new JLabel(icon);
            int panelWidth = getSize().width > 0 ? getSize().width : 300; // Запасной размер, если getSize() == 0
            int panelHeight = getSize().height > 0 ? getSize().height : 300;

            int imgWidth = (int) (panelWidth * sizeFactor);
            int imgHeight = (int) ((double) imgWidth * icon.getIconHeight() / icon.getIconWidth());

            ingredientLabel.setBounds(defaultX, defaultY, imgWidth, imgHeight);
            add(ingredientLabel);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
*/