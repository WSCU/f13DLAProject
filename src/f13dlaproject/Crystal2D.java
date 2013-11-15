/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point2.*;
import static f13dlaproject.Particle2D.*;
import java.io.File;
import java.util.List;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A class to represent a crystal in a 2D environment. Implements from
 * {@link Crystal} Singleton pattern
 *
 * @author Graham and Ryan
 */
public class Crystal2D implements Crystal {

    /**
     * A unique crystal
     */
    private static Crystal2D instance;
    /**
     * The number of nodes in a crystal
     */
    private int count;
    /**
     * The radius of the crystal
     */
    private double radius;
    /**
     * zoom factor to make the crystal fit to the window
     */
    private double zoom;
    /**
     * A coloring strategy for the crystal
     */
    private ColoringStrategy color;
    //private ArrayList<CParticle> parts = new ArrayList();
    /**
     * An ArrayList of CParticles that make up the crystal
     */
    private List<CParticle2> parts = new CopyOnWriteArrayList();

    /*
     * Declaration of inner CParticle class
     * Represents each node in crystal
     * @author Graham and Ryan
     */
    private class CParticle2 implements CParticle {

        /**
         * Point position of crystal node
         */
        private Point p; //position of node
        /**
         * Number in which the node was added to the crystal
         */
        private int num; //number in which it was added to crystal
        /**
         * Distance of node from center of crystal
         */
        private double dist; //distance from center of cystal
        /**
         * Color of crystal node
         */
        private Color c;
        /**
         * Parent of the crystal node
         */
        private CParticle parent;

