package org.team2168.DiagnosticTools;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.xy.XYSeriesCollection;
import sun.rmi.log.ReliableLog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

class OI {
    public static LogFileData mainLog;
    public static ArrayList<LogFileData> trendLogs = new ArrayList<LogFileData>();
}

class LogFileData {
    public String[] TrendData;
    public ArrayList<DataPoint> PointData = new ArrayList<DataPoint>();

    /**
     * Converts all X data to Float array
     * @return Float array of X Data
     */
    public float[] PointDataTo1DFloat_X(){
        float[] returnData = new float[PointData.size()];

        for(int place = 0; place <= PointData.size() - 1; place++) {
            returnData[place] = (float) PointData.get(place).getX();
        }

        return returnData;
    }

    /**
     * Converts all Y data to Float array
     * @return Float array of Y Data
     */
    public float[] PointDataTo1DFloat_Y(){
        float[] returnData = new float[PointData.size()];

        for(int place = 0; place <= PointData.size() - 1; place++) {
            returnData[place] = (float) PointData.get(place).getY();
        }

        return returnData;
    }

    /**
     * @return Returns the total amount of data points captured
     */
    public int GetTotalFramesCaptured() {
        return PointData.size();
    }

    /**
     * Gets the time stamp of the point in the PointData array
     * @param location The location of the array to get the time out of
     * @return The time in (mm:ss)
     */
    public String PointLocationToTime(int location) {
        double timeElapsed = PointData.get(location).TimeElapsed;
        if (timeElapsed > 60) {
            int min = (int) timeElapsed / 60;
            double sec = timeElapsed % 60;
            return String.valueOf(min + ":" + String.valueOf(sec).substring(0, 2));
        }else{
            double sec = (int) timeElapsed;
            return String.valueOf("00:" + String.valueOf(sec).substring(0, 2));
        }
    }

}

class DataPoint {

    public double TimeElapsed;

    public double X;
    public double Y;
    public double Heading;

    public double LeftSpeed;
    public double RightSpeed;

    public double[] LeftVoltage;
    public double[] RightVoltage;

    public double[] LeftCurrent;
    public double[] RightCurrent;

    public DataPoint(double x, double y, double leftSpeed, double heading, double rightSpeed, double[] leftVoltage,
                     double[] rightVoltage, double[] leftCurrent, double[] rightCurrent, double timeElapsed) {
        X = x;
        Y = y;
        LeftSpeed = leftSpeed;
        Heading = heading;
        RightSpeed = rightSpeed;
        LeftVoltage = leftVoltage;
        RightVoltage = rightVoltage;
        LeftCurrent = leftCurrent;
        RightCurrent = rightCurrent;
        TimeElapsed = timeElapsed;
    }

    public double getY() {
        return Y;
    }

    public double getHeading() {
        return Heading;
    }

    public double getLeftSpeed() {
        return LeftSpeed;
    }

    public double getRightSpeed() {
        return RightSpeed;
    }

    public double[] getLeftVoltage() {
        return LeftVoltage;
    }

    public double[] getRightVoltage() {
        return RightVoltage;
    }

    public double[] getLeftCurrent() {
        return LeftCurrent;
    }

    public double[] getRightCurrent() {
        return RightCurrent;
    }

    public double getX() {
        return X;
    }
}

class LogFileReader{

    private String logFileLocation;
    private LogFileData logFileData = new LogFileData();

    public int RunTime = 0;
    public int VoltageLeftStart = 1;
    public int VoltageRightStart = 4;
    public int CurrentLeftStart = 7;
    public int CurrentRightStart = 10;
    public int SPIGyroAngle = 13;
    public int LeftEncoderPosition = 17;
    public int RightEncoderPosition = 19;

    public final double MinimumStartTime = 20.0;

    /**
     * Default constructor
     * @param logFileLocation Location of the log file to read
     */
    public LogFileReader(String logFileLocation) throws IOException{
        this.logFileLocation = logFileLocation;
    }

