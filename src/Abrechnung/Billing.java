package Abrechnung;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import Menu.BillingMenu;

public class Billing {
	
	JFrame billingJframe;
	BillingMenu billingMenu;
	
	
	private final int BILLING_WIDTH = 900;
	private final int BILLING_HEIGHT = 600;
	
	public Billing() {
		billingJframe = new JFrame("Abrechnung");
		billingJframe.setSize(BILLING_WIDTH, BILLING_HEIGHT);
		billingJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add menu
		billingMenu = new BillingMenu();
		
		layout();
		
		
		billingJframe.setVisible(true);
	}
	
	
	private void layout() {
		billingJframe.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		billingJframe.setJMenuBar(billingMenu.getBillingMenu());
	}
	
	public static void main(String[] args) {
		new Billing();
	}
}
