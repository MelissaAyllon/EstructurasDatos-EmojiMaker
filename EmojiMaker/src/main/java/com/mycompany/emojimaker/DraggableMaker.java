/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.emojimaker;

/**
 *
 * @author USUARIO
 */
import javafx.scene.Node;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

/**
 *  ************* How to use ************************
 *
 * Rectangle rectangle = new Rectangle(50, 50);
 * rectangle.setFill(Color.BLACK);
 * DragResizeMod.makeResizable(rectangle, null);
 *
 * Pane root = new Pane();
 * root.getChildren().add(rectangle);
 *
 * primaryStage.setScene(new Scene(root, 300, 275));
 * primaryStage.show();
 *
 * ************* OnDragResizeEventListener **********
 *
 * You need to override OnDragResizeEventListener and
 * 1) preform out of main field bounds check
 * 2) make changes to the node
 * (this class will not change anything in node coordinates)
 *
 * There is defaultListener and it works only with Canvas nad Rectangle
 */

public class DraggableMaker {
    public interface OnDragResizeEventListener {
        void onDrag(Node node, RectangleDimensions rectangle);

        void onResize(Node node, RectangleDimensions rectangle);
    }

    private static final OnDragResizeEventListener defaultListener = new OnDragResizeEventListener() {
        @Override
        public void onDrag(Node node, RectangleDimensions rectangle) {
            /*
            // TODO find generic way to get parent width and height of any node
            // can perform out of bounds check here if you know your parent size
            if (x > width - w ) x = width - w;
            if (y > height - h) y = height - h;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            */
            setNodeSize(node, rectangle);
        }

        @Override
        public void onResize(Node node, RectangleDimensions rectangle) {
            /*
            // TODO find generic way to get parent width and height of any node
            // can perform out of bounds check here if you know your parent size
            if (w > width - x) w = width - x;
            if (h > height - y) h = height - y;
            if (x < 0) x = 0;
            if (y < 0) y = 0;
            */
            setNodeSize(node, rectangle);
        }

        private void setNodeSize(Node node, RectangleDimensions rectangle) {
            node.setLayoutX(rectangle.getX());
            node.setLayoutY(rectangle.getY());
            // TODO find generic way to set width and height of any node
            // here we cant set height and width to node directly.
            // or i just cant find how to do it,
            // so you have to wright resize code anyway for your Nodes...
            //something like this
            if (node instanceof ImageView) {
                ((ImageView) node).setFitWidth(rectangle.getWidth());
                ((ImageView) node).setFitHeight(rectangle.getHigh());
            } 
        }
    };

    public static enum S {
        DEFAULT,
        DRAG,
        NW_RESIZE,
        SW_RESIZE,
        NE_RESIZE,
        SE_RESIZE,
        E_RESIZE,
        W_RESIZE,
        N_RESIZE,
        S_RESIZE;
    }


    private double clickX, clickY, nodeX, nodeY, nodeH, nodeW;

    private S state = S.DEFAULT;

    private Node node;
    private OnDragResizeEventListener listener = defaultListener;

    private static final int MARGIN = 8;
    private static final double MIN_W = 30;
    private static final double MIN_H = 20;

    private DraggableMaker(Node node, OnDragResizeEventListener listener) {
        this.node = node;
        if (listener != null)
            this.listener = listener;
    }

    public static void makeResizable(Node node) {
        makeResizable(node, null);
    }

