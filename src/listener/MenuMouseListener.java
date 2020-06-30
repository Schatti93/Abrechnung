package listener;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import NewBill.NewBillWindow;

// takes only JMenuItems

public class MenuMouseListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem)e.getSource();
		if(item.getText() == "Neue Rechnung") {
			NewBillWindow window = new NewBillWindow();
			window.NewBillWindowShow();
		}
		
	}

	
}
