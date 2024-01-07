import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainScreen extends JFrame {

    JPanel subscriberPanel;
    static JTextField subName;
    static JTextField subLastName;
    static JTextField subMobile;
    static JTextField subCity;

    JLabel nameLabel;
    JLabel lastNLabel;
    JLabel mobileLabel;

    JLabel cityLabel;

    //Subscriber Cycle details
    JPanel cyclePanel;

    //Creating the text field for cycle
    JTextField startCycleFLD;
    JTextField endCycleFlD;
    JTextField numberTVFLD;

    JLabel startLBL;
    JLabel endLBL;

    JLabel numberTVLBL;
    JLabel todayLBL;

    SimpleDateFormat df;
    Date currentDate;


    // Creating Channels Packages
    JPanel packagesPanel;
    JLabel packageLBL;
    JButton subscriptionBTN;
    JCheckBox sportCHKBX;
    JCheckBox movieCHKBX;
    JCheckBox documentaryCHKBX;


    // Package details

    JPanel detailsPanel;

    static JTextArea detailsPackageS;
    static JTextArea detailsPackageM;
    static JTextArea detailsPackageD;
    // Fee and Check
    JPanel feePanel;

    JLabel installationFeeLBL;
    JLabel packageFeeLBL;

    JLabel totalFeeLBL;

    //Table panel
    JTable table;

    JPanel p6Panel;

    DefaultTableModel tableModel;

    //Action Panel
    JPanel p7actionPanel;
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;


    Subscriber subscriber;
    Subscription subscription;

    int packageSelectedPrice = 0;
    int totalPrice;

    //Create an array List to save Subscription
    ArrayList<Subscription> saveSub = new ArrayList<>();

    public MainScreen() {

        /*
         * ************************** PANEL 1 ****************************/
        /*
         * This consist of subscriber details panels and Text field  that consist of several user details
         * */
        subscriberPanel = new JPanel();
        Border borderTitle = BorderFactory.createTitledBorder("Subscriber Details");
        subscriberPanel.setBorder(borderTitle);
        subscriberPanel.setBounds(15, 15, 300, 200);
        subscriberPanel.setLayout(new GridLayout(4, 2));

        // JLabel
        nameLabel = new JLabel("First Name: ");
        lastNLabel = new JLabel("Last Name: ");
        mobileLabel = new JLabel("Mobile: ");
        cityLabel = new JLabel("City: ");

        //TextField
        subName = new JTextField();
        subLastName = new JTextField();
        subMobile = new JTextField();
        subCity = new JTextField();

        //Adding the component
        subscriberPanel.add(nameLabel);
        subscriberPanel.add(subName);
        subscriberPanel.add(lastNLabel);
        subscriberPanel.add(subLastName);
        subscriberPanel.add(mobileLabel);
        subscriberPanel.add(subMobile);
        subscriberPanel.add(cityLabel);
        subscriberPanel.add(subCity);

        subName.setOpaque(false);
        subLastName.setOpaque(false);
        subMobile.setOpaque(false);
        subCity.setOpaque(false);

        /*
         ******************************** Panel 2 Subscription Cycle panel *******************/

        cyclePanel = new JPanel();
        cyclePanel.setBounds(15, 250, 300, 450);
        Border cycleBorder = BorderFactory.createTitledBorder("Subscription Cycle Details");
        cyclePanel.setBorder(cycleBorder);
        cyclePanel.setLayout(new GridLayout(10, 1));


        //Component Of Cycle Panel
        todayLBL = new JLabel("Today :");
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today " + df.format(currentDate));


        // Start Cycle Date
        startLBL = new JLabel("Start Date (DD/MM/YYYY) ");
        startCycleFLD = new JTextField();

        // End Cycle Date
        endLBL = new JLabel(" End Date (DD/MM/YYYY) ");
        endCycleFlD = new JTextField();

        //Number of TV label
        numberTVLBL = new JLabel("Number of TV (DD/MM/YYYY) ");
        numberTVFLD = new JTextField();


        //Adding Cycle Panel  Date component
        cyclePanel.add(todayLBL);
        cyclePanel.add(startLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endLBL);
        cyclePanel.add(endCycleFlD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);
        //Set Opacity
        startCycleFLD.setOpaque(false);
        endCycleFlD.setOpaque(false);
        numberTVFLD.setOpaque(false);

        /*
         ********************************** Panel 3 Channel's Packages********************************/
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330, 15, 300, 200);
        Border packageBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packageBorder);
        packagesPanel.setLayout(new GridLayout(5, 1));

        //Package label
        packageLBL = new JLabel("Please select your Package");
        //Creating Packages CheckBoxes
        sportCHKBX = new JCheckBox("Sport Package");
        movieCHKBX = new JCheckBox("Movie Package");
        documentaryCHKBX = new JCheckBox("Documentary Package");
        subscriptionBTN = new JButton("Subscribe");


        //Adding channel package component
        packagesPanel.add(packageLBL);
        packagesPanel.add(sportCHKBX);
        packagesPanel.add(movieCHKBX);
        packagesPanel.add(documentaryCHKBX);
        packagesPanel.add(subscriptionBTN);

        //Check box Event listener
        sportCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sportCHKBX.isSelected()) {
                    DisplaySportChannel();
                } else {
                    detailsPackageS.setText(" ");
                }
            }
        });
        movieCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (movieCHKBX.isSelected()) {
                    DisplayMovieChannel();
                } else {
                    detailsPackageM.setText(" ");
                }
            }
        });
        documentaryCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (documentaryCHKBX.isSelected()) {
                    DisplayDocumentary();
                } else {
                    detailsPackageD.setText(" ");
                }
            }
        });
        subscriptionBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    GetSubscriberData();
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /*
         * ************************************** Panel 4 Package Details *********************/

        detailsPanel = new JPanel();
        detailsPanel.setBounds(330, 250, 300, 450);
        Border detailsBorder = BorderFactory.createTitledBorder("Available Channel");
        detailsPanel.setBorder(detailsBorder);
        detailsPanel.setLayout(new GridLayout(3, 1));

        //Adding TextArea of Available Channel

        detailsPackageS = new JTextArea(5, 1);
        detailsPackageS.setOpaque(false);
        detailsPackageS.setEnabled(false);
        detailsPackageS.setLineWrap(true);


        detailsPackageM = new JTextArea(5, 1);
        detailsPackageM.setOpaque(false);
        detailsPackageM.setEnabled(false);
        detailsPackageM.setLineWrap(true);

        detailsPackageD = new JTextArea(5, 1);
        detailsPackageD.setOpaque(false);
        detailsPackageD.setEnabled(false);
        detailsPackageD.setLineWrap(true);


        //Adding Component to the details panel

        detailsPanel.add(detailsPackageS);
        detailsPanel.add(detailsPackageM);
        detailsPanel.add(detailsPackageD);

        /*
         * ******************************* Panel 5 fee and Check *******************************/

        feePanel = new JPanel();
        feePanel.setBounds(650, 15, 200, 200);
        Border borderFee = BorderFactory.createTitledBorder("Fee & Check");
        feePanel.setBorder(borderFee);
        feePanel.setLayout(new GridLayout(3, 1));

        // Adding the label Component
        installationFeeLBL = new JLabel("Installation fee: ");

        packageFeeLBL = new JLabel("Package fee: ");
        totalFeeLBL = new JLabel("Total Amount to pay:");


        feePanel.add(installationFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);




        /***************************Panel 6 TABLE PANEL**************************/

        p6Panel = new JPanel();
        p6Panel.setBounds(650, 250, 350, 450);
        Border p6Border = BorderFactory.createTitledBorder("Our Customers");
        p6Panel.setBorder(p6Border);
        p6Panel.setLayout(new GridLayout(3, 1));


        // Table
        // 1- table Model

        tableModel = new DefaultTableModel();

        table = new JTable(tableModel);

        tableModel.addColumn("First Name");
        tableModel.addColumn("Lat Name");
        tableModel.addColumn("Mobile No");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        JScrollPane scrollPane = new JScrollPane(table);
        p6Panel.add(scrollPane);


        /*
        **************************************** Panel 7 Action PANEL **********************/

        p7actionPanel = new JPanel();
        p7actionPanel.setBounds(870, 15, 300, 200);
        Border actionBorder = BorderFactory.createTitledBorder("Action Tab");
        p7actionPanel.setBorder(actionBorder);
        p7actionPanel.setLayout(new GridLayout(4, 1));


        // Action Btn

        newBTN = new JButton("New Subscription");
        saveBTN = new JButton("Save Button");
        loadBTN = new JButton("Load Button");

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });
        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscription();
            }
        });


        p7actionPanel.add(newBTN);
        p7actionPanel.add(saveBTN);
        p7actionPanel.add(loadBTN);

