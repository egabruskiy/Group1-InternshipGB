package windowGUI.component.workDirectory;

import windowGUI.editingDirectoryWindow.add.AddSiteWindow;
import windowGUI.editingDirectoryWindow.delete.DelSiteWindow;
import windowGUI.editingDirectoryWindow.edit.EditSiteWindow;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import static java.awt.GridBagConstraints.REMAINDER;

/*
 * Класс-справочник, отвечающий за функциональную деятельность справочника Sites
 * */
public class SitesDirectory extends Directory{
    private static final String TAB_NAME = "Сайты";

    private static String nameSites ;
    private JTable dataTable;
    private JScrollPane dataScrollPane;

    public SitesDirectory() {
        setNameTab(TAB_NAME);
    }

    @Override
    public void fillOptionsPanel() {
        getGBL().setConstraints(getBtnRefresh(), getCGBL().configGBC(REMAINDER,false));
        getOptionsPanel().add(getBtnRefresh());
    }

    @Override
    public void initDataTable(){
        dataTable = new JTable(getPSitesT().getArrayFillTable(getColumnNames().length), getColumnNames());
        dataTable.getSelectionModel().addListSelectionListener(this::initSelectedRow);
        dataScrollPane = new JScrollPane(dataTable);
        getPanelDirectory().add(dataScrollPane, BorderLayout.CENTER);
    }

    @Override
    public void refreshDataTable(ActionEvent actionEvent) {
        removeDataTable(dataScrollPane);
        initDataTable();
    }

    @Override
    public void initSelectedRow(ListSelectionEvent selectionEvent){
        TableModel model = dataTable.getModel();
        Object value = model.getValueAt(dataTable.getSelectedRow(), 0);
        nameSites = (String) value;
    }

    @Override
    public void visibleWindowAdd(ActionEvent actionEvent) {
        new AddSiteWindow(getBtnAdd().getText() + " новую личность");
    }

    @Override
    public void visibleWindowDel(ActionEvent actionEvent) {
        if(nameSites == null ){
            JOptionPane.showMessageDialog(null,
                    "Для удаления сайта необходимо выбрать сайт из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new DelSiteWindow(getBtnDelete().getText() + " сайт ",
                    nameSites,
                    getPSitesT().getIDSiteByNameSite(nameSites));
            nameSites = null;
        }
    }

    @Override
    public void visibleWindowEdit(ActionEvent actionEvent) {
        if(nameSites == null){
            JOptionPane.showMessageDialog(null,
                    "Для редактирования сайта необходимо выбрать сайт из списка",
                    "Не инициализированы поля",
                    JOptionPane.WARNING_MESSAGE);
        }else {
            new EditSiteWindow(getBtnEdit().getText() + " сайт ",
                    nameSites,
                    getPSitesT().getIDSiteByNameSite(nameSites),
                    getPSitesT().getURLSiteByNameSite(nameSites),
                    getPSitesT().getActiveSiteByNameSite(nameSites));
            nameSites = null;
        }
    }
}
