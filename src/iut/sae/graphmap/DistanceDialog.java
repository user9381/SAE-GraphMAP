/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package iut.sae.graphmap;

import iut.sae.graphmap.models.Node;
import iut.sae.graphmap.models.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 * Represents the distance-table dialog
 * @author Jonathan MONTMAIN <jmontmain at gmail.com>
 */
public class DistanceDialog extends javax.swing.JDialog {
    
    /**
     * Model of the table
     */
    private final TableModel model;

    /**
     * Instanciates a new dialog window
     * @param parent Parent of the dialog
     * @param model Model of the distance-table
     */
    public DistanceDialog(java.awt.Frame parent, TableModel model) {
        super(parent, true);
        this.model = model;
        initComponents();
    }
    
    /**
     * Shows the dialog
     * @param parent Parent of the dialog
     * @param from Initial node
     * @param paths List of Path
     * @param maxDistance Maximum distance of a single Path
     */
    public static void showDialog(java.awt.Frame parent, Node from, List<Path> paths, int maxDistance) {
        List<Path> sortedAndFilteredPaths = new ArrayList<>();
        for (Path path : paths) {
            if (path.getEdges().size() <= maxDistance && path.getDistance() >= 0) sortedAndFilteredPaths.add(path);
        }
        sortedAndFilteredPaths.sort((Path a, Path b) -> {
            if (a.getEdges().size() == b.getEdges().size()) return 0;
            if (a.getEdges().size() < b.getEdges().size()) return -1;
            return 1;
        });
        DistanceDialog window = new DistanceDialog(parent, new DistanceTableModel(sortedAndFilteredPaths));
        window.setTitle("Chemins partant de " + from.getName());
        window.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static class DistanceTableModel extends AbstractTableModel {
        
        private final String[] columnsNames;
        private final List<Path> paths;
        
        public DistanceTableModel(List<Path> paths) {
            this.columnsNames = new String[]{"Destination", "Chemin", "Distance"};
            this.paths = paths;
        }

        @Override
        public int getRowCount() {
            return this.paths.size();
        }

        @Override
        public int getColumnCount() {
            return this.columnsNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return this.columnsNames[column];
        }              

        @Override
        public Object getValueAt(int rowIndex, int colIndex) {
            Path path = this.paths.get(rowIndex);
            
            switch (colIndex) {
                case 0:
                    return path.getTo().getName();
                case 1:
                    return path.toString();
                case 2:
                    return path.getDistance();
                default:
                    return null;
            }
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
