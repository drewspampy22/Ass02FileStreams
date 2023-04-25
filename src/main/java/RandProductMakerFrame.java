import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Pattern;

public class RandProductMakerFrame extends JFrame {
    RandomAccessFile randFile;

    JPanel mainPnl, titlePnl, entryPnl, entryPnl2, productPnl, btnPnl;
    JLabel titleLbl, nameLbl, descLbl, IDLbl, costLbl, counterLbl;
    JTextField nameTF, descTF, IDTF, costTF, counterTF;
    JButton quitBtn, submitBtn;

    String cost;
    int productCounter = 0;

    public RandProductMakerFrame() {
        setTitle("Product List Maker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);

        entryPnl = new JPanel();
        entryPnl.setLayout(new GridLayout(5,2));
        entryPnl2 = new JPanel();
        entryPnl2.setLayout(new GridLayout(5,1));

        createTitlePanel();
        createProductPanel();
        createButtonPanel();

        setVisible(true);
    }

    private void createTitlePanel()
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Enter Product Information:", JLabel.CENTER);
        titleLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 48));


        titleLbl.setVerticalTextPosition(JLabel.BOTTOM);
        titleLbl.setHorizontalTextPosition(JLabel.CENTER);

        titlePnl.add(titleLbl);
        mainPnl.add(titlePnl, BorderLayout.NORTH);

    }

    private void createProductPanel()
    {
        productPnl = new JPanel();

        counterLbl = new JLabel("# Products Entered:");
        counterLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        counterTF =  new JTextField("", 20);


        nameLbl = new JLabel("Product Name:");
        nameLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        nameTF =  new JTextField("", 30);

        descLbl = new JLabel("Product Description:");
        descLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        descTF =  new JTextField("", 30);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 30);

        IDLbl = new JLabel("Product ID:");
        IDLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        IDTF =  new JTextField("", 30);

        costLbl = new JLabel("Product Cost:");
        costLbl.setFont(new Font("Times New Roman MS", Font.PLAIN, 20));
        costTF =  new JTextField("", 30);


        entryPnl.add(counterLbl, new GridLayout(1,1));
        entryPnl2.add(counterTF, new GridLayout(1,1));
        counterTF.setEditable(false);

        entryPnl.add(nameLbl, new GridLayout(2,1));
        entryPnl2.add(nameTF, new GridLayout(2,1));

        entryPnl.add(descLbl, new GridLayout(3,1));
        entryPnl2.add(descTF, new GridLayout(3,1));

        entryPnl.add(IDLbl, new GridLayout(4,1));
        entryPnl2.add(IDTF, new GridLayout(4,1));

        entryPnl.add(costLbl, new GridLayout(5,1));
        entryPnl2.add(costTF, new GridLayout(5,1));


        productPnl.add(entryPnl, BorderLayout.WEST);
        productPnl.add(entryPnl2, BorderLayout.EAST);
        mainPnl.add(productPnl, BorderLayout.CENTER);
    }


    private void createButtonPanel() {
        btnPnl = new JPanel();
        btnPnl.setLayout(new GridLayout(1,3));

        submitBtn = new JButton("Submit");
        submitBtn.setFont(new Font("Times New Roman MS", Font.PLAIN, 48));
        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Times New Roman MS", Font.PLAIN, 48));

        btnPnl.add(submitBtn);
        btnPnl.add(quitBtn);

        mainPnl.add(btnPnl, BorderLayout.SOUTH);


        quitBtn.addActionListener(new ActionListener() {
            JOptionPane pane =new JOptionPane();
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(pane,"Do you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
                if(result == JOptionPane.YES_OPTION){System.exit(0);}
                else {setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
                }}});

        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              
                cost = costTF.getText();
                if (Pattern.matches("[\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})?]+", cost) && IDTF.getText().length() == 6 && descTF.getText().length() <= 75 && nameTF.getText().length() <= 35) {
                    System.out.println(nameTF.getText()); 
                    System.out.println(descTF.getText()); 
                    System.out.println(IDTF.getText());   
                    System.out.println(costTF.getText()); 


                    try {
                        randFile = new RandomAccessFile(new File("RandProducts.txt"), "rw");
                        randFile.seek(randFile.length());
                        randFile.write(String.format("%6s%35s%8s%75s\n",IDTF.getText(), nameTF.getText(), costTF.getText(), descTF.getText() ).getBytes());
                        randFile.close();
                    } catch (FileNotFoundException i) {
                        i.printStackTrace();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                    nameTF.setText("");
                    descTF.setText("");
                    IDTF.setText("");
                    costTF.setText("");
                    productCounter++;
                    counterTF.setText(String.valueOf(productCounter));

                } else {
                    String wrongInput = "Requirements:\n" +
                            "- Name can not be more than 35 characters\n"+
                            "- Description must be less than 75 characters\n"+
                            "- ID must include 6 characters!\n" +
                            "- Must entered a number for cost!";
                    JOptionPane.showMessageDialog(null, wrongInput);
                }
            }
        });
    }
}

