package p;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tool extends JComponent {

    public final String name;
    private boolean draggable;
    private int initialX;
    private int initialY;

    private final Icon regularIcon;
    private final Icon selectedIcon;
    private final Icon targetedIcon;

    private boolean selected = false;
    private boolean targeted = false;

    private static final String REGULAR = "regular";
    private static final String SELECTED = "selected";
    private static final String TARGETED = "targeted";
    protected final CardLayout cardLayout = new CardLayout();
    private JLayeredPane parent;
    private int x;
    private int y;

    public Tool(String name, boolean draggable,
                String regularImagePath, String selectedImagePath, String targetedImagePath) {

        this.name = name;
        this.draggable = draggable;

        regularIcon = new ImageIcon(getClass().getClassLoader().getResource(regularImagePath));
        selectedIcon = new ImageIcon(getClass().getClassLoader().getResource(selectedImagePath));
        targetedIcon = new ImageIcon(getClass().getClassLoader().getResource(targetedImagePath));

        setLayout(cardLayout);
        add(new JLabel(regularIcon), REGULAR);
        add(new JLabel(selectedIcon), SELECTED);
        add(new JLabel(targetedIcon), TARGETED);

        if (draggable) {
            enableEvents(AWTEvent.MOUSE_EVENT_MASK);
            enableEvents(AWTEvent.MOUSE_MOTION_EVENT_MASK);
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void addNotify() {
        super.addNotify();
        parent = (JLayeredPane) SwingUtilities.getAncestorOfClass(JLayeredPane.class, this);
        initialX = getX();
        initialY = getY();
    }

    public void returnToOriginalLocation() {
        selected = false;
        //targeted = false;
        cardLayout.show(this, REGULAR);
        setLocation(initialX, initialY);
    }

    public void setTargeted(boolean b) {
        if(targeted != b) {
            targeted = b;
            cardLayout.show(this, targeted ? TARGETED : REGULAR);
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent event) {
        super.processMouseEvent(event);
        if (event.isConsumed()) {
            return;
        }

        if (event.getID() == MouseEvent.MOUSE_PRESSED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                x = event.getX();
                y = event.getY();
            }
        }
        else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                x = y = -1;
                returnToOriginalLocation();
            }
        }
        else if (event.getID() == MouseEvent.MOUSE_ENTERED) {
            if(targeted) {
                // nothing
            }
            else {
                selected = true;
                cardLayout.show(this, SELECTED);
            }
        }
        else if (event.getID() == MouseEvent.MOUSE_EXITED) {
            if(targeted) {
                // nothing
            }
            else {
                selected = false;
                cardLayout.show(this, REGULAR);
            }
        }
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent event) {
        if (event.getID() == MouseEvent.MOUSE_DRAGGED) {
            if (SwingUtilities.isLeftMouseButton(event)) {
                parent.moveToFront(this);
                int deltaX = event.getX() - x;
                int deltaY = event.getY() - y;
                setLocation(getX() + deltaX, getY() + deltaY);
            }
        }
        super.processMouseMotionEvent(event);
    }
}