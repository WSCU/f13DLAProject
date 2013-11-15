/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import static f13dlaproject.Point3.*;
import static f13dlaproject.Particle3D.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to represent a crystal in a 3D environment. Implements from
 * {@link Crystal}
 *
 * @author stu738510
 */
public class Crystal3D implements Crystal {

    /**
     * A unique crystal
     */
    private static Crystal3D instance;
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
    /**
     * An ArrayList of CParticles that make up the crystal
     */
    private List<CParticle3> parts = new CopyOnWriteArrayList();
    /**
     * Most recent CParticle
     */
    private CParticle recent;

    /**
     * Constructor for a 3D crystal Instantiates the count, radius, zoom, color
     * array, coloring strategy, CParticle
     */
    private Crystal3D() {
        this.count = 1;
        this.radius = 1;
        this.zoom = 0;
        Color[] c = {Color.RED, Color.BLUE, Color.BLACK};
        this.color = new StandardColor(c);
        //parts.add(new CParticle3(point3(0, 0, 0), 1, null));
        CParticle3 p = new CParticle3(point3(0, 0, 0), 1, null);
        color.chooseColor(p);
        parts.add(p);

    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public double getZoom() {
        return this.zoom;
    }

    @Override
    public void add(Particle p, f13dlaproject.CParticle parent) {
        count++;
        CParticle3 part = new CParticle3(p.getPosition(), count, parent);
        color.chooseColor(part);
        parts.add(part);
        double dist = part.p.length();
        if (dist > radius) {
            radius = dist;
        }
    }

    @Override
    public int getSize() {
        return this.count;
    }

    @Override
    public boolean collides() {
        for (CParticle3 partic : parts) {
            CParticle recent = partic.collides();
            if (recent != null) {
                this.add(particle3D(), recent);
                return true;
            }
        }
        return false;
    }

    @Override
    public void setColorStrategy(ColoringStrategy Color) {
        this.color = Color;
    }

    @Override
    public void draw(Graphics g) {
        for (CParticle partic : parts) {
            partic.draw(g);
        }
    }

    /**
     * Singleton static factory that generates a unique 3D crystal
     *
     * @return unique instance of a 3D crystal
     */
    public static Crystal3D crystal3D() {
        if (instance == null) {
            instance = new Crystal3D();
        }
        return instance;
    }

    @Override
    public void setZoom(int z) {
        this.zoom = z;
    }

    @Override
    public void clear() {
        this.count = 1;
        this.radius = 1;
        this.zoom = 0;
        Color[] c = {Color.RED, Color.BLUE, Color.BLACK};
        this.color = new StandardColor(c);
    }

    private static class CParticle3 implements CParticle {

        private Point p; //position of node
        private int num; //number in which it was added to crystal
        private double dist; //distance from center of cystal
        private Color c;
        CParticle parent;

        public CParticle3(Point p, int i, CParticle parent) {
            this.p = p.clone(p);
            this.num = i;
            this.parent = parent;
            if (parent != null) {
                double tx = p.getX();
                double ty = p.getY();
                double tz = p.getZ();
                Point pp = parent.getPos();
                double px = pp.getX();
                double py = pp.getY();
                double pz = pp.getZ();
                double nx = tx - px;
                double ny = ty - py;
                double nz = tz - pz;
                double nl = Math.sqrt(Math.pow(nx, 2) + Math.pow(ny, 2) + Math.pow(nz, 2));
                nx = nx / nl;
                ny = ny / nl;
                nz = nz / nl;
                this.p.setX(px + nx);
                this.p.setY(py + ny);
                this.p.setZ(py + nz);
            }
        }

        @Override
        public CParticle collides() {
            Particle3D t = particle3D();
            Point pos = t.getPosition();
            double l = Math.sqrt(Math.pow(p.getX() - pos.getX(), 2) + Math.pow(p.getY() - pos.getY(), 2) + Math.pow(p.getZ() - pos.getZ(), 2));
            if (l < 1) {
                return this;
            }
            return null;
        }

        @Override
        public void draw(Graphics g) {

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

    @Override
    public void outputToFile() {
        double prad = zoom / 2;
        try {
            PrintWriter writer = new PrintWriter(DLAFrame.fileName + ".pov", "UTF-8");
            writer.println(" #include \"colors.inc\"\n"
                    + "#include \"textures.inc\"");
            writer.println("camera {\n"
                            + "       location  <0, 0, -100>\n"
                            + "       look_at   <0, 0, 0>\n"
                            + "       angle 48\n"
                            + "   }");
            writer.println("light_source {\n"
                    + "      <2, 10, -3>\n"
                    + "      color White\n"
                    + "    }");
            for (CParticle3 p : parts) {
                double x = p.p.getX();
                double y = p.p.getY();
                double z = p.p.getZ();
                float[] colors = new float[3];
                p.getColor().getColorComponents(colors);
                writer.println(String.format("sphere {\n"
                        + "      <%f,%f,%f>,1\n"
                        + "      texture { pigment { color rgb<%f, %f, %f>} }\n"
                        + "    }", x, y, z,  colors[0], colors[1], colors[2]));
            }
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Crystal3D.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Crystal3D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
