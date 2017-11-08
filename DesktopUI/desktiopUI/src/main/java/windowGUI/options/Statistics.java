package windowGUI.options;

import windowGUI.MyCalendar;
import windowGUI.options.workSQL.ProcessingPersonPageRankTable;
import windowGUI.options.workSQL.ProcessingSitesTable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class Statistics {
    private String tabName ;

    private final GridBagLayout GBL = new GridBagLayout();

    private JPanel panelStat = new JPanel();
    private JPanel optionsPanel = new JPanel();

    private final JLabel headlineSite = new JLabel(" Сайты: ");
    private final JButton btnConfirm = new JButton(" Подтвердить");

    private final ProcessingPersonPageRankTable PPersonPageRankT = new ProcessingPersonPageRankTable();

    private final ProcessingSitesTable PSitesT = new ProcessingSitesTable();
    private final JComboBox<Object> listSite = new JComboBox<>(PSitesT.getColumnName());
    String[] columnNames;
    JTable dataTable;
    JScrollPane dataScrollPane;

    private int numberStr = 0;

    Statistics() {
        panelStat.setLayout(new BorderLayout());
    }

    public abstract void fillOptionsPanel();

    public abstract void initNameSites(ActionEvent actionEvent);

    GridBagConstraints configGBC(Component component, boolean moveToNewLine){
        GridBagConstraints gbc =  new GridBagConstraints();
        if(component instanceof JLabel){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = 1;
            gbc.anchor = GridBagConstraints.EAST;
            return gbc;
        }
        if(component instanceof JComboBox || component instanceof MyCalendar){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        if(component instanceof JButton){
            if(moveToNewLine){
                numberStr++;
            }
            gbc.gridy = numberStr;
            gbc.gridwidth  = GridBagConstraints.REMAINDER ;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            return gbc;
        }
        return gbc;
    }

    void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabName() {
        return tabName;
    }

    public JPanel getPanelStat() {
        return panelStat;
    }

    JPanel getOptionsPanel() {
        return optionsPanel;
    }

    JLabel getHeadlineSite() {
        return headlineSite;
    }

    public ProcessingSitesTable getPSitesT() {
        return PSitesT;
    }

    JComboBox<Object> getListSite() {
        return listSite;
    }

    ProcessingPersonPageRankTable getPPersonPageRankT() {
        return PPersonPageRankT;
    }

    JButton getBtnConfirm() {
        return btnConfirm;
    }

    GridBagLayout getGBL() {
        return GBL;
    }
}
