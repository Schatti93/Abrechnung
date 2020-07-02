package NewBill;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewBillWindow implements ActionListener, FocusListener{
	// Window size
	private final int HEIGHT = 900;
	private final int WIDTH = 600;
	
	//Main frame
	private JFrame newBillJFrame;
	
	// Panel for the first labels and Textfields
	private JPanel labelPanel;
	
	// panels for the Table
	private JPanel billPositions;
	private JScrollPane scrollPane;
	
	// panel for save and quit
	private JPanel SQPanel;
	
	// Table Variables
	private JTable positionsTable;
	private String[] billPositionsColumNames = new String[]{"Beschreibung", "Stunden", "Preis/h", "Kosten in €"};
	private final DefaultTableModel model = new DefaultTableModel( billPositionsColumNames, 1 );
	
	//Button for adding row in table
	private JButton buttonAddRow;
	
	// Buttons for save and quit
	private JButton save;
	private JButton quit;
	
	// result of Table FocusLost event
	private float result;
	
	// Strings for creating labels
	private String[] labels = {"Kundenname: ", "Adresszusatz: ", "Adresse: ", "PLZ und Ort: ",
								"Datum: ", "Projekt: ", "Rechnungsnr.: ", "Zahlungsziel: ", 
								"Bemerkung:"};
	
	public void NewBillWindowShow() {
		newBillJFrame = new JFrame("Neue Rechnung");
		newBillJFrame.setSize(WIDTH, HEIGHT);
		newBillJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(5, 4, 5, 5));
		
		billPositions = new JPanel(new BorderLayout());
		
		createLabelsAndTextFields(labelPanel, labels);
		createBillPositions();
		creatSaveQuit();
		
		layout();
		
		newBillJFrame.setVisible(true);
	}
	
	private void layout() {
		
		newBillJFrame.getContentPane().add(labelPanel, BorderLayout.PAGE_START);
		newBillJFrame.getContentPane().add(billPositions, BorderLayout.CENTER);
		
		billPositions.add(scrollPane, BorderLayout.CENTER);
		billPositions.add(buttonAddRow, BorderLayout.NORTH);
		billPositions.add(SQPanel, BorderLayout.SOUTH);
		

	}
	
	private void createLabelsAndTextFields(JPanel panel, String[] strings) {
		
		for(String name : strings) {
			JLabel item = new JLabel(name);
			panel.add(item);
			
			JTextField item2 = new JTextField();
			item2.setPreferredSize(new Dimension(400, 30));
			panel.add(item2);
		}
//		TODO add save button -> get components text 1, 3, 5, 7 ...
//		JTextField item = (JTextField)customer.getComponent(1);
//		System.out.println(item.getText());
	}
	
	private void createBillPositions() {
		
		positionsTable = new JTable();
		positionsTable.addFocusListener(this);
		
		
		positionsTable.setModel(model);
		scrollPane = new JScrollPane(positionsTable);
		positionsTable.setFillsViewportHeight(true);
				
		buttonAddRow = new JButton( "Zeile hinzufügen" );
		buttonAddRow.addActionListener(this);
	}
	
	private void creatSaveQuit() {
		SQPanel = new JPanel(new FlowLayout());
		save = new JButton("Speichern");
		quit = new JButton("Abbrechen");
		
		save.addActionListener(this);
		quit.addActionListener(this);
		
		SQPanel.add(save);
		SQPanel.add(quit);
	}
	
	
	private Vector createDataVector( String prefix, int size ){
		Vector vector = new Vector( size );
		for( int i = 0; i < size; i++ )
			vector.add("");
		return vector;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
		
		case "Abbrechen":
			newBillJFrame.dispose();
			break;
			
		case "Speichern":
			// TODO save in class > think about hibernate
			break;
			
		case "Zeile hinzufügen":
			// Die Anzahl Columns (Breite) der Tabelle
			int size = model.getColumnCount();
			
			// einen neuen Vector mit Daten herstellen
			Vector newDatas = createDataVector( "row", size );
			
			// eine neue Row hinzufügen
			model.addRow( newDatas );
			
		default:
			break;
		}
		
	}

	@Override
	public void focusGained(FocusEvent e) {}

	@Override
	public void focusLost(FocusEvent e) {
		int row, col;
		JTable table = (JTable) e.getSource();
		row = table.getSelectedRow();
		col = table.getSelectedColumn();
		
		if(col == 2) {
			//result = (float)table.getValueAt(row, col);
			String col2 = (String)table.getValueAt(row, col - 1);
			String col3 = (String)table.getValueAt(row, col);
			result = Float.parseFloat(col2) * Float.parseFloat(col3);
			String s = String.valueOf(result);
			model.setValueAt(s, row, 3);
			
			System.out.println(s);
		}
	}
}
