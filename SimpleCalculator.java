public class SimpleCalculator {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            javax.swing.JFrame frame = new javax.swing.JFrame("Simple Calculator");
            frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 400);
            frame.setLayout(new java.awt.BorderLayout());

            javax.swing.JTextField display = new javax.swing.JTextField();
            display.setEditable(false);
            display.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
            display.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 24));
            frame.add(display, java.awt.BorderLayout.NORTH);

            
            javax.swing.JButton clearBtn = new javax.swing.JButton("C");
            clearBtn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18));
            clearBtn.setFocusPainted(false);

            clearBtn.addActionListener(e -> {
                display.setText("");
            });

            String[] buttons = {
                "7", "8", "9", "/", 
                "4", "5", "6", "*", 
                "1", "2", "3", "-", 
                ".", "0", "=", "+"
            };

            javax.swing.JPanel centerPanel = new javax.swing.JPanel();
            centerPanel.setLayout(new java.awt.BorderLayout(5, 5));

            
            javax.swing.JPanel clearPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
            clearPanel.add(clearBtn, java.awt.BorderLayout.CENTER);
            centerPanel.add(clearPanel, java.awt.BorderLayout.NORTH);

            javax.swing.JPanel panel = new javax.swing.JPanel();
            panel.setLayout(new java.awt.GridLayout(4, 4, 5, 5));

            for (String text : buttons) {
                javax.swing.JButton btn = new javax.swing.JButton(text);
                btn.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 22));
                btn.setFocusPainted(false);

                btn.addActionListener(e -> {
                    String cmd = e.getActionCommand();
                    if ("0123456789.".contains(cmd)) {
                        display.setText(display.getText() + cmd);
                    } else if ("/-*+".contains(cmd)) {
                        if (!display.getText().isEmpty() && !"+-*/".contains("" + display.getText().charAt(display.getText().length() - 1))) {
                            display.setText(display.getText() + " " + cmd + " ");
                        }
                    } else if ("=".equals(cmd)) {
                        try {
                            String[] tokens = display.getText().split(" ");
                            if (tokens.length == 3) {
                                double a = Double.parseDouble(tokens[0]);
                                double b = Double.parseDouble(tokens[2]);
                                String op = tokens[1];
                                double result = 0;
                                switch (op) {
                                    case "+": result = a + b; break;
                                    case "-": result = a - b; break;
                                    case "*": result = a * b; break;
                                    case "/": result = b != 0 ? a / b : 0; break;
                                }
                                display.setText("" + result);
                            }
                        } catch (Exception ex) {
                            display.setText("Error");
                        }
                    }
                });
                panel.add(btn);
            }

            centerPanel.add(panel, java.awt.BorderLayout.CENTER);
            frame.add(centerPanel, java.awt.BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}
