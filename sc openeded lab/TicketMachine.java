import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketMachine extends JFrame implements ActionListener {
    
    // Data
    private final String[] destinations = {"Faizabad Central", "Rawalpindi Station", "Islamabad Airport", "Saddar", "Committee Chowk"};
    private final double[] prices = {50.00, 80.00, 150.00, 40.00, 60.00};
    private final String[] ticketTypes = {"Adult", "Child (50% Off)", "Senior (30% Off)"};
    private final double[] discounts = {1.0, 0.5, 0.7};
    
    // Colors
    private final Color PRIMARY = new Color(34, 139, 34);
    private final Color DARK = new Color(33, 37, 41);
    private final Color ACCENT = new Color(220, 53, 69);
    private final Color LIGHT_BG = new Color(248, 249, 250);
    
    // Components
    private JComboBox<String> destinationCombo, ticketTypeCombo;
    private JLabel quantityLabel, totalLabel, balanceLabel, timeLabel;
    private JTextArea receiptArea;
    private JButton[] moneyBtns;
    private JButton minusBtn, plusBtn, cancelBtn, purchaseBtn, receiptBtn;
    
    private int quantity = 1;
    private double currentBalance = 0.0;
    private double ticketPrice = 50.0;
    
    public TicketMachine() {
        setTitle("Faizabad Metro - Ticket Kiosk");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        getContentPane().setBackground(LIGHT_BG);
        
        add(createTopBar(), BorderLayout.NORTH);
        add(createLeftPanel(), BorderLayout.WEST);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createRightPanel(), BorderLayout.EAST);
        
        updatePrice();
        startClock();
        setVisible(true);
    }

    private void startClock() {
        Timer timer = new Timer(1000, e -> {
            timeLabel.setText(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        });
        timer.start();
    }
    
    private JPanel createTopBar() {
        JPanel bar = new JPanel(new BorderLayout());
        bar.setBackground(PRIMARY);
        bar.setPreferredSize(new Dimension(750, 60));
        bar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        JLabel logo = new JLabel("FAIZABAD METRO");
        logo.setFont(new Font("Arial", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        
        JPanel rightInfo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightInfo.setOpaque(false);
        
        JLabel dateLabel = new JLabel(new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date()));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(200, 230, 200));
        
        timeLabel = new JLabel(new SimpleDateFormat("hh:mm:ss a").format(new Date()));
        timeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        timeLabel.setForeground(Color.WHITE);
        
        rightInfo.add(dateLabel);
        rightInfo.add(Box.createHorizontalStrut(15));
        rightInfo.add(timeLabel);
        
        bar.add(logo, BorderLayout.WEST);
        bar.add(rightInfo, BorderLayout.EAST);
        
        return bar;
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 500));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(220, 220, 220)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setOpaque(false);
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Title
        JLabel title = new JLabel("SELECT YOUR TICKET");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(DARK);
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        content.add(title);
        content.add(Box.createVerticalStrut(20));
        
        // Destination
        content.add(createFieldLabel("Where to?"));
        destinationCombo = createCombo(destinations);
        content.add(destinationCombo);
        content.add(Box.createVerticalStrut(15));
        
        // Ticket Type
        content.add(createFieldLabel("Passenger Type"));
        ticketTypeCombo = createCombo(ticketTypes);
        content.add(ticketTypeCombo);
        content.add(Box.createVerticalStrut(15));
        
        // Quantity with +/- buttons
        content.add(createFieldLabel("Number of Tickets"));
        content.add(Box.createVerticalStrut(5));
        content.add(createQuantityPanel());
        content.add(Box.createVerticalStrut(25));
        
        // Total Display
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(DARK);
        totalPanel.setMaximumSize(new Dimension(300, 90));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(12, 15, 12, 15));
        totalPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel totalText = new JLabel("TOTAL");
        totalText.setFont(new Font("Arial", Font.PLAIN, 11));
        totalText.setForeground(new Color(180, 180, 180));
        
        totalLabel = new JLabel("Rs. 50.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 32));
        totalLabel.setForeground(Color.WHITE);
        
        totalPanel.add(totalText, BorderLayout.NORTH);
        totalPanel.add(totalLabel, BorderLayout.CENTER);
        content.add(totalPanel);
        
        panel.add(content);
        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(LIGHT_BG);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Balance at top
        JPanel balanceCard = new JPanel(new BorderLayout());
        balanceCard.setBackground(new Color(0, 123, 255));
        balanceCard.setPreferredSize(new Dimension(300, 80));
        balanceCard.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        
        JLabel balText = new JLabel("INSERTED AMOUNT");
        balText.setFont(new Font("Arial", Font.BOLD, 10));
        balText.setForeground(new Color(200, 220, 255));
        
        balanceLabel = new JLabel("Rs. 0.00");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 32));
        balanceLabel.setForeground(Color.WHITE);
        
        balanceCard.add(balText, BorderLayout.NORTH);
        balanceCard.add(balanceLabel, BorderLayout.CENTER);
        
        // Money buttons in center
        JPanel moneyPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        moneyPanel.setOpaque(false);
        moneyPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        
        int[] amounts = {10, 20, 50, 100, 500, 1000};
        moneyBtns = new JButton[6];
        
        for (int i = 0; i < 6; i++) {
            moneyBtns[i] = createMoneyButton(amounts[i]);
            moneyPanel.add(moneyBtns[i]);
        }
        
        // Action buttons at bottom
        JPanel actionPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        actionPanel.setOpaque(false);
        actionPanel.setPreferredSize(new Dimension(300, 60));
        
        cancelBtn = createActionButton("CANCEL", ACCENT);
        purchaseBtn = createActionButton("BUY TICKET", PRIMARY);
        receiptBtn = createActionButton("PRINT RECEIPT", new Color(108, 117, 125));
        
        actionPanel.add(cancelBtn);
        actionPanel.add(purchaseBtn);
        actionPanel.add(receiptBtn);
        
        panel.add(balanceCard, BorderLayout.NORTH);
        panel.add(moneyPanel, BorderLayout.CENTER);
        panel.add(actionPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createRightPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(220, 500));
        panel.setBackground(new Color(250, 250, 250));
        panel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(220, 220, 220)));
        
        JLabel receiptTitle = new JLabel("  RECEIPT", SwingConstants.LEFT);
        receiptTitle.setFont(new Font("Arial", Font.BOLD, 12));
        receiptTitle.setForeground(DARK);
        receiptTitle.setPreferredSize(new Dimension(220, 35));
        receiptTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));
        
        receiptArea = new JTextArea();
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Consolas", Font.PLAIN, 10));
        receiptArea.setBackground(new Color(250, 250, 250));
        receiptArea.setMargin(new Insets(10, 10, 10, 10));
        showWelcome();
        
        JScrollPane scroll = new JScrollPane(receiptArea);
        scroll.setBorder(null);
        
        panel.add(receiptTitle, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void showWelcome() {
        StringBuilder sb = new StringBuilder();
        sb.append("--------------------\n");
        sb.append(" FAIZABAD METRO\n");
        sb.append("--------------------\n\n");
        sb.append("Welcome!\n\n");
        sb.append("1. Select destination\n");
        sb.append("2. Choose type\n");
        sb.append("3. Set quantity\n");
        sb.append("4. Insert money\n");
        sb.append("5. Press BUY TICKET\n\n");
        sb.append("--------------------\n");
        receiptArea.setText(sb.toString());
    }

    private JLabel createFieldLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 11));
        label.setForeground(new Color(100, 100, 100));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }
    
    private JComboBox<String> createCombo(String[] items) {
        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(new Font("Arial", Font.PLAIN, 16));
        combo.setMaximumSize(new Dimension(300, 45));
        combo.setAlignmentX(Component.LEFT_ALIGNMENT);
        combo.addActionListener(this);
        return combo;
    }
    
    private JPanel createQuantityPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.setOpaque(false);
        panel.setMaximumSize(new Dimension(300, 50));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        minusBtn = new JButton("-");
        minusBtn.setFont(new Font("Arial", Font.BOLD, 22));
        minusBtn.setPreferredSize(new Dimension(55, 48));
        minusBtn.setBackground(LIGHT_BG);
        minusBtn.setFocusPainted(false);
        minusBtn.addActionListener(this);
        
        quantityLabel = new JLabel("1", SwingConstants.CENTER);
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 24));
        quantityLabel.setPreferredSize(new Dimension(70, 48));
        quantityLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        
        plusBtn = new JButton("+");
        plusBtn.setFont(new Font("Arial", Font.BOLD, 22));
        plusBtn.setPreferredSize(new Dimension(55, 48));
        plusBtn.setBackground(LIGHT_BG);
        plusBtn.setFocusPainted(false);
        plusBtn.addActionListener(this);
        
        panel.add(minusBtn);
        panel.add(quantityLabel);
        panel.add(plusBtn);
        
        return panel;
    }
    
    private JButton createMoneyButton(int amount) {
        JButton btn = new JButton("Rs. " + amount);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(0, 123, 255));
        btn.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2));
        btn.setFocusPainted(false);
        btn.addActionListener(this);
        return btn;
    }
    
    private JButton createActionButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.addActionListener(this);
        return btn;
    }
    
    private void updatePrice() {
        int destIndex = destinationCombo.getSelectedIndex();
        int typeIndex = ticketTypeCombo.getSelectedIndex();
        ticketPrice = prices[destIndex] * discounts[typeIndex] * quantity;
        totalLabel.setText(String.format("Rs. %.2f", ticketPrice));
    }
    
    private void addMoney(int amount) {
        currentBalance += amount;
        balanceLabel.setText(String.format("Rs. %.2f", currentBalance));
    }

    private void purchaseTicket() {
        if (currentBalance < ticketPrice) {
            JOptionPane.showMessageDialog(this,
                String.format("Please insert Rs. %.2f more", ticketPrice - currentBalance),
                "Insufficient Amount", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        double change = currentBalance - ticketPrice;
        String dest = (String) destinationCombo.getSelectedItem();
        String type = ((String) ticketTypeCombo.getSelectedItem()).split(" ")[0];
        String dateTime = new SimpleDateFormat("dd/MM/yy HH:mm").format(new Date());
        
        StringBuilder receipt = new StringBuilder();
        receipt.append("\n--------------------\n");
        receipt.append(" FAIZABAD METRO\n");
        receipt.append("--------------------\n");
        receipt.append(String.format("Date: %s\n", dateTime));
        receipt.append(".....................\n");
        receipt.append(String.format("To: %s\n", dest));
        receipt.append(String.format("Type: %s\n", type));
        receipt.append(String.format("Qty: %d\n", quantity));
        receipt.append(".....................\n");
        receipt.append(String.format("Total: Rs.%.2f\n", ticketPrice));
        receipt.append(String.format("Paid:  Rs.%.2f\n", currentBalance));
        receipt.append(String.format("Change:Rs.%.2f\n", change));
        receipt.append("--------------------\n");
        receipt.append("  Have a safe trip!\n");
        receipt.append("--------------------\n");
        
        receiptArea.setText(receipt.toString());
        
        String msg = change > 0 ? 
            String.format("Success!\nCollect change: Rs. %.2f", change) : 
            "Success!\nThank you!";
        JOptionPane.showMessageDialog(this, msg, "Ticket Purchased", JOptionPane.INFORMATION_MESSAGE);
        
        currentBalance = 0;
        balanceLabel.setText("Rs. 0.00");
    }
    
    private void cancelTransaction() {
        if (currentBalance > 0) {
            JOptionPane.showMessageDialog(this,
                String.format("Refunded: Rs. %.2f", currentBalance),
                "Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
        currentBalance = 0;
        balanceLabel.setText("Rs. 0.00");
        quantity = 1;
        quantityLabel.setText("1");
        destinationCombo.setSelectedIndex(0);
        ticketTypeCombo.setSelectedIndex(0);
        updatePrice();
        showWelcome();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        
        // Money buttons
        int[] amounts = {10, 20, 50, 100, 500, 1000};
        for (int i = 0; i < moneyBtns.length; i++) {
            if (src == moneyBtns[i]) {
                addMoney(amounts[i]);
                return;
            }
        }
        
        if (src == minusBtn && quantity > 1) {
            quantity--;
            quantityLabel.setText(String.valueOf(quantity));
        } else if (src == plusBtn && quantity < 10) {
            quantity++;
            quantityLabel.setText(String.valueOf(quantity));
        } else if (src == cancelBtn) {
            cancelTransaction();
            return;
        } else if (src == purchaseBtn) {
            purchaseTicket();
            return;
        } else if (src == receiptBtn) {
            openReceiptWindow();
            return;
        }
        updatePrice();
    }
    
    private void resetForNewTicket() {
        quantity = 1;
        quantityLabel.setText("1");
        destinationCombo.setSelectedIndex(0);
        ticketTypeCombo.setSelectedIndex(0);
        updatePrice();
        showWelcome();
    }
    
    private void openReceiptWindow() {
        if (receiptArea.getText().contains("Welcome")) {
            JOptionPane.showMessageDialog(this, "No receipt to display.\nPlease buy a ticket first.", 
                "No Receipt", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Create new receipt window
        JFrame receiptFrame = new JFrame("Receipt - Faizabad Metro");
        receiptFrame.setSize(400, 500);
        receiptFrame.setLocationRelativeTo(this);
        receiptFrame.setLayout(new BorderLayout());
        
        // Header
        JPanel header = new JPanel();
        header.setBackground(PRIMARY);
        header.setPreferredSize(new Dimension(400, 50));
        JLabel headerLabel = new JLabel("FAIZABAD METRO - TICKET RECEIPT");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        headerLabel.setForeground(Color.WHITE);
        header.add(headerLabel);
        
        // Receipt content
        JTextArea receiptText = new JTextArea();
        receiptText.setEditable(false);
        receiptText.setFont(new Font("Consolas", Font.PLAIN, 14));
        receiptText.setMargin(new Insets(20, 30, 20, 30));
        receiptText.setText(receiptArea.getText());
        
        JScrollPane scroll = new JScrollPane(receiptText);
        
        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        btnPanel.setBackground(LIGHT_BG);
        
        JButton printBtn = new JButton("PRINT");
        printBtn.setBackground(PRIMARY);
        printBtn.setForeground(Color.WHITE);
        printBtn.setFocusPainted(false);
        printBtn.setBorderPainted(false);
        printBtn.setOpaque(true);
        printBtn.setFont(new Font("Arial", Font.BOLD, 14));
        printBtn.setPreferredSize(new Dimension(120, 40));
        printBtn.addActionListener(ev -> {
            JOptionPane.showMessageDialog(receiptFrame, "Receipt sent to printer!", "Print", JOptionPane.INFORMATION_MESSAGE);
        });
        
        JButton closeBtn = new JButton("CLOSE");
        closeBtn.setBackground(ACCENT);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setFocusPainted(false);
        closeBtn.setBorderPainted(false);
        closeBtn.setOpaque(true);
        closeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        closeBtn.setPreferredSize(new Dimension(120, 40));
        closeBtn.addActionListener(ev -> receiptFrame.dispose());
        
        btnPanel.add(printBtn);
        btnPanel.add(closeBtn);
        
        receiptFrame.add(header, BorderLayout.NORTH);
        receiptFrame.add(scroll, BorderLayout.CENTER);
        receiptFrame.add(btnPanel, BorderLayout.SOUTH);
        
        receiptFrame.setVisible(true);
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}
        SwingUtilities.invokeLater(() -> new TicketMachine());
    }
}