    /**
     * Loads in the log file passed in through the constructor
     */
    public LogFileData loadLog() throws IOException{
        System.out.println("Loading " + this.logFileLocation);

        // Load in log file
        BufferedReader br = new BufferedReader(new FileReader(this.logFileLocation));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String logData = sb.toString();
        br.close();

        // Start data needed for calculations
        double prevX = 0;
        double prevY = 0;

        double prevEncoderRight = 0;
        double prevEncoderLeft = 0;

        double prevTime = 0;

        // Get Header and put it into the TrendAnalysis Variable
        logFileData.TrendData = logData.split("\n")[0].split("\t");

        // Interpret Data
        for (String loggedData : logData.split("\n")) {
            // Check to make sure the data line is a real data line and not the header
            if (!loggedData.startsWith("Time")) {
                // Check to make sure the read in data is past the minimum reading time
                String[] entryData = loggedData.split("\t");
                if (Double.parseDouble(entryData[RunTime]) >= MinimumStartTime) {
                    // All criteria has been met. Extract data from the logged entry and add to PointData

                    // Get heading
                    double heading = Double.parseDouble(entryData[SPIGyroAngle]);

                    // Get Left Voltages
                    double leftVoltage[] = {
                            Double.parseDouble(entryData[VoltageLeftStart]),
                            Double.parseDouble(entryData[VoltageLeftStart + 1])
                    };

                    // Get Right Voltages
                    double rightVoltage[] = {
                            Double.parseDouble(entryData[VoltageRightStart]),
                            Double.parseDouble(entryData[VoltageRightStart + 1])
                    };

                    // Get Left Currents
                    double leftCurrent[] = {
                            Double.parseDouble(entryData[CurrentLeftStart]),
                            Double.parseDouble(entryData[CurrentLeftStart + 1])
                    };

                    // Get Right Currents
                    double rightCurrent[] = {
                            Double.parseDouble(entryData[CurrentRightStart]),
                            Double.parseDouble(entryData[CurrentRightStart + 1])
                    };

                    // Get left distance traveled based on the encoder
                    double leftDistanceTraveled = Double.parseDouble(entryData[LeftEncoderPosition]) - prevEncoderLeft;
                    prevEncoderLeft = Double.parseDouble(entryData[LeftEncoderPosition]);

                    // Get right distance traveled based on the encoder
                    double rightDistanceTraveled = Double.parseDouble(entryData[RightEncoderPosition])
                            - prevEncoderRight;
                    prevEncoderRight = Double.parseDouble(entryData[RightEncoderPosition]);

                    // Get total distance traveled by averaging left and right distances
                    double totalDistanceTraveled = (leftDistanceTraveled + rightDistanceTraveled) / 2;

                    // Convert distances to feet
                    leftDistanceTraveled /= 12;
                    rightDistanceTraveled /= 12;

                    // Calculate feet per second
                    double timeDifference = Double.parseDouble(entryData[RunTime]) - prevTime;
                    prevTime = Double.parseDouble(entryData[RunTime]);
                    double leftFeetPerSecond = leftDistanceTraveled / timeDifference;
                    double rightFeetPerSecond = rightDistanceTraveled / timeDifference;

                    // Calculate new (X, Y) point
                    double x = prevX + calculateX(totalDistanceTraveled, heading);
                    double y = prevY + calculateY(totalDistanceTraveled, heading);

                    // Add the log data
                    logFileData.PointData.add(new DataPoint(x, y, leftFeetPerSecond, heading, rightFeetPerSecond,
                            leftVoltage, rightVoltage, leftCurrent, rightCurrent,
                            Double.parseDouble(entryData[RunTime])));

                    prevX = x;
                    prevY = y;
                }
            }

        }

        return logFileData;

    }

    /**
     * Calculates the new point based on distance tr
     * @param totalDistanceTraveled Total distance traveled
     * @param heading Heading in degrees
     * @return New X value
     */
    public static double calculateX(double totalDistanceTraveled, double heading) {
        // (1/2) * distance^2 * Cos(heading)
        double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
        double p_2 = Math.cos(Math.toRadians(heading));
        return (Math.toDegrees(p_1 * p_2));
    }

    /**
     *
     * @param totalDistanceTraveled Total distance traveled
     * @param heading Heading in degrees
     * @return New Y value
     */
    public static double calculateY(double totalDistanceTraveled, double heading) {
        double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
        double p_2 = Math.sin(Math.toRadians(heading));
        return (Math.toDegrees(p_1 * p_2));
    }

}

public class DiagnosticTools {

    private static LogFileReader logFileReader;