//        newBTN.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(newBTN.)
//            }
//        });


        //Adding panel to JFrame
        setLayout(null);
        add(subscriberPanel); //Panel 1
        add(cyclePanel);      //Panel 2
        add(packagesPanel);   //Panel 3
        add(detailsPanel);    //Panel 4
        add(feePanel);        //Panel 5
        add(p6Panel);         //Panel 6
        add(p7actionPanel);   //Panel 7



    }



    /*********************** Method for the Channels***************************/
    public void GetSubscriberData() throws ParseException {
        Date currentDate = new Date();
        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getName(),
                subCity.getName(),
                Integer.parseInt(subMobile.getText()));

        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle = df.parse(endCycleFlD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                        df.format(startCycle),
                        df.format(endCycle)
                );
        subscription = new Subscription(
                        Integer.parseInt(numberTVFLD.getText()),
                        subscriber,cycle, df.format(currentDate)
                );


        //Installation
        
        showPrice();
    }

    private void showPrice() {

        if (sportCHKBX.isSelected()){
            packageSelectedPrice += DisplaySportChannel();
        } else if (movieCHKBX.isSelected()) {
            packageSelectedPrice+= DisplayMovieChannel();
        } else if (documentaryCHKBX.isSelected()) {
            packageSelectedPrice += DisplayDocumentary();

        }
        installationFeeLBL.setText("Installation Fee "+subscription.getTotalFee() +"$");
        packageFeeLBL.setText("Package Fee "+ packageSelectedPrice + " $");
        totalPrice = subscription.getTotalFee() + packageSelectedPrice;

        totalFeeLBL.setText("Total Amount to Pay "+ totalPrice +" $");
    }

    private void NewSubscription() {
        subName.setText(" ");
        subLastName.setText(" ");
        subMobile.setText(" ");
        subCity.setText(" ");

        startCycleFLD.setText(" ");
        endCycleFlD.setText(" ");
        numberTVFLD.setText(" ");

        installationFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Package Fee: ");
        totalFeeLBL.setText("Total Amount to Pay: ");

        //Deselect checkBoxes
        sportCHKBX.setSelected(false);
        movieCHKBX.setSelected(false);
        documentaryCHKBX.setSelected(false);

        detailsPackageD.setText("");
        detailsPackageS.setText(" ");
        detailsPackageM.setText(" ");
    }

        private int DisplaySportChannel () {

        ArrayList<SportChannel> sportChannels = getSportChannels();

            String sportChannelString = " ";
            int packagePrice = 0;

            for (int i = 0; i < sportChannels.size(); i++) {
                sportChannelString += " " + sportChannels.get(i).getChannelName()
                        + "  " + sportChannels.get(i).getLanguage()
                        + "  " + sportChannels.get(i).getPrice()
                        + "\n";
                packagePrice += sportChannels.get(i).getPrice();
            }

            detailsPackageS.setText(sportChannelString);
            return packagePrice;
        }
        private static ArrayList<SportChannel> getSportChannels(){
            SportChannel s1 = new SportChannel("AFN Sports", "EN", "SPRT", 5);
            SportChannel s2 = new SportChannel("beIN Sports", "FR", "SPRT", 3);
            SportChannel s3 = new SportChannel("Eleven Sports", "EN", "SPRT", 8);
            SportChannel s4 = new SportChannel("NBA TV", "EN", "SPRT", 6);
            SportChannel s5 = new SportChannel("NFL Network", "AR", "SPRT", 3);
            SportChannel s6 = new SportChannel("The Ski Channel", "USA", "SPRT", 1);


            ArrayList<SportChannel> sportChannels = new ArrayList<>();
            sportChannels.add(s1);
            sportChannels.add(s2);
            sportChannels.add(s3);
            sportChannels.add(s4);
            sportChannels.add(s5);
            sportChannels.add(s6);

            return sportChannels;
        }

        public int DisplayMovieChannel () {
        ArrayList<MovieChannel> movieChannels  = getMovieChannel();

            StringBuilder movieChannelString = new StringBuilder();
            int packagePrice = 0;

            for (MovieChannel movieChannel : movieChannels) {
                movieChannelString.append(movieChannel.getChannelName()).
                        append("   ").append(movieChannel.getLanguage()).
                        append("   ").append(movieChannel.getPrice()).append("\n");

                packagePrice += movieChannel.getPrice();
            }
            detailsPackageM.setText(String.valueOf(movieChannelString));
            return packagePrice;
        }
    private static ArrayList<MovieChannel> getMovieChannel(){
        MovieChannel m1 = new MovieChannel("MBC Bundle", "EN", "MOV", 4);
        MovieChannel m2 = new MovieChannel("Cinema One", "EN", "MOV", 5);
        MovieChannel m3 = new MovieChannel("Cinema Pro", "RU", "MOV", 6);
        MovieChannel m4 = new MovieChannel("Cinema 1", "AR", "MOV", 2);
        MovieChannel m5 = new MovieChannel("Movie Home", "GR", "MOV", 4);
        MovieChannel m6 = new MovieChannel("Film4", "FR", "MOV", 2);

        ArrayList<MovieChannel> movieChannels = new ArrayList<>();
        movieChannels.add(m1);
        movieChannels.add(m2);
        movieChannels.add(m3);
        movieChannels.add(m4);
        movieChannels.add(m5);
        movieChannels.add(m6);

        return movieChannels;
    }

        public int DisplayDocumentary () {
        ArrayList<DocumentaryChannel> documentaryChannels = getDocumentaryChannel();

            String docChannelString = " ";
            int packagePrice = 0;

            for (DocumentaryChannel documentaryChannel : documentaryChannels) {
                docChannelString += documentaryChannel.getChannelName()
                        + "   " + documentaryChannel.getLanguage()
                        + "   " + documentaryChannel.getPrice()
                        + "\n";


            }
            detailsPackageD.setText(docChannelString);
            return packagePrice;
        }
        private static ArrayList<DocumentaryChannel> getDocumentaryChannel(){
            DocumentaryChannel m1 = new DocumentaryChannel("Nat Geo", "Spain", "DOC", 3);
            DocumentaryChannel m2 = new DocumentaryChannel("PBS America", "EN", "DOC", 4);
            DocumentaryChannel m3 = new DocumentaryChannel("Al jazeera", "IN", "DOC", 2);
            DocumentaryChannel m4 = new DocumentaryChannel("Discovery Historia", "AR", "DOC", 5);


            ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
            documentaryChannels.add(m1);
            documentaryChannels.add(m2);
            documentaryChannels.add(m3);
            documentaryChannels.add(m4);

            return documentaryChannels;
        }
        private void SaveSubscription () {
            saveSub.add(subscription);
            File file = new File("c:\\data\\input.txt");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(saveSub);
                objectOutputStream.flush();
                objectOutputStream.close();
            } catch (FileNotFoundException ee) {
                throw new RuntimeException("File not Found");
            }catch (IOException exception){
                exception.printStackTrace();
            }
        }


        public static void main (String[]args){

            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
            mainScreen.setBounds(20, 10, 1200, 800);


        }
    }