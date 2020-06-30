package Menu;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import listener.MenuMouseListener;

public class  BillingMenu {
	private JMenuBar billingMenu;
	
	public BillingMenu() {
		createMenu();
	}
	
	private void createMenu() {
		// creates the JMenuBar
		billingMenu = new JMenuBar();
		
		//Menu entrys
		JMenu file = new JMenu("datei");
		
		//Entry items
		JMenuItem newBill = new JMenuItem("Neue Rechnung");
		
		// Actions 
		newBill.addActionListener(new MenuMouseListener());
		
		// Add JMenu
		billingMenu.add(file);
		
		//Add JMenuItem
		file.add(newBill);
	}

	public JMenuBar getBillingMenu() {
		return billingMenu;
	}

	public void setBillingMenu(JMenuBar billingMenu) {
		this.billingMenu = billingMenu;
	}
	
}