    public static void main(String[] args) {
        System.out.println("********** 2168 Diagnostic Tool **********");
        System.out.println("**********  Vittorio Papandrea  **********");

        // Read log files and display them to the user in an order fashion.
        ArrayList<String> LogFiles = GetLogFiles();
        if (LogFiles.size() > 0) {
            System.out.println("\nChoose a log file: ");
            for (int i = 0; i <= LogFiles.size() - 1; i++) {
                System.out.println("\t " + i + " : " + LogFiles.get(i));
            }
        } else {
            System.err.println("Unable to locate any log files inside of \"logs/\"");
            System.exit(0x001);
        }

        // Ask the user what log file would like to be viewed
        Scanner userInput = new Scanner(System.in);
        System.out.print("Log File ID: ");
        int ID = 0;
        try {
            ID = userInput.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("NaN Exception Occurred. Please enter valid ID");
            System.exit(0x002);
        }

        // Load in log file
        long startTime = System.nanoTime();
        try {
            logFileReader = new LogFileReader("logs/" + LogFiles.get(ID));
            OI.mainLog = logFileReader.loadLog();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0x003);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Loaded log file in " + duration + "ms");

        new DiagnosticViewer();

    }

    /**
     * Recurse through all the files located in the "logs/" folder.
     *
     * @return All files found in directory
     */
    public static ArrayList<String> GetLogFiles() {
        ArrayList<String> LogFiles = new ArrayList<String>();

        File folder = new File("./logs/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                LogFiles.add(listOfFiles[i].getName());
            }
        }

        return LogFiles;
    }

}

/**
 * Diagnostic Tools Viewer
 */
class DiagnosticViewer extends JFrame{

    private final JFrame self = this;

    private static final int WIDTH = 1024;
    private static final int HEIGHT = 720;

    // ******* Dialogs ******** \\
    private JDialog optionsDialog;
    private JDialog trendAnalysis;

    // ******* Misc Objects ******* \\
    private JSlider timeProfile;

    // ******* Labels ******** \\
    private JLabel currentTime;
    private JLabel gyroAngle;
    private JLabel optionsDialogFrameDelay;
    private JLabel optionsGlobalMotorScaleDomain;
    private JLabel trendAnalysisSelectTrend;
    private JLabel trendFilesToBeAnalyzed;

    // ******* TextAreas ******* \\
    private JTextField optionsDialogFrameDelayInput;
    private JTextField optionsDialogGlobalMotorScaleDomain;

    // ******* Buttons ******** \\
    private JButton btnStart;
    private JButton btnPause;
    private JButton btnOptions;
    private JButton btnTrendAnalysis;
    private JButton btnOptionsDialogOK;
    private JButton btnOptionsResetPlayBack;
    private JButton btnStartTrend;

    // ******* Combo Boxes ******* \\
    private JComboBox cbTrendData;

    // ******* Charts ******** \\
        // Location Chart
        private XYSeries locationSeries;
        private XYSeriesCollection locationCollection;
        private JFreeChart locationChart;
        private ChartPanel locationPanel;

        // Left Motor 1 Data
        private XYSeries leftMotorSeriesVoltage_1;
        private XYSeries leftMotorSeriesCurrent_1;
        private XYSeriesCollection leftMotorDataCollection_1;
        private JFreeChart leftMotorChart_1;
        private ChartPanel leftMotorPanel_1;

        // right Motor 1 Data
        private XYSeries rightMotorSeriesVoltage_1;
        private XYSeries rightMotorSeriesCurrent_1;
        private XYSeriesCollection rightMotorDataCollection_1;
        private JFreeChart rightMotorChart_1;
        private ChartPanel rightMotorPanel_1;


    // Playback Options
    private int currentPlaybackPoint = 0;
    private boolean isPaused = false;
    private int frameDelay = 20;

    // Chart Scale Options
    ValueAxis rightMotorScale;
    ValueAxis leftMotorScale;
    /**
     * Default Constructor
     * Sets up simple window properties
     */
    public DiagnosticViewer() {
        setTitle("2168 Diagnostic Tools");
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(WIDTH, HEIGHT));

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Main Frame Elements
        SetUpDisplayElements();

        //Dialogs
        SetUpOptionsDialog();
        SetUpTrendAnalysis();

