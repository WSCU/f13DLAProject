/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package f13dlaproject;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.Timer;
import static f13dlaproject.Particle2D.*;
import static f13dlaproject.Crystal2D.*;
import static f13dlaproject.Particle3D.*;
import static f13dlaproject.Crystal3D.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

/**
 *
 * @author jiig
 */
public class DLAFrame extends javax.swing.JFrame {

    /**
     * delays time by 30 units.
     */
    public int timerDelay = 30;
    /**
     *
     */
    public static boolean autoZ = true;
    /**
     *
     */
    public static int cx, cy, dx, dy = 0;
    /**
     *
     */
    public static boolean paused = true;
    /**
     * tells whether to display the individual particles moving
     */
    public static volatile boolean display = true;
    /**
     * width of the panel
     */
    public static final int WIDTH = 600;
    /**
     * height of the panel
     */
    public static final int HEIGHT = 400;
    /**
     * holds time for the moveloop
     */
    public static long time = 0;
    /**
     * Thread for the moveloop
     */
    public volatile Thread t;
    
    public static Color[] cArray = {Color.RED};

    /**
     * Creates new form DLAFrame
     */
    
    /**
     * creates the particle variable(can hold 2D or 3D)
     */
    public Particle p;
    /**
     * creates the crystal variable(hold 2 or 3 D)
     */
    public Crystal c;
    
    
    public DLAFrame() {
        p = particle2D();
        c = crystal2D();
        initComponents();
        //Particle p = particle2D();  

    }

    /**
     * Loop for multi-threaded display. Moves the particle2D every pass through
     * the loop. Hangs the thread at the interval to paint the screen.
     */
    public class Moveloop implements Runnable {

        @Override
        public void run() {
            Thread thisThread = Thread.currentThread();
            while (t == thisThread) {
                try {
                    while (!display) {
                        p.setAngle();
                        p.move();
                        time++;
                        if (time % Integer.parseInt(intervalfield.getText()) == 0) {
                            Thread.sleep(50);
                            updateLabels();
                            repaint();
                        }
                        //System.out.println("HELLO");
                    }
                    
                } catch (InterruptedException e) {
                    return;
                }
            }
        }

    }

    /**
     * Starts the moveloop thread.
     */
    public void start() {
        t = new Thread(new Moveloop());
        t.start();
    }

    /**
     * Stops the moveloop thread.
     */
    public void stop() {
        t = null;
    }
    private void displayGUI() {
        final JFrame frame2 = new JFrame("Screenshot Dialog");
        frame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame2.addWindowListener(new WindowListener() {
            @Override
            public void windowClosing(WindowEvent e) {
                    frame2.setVisible(false);
                    frame2.dispose();
            }

            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e){
            }
        });
        JPanel contentPane = new JPanel();
        contentPane.setBorder(
        BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1 = new DialogPanel(contentPane);
        contentPane.add(panel1, "Panel 1");
        frame2.setContentPane(contentPane);
        frame2.pack();   
        frame2.setLocationByPlatform(true);
        frame2.setVisible(true);
 
    }
    
    class DialogPanel extends JPanel {

        private JTextField How;
        private JLabel exlabel;
        private JButton exbutton;
        private JPanel contentPane;

        public DialogPanel(JPanel panel) {

            contentPane = panel;
            How = new JTextField (1);
            exlabel = new JLabel ("Example Label:");
            exbutton = new JButton ("Button");
            //adjust size and set layout
            setPreferredSize (new Dimension (315, 85));
            setLayout (null);

            //set component bounds (only needed by Absolute Positioning)
            How.setBounds (100, 50, 200, 25);
            exlabel.setBounds (0, 50, 100, 20);
            exbutton.setLocation(0, 0);
            exbutton.setSize(100, 25);
            exbutton.addActionListener( new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Test Action");
                }
            });

