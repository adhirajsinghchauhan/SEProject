/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Adhiraj
 */
public class Home extends javax.swing.JFrame {

    private String userType = "government";

    /**
     * Creates new form User
     */
    public Home() {
        initComponents();
        jScrollPane3.setVisible(false);
        setUpTreeNodes();
    }

    public Home(String userType) {
        this.userType = userType;
        initComponents();
        jScrollPane3.setVisible(false);
        setUpTreeNodes();
    }

    /**
     * Citizen: Can view all policies, but no replies allowed. Can apply for visa too
     *
     * Trader: Everything that citizen can do + apply for an export license
     *
     * Government: View all request from citizens (visa) and traders (export), and accept/reject them
     */
    private void setUpTreeNodes() {
        DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Home");
        if (userType.equals("citizen")) {
            jTree1.removeAll();
            DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
            DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for visa");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            this.setTitle("Citizen");
        } else if (userType.equals("trader")) {
            jTree1.removeAll();
            DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
            DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for visa");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("application 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for export");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("export 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("export 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("export 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("export 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            this.setTitle("Trader");
        } else if (userType.equals("government")) {
            DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
            DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Replies");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Requests");
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 1");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 2");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 3");
            treeNode2.add(treeNode3);
            treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 4");
            treeNode2.add(treeNode3);
            treeNode1.add(treeNode2);
            this.setTitle("Government");
        }
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        searchPanel = new org.jdesktop.swingx.JXSearchPanel();
        menuBar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        exitMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        selectAllMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));

        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new java.awt.Dimension(1280, 520));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Choose a category from the sidebar on the left");
        jLabel1.setToolTipText("");

        jScrollPane3.setPreferredSize(new java.awt.Dimension(1080, 620));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Name", "Country", "Status"
            }
        ));
        jTable1.setShowHorizontalLines(true);
        jScrollPane3.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 998, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(276, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 670, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setViewportView(jPanel1);

        splitPane.setRightComponent(jScrollPane1);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Home");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 2");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 3");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("policy 4");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 2");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 3");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("announcement 4");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Replies");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 2");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 3");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("reply 4");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Requests");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 1");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 2");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 3");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("request 4");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.setPreferredSize(new java.awt.Dimension(250, 76));
        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTree1ValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        splitPane.setLeftComponent(jScrollPane2);

        jMenu1.setText("File");

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(newMenuItem);
        jMenu1.add(jSeparator2);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.setToolTipText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(exitMenuItem);

        logoutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        logoutMenuItem.setLabel("Logout");
        jMenu1.add(logoutMenuItem);

        menuBar.add(jMenu1);

        jMenu2.setText("Edit");

        cutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutMenuItem.setText("Cut");
        jMenu2.add(cutMenuItem);

        copyMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyMenuItem.setText("Copy");
        jMenu2.add(copyMenuItem);

        pasteMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteMenuItem.setText("Paste");
        jMenu2.add(pasteMenuItem);
        jMenu2.add(jSeparator1);

        selectAllMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        selectAllMenuItem.setText("Select All");
        jMenu2.add(selectAllMenuItem);

        menuBar.add(jMenu2);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setBounds(0, 0, 1298, 767);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        // TODO add your handling code here:
        JPanel exit = new JPanel();
        exit.setLayout(new BoxLayout(exit, BoxLayout.Y_AXIS));
        exit.add(new JLabel("Are you sure you want to exit?"));
        int result = JOptionPane.showConfirmDialog(this, exit, "Confirm", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void jTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTree1ValueChanged
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        Object nodeInfo = node.getUserObject();
        if (node.getUserObject().toString().equals("Home")) {
            jLabel1.setText("Choose a category from the sidebar on the left");
            jScrollPane3.setVisible(false);
            jScrollPane3.setSize(jPanel1.getWidth(), jPanel1.getHeight());
        } else {
            jLabel1.setText(nodeInfo.toString());
            jScrollPane3.setVisible(true);
            try {
                Connection connection = DriverManager.getConnection("url", "user", "password");
                Statement statement = connection.createStatement();
                // Get all results from the database
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + nodeInfo.toString());
                jTable1.setModel(Main.buildTableModel(resultSet));
            } catch (SQLException sqlException) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, sqlException);
            }
            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    switch (userType) {
                        case "citizen":
                            new VisaApplication().setVisible(true);
                            break;
                        case "trader":
                            new ExportApplication().setVisible(true);
                            break;
                        case "government":
                            // TODO: Add code for new policy/announcement
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }//GEN-LAST:event_jTree1ValueChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private org.jdesktop.swingx.JXSearchPanel searchPanel;
    private javax.swing.JMenuItem selectAllMenuItem;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables
}