        /**
         * Constructs a new node in the crystal of a 2D environment
         *
         * @param p Position of the node, represented by point (x, y)
         * @param num Number in which the node was added to the crystal
         * @param parent Parent of the crystal node
         */
        public CParticle2(Point p, int num, CParticle parent) { //constructor
            this.p = p.clone(p);
            this.num = num;
            this.dist = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
            this.parent = parent;

            if (parent != null) {
                double tx = p.getX();
                double ty = p.getY();
                Point pp = parent.getPos();
                double px = pp.getX();
                double py = pp.getY();
                double nx = tx - px;
                double ny = ty - py;
                double nl = Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2));
                nx = nx / nl;
                ny = ny / nl;
                this.p.setX(px + nx);
                this.p.setY(py + ny);
            }
        }

        @Override
        public void draw(Graphics g) { //draw
            g.setColor(c);
            g.fillOval((int) (p.getX() * zoom - zoom / 2) + (DLAFrame.WIDTH / 2) - DLAFrame.dx, (int) (p.getY() * zoom - zoom / 2) + (DLAFrame.HEIGHT / 2) - DLAFrame.dy, (int) zoom, (int) zoom);
        }

        @Override
        public CParticle collides() {
            Particle2D t = particle2D();
            Point pos = t.getPosition();
            double distance = Math.sqrt(Math.pow(p.getX() - pos.getX(), 2) + Math.pow(p.getY() - pos.getY(), 2));
            if (distance <= 1) {
                return this;
            }
            return null;
        }

        @Override
        public void setColor(Color c) {
            this.c = c;
        }

        @Override
        public Color getColor() {
            return c;
        }

        @Override
        public Point getPos() {
            return p;
        }

        @Override
        public int getNum() {
            return num;
        }

        @Override
        public double getDist() {
            return dist;
        }

        @Override
        public CParticle getParent() {
            return this.parent;
        }
    }

    /**
     * Constructor for a 2D crystal Instantiates the count, radius, zoom, color
     * array, coloring strategy, CParticle
     */
    private Crystal2D() { //constructor
        this.count = 1;
        this.radius = 0;
        this.zoom = 20;
        Color[] c = DLAFrame.cArray;
        this.color = new StandardColor(c);
        CParticle2 cp = new CParticle2(point2(0, 0), count, null);
        color.chooseColor(cp);
        parts.add(cp);
    }

    @Override
    public double getRadius() {
        return radius;
    }

    @Override
    public double getZoom() {
        return zoom;
    }

    public List<CParticle2> getCrystal() {
        return parts;
    }

    @Override
    public void add(Particle p, CParticle parent) {//adds node to crystal
        count++;
        Point po = p.getPosition();
        double dist = po.length();
        CParticle2 part = new CParticle2(po, count, parent);
        color.chooseColor(part);
        parts.add(part);
        if (dist > radius) {
            radius = dist;
            if (DLAFrame.autoZ) {
                zoom = 20 - Math.pow((radius) / 11, 2);
                if (zoom < 5) {
                    zoom = 5;
                }
            }
        }
    }

    @Override
    public int getSize() { //returns the number of nodes
        return count;
    }

    @Override
    public void setZoom(int z) {
        this.zoom = z;
    }

    /**
     * Colors each particle in the node array to the passed in coloring strategy
     */
    @Override
    public void setColorStrategy(ColoringStrategy color) {
        this.color = color;
        for (CParticle2 p : parts) {
            color.chooseColor(p);
        }
    }

    /**
     * Resets the node list to empty and the count to 0
     */
    @Override
    public void clear() { //resets the cystal to nothing
        parts = new ArrayList();
        count = 0;
        CParticle2 cp = new CParticle2(point2(0, 0), count, null);
        color.chooseColor(cp);
        parts.add(cp);
        count = 0;
        zoom = 90;
        radius = 0;
    }

    @Override
    public boolean collides() {
        for (CParticle2 p : parts) {
            CParticle recent = p.collides();
            if (recent != null) {
                this.add(particle2D(), recent);
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates the nodes and draws each one
     */
    @Override
    public synchronized void draw(Graphics g) {//iterates the nodes and draws each one
        Iterator<CParticle2> iterator = parts.iterator();
        for (CParticle p : parts) {
            p.draw(g);
        }
//        while (iterator.hasNext()) {
//            CParticle p = iterator.next();
//            p.draw(g);
//        }
    }

    /**
     * Singleton static factory that generates a unique 2D crystal
     *
     * @return unique instance of a crystal
     */
    public static Crystal2D crystal2D() {
        if (instance == null) {
            instance = new Crystal2D();
        }
        return instance;
    }

    /**
     * Method to display the number of nodes in the crystal
     *
     * @return A String detailing the number of nodes in the crystal
     */
    @Override
    public String toString() { // toString()
        return "Size: " + count;
    }

    /////////////////////////////////////////////////////
    public void toFile(List<Crystal2D> c) {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("svg");
            doc.appendChild(rootElement);

            // set attribute to xmlns
            Attr attr = doc.createAttribute("xmlns");
            attr.setValue("http://www.w3.org/2000/svg");
            rootElement.setAttributeNode(attr);

            // set attribute to version
            Attr attr2 = doc.createAttribute("version");
            attr2.setValue("1.1");
            rootElement.setAttributeNode(attr2);


            for (CParticle cp : parts) {
                double crad = radius;
                double prad = 20/zoom;
                Point pp = cp.getPos();
                double xp = pp.getX();
                double yp = pp.getY();
                xp = crad - xp;
                yp = crad - yp;
                String xpos = String.valueOf(xp);
                String ypos = String.valueOf(yp);
                String color = "rgb("+cp.getColor().getRed()+","+cp.getColor().getGreen()+","+cp.getColor().getBlue()+")";
                String radius = String.valueOf(prad);

                // circle
                Element circle = doc.createElement("circle");
                rootElement.appendChild(circle);

                // set attribute to cx(circle x pos)
                Attr attr3 = doc.createAttribute("cx");
                attr3.setValue(xpos);
                circle.setAttributeNode(attr3);

                // set attribute to cy(circle y pos)
                Attr attr4 = doc.createAttribute("cy");
                attr4.setValue(ypos);
                circle.setAttributeNode(attr4);

                // set attribute to r(circle radius)
                Attr attr5 = doc.createAttribute("r");
                attr5.setValue(radius);
                circle.setAttributeNode(attr5);

                // set attribute to fil(circle fill color)
                Attr attr6 = doc.createAttribute("fill");
                attr6.setValue(color);
                circle.setAttributeNode(attr6);

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\stu738510\\Desktop\\Crystal.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