        //add components
            add (How);
            add (exlabel);
            add (exbutton);
        }
    }

    public class MyPanel extends JPanel {

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            p.draw(g);
            c.draw(g);
        }
    }
    /**
     * Measures the time.
     *
     * @param timerDelay delays clock by 30 units.
     * @param ActionListener the current time.
     */
    public Timer clock = new Timer(timerDelay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            tick();
        }
    });

    /**
     * Repaints at each tick. Resets the particles launched and crystal size
     * text at each tick. Also repaints the window as particles move.
     */
    public void tick() {

        if (display) {
            updateLabels();
            p.setAngle();
            //System.out.println("hello");
            p.move();
            repaint();
        }
        zoomFactor.setValue((int) c.getZoom());
    }

    /**
     * Updates the particles launched and crystal size labels.
     */
    public void updateLabels() {
        launchedLabel.setText("Particles Launched: " + p.getLaunched());
        sizeLabel.setText("Crystal Size: " + c.getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem2 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem3 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem4 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem5 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem6 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem7 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem8 = new javax.swing.JRadioButtonMenuItem();
        jRadioButtonMenuItem9 = new javax.swing.JRadioButtonMenuItem();
        jPanel1 = new MyPanel();
        sizeLabel = new javax.swing.JLabel();
        launchedLabel = new javax.swing.JLabel();
        afield = new javax.swing.JTextField();
        alabel = new javax.swing.JLabel();
        velocitylabel = new javax.swing.JLabel();
        velocityfield = new javax.swing.JTextField();
        clearButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        displayCheck = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        intervalfield = new javax.swing.JTextField();
        zoomCheck = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        zoomFactor = new javax.swing.JSlider();
        cDim = new javax.swing.JComboBox();
        colorStrat = new javax.swing.JComboBox();
        colorBox = new javax.swing.JComboBox();
        colors = new javax.swing.JButton();
        addArray = new javax.swing.JButton();

        popUp.setInvoker(colors);

        jRadioButtonMenuItem1.setText("Red");
        popUp.add(jRadioButtonMenuItem1);

        jRadioButtonMenuItem2.setText("Green");
        popUp.add(jRadioButtonMenuItem2);

        jRadioButtonMenuItem3.setText("Blue");
        popUp.add(jRadioButtonMenuItem3);

        jRadioButtonMenuItem4.setText("Yellow");
        popUp.add(jRadioButtonMenuItem4);

        jRadioButtonMenuItem5.setText("Orange");
        popUp.add(jRadioButtonMenuItem5);

        jRadioButtonMenuItem6.setText("Cyan");
        popUp.add(jRadioButtonMenuItem6);

        jRadioButtonMenuItem7.setText("Magenta");
        popUp.add(jRadioButtonMenuItem7);

        jRadioButtonMenuItem8.setText("Black");
        popUp.add(jRadioButtonMenuItem8);

        jRadioButtonMenuItem9.setText("White");
        popUp.add(jRadioButtonMenuItem9);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        setResizable(true);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(600, 400));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        sizeLabel.setText("Crystal Size: 0");

        launchedLabel.setText("Particles Launched: 0");

        afield.setText("0");
        afield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                afieldActionPerformed(evt);
            }
        });

        alabel.setText("a:");

        velocitylabel.setText("Velocity:");

        velocityfield.setText("0.0");
        velocityfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                velocityfieldActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("Pause");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        displayCheck.setText("Display Particle");
        displayCheck.setSelected(true);
        displayCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayCheckActionPerformed(evt);
            }
        });

        jLabel1.setText("Display Interval:");

        intervalfield.setText("100");
        intervalfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                intervalfieldActionPerformed(evt);
            }
        });

        zoomCheck.setSelected(true);
        zoomCheck.setText("Auto Zoom");
        zoomCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomCheckActionPerformed(evt);
            }
        });

        jLabel2.setText("Zoom Level: ");

        zoomFactor.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                zoomFactorStateChanged(evt);
            }
        });

        cDim.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2D", "3D" }));
        cDim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDimActionPerformed(evt);
            }
        });

        colorStrat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard Distance", "Standard Time", "Ring", "ProgPie", "Pie", "Structure", "Nuclear", " " }));
        colorStrat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorStratActionPerformed(evt);
            }
        });

        colorBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Green", "Blue", "Yellow", "Orange", " " }));

        colors.setText("Choose Colors(right-click)");
        colors.setComponentPopupMenu(popUp);
        colors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                colorsActionPerformed(evt);
            }
        });

        addArray.setText("Make an Array of Colors");
        addArray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addArrayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(zoomCheck)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zoomFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(47, 47, 47)
                            .addComponent(alabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(afield, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7))
                        .addComponent(launchedLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(intervalfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(displayCheck, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pauseButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cDim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(colorStrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(velocitylabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(velocityfield, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(sizeLabel))
                        .addGap(40, 40, 40)
                        .addComponent(colorBox, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(colors)
                    .addComponent(addArray))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(clearButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(startButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(colorStrat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)))
                .addComponent(pauseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayCheck)
                    .addComponent(cDim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(intervalfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(zoomCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(zoomFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velocitylabel)
                    .addComponent(velocityfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(alabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(afield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(launchedLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sizeLabel)
                .addGap(38, 38, 38)
                .addComponent(colors)
                .addGap(38, 38, 38)
                .addComponent(addArray)
                .addGap(135, 135, 135))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void afieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_afieldActionPerformed
        p.setA(Double.parseDouble(afield.getText()));
    }//GEN-LAST:event_afieldActionPerformed

    private void velocityfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_velocityfieldActionPerformed
        p.setVelocity(Double.parseDouble(velocityfield.getText()));
    }//GEN-LAST:event_velocityfieldActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        clock.start();
        p.setVelocity(Double.parseDouble(velocityfield.getText()));
        p.setA(Double.parseDouble(afield.getText()));
        paused = false;
    }//GEN-LAST:event_startButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        clock.stop();
        paused = true;
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void displayCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayCheckActionPerformed
        display = displayCheck.isSelected();
        if (!display) {
            start();
        } else {
            stop();
        }
    }//GEN-LAST:event_displayCheckActionPerformed

    private void intervalfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_intervalfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_intervalfieldActionPerformed

    private void zoomCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomCheckActionPerformed
        // TODO add your handling code here:
        if (autoZ) {
            autoZ = false;
        } else {
            autoZ = true;
        }

    }//GEN-LAST:event_zoomCheckActionPerformed

    private void zoomFactorStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_zoomFactorStateChanged
        // TODO add your handling code here:
        if (!autoZ) {
            c.setZoom(zoomFactor.getValue());
        }
    }//GEN-LAST:event_zoomFactorStateChanged

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        // TODO add your handling code here:
        cx = evt.getX();
        cy = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        // TODO add your handling code here:
        dx = (cx - evt.getX());
        dy = (cy - evt.getY());
        // System.out.println("huh?");
    }//GEN-LAST:event_jPanel1MouseDragged

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        p.clear();
        c.clear();
    }//GEN-LAST:event_clearButtonActionPerformed

    private void screenshotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_screenshotButtonActionPerformed
    displayGUI();
    }//GEN-LAST:event_screenshotButtonActionPerformed

    private void cDimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDimActionPerformed
        if(cDim.getSelectedIndex() == 1){
            p = particle3D();
            c = crystal3D();
        }
        else{
            p = particle2D();
            c = crystal2D();
        }
    }//GEN-LAST:event_cDimActionPerformed

    private void colorStratActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorStratActionPerformed
        Color[] co =  {Color.RED, Color.CYAN, Color.MAGENTA, Color.YELLOW, Color.BLUE, Color.gray};       
        if(colorStrat.getSelectedIndex() == 0){
            c.setColorStrategy(new StandardColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 1){
            c.setColorStrategy(new StandardTimeColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 2){
            c.setColorStrategy(new RingColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 3){
            c.setColorStrategy(new ProgPieColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 4){
            c.setColorStrategy(new PieColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 5){
            c.setColorStrategy(new StructColor(co));
        }
        else if(colorStrat.getSelectedIndex() == 6){
            c.setColorStrategy(new NuclearColor());
        }
    }//GEN-LAST:event_colorStratActionPerformed

    private void colorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_colorsActionPerformed
        
    }//GEN-LAST:event_colorsActionPerformed

    private void addArrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addArrayActionPerformed
        MenuElement[] ar = popUp.getSubElements();
        JRadioButtonMenuItem[] jar = new JRadioButtonMenuItem[ar.length];
        ArrayList<Color> cAList = new ArrayList<>(); 
        
        for(int i=0;i<ar.length;i++) {
            jar[i] = (JRadioButtonMenuItem)ar[i];
            if(jar[i].isSelected()) {
                if(i == 0) {
                    cAList.add(Color.RED);
                }
                if(i == 1) {
                    cAList.add(Color.GREEN);
                }
                if(i == 2) {
                    cAList.add(Color.BLUE);
                }
                if(i == 3) {
                    cAList.add(Color.YELLOW);
                }
                if(i == 4) {
                    cAList.add(Color.ORANGE);
                }
                if(i == 5) {
                    cAList.add(Color.CYAN);
                }
                if(i == 6) {
                    cAList.add(Color.MAGENTA);
                }
                if(i == 7) {
                    cAList.add(Color.BLACK);
                }
                if(i == 8) {
                    cAList.add(Color.WHITE);
                }                
            }            
        }
        cArray = new Color[cAList.size()];
        for(int i=0;i<cArray.length;i++) {
            cArray[i] = cAList.get(i);
            System.out.println(cArray[i]);
        }        
        if(colorStrat.getSelectedIndex() == 0){
            c.setColorStrategy(new StandardColor(cArray));
        }
        else if(colorStrat.getSelectedIndex() == 1){
            c.setColorStrategy(new StandardTimeColor(cArray));
        }
        else if(colorStrat.getSelectedIndex() == 2){
            c.setColorStrategy(new RingColor(cArray));
        }
        else if(colorStrat.getSelectedIndex() == 3){
            c.setColorStrategy(new ProgPieColor(cArray));
        }
        else if(colorStrat.getSelectedIndex() == 4){
            c.setColorStrategy(new PieColor(cArray));
        }
        else if(colorStrat.getSelectedIndex() == 5){
            c.setColorStrategy(new StructColor(cArray));
        }
    }//GEN-LAST:event_addArrayActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DLAFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DLAFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DLAFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DLAFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DLAFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addArray;
    private javax.swing.JTextField afield;
    private javax.swing.JLabel alabel;
    private javax.swing.JComboBox cDim;
    private javax.swing.JButton clearButton;
    private javax.swing.JComboBox colorBox;
    private javax.swing.JComboBox colorStrat;
    private javax.swing.JButton colors;
    private javax.swing.JCheckBox displayCheck;
    private javax.swing.JTextField intervalfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem2;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem3;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem4;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem5;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem6;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem7;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem8;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem9;
    private javax.swing.JLabel launchedLabel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JLabel sizeLabel;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField velocityfield;
    private javax.swing.JLabel velocitylabel;
    private javax.swing.JCheckBox zoomCheck;
    private javax.swing.JSlider zoomFactor;
    // End of variables declaration//GEN-END:variables
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
}
