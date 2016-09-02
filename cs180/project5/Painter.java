import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Painter extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
 private History history;
 private DrawingArea canvas;
 private String mode;
 private int startX;
 private int startY;
 
 /**
  * Launch the application.
  */
 public static void main(String[] args) {
  Painter window = new Painter();
 }
 
 
 public Painter() {
  super("Painter");
  history = new History();
  setBounds(100,100,979,665);
  setDefaultCloseOperation(EXIT_ON_CLOSE);
  
  // Build Menu
  String[] types = ShapeFactory.getTypes();
  if(types.length == 0) {
   mode = null;
  }
  else {
   mode = types[0];
  }
  MenuBar bar = new MenuBar();
  Menu shapeMenu = new Menu("Shape");
  for(int i = 0; i < types.length; i++) {
   MenuItem item = new MenuItem(types[i]);
   item.addActionListener(this);
   shapeMenu.add(item);
  }
  Menu navigationMenu = new Menu("Edit");
  MenuItem undoItem = new MenuItem("Undo");
  undoItem.addActionListener(this);
  navigationMenu.add(undoItem);
  MenuItem redoItem = new MenuItem("Redo");
  redoItem.addActionListener(this);
  navigationMenu.add(redoItem);
  MenuItem clearItem = new MenuItem("Clear");
  clearItem.addActionListener(this);
  navigationMenu.add(clearItem);
  bar.add(shapeMenu);
  bar.add(navigationMenu);
  setMenuBar(bar);
  
  // Set up drawing panel
  canvas = new DrawingArea();
  canvas.addMouseListener(this);
  canvas.addMouseMotionListener(this);
  getContentPane().add(canvas,BorderLayout.CENTER);
  setVisible(true);
 }
 
 public void actionPerformed(ActionEvent e) {
  if(mode == null) return;
  System.out.println("Action Command: " + e.getActionCommand());
  String cmd = e.getActionCommand();
  if(cmd.equals("Undo")) {
   history.undo();
   canvas.drawing = history.getShapes();
   canvas.repaint();
  }
  else if (cmd.equals("Redo")) {
   history.redo();
   canvas.drawing = history.getShapes();
   canvas.repaint();
  }
  else if (cmd.equals("Clear")) {
   history.clear();
   canvas.drawing = history.getShapes();
   canvas.repaint();
  }
  else {
   mode = cmd;
  }
 }
 
 public void mouseMoved(MouseEvent e) {}
 
 public void mouseDragged(MouseEvent e) {
  if(mode == null) return;
  canvas.radius = (int)(Math.round(Math.hypot(e.getX()-startX,e.getY()-startY)));
  canvas.repaint();
 }
 
 public void mouseClicked(MouseEvent e) {}
 public void mouseEntered(MouseEvent e) {}
 public void mouseExited(MouseEvent e) {}

 public void mousePressed(MouseEvent e) {
  if(mode == null) return;
  System.out.println("Pressed: X = " + e.getX() + " : Y = " + e.getY());
  startX = e.getX();
  startY = e.getY();
  canvas.x = startX;
  canvas.y = startY;
  canvas.radius = 0;
  canvas.pressed = true;
 }

 public void mouseReleased(MouseEvent e) {
  if(mode == null) return;
  System.out.println("Released: X = " + e.getX() + " : Y = " + e.getY());
  int radius = (int)(Math.round(Math.hypot(e.getX()-startX,e.getY()-startY)));
  System.out.println("Making shape: mode = " + mode + " : startX = " + startX + " : startY = " + startY + " : radius = " + radius);
  Shape temp = ShapeFactory.makeShape(mode,startX,startY,radius);
  if(temp != null) {
   history.add(temp);
  }
  canvas.drawing = history.getShapes();
  canvas.pressed = false;
  canvas.repaint();
 }
 
 private class DrawingArea extends JPanel {  
  public Shape[] drawing = new Shape[0];
  public int x,y,radius = 0;
  public boolean pressed = false;

  @Override
  protected void paintComponent(Graphics g) {
   super.paintComponent(g);
   if(pressed) {
    g.setColor(Color.BLUE);
    g.drawOval(x-radius,y-radius,radius*2,radius*2);
    g.setColor(Color.BLACK);
   }
   for(int i = 0; i < drawing.length; i++) {
    drawing[i].setGraphics(g);
    drawing[i].draw();
   }
  }
  
 }
}
