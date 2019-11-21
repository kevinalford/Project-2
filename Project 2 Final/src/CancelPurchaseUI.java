import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelPurchaseUI {

	public JFrame view;
	
	public JButton btnCancel = new JButton("Cancel Purchase");
	
	public JTextField txtCustomerID = new JTextField(20);
	public JTextField txtPurchaseID = new JTextField(20);
	
	public CancelPurchaseUI() {
		this.view = new JFrame();
		
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		view.setTitle("Cancel a Purchase");
		view.setSize(600, 400);
		view.getContentPane().setLayout(new BoxLayout(view.getContentPane(), BoxLayout.PAGE_AXIS));
		
		JPanel panelButtons = new JPanel(new FlowLayout());
		panelButtons.add(btnCancel);
		view.getContentPane().add(panelButtons);
		
		JPanel line1 = new JPanel(new FlowLayout());
		line1.add(new JLabel("CustomerID "));
		line1.add(txtCustomerID);
		view.getContentPane().add(line1);
		
		JPanel line2 = new JPanel(new FlowLayout());
		line2.add(new JLabel("PurchaseID "));
		line2.add(txtPurchaseID);
		view.getContentPane().add(line2);
		
		btnCancel.addActionListener(new CancelButtonListener());
		
	
	}
	
	public void run() {
		view.setVisible(true);
	}
	
	class CancelButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			PurchaseModel purchase = new PurchaseModel();
			String purchaseid = txtPurchaseID.getText();
			String customerid = txtCustomerID.getText();
			
		if (purchaseid.length() == 0) {
			JOptionPane.showMessageDialog(null, "PurchaseID cannot be null!");
			return;
		}
		

		if (customerid.length() == 0) {
			JOptionPane.showMessageDialog(null, "CustomerID cannot be null!");
		}
		
		try {
			purchase.mPurchaseID = Integer.parseInt(purchaseid);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "PurchaseID is invalid!");
			return;
		}
		
		
		
		int res = StoreManager.getInstance().getDataAdapter().cancelPurchase(purchase);
		
		if (res == IDataAdapter.CANCEL_FAILED)
			JOptionPane.showMessageDialog(null, "Purchase is NOT canceled!");
		else
			JOptionPane.showMessageDialog(null,  "Your Purchase has been canceled successfully!");
		
			
		}
	}
	
	
	
}
