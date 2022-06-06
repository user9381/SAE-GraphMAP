package iut.sae.graphmap;

import iut.sae.graphmap.models.RecentFilesListModel;
import java.beans.PropertyChangeEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Represents the file selection dialog
 * @author Jonathan MONTMAIN <jonathan.montmain at gmail.com>
 */
public class FileSelectionDialog extends javax.swing.JDialog {
    
    /**
     * Represents the List model of the recent files
     */
    private final RecentFilesListModel recentFilesListModel;
    
    /**
     * Represents the selected file
     */
    private String selectedFile;
    
    /**
     * Represents the submitted file
     */
    private File submittedFile;

    /**
     * Instanciates a new dialog window
     * @param parent Parent of the dialog
     */
    public FileSelectionDialog(java.awt.Frame parent) {
        super(parent, true);
        String[] files;
        try {
            files = getRecentFiles();
        } catch(IOException ex) {
            files = null;
        }
        recentFilesListModel = new RecentFilesListModel();
        if (files != null) {
            for (var file : files) {
                recentFilesListModel.addElement(file);
            }
        }
        selectedFile = null;
        submittedFile = null;
        setTitle("GraphMap");
        initComponents();
        recentFilesList.addListSelectionListener((ListSelectionEvent e) -> {
            if (e.getSource() == recentFilesList) {
                selectedFile = recentFilesListModel.getRawElementAt(recentFilesList.getSelectedIndex());
            }
        });
        FileFilter filter = new FileNameExtensionFilter("Document CSV (.csv)", "csv");
        fileChooser.addChoosableFileFilter(filter);
        fileChooser.setFileFilter(filter);
        fileChooser.addPropertyChangeListener((PropertyChangeEvent e) -> {
            if (e.getSource() == fileChooser && e.getNewValue() != null) {
                selectedFile = e.getNewValue().toString();
                recentFilesList.clearSelection();
            }
        });
    }
    
    /**
     * Shows the dialog
     * @param parent Parent of the dialog
     * @return Chosen file
     */
    public static File showDialog(java.awt.Frame parent, boolean graphState) {
        FileSelectionDialog window = new FileSelectionDialog(parent);
        if (graphState) window.graphStateLabel.setText("Un graphe est déjà ouvert !");
        window.setVisible(true);
        return window.submittedFile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rightPanel = new javax.swing.JPanel();
        recentFilesPanel = new javax.swing.JPanel();
        recentFilesListScrollPane = new javax.swing.JScrollPane();
        recentFilesList = new javax.swing.JList<>();
        bottomPanel = new javax.swing.JPanel();
        openFileButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        topPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        graphStateLabel = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        fileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        rightPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        rightPanel.setPreferredSize(new java.awt.Dimension(300, 380));

        recentFilesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Fichiers récents"));

        recentFilesList.setModel(recentFilesListModel);
        recentFilesList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        recentFilesListScrollPane.setViewportView(recentFilesList);

        javax.swing.GroupLayout recentFilesPanelLayout = new javax.swing.GroupLayout(recentFilesPanel);
        recentFilesPanel.setLayout(recentFilesPanelLayout);
        recentFilesPanelLayout.setHorizontalGroup(
            recentFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recentFilesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recentFilesListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                .addContainerGap())
        );
        recentFilesPanelLayout.setVerticalGroup(
            recentFilesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recentFilesPanelLayout.createSequentialGroup()
                .addComponent(recentFilesListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recentFilesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recentFilesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(rightPanel, java.awt.BorderLayout.LINE_END);

        openFileButton.setText("Ouvrir le fichier");
        openFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Annuler");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(422, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(openFileButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        titleLabel.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("GraphMap");

        graphStateLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        graphStateLabel.setText("Aucun graphe n'est actuellement ouvert");

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                    .addComponent(graphStateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphStateLabel))
        );

        getContentPane().add(topPanel, java.awt.BorderLayout.PAGE_START);

        fileChooser.setControlButtonsAreShown(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fileChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Handles "Open File" button
     * @param evt 
     */
    private void openFileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileButtonActionPerformed
        if (selectedFile != null) {
            File file = new File(selectedFile);
            if (file.isFile()) {
                if (!recentFilesListModel.contains(selectedFile)) {
                    BufferedWriter bw = null;
                    try {
                        new File("cache/graphmap_recentfiles.txt").getParentFile().mkdirs();
                        bw = new BufferedWriter(new FileWriter("cache/graphmap_recentfiles.txt", true));
                        bw.append(selectedFile + "\n");
                    } catch (IOException ex) {
                        Logger.getLogger(FileSelectionDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (bw != null) try {
                        bw.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FileSelectionDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                submittedFile = file;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Le fichier: \"" + selectedFile + "\" n'existe pas !", "Erreur lors de l'ouverture du fichier", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_openFileButtonActionPerformed

    /**
     * Handles "Cancel" button
     * @param evt 
     */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    /**
     * Retrieves recent files from a cache file
     * @return Array of recent files
     * @throws IOException 
     */
    private String[] getRecentFiles() throws IOException {
        File file = new File("cache/graphmap_recentfiles.txt");
        String content = new String(new FileInputStream(file).readAllBytes());
        String[] files = content.replace("\r", "").split("\n");
        return files;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JLabel graphStateLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton openFileButton;
    private javax.swing.JList<String> recentFilesList;
    private javax.swing.JScrollPane recentFilesListScrollPane;
    private javax.swing.JPanel recentFilesPanel;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel topPanel;
    // End of variables declaration//GEN-END:variables
}
