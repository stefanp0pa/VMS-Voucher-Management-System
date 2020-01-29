import javax.swing.*;
import java.awt.*;
import java.util.Set;
import java.util.Vector;

public class GuestVouchersFrame extends JFrame {
    private JTable vouchersTable;
    private JScrollPane vouchersScrollPane;

    private String[] vouchersTableColumnsName = {
            "Voucher ID",
            "Voucher Code",
            "Campaign ID",
            "Voucher Type",
            "Value",
            "Status"
    };

    private User user;

    public GuestVouchersFrame(User user){
        this.user = user;
        initializeComponents();
        setUpFrame();
    }
    private void setUpFrame(){
        setSize(750,500);
        setTitle(user.getUserName() + " - Vouchers");
        centerFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void centerFrame(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    private void initializeComponents(){
        initializeTable();
    }

    private void initializeTable(){
        Set<Integer> keySet = user.getReceivedVouchers().keySet();
        Vector<Integer> campaignIds = new Vector<>();
        for(Integer i:keySet)
            campaignIds.add(i);
        Vector<Voucher> userVouchers = new Vector<>();
        for(int i = 0; i < campaignIds.size(); i++){
            Vector<Voucher> v = user.getReceivedVouchers().get(campaignIds.get(i));
            for(int j = 0; j < v.size(); j++)
                userVouchers.add(v.get(j));
        }


        Object[][] data = new Object[userVouchers.size()][vouchersTableColumnsName.length];
        for(int i = 0; i < userVouchers.size(); i++){
            Voucher v = userVouchers.get(i);
            data[i][0] = v.getVoucherId();
            data[i][1] = v.getVoucherCode();
            data[i][2] = v.getCampaignId();
            data[i][3] = v.getVoucherType();
            data[i][4] = v.getValue();
            data[i][5] = v.getVoucherStatusType();
        }
        vouchersTable = new JTable(data,vouchersTableColumnsName);
        vouchersTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        vouchersTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(4).setPreferredWidth(120);
        vouchersTable.getColumnModel().getColumn(5).setPreferredWidth(120);
        vouchersTable.setPreferredScrollableViewportSize(vouchersTable.getPreferredSize());

        vouchersScrollPane = new JScrollPane(vouchersTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(vouchersScrollPane);
        vouchersScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );
        this.repaint();
        this.revalidate();
    }
}