        setVisible(true);
    }

    /**
     * Recurse through all the files located in the "trends/" folder.
     *
     * @return All files found in directory
     */
    public static ArrayList<String> GetTrendFiles() {
        ArrayList<String> LogFiles = new ArrayList<String>();

        File folder = new File("./trends/");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                LogFiles.add(listOfFiles[i].getName());
            }
        }

        return LogFiles;
    }

    /**
     * Setup Trend Analysis Dialog
     */
    public void SetUpTrendAnalysis() {

        final ArrayList<String> TrendFiles = GetTrendFiles();

        trendAnalysis = new JDialog(this, "Trend Analysis");
        trendAnalysis.setLayout(null);
        trendAnalysis.setSize(320, 200);

        trendFilesToBeAnalyzed = new JLabel("Files to be analyzed: " + TrendFiles.size());
        trendFilesToBeAnalyzed.setSize(trendFilesToBeAnalyzed.getPreferredSize().getSize());
        trendFilesToBeAnalyzed.setLocation(10, 40);
        trendAnalysis.add(trendFilesToBeAnalyzed);

        trendAnalysisSelectTrend = new JLabel("Select Data To View Trends:");
        trendAnalysisSelectTrend.setSize(trendAnalysisSelectTrend.getPreferredSize().getSize());
        trendAnalysisSelectTrend.setLocation(10, 10);
        trendAnalysis.add(trendAnalysisSelectTrend);

        cbTrendData = new JComboBox(OI.mainLog.TrendData);
        cbTrendData.setSize(cbTrendData.getPreferredSize().getSize());
        cbTrendData.setLocation(15 + trendAnalysisSelectTrend.getWidth(), 7);
        trendAnalysis.add(cbTrendData);

        btnTrendAnalysis = new JButton("Start Trend Analysis");
        btnTrendAnalysis.setSize(btnTrendAnalysis.getPreferredSize().getSize());
        btnTrendAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread CalculateTrendData = new Thread() {
                    @Override
                    public void run() {
                        CalculateTrendData(TrendFiles);
                    }
                };
                CalculateTrendData.start();
            }
        });
        btnTrendAnalysis.setLocation(153, 32);
        trendAnalysis.add(btnTrendAnalysis);

    }

    /**
     * Calculates trend data between
     * @param TrendFiles List of files to analise a trend on.
     */
    public void CalculateTrendData(ArrayList<String> TrendFiles) {

    }

    /**
     * Creates and sets up the options
     */
    public void SetUpOptionsDialog() {

        rightMotorScale = ((XYPlot) rightMotorChart_1.getPlot()).getDomainAxis();
        leftMotorScale = ((XYPlot) leftMotorChart_1.getPlot()).getDomainAxis();

        optionsDialog = new JDialog(this, "Playback Options");
        optionsDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        optionsDialog.setLayout(null);
        optionsDialog.setSize(new Dimension(250, 250));

        optionsDialogFrameDelay = new JLabel("Frame Delay (ms):");
        optionsDialogFrameDelay.setSize(optionsDialogFrameDelay.getPreferredSize().getSize());
        optionsDialogFrameDelay.setLocation(10, 10);
        optionsDialog.add(optionsDialogFrameDelay);

        optionsDialogFrameDelayInput = new JTextField(String.valueOf(frameDelay));
        optionsDialogFrameDelayInput.setSize(50, 20);
        optionsDialogFrameDelayInput.setLocation((int) optionsDialogFrameDelay.getPreferredSize().getSize().getWidth()
                        + 15, 7
        );
        optionsDialog.add(optionsDialogFrameDelayInput);

        optionsGlobalMotorScaleDomain = new JLabel("Motor Scale: ");
        optionsGlobalMotorScaleDomain.setSize(optionsGlobalMotorScaleDomain.getPreferredSize().getSize());
        optionsGlobalMotorScaleDomain.setLocation(10, 35);
        optionsDialog.add(optionsGlobalMotorScaleDomain);

        optionsDialogGlobalMotorScaleDomain = new JTextField("X1-X2");
        optionsDialogGlobalMotorScaleDomain.setSize(50, 20);
        optionsDialogGlobalMotorScaleDomain.setLocation(10 + optionsGlobalMotorScaleDomain.getWidth() + 8, 32);
        optionsDialog.add(optionsDialogGlobalMotorScaleDomain);

        btnOptionsResetPlayBack = new JButton("Reset Playback");
        btnOptionsResetPlayBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResetPlayback();
            }
        });
        btnOptionsResetPlayBack.setSize(btnOptionsResetPlayBack.getPreferredSize().getSize());
        btnOptionsResetPlayBack.setLocation(10, 180);
        optionsDialog.add(btnOptionsResetPlayBack);

        btnOptionsDialogOK = new JButton("OK");
        btnOptionsDialogOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameDelay = Integer.parseInt(optionsDialogFrameDelayInput.getText());
                setEnabled(true);
                try {
                    rightMotorScale.setRange(
                            Double.parseDouble(optionsDialogGlobalMotorScaleDomain.getText().split("-")[0]),
                            Double.parseDouble(optionsDialogGlobalMotorScaleDomain.getText().split("-")[1])
                    );
                    leftMotorScale.setRange(
                            Double.parseDouble(optionsDialogGlobalMotorScaleDomain.getText().split("-")[0]),
                            Double.parseDouble(optionsDialogGlobalMotorScaleDomain.getText().split("-")[1])
                    );

                    ((XYPlot) leftMotorChart_1.getPlot()).setDomainAxis(leftMotorScale);
                    ((XYPlot) rightMotorChart_1.getPlot()).setDomainAxis(rightMotorScale);
                }catch (NumberFormatException ex) {}
                optionsDialog.dispose();
            }
        });
        btnOptionsDialogOK.setSize(btnOptionsDialogOK.getPreferredSize().getSize());
        btnOptionsDialogOK.setLocation(180, 180);
        optionsDialog.add(btnOptionsDialogOK);

    }

    /**
     * Sets up all content on the window
     */
    public void SetUpDisplayElements() {
        currentTime = new JLabel();
        currentTime.setText("Current Time: 00:00");
        currentTime.setFont(new Font("Arial", Font.ITALIC, 16));
        currentTime.setSize(currentTime.getPreferredSize().getSize());
        currentTime.setLocation(850, 10);
        add(currentTime);

        timeProfile = new JSlider(JSlider.HORIZONTAL, 0, OI.mainLog.GetTotalFramesCaptured() - 1, 0);
        timeProfile.setMajorTickSpacing(40);
        timeProfile.setPaintTicks(true);
        timeProfile.setSize(new Dimension(1000, 40));
        timeProfile.setLocation(5, 640);

        timeProfile.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int dataPointLocation = ((JSlider)e.getSource()).getValue();
                currentTime.setText("Current Time: " +
                        String.valueOf(OI.mainLog.PointLocationToTime(dataPointLocation)));
                currentTime.setSize(currentTime.getPreferredSize().getSize());
                revalidate();
            }
        });
        add(timeProfile);

        // Start Button
        btnStart = new JButton("Start Replay");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread queryThread = new Thread() {
                    public void run() {
                        btnStart.setEnabled(false);
                        isPaused = false;
                        UpdateDisplayedData();
                    }
                };
                queryThread.start();
            }
        });
        btnStart.setSize(btnStart.getPreferredSize().getSize());
        btnStart.setLocation(10, 10);
        add(btnStart);

        // Pause Button
        btnPause = new JButton("Pause Replay");
        btnPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(true);
                isPaused = true;
            }
        });
        btnPause.setSize(btnPause.getPreferredSize().getSize());
        btnPause.setLocation(105, 10);
        add(btnPause);

        // Options Button
        btnOptions = new JButton("Settings");
        btnOptions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEnabled(false);
                optionsDialog.setVisible(true);
                optionsDialog.setLocationRelativeTo(self);
            }
        });
        btnOptions.setSize(btnOptions.getPreferredSize().getSize());
        btnOptions.setLocation(205, 10);
        add(btnOptions);

        // Trend Analysis
        btnTrendAnalysis = new JButton("Trend Analysis");
        btnTrendAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                trendAnalysis.setVisible(true);
            }
        });
        btnTrendAnalysis.setSize(264, 25);
        btnTrendAnalysis.setLocation(10, 40);
        add(btnTrendAnalysis);

        // Gyro Angle Label
        gyroAngle = new JLabel("Gyro Angle: 0\u00B0");
        gyroAngle.setFont(gyroAngle.getFont().deriveFont(12f));
        gyroAngle.setSize(gyroAngle.getPreferredSize().getSize());
        gyroAngle.setLocation(485, 450);
        add(gyroAngle);

        // Location Plot Chart
        {
            // Start initializing of Robot Location Series
            locationSeries = new XYSeries("Robot Location", false);
            locationSeries.add(0, 0);   // Add center start point

            // Create the Location data set
            locationCollection = new XYSeriesCollection();
            locationCollection.addSeries(locationSeries);

            // Create the JFreeChart
            locationChart = ChartFactory.createXYLineChart(
                    "          Robot Path Data",
                    "",
                    "",
                    locationCollection,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    true
            );
            locationChart.setBackgroundPaint(getBackground());

            // Create a chart panel that gets displayed
            locationPanel = new ChartPanel(locationChart);
            locationPanel.setLocation(300, 10);
            locationPanel.setSize(400, 400);
            add(locationPanel);
        }

        //Left Motor Data
        {
            //Set up the charts, Voltage and Current
            leftMotorSeriesVoltage_1 = new XYSeries("Voltage");
            leftMotorSeriesVoltage_1.add(0, 0);
            leftMotorSeriesCurrent_1 = new XYSeries("Current");
            leftMotorSeriesCurrent_1.add(0, 0);

            leftMotorDataCollection_1 = new XYSeriesCollection();
            leftMotorDataCollection_1.addSeries(leftMotorSeriesCurrent_1);
            leftMotorDataCollection_1.addSeries(leftMotorSeriesVoltage_1);

            leftMotorChart_1 = ChartFactory.createXYLineChart(
                    "Left Motor Data",
                    "",
                    "",
                    leftMotorDataCollection_1,
                    PlotOrientation.VERTICAL,
                    true,
                    false,
                    false
            );
            leftMotorChart_1.setBackgroundPaint(getBackground());

            leftMotorPanel_1 = new ChartPanel(leftMotorChart_1);
            leftMotorPanel_1.setSize(300, 200);
            leftMotorPanel_1.setLocation(0, 435);
            add(leftMotorPanel_1);
        }

        // Right Motor Data
        {
            //Set up the two charts, Voltage and Current
            rightMotorSeriesVoltage_1 = new XYSeries("Voltage");
            rightMotorSeriesVoltage_1.add(0, 0);
            rightMotorSeriesCurrent_1 = new XYSeries("Current");
            rightMotorSeriesCurrent_1.add(0, 0);

            rightMotorDataCollection_1 = new XYSeriesCollection();
            rightMotorDataCollection_1.addSeries(rightMotorSeriesCurrent_1);
            rightMotorDataCollection_1.addSeries(rightMotorSeriesVoltage_1);

            rightMotorChart_1 = ChartFactory.createXYLineChart(
                    "Right Motor Data",
                    "",
                    "",
                    rightMotorDataCollection_1,
                    PlotOrientation.VERTICAL,
                    false,
                    false,
                    false
            );
            rightMotorChart_1.setBackgroundPaint(getBackground());

            rightMotorPanel_1 = new ChartPanel(rightMotorChart_1);
            rightMotorPanel_1.setSize(300, 200);
            rightMotorPanel_1.setLocation(0, 235);
            add(rightMotorPanel_1);
        }

    }

    /**
     * UpdateDisplayedData runs in separate thread and is called when Start Replay is pressed.
     * This function loops through the DataPoints in PointData and displays them back to the user.
     */
    public void UpdateDisplayedData() {

        for (int i = currentPlaybackPoint; i <= OI.mainLog.GetTotalFramesCaptured() - 1; i++) {

            if (!isPaused) {
                DataPoint p = OI.mainLog.PointData.get(currentPlaybackPoint);

                // Update slider
                timeProfile.setValue(i);

                // Update location graph by adding new (X, Y) point
                locationSeries.add(
                        p.getX(),
                        p.getY()
                );

                // Update left motor current and voltage
                leftMotorSeriesVoltage_1.add(OI.mainLog.PointData.get(i).TimeElapsed
                        , p.getLeftVoltage()[0]);
                leftMotorSeriesCurrent_1.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getLeftCurrent()[0]);
                rightMotorSeriesVoltage_1.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getRightVoltage()[0]);
                rightMotorSeriesCurrent_1.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getRightCurrent()[0]);


                DecimalFormat df = new DecimalFormat("####.00");
                String heading = df.format(OI.mainLog.PointData.get(i).getHeading());
                gyroAngle.setText("Gyro Angle: " +  heading + "\u00B0");
                gyroAngle.setSize(gyroAngle.getPreferredSize().getSize());

                currentPlaybackPoint = i;
            } else {
                break;
            }

            try {
                Thread.sleep(frameDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        if (currentPlaybackPoint == OI.mainLog.GetTotalFramesCaptured()) {
            currentPlaybackPoint = 0;
        }

    }

    /**
     * Resets all graphs and objects linked to viewing a playback.
     */
    public void ResetPlayback() {
        currentPlaybackPoint = 0;
        timeProfile.setValue(0);

        leftMotorSeriesCurrent_1.clear();
        leftMotorSeriesVoltage_1.clear();

        rightMotorSeriesCurrent_1.clear();
        rightMotorSeriesVoltage_1.clear();

        locationSeries.clear();

    }

}