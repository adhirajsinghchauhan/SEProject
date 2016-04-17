/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seproject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Adhiraj
 */
public class Home extends javax.swing.JFrame {

    private String userType = "Government Official";
    private String emailID = null;
    private String userID;
    MySQLAccess msa = new MySQLAccess();
    private PublicKey pubkey;
    KeyFactory keyFactory;

    /**
     * Creates new form User
     */
    public Home() {
        try {
            this.keyFactory = KeyFactory.getInstance("DSA");
            initComponents();
            tableScrollPane.setVisible(false);
            setUpTreeNodes();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PublicKey blobToPublicKey(final byte[] blob) {
        Object result = null;
        final ByteArrayInputStream bais = new ByteArrayInputStream(blob);
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(bais);
            result = ois.readObject();
        } catch (final IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (PublicKey) result;
    }

    class WrongKeyException extends Exception {

    }

    boolean verify(String signature, String msg, PublicKey pubkey) {

        try {
            Signature veralg = Signature.getInstance("DSA");
            veralg.initVerify(pubkey);
            veralg.update(msg.getBytes());
            if (!veralg.verify(signature.getBytes())) {
                throw new WrongKeyException();
            }
            return true;
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WrongKeyException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Home(String userType, String emailID) {
        try {
            this.keyFactory = KeyFactory.getInstance("DSA");
            try {
                this.userType = userType;
                this.emailID = emailID;
                msa = new MySQLAccess();
                msa.loadDatabase();
                msa.preparedStatement = msa.connect.prepareStatement("select userID from user where emailID = ?");
                msa.preparedStatement.setString(1, emailID);
                msa.resultSet = msa.preparedStatement.executeQuery();
                msa.resultSet.next();
                this.userID = new String(msa.resultSet.getString(1));
                msa.close();
                initComponents();
                tableScrollPane.setVisible(false);
                setUpTreeNodes();
            } catch (Exception ex) {
                Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        switch (userType) {
            case "Citizen": {
                jTree1.removeAll();
                DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for Visa");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ask a Question");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Replies");
                treeNode1.add(treeNode2);
                this.setTitle("Citizen Portal");
                break;
            }
            case "Trader": {
                jTree1.removeAll();
                DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for Visa");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Apply for Export");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Ask a Question");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Replies");
                treeNode1.add(treeNode2);
                this.setTitle("Trader Portal");
                break;
            }
            case "Government Official": {
                jTree1.removeAll();
                DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Policies");
                DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Send Message");
                treeNode2.add(treeNode3);
                treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("View Unread Messages");
                treeNode2.add(treeNode3);
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Announcements");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Queries");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Trade Requests");
                treeNode1.add(treeNode2);
                treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Visa Applications");
                treeNode1.add(treeNode2);
                this.setTitle("Government Portal");
                break;
            }
            default:
                break;
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
        jPanel2 = new javax.swing.JPanel();
        tableScrollPane = new javax.swing.JScrollPane();
        resultTable = new javax.swing.JTable();
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

        splitPane.setDividerLocation(250);
        splitPane.setPreferredSize(new java.awt.Dimension(1280, 520));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1280, 720));

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Choose a category from the sidebar on the left");
        jLabel1.setToolTipText("");

        resultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "From", "Message", "On"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        resultTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableScrollPane.setViewportView(resultTable);
        resultTable.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 1){
                    if(SwingUtilities.isRightMouseButton(e)){

                    }
                }
                if (e.getClickCount() == 2) {
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    // do some action
                }
            }});

            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 967, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(19, Short.MAX_VALUE))
            );
            jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 41, Short.MAX_VALUE))
            );

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addContainerGap(282, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(58, Short.MAX_VALUE))
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
        tableScrollPane.setVisible(false);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        Object nodeInfo = node.getUserObject();
        JPanel message = new JPanel();
        message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));
        if (nodeInfo.toString().equals("Home")) {
            jLabel1.setText("Choose a category from the sidebar on the left");
            jPanel1.setVisible(true);
        } else {
            jLabel1.setText(nodeInfo.toString());
            tableScrollPane.setVisible(true);
            if (nodeInfo.toString().equals("Send Message")) {
                jPanel1.setVisible(true);
                new SendMessage(userID, "message").setVisible(true);
            } else if (nodeInfo.toString().equals("View Unread Messages")) {
                try {
                    resultTable.setVisible(true);
                    byte bytes[] = {};
                    msa.loadDatabase();
                    /*
                     * msa.preparedStatement = msa.connect.prepareStatement("select name,string,encryptedString,publicKey,timestamp from user join messages where senderID = userID and receiverID
                     * = ?");
                     * msa.preparedStatement.setString(1, userID);
                     * msa.resultSet = msa.preparedStatement.executeQuery();
                     * while(msa.resultSet.next()){
                     * Blob b = msa.resultSet.getBlob(4);
                     * bytes = b.getBytes(1, (int) b.length());
                     * EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytes);
                     * pubkey = keyFactory.generatePublic(publicKeySpec);
                     * if(verify(msa.resultSet.getString(3),msa.resultSet.getString(2),pubkey) == false){
                     * message.add(new JLabel("Decryption failed."));
                     * JOptionPane.showConfirmDialog(this, message, "Error", JOptionPane.OK_CANCEL_OPTION);
                     * System.exit(0);
                     * }
                     * }
                     */
                    msa.preparedStatement = msa.connect.prepareStatement("select name, string, timestamp from user join messages where senderID = userID and receiverID = ? and status = ?");
                    msa.preparedStatement.setString(1, userID);
                    msa.preparedStatement.setString(2, "Pending");
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (nodeInfo.toString().equals("Announcements")) {
                try {
                    msa.loadDatabase();
                    msa.preparedStatement = msa.connect.prepareStatement("select name,string,timestamp from user join messages where senderID = userID and status = ?");
                    msa.preparedStatement.setString(1, "Approved");
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nodeInfo.toString().equals("Apply for Visa") || nodeInfo.toString().equals("Apply for Export")) {
                String what = nodeInfo.toString().equals("Apply for Visa") ? "visa" : "export";
                new Application(this, this.userID, what).setVisible(true);
            } else if (nodeInfo.toString().equals("Ask a Question")) {
                new SendMessage(userID, "question").setVisible(true);
            } else if (nodeInfo.toString().equals("Visa Applications")) {
                try {
                    msa.loadDatabase();
                    msa.preparedStatement = msa.connect.prepareStatement("select country from user where userID = ?");
                    msa.preparedStatement.setString(1, userID);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    msa.resultSet.next();
                    String country = msa.resultSet.getString(1);
                    msa.preparedStatement = msa.connect.prepareStatement("select * from visaforms where country = ?");
                    msa.preparedStatement.setString(1, country);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nodeInfo.toString().equals("Export Applications")) {
                try {
                    msa.loadDatabase();
                    msa.preparedStatement = msa.connect.prepareStatement("select country from user where userID = ?");
                    msa.preparedStatement.setString(1, userID);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    msa.resultSet.next();
                    String country = msa.resultSet.getString(1);
                    msa.preparedStatement = msa.connect.prepareStatement("select * from exports where country = ?");
                    msa.preparedStatement.setString(1, country);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nodeInfo.toString().equals("Queries")) {
                try {
                    msa.loadDatabase();
                    msa.preparedStatement = msa.connect.prepareStatement("select name, query, timestamp from user join queries where receiverID = ? and isAnswered = ?");
                    msa.preparedStatement.setString(1, userID);
                    msa.preparedStatement.setInt(2, 0);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (nodeInfo.toString().equals("Replies")) {
                try {
                    msa.loadDatabase();
                    msa.preparedStatement = msa.connect.prepareStatement("select name, query, answer from user join queries where senderID = ? and isAnswered = ?");
                    msa.preparedStatement.setString(1, userID);
                    msa.preparedStatement.setInt(2, 1);
                    msa.resultSet = msa.preparedStatement.executeQuery();
                    resultTable.setModel(Main.buildTableModel(msa.resultSet));
                    msa.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (nodeInfo.toString().equals("Visa Applications") || nodeInfo.toString().equals("View Unread Messages") || nodeInfo.toString().equals("Trade Requests") || nodeInfo.toString().equals("Queries")) {
            resultTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    switch (userType) {
                        case "Government Official":
                            if (nodeInfo.toString().equals("Queries")) {
                                Object query = resultTable.getModel().getValueAt(resultTable.getSelectedRow(), 1);
                                new AnswerMessage(userID, query.toString()).setVisible(true);
                            } else {
                                String country = "";
                                try {
                                    msa.loadDatabase();
                                    msa.preparedStatement = msa.connect.prepareStatement("select country from user where userID = ?");
                                    msa.preparedStatement.setString(1, userID);
                                    msa.resultSet = msa.preparedStatement.executeQuery();
                                    msa.resultSet.next();
                                    country = msa.resultSet.getString(1);
                                } catch (SQLException ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                                }

                                Object string = resultTable.getModel().getValueAt(resultTable.getSelectedRow(), 1);
                                new Status(userID, country, string.toString()).setVisible(true);
                            }
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTree jTree1;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JTable resultTable;
    private org.jdesktop.swingx.JXSearchPanel searchPanel;
    private javax.swing.JMenuItem selectAllMenuItem;
    private javax.swing.JSplitPane splitPane;
    private javax.swing.JScrollPane tableScrollPane;
    // End of variables declaration//GEN-END:variables
}