    public static void makeResizable(Node node, OnDragResizeEventListener listener) {
        final DraggableMaker resizer = new DraggableMaker(node, listener);

        node.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resizer.mousePressed(event);
            }
        });
        node.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resizer.mouseDragged(event);
            }
        });
        node.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resizer.mouseOver(event);
            }
        });
        node.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                resizer.mouseReleased(event);
            }
        });
    }

    protected void mouseReleased(MouseEvent event) {
        node.setCursor(Cursor.DEFAULT);
        state = S.DEFAULT;
    }

    protected void mouseOver(MouseEvent event) {
        S state = currentMouseState(event);
        Cursor cursor = getCursorForState(state);
        node.setCursor(cursor);
    }

    private S currentMouseState(MouseEvent event) {
        S state = S.DEFAULT;
        boolean left = isLeftResizeZone(event);
        boolean right = isRightResizeZone(event);
        boolean top = isTopResizeZone(event);
        boolean bottom = isBottomResizeZone(event);

        if (left && top) state = S.NW_RESIZE;
        else if (left && bottom) state = S.SW_RESIZE;
        else if (right && top) state = S.NE_RESIZE;
        else if (right && bottom) state = S.SE_RESIZE;
        else if (right) state = S.E_RESIZE;
        else if (left) state = S.W_RESIZE;
        else if (top) state = S.N_RESIZE;
        else if (bottom) state = S.S_RESIZE;
        else if (isInDragZone(event)) state = S.DRAG;

        return state;
    }

    private static Cursor getCursorForState(S state) {
        switch (state) {
            case NW_RESIZE:
                return Cursor.NW_RESIZE;
            case SW_RESIZE:
                return Cursor.SW_RESIZE;
            case NE_RESIZE:
                return Cursor.NE_RESIZE;
            case SE_RESIZE:
                return Cursor.SE_RESIZE;
            case E_RESIZE:
                return Cursor.E_RESIZE;
            case W_RESIZE:
                return Cursor.W_RESIZE;
            case N_RESIZE:
                return Cursor.N_RESIZE;
            case S_RESIZE:
                return Cursor.S_RESIZE;
            default:
                return Cursor.DEFAULT;
        }
    }


    protected void mouseDragged(MouseEvent event) {

        if (listener != null) {
            double mouseX = parentX(event.getX());
            double mouseY = parentY(event.getY());
            RectangleDimensions rectangle;
            if (state == S.DRAG) {
                rectangle = new RectangleDimensions(mouseX - clickX, mouseY - clickY, nodeH, nodeW);
                listener.onDrag(node, rectangle);
            } else if (state != S.DEFAULT) {
                //resizing
                rectangle = new RectangleDimensions();
                // Right Resize
                if (state == S.E_RESIZE || state == S.NE_RESIZE || state == S.SE_RESIZE) {
                    rectangle.setX(mouseX - nodeX);
                }
                // Left Resize
                if (state == S.W_RESIZE || state == S.NW_RESIZE || state == S.SW_RESIZE) {
                    rectangle.setWidth(nodeW + nodeX - rectangle.getX());
                }

                // Bottom Resize
                if (state == S.S_RESIZE || state == S.SE_RESIZE || state == S.SW_RESIZE) {
                    rectangle.setHigh(mouseY - nodeY);
                }
                // Top Resize
                if (state == S.N_RESIZE || state == S.NW_RESIZE || state == S.NE_RESIZE) {
                    rectangle.setHigh(nodeH + nodeY - rectangle.getY());
                }

                //min valid rect Size Check
                if (rectangle.getWidth() < MIN_W) {
                    if (state == S.W_RESIZE || state == S.NW_RESIZE || state == S.SW_RESIZE)
                        rectangle.setX(rectangle.getX()- MIN_W + rectangle.getWidth());
                    rectangle.setWidth(MIN_W);
                }

                if (rectangle.getHigh() < MIN_H) {
                    if (state == S.N_RESIZE || state == S.NW_RESIZE || state == S.NE_RESIZE)
                        rectangle.setY(rectangle.getY() + rectangle.getHigh() - MIN_H);
                    rectangle.setHigh(MIN_H);
                }

                listener.onResize(node, rectangle);
            }
        }
    }

    protected void mousePressed(MouseEvent event) {

        if (isInResizeZone(event)) {
            setNewInitialEventCoordinates(event);
            state = currentMouseState(event);
        } else if (isInDragZone(event)) {
            setNewInitialEventCoordinates(event);
            state = S.DRAG;
        } else {
            state = S.DEFAULT;
        }
    }

    private void setNewInitialEventCoordinates(MouseEvent event) {
        nodeX = nodeX();
        nodeY = nodeY();
        nodeH = nodeH();
        nodeW = nodeW();
        clickX = event.getX();
        clickY = event.getY();
    }

    private boolean isInResizeZone(MouseEvent event) {
        return isLeftResizeZone(event) || isRightResizeZone(event)
                || isBottomResizeZone(event) || isTopResizeZone(event);
    }

    private boolean isInDragZone(MouseEvent event) {
        double xPos = parentX(event.getX());
        double yPos = parentY(event.getY());
        double nodeX = nodeX() + MARGIN;
        double nodeY = nodeY() + MARGIN;
        double nodeX0 = nodeX() + nodeW() - MARGIN;
        double nodeY0 = nodeY() + nodeH() - MARGIN;

        return (xPos > nodeX && xPos < nodeX0) && (yPos > nodeY && yPos < nodeY0);
    }

    private boolean isLeftResizeZone(MouseEvent event) {
        return intersect(0, event.getX());
    }

    private boolean isRightResizeZone(MouseEvent event) {
        return intersect(nodeW(), event.getX());
    }

    private boolean isTopResizeZone(MouseEvent event) {
        return intersect(0, event.getY());
    }

    private boolean isBottomResizeZone(MouseEvent event) {
        return intersect(nodeH(), event.getY());
    }

    private boolean intersect(double side, double point) {
        return side + MARGIN > point && side - MARGIN < point;
    }

    private double parentX(double localX) {
        return nodeX() + localX;
    }

    private double parentY(double localY) {
        return nodeY() + localY;
    }

    private double nodeX() {
        return node.getBoundsInParent().getMinX();
    }

    private double nodeY() {
        return node.getBoundsInParent().getMinY();
    }

    private double nodeW() {
        return node.getBoundsInParent().getWidth();
    }

    private double nodeH() {
        return node.getBoundsInParent().getHeight();
    }
}
//public class DraggableMaker {
//
//    private double mouseAnchorX;
//    private double mouseAnchorY;
//
//    public void makeDraggable(Node node){
//
//        node.setOnMousePressed(mouseEvent -> {
//            mouseAnchorX = mouseEvent.getSceneX()-node.getTranslateX();
//            mouseAnchorY = mouseEvent.getSceneY()-node.getTranslateY();
//        });
//
//        node.setOnMouseDragged(mouseEvent -> {
//            node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
//            node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
//        });
//    }
//}