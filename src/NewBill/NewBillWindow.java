package NewBill;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class NewBillWindow {
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
	
	// Table Variables
	private JTable positionsTable;
	private String[] billPositionsColumNames = new String[]{"Beschreibung", "Stunden", "Preis/h", "Kosten"};
	private final DefaultTableModel model = new DefaultTableModel( billPositionsColumNames, 1 );
	
	//Button for adding row in table
	private JButton buttonAddRow;
	
	// Strings for creating labels
	private String[] labels = {"Kundenname: ", "Adresszusatz: ", "Adresse: ", "PLZ und Ort: ",
								"Datum: ", "Projekt: ", "Rechnungsnr.: ", "Zahlungsziel: ", 
								"Bemerkung"};
	
	public void NewBillWindowShow() {
		newBillJFrame = new JFrame("Neue Rechnung");
		newBillJFrame.setSize(WIDTH, HEIGHT);
		newBillJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(5, 4, 5, 5));
		
		billPositions = new JPanel(new BorderLayout());
		
		createLabelsAndTextFields(labelPanel, labels);
		createBillPositions();
		layout();
		
		newBillJFrame.setVisible(true);
	}
	
	private void layout() {
		
		newBillJFrame.getContentPane().add(labelPanel, BorderLayout.PAGE_START);
		newBillJFrame.getContentPane().add(billPositions, BorderLayout.CENTER);
		billPositions.add(scrollPane, BorderLayout.CENTER);
		billPositions.add(buttonAddRow, BorderLayout.NORTH);
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
		positionsTable.setModel(model);
		scrollPane = new JScrollPane(positionsTable);
//		positionsTable.setFillsViewportHeight(true);
		
		buttonAddRow = new JButton( "Zeile hinzufügen" );
		buttonAddRow.addActionListener( new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Die Anzahl Columns (Breite) der Tabelle
				int size = model.getColumnCount();
				
				// einen neuen Vector mit Daten herstellen
				Vector newDatas = createDataVector( "row", size );
				
				// eine neue Row hinzufügen
				model.addRow( newDatas );
			}
		});
	}
	
	private Vector createDataVector( String prefix, int size ){
		Vector vector = new Vector( size );
		for( int i = 0; i < size; i++ )
			vector.add("");
		
		return vector;
	}
}
