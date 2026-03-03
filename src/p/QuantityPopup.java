package p;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class QuantityPopup extends JComponent implements ActionListener {
    public static final String ACTIONCOMMAND_OK = "ok";
    private final Icon backgroundIcon;
    private final Rectangle incArea;
    private final Rectangle decArea;
    private final Rectangle okArea;
    private final ActionListener actionListener;
    private final ActionEvent actionEvent = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, ACTIONCOMMAND_OK);
    private final int minValue;
    private final int maxValue;
    private int value;
    private int step;
    private int iterationCounter;
    private final Cursor HAND_CURSOR = new Cursor(Cursor.HAND_CURSOR);
    private final Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
    private final Timer incTimer = new Timer(100, this);
    private final Timer decTimer = new Timer(100, this);
    private static Font VALUE_FONT = new Font("Arial", Font.BOLD + Font.ITALIC, 35);

    public QuantityPopup(String name,
                         int minValue, int maxValue, int initialValue,
                         String backgroundImagePath,
                         Rectangle incArea, Rectangle decArea, Rectangle okArea,
                         ActionListener actionListener) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        value = initialValue;
        this.incArea = incArea;
        this.decArea = decArea;
        this.okArea = okArea;
        this.actionListener = actionListener;

        backgroundIcon = new ImageIcon(getClass().getClassLoader().getResource(backgroundImagePath));
        setSize(backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());

        setLayout(null);
        JLabel l = new JLabel(backgroundIcon);
        l.setBounds(0, 0, backgroundIcon.getIconWidth(), backgroundIcon.getIconHeight());
        add(l);

        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
    }

    public int getValue() {
        return value;
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                if (incArea.contains(event.getX(), event.getY())) {
                    step = 1;
                    iterationCounter = 0;
                    incTimer.setInitialDelay(500);
                    incTimer.start();
                } else if (decArea.contains(event.getX(), event.getY())) {
                    step = 1;
                    iterationCounter = 0;
                    decTimer.setInitialDelay(500);
                    decTimer.start();
                }
            }
        } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                incTimer.stop();
                decTimer.stop();
            }
        } else if (event.getID() == MouseEvent.MOUSE_CLICKED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                if (incArea.contains(event.getX(), event.getY())) {
                    step = 1;
                    iterationCounter = 0;
                    if (incValue()) {
                        repaint();
                    }
                } else if (decArea.contains(event.getX(), event.getY())) {
                    step = 1;
                    iterationCounter = 0;
                    if (decValue()) {
                        repaint();
                    }
                } else if (okArea.contains(event.getX(), event.getY())) {
                    actionListener.actionPerformed(actionEvent);
                }
            }
        }
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        super.processMouseMotionEvent(event);
        if (incArea.contains(event.getX(), event.getY()) || decArea.contains(event.getX(), event.getY()) || okArea.contains(event.getX(), event.getY())) {
            setCursor(HAND_CURSOR);
        } else {
            setCursor(DEFAULT_CURSOR);
            incTimer.stop();
            decTimer.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (incTimer == source) {
            if (incValue()) {
                repaint();
            }
        } else if (decTimer == source) {
            if (decValue()) {
                repaint();
            }
        }
    }

    private boolean incValue() {
        if (value + step <= maxValue) {
            value += step;
            iterationCounter += 1;
            if (iterationCounter > 10 && step < 100) {
                iterationCounter = 0;
                step *= 10;
            }
            return true;
        } else {
            return false;
        }
    }

    private boolean decValue() {
        if (value - step >= minValue) {
            value -= step;
            iterationCounter += 1;
            if (iterationCounter > 10 && step < 100) {
                iterationCounter = 0;
                step *= 10;
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        backgroundIcon.paintIcon(this, g, 0, 0);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(VALUE_FONT);
        g.setColor(Color.RED);

        FontMetrics metrics = g.getFontMetrics(VALUE_FONT);
        String text = String.valueOf(value);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int x = (getWidth() - textWidth) / 2 - 20;
        int y = getHeight() / 2 + textHeight / 2;

        g.drawString(text, x, y);
    }
}