
package f13dlaproject;

import f13dlaproject.CParticle;
import java.awt.List;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Quadtree {
    private int MAX_OBJECTS = 30; //max number of objects in a single node
    private int MAX_LEVELS = 100; //max depth of the tree
 
    private int level; //current depth of the node
    private ArrayList<CParticle> objects; //array of objects in the current node
    private Rectangle bounds; //the boundaries of the current node
    private Quadtree[] nodes; //array for holding four child nodes
    public double size; //diameter of the particle
    private double qtsize; //size of the initial quadtree node (used for particle offset)

    public Quadtree(int pLevel, Rectangle pBounds, double s, double qts) {
        level = pLevel;
        objects = new ArrayList();
        bounds = pBounds;
        nodes = new Quadtree[4];
        size = s;
        qtsize = qts;
    }
    
    public int getWidth() { //returns the width of the node
        return bounds.width;
    }
    
    public int getHeight() { //returns the height of the node
        return bounds.height;
    }
    
    public void clear() { //clears all nodes in the quadtree
        objects.clear();
 
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] != null) {
                nodes[i].clear();
                nodes[i] = null;
            }
        }
    }
    
    private void split() { //creates four child nodes within the bounds of the current quadtree node
        int subWidth = (int)(bounds.width / 2);
        int subHeight = (int)(bounds.height / 2);
        int x = (int)bounds.getX();
        int y = (int)bounds.getY();
        nodes[0] = new Quadtree(level+1, new Rectangle(x + subWidth, y, subWidth, subHeight), size, qtsize);
        nodes[1] = new Quadtree(level+1, new Rectangle(x, y, subWidth, subHeight), size, qtsize);
        nodes[2] = new Quadtree(level+1, new Rectangle(x, y + subHeight, subWidth, subHeight), size, qtsize);
        nodes[3] = new Quadtree(level+1, new Rectangle(x + subWidth, y + subHeight, subWidth, subHeight), size, qtsize);
    }
    private int getIndex(CParticle pCryst) { //recurses through child nodes to find where to place new objects
        int index = -1;
        double verticalMidpoint = bounds.x + (bounds.width / 2);
        double horizontalMidpoint = bounds.y + (bounds.height / 2);
        double px = pCryst.getPos().getX() + qtsize/2;
        double py = pCryst.getPos().getY() + qtsize/2;

        // determines if the object is in the top or bottom quadrant
        boolean topQuadrant = (py < horizontalMidpoint && py + size < horizontalMidpoint);
        boolean bottomQuadrant = (py > horizontalMidpoint); //
 
        // Object can completely fit within the left quadrants
        if (px < verticalMidpoint && px + size < verticalMidpoint) {
            if (topQuadrant) {
                index = 1;  //top left node
            }
            else if (bottomQuadrant) {
                index = 2;  //bottom left node
            }
        }
        
        // Object can completely fit within the right quadrants
        else if (px > verticalMidpoint) {
            if (topQuadrant) {
                index = 0;  //top right node
            }
            else if (bottomQuadrant) {
                index = 3;  //bottom right node
            }
        }
        return index;
    }
    public void insert(CParticle pCryst) {
        if (nodes[0] != null) { //if the current node has children
            int index = getIndex(pCryst); //find what quadrant it belongs in
 
            if (index != -1) { //if the child node exists (error catch)
                nodes[index].insert(pCryst); //repeat insert for child node
                return;
            }
        }
 
        objects.add(pCryst); //adds the object to the node once a leaf is found
 
        if (objects.size() > MAX_OBJECTS && level < MAX_LEVELS) { //if there are too many objects in the node and maxlevels hasn't been reached
            if (nodes[0] == null) { 
                split(); //split the node
            }
 
            int i = 0;
            while (i < objects.size()) { //copies the objects of the current node to the new child nodes
                int index = getIndex(objects.get(i)); //determines what subnodes each object is copied to
                if (index != -1) {
                    nodes[index].insert(objects.remove(i));
                }
                else {
                    i++;
                }
            }
        }
    }
    public ArrayList retrieve(ArrayList returnObjects, CParticle pCryst) { //finds what node "pCryst" would fall into and returns objects from that node.
        int index = getIndex(pCryst);
        if (index != -1 && nodes[0] != null) {
            nodes[index].retrieve(returnObjects, pCryst);
        }
            returnObjects.addAll(objects);
        return returnObjects;
    }
}
