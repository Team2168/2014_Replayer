package org.team2168.DiagnosticTools;

import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.data.xy.XYSeriesCollection;
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.stat.StatUtils;

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

    public double LiftVoltage;

    public double LiftCurrent;

    public double DeltaTime;

    public DataPoint(double x, double y, double leftSpeed, double heading, double rightSpeed, double[] leftVoltage,
                     double[] rightVoltage, double[] leftCurrent, double[] rightCurrent, double timeElapsed,
                     double liftVoltage, double liftCurrent, double deltaTime) {
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
        LiftVoltage = liftVoltage;
        LiftCurrent = liftCurrent;
        DeltaTime = deltaTime;
    }

    public double getDeltaTime() {
        return DeltaTime;
    }

    public double getLiftVoltage() {
        return LiftVoltage;
    }

    public double getLiftCurrent() {
        return LiftCurrent;
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
    public int SPIGyroAngle = 15;
    public int LeftEncoderPosition = 17;
    public int RightEncoderPosition = 19;
    public int LiftMotorVoltage = 21;
    public int LiftMotorCurrent = 22;
    public int RobotState = 23;
    public int CompressorStatus = 24;

    public final double MinimumStartTime = 0.0;

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
                    double heading = Double.parseDouble(entryData[SPIGyroAngle]) + 90;

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

                    //Get lift voltage and current
                    double liftVoltage = Double.parseDouble(entryData[LiftMotorVoltage]);
                    double liftCurrent = Double.parseDouble(entryData[LiftMotorCurrent]);

                    // Convert distances to feet
                    leftDistanceTraveled /= 12;
                    rightDistanceTraveled /= 12;

                    // Calculate feet per second
                    double timeDifference = Double.parseDouble(entryData[RunTime]) - prevTime;
                    prevTime = Double.parseDouble(entryData[RunTime]);
                    double leftFeetPerSecond = leftDistanceTraveled / timeDifference;
                    double rightFeetPerSecond = rightDistanceTraveled / timeDifference;

                    boolean direction = true;
                    if (leftFeetPerSecond > 0 && rightFeetPerSecond > 0)
                        direction = true;
                    if (leftFeetPerSecond < 0 && rightFeetPerSecond < 0)
                        direction = false;

                    // Calculate new (X, Y) point
                    double x = prevX + calculateX(totalDistanceTraveled, heading, direction, timeDifference);
                    double y = prevY + calculateY(totalDistanceTraveled, heading, direction, timeDifference);

                    // Add the log data
                    logFileData.PointData.add(new DataPoint(x, y, leftFeetPerSecond, heading, rightFeetPerSecond,
                            leftVoltage, rightVoltage, leftCurrent, rightCurrent,
                            Double.parseDouble(entryData[RunTime]), liftVoltage, liftCurrent, timeDifference));

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
    public static double calculateX(double totalDistanceTraveled, double heading, boolean direction, double deltaT) {
        // (1/2) * distance^2 * Cos(heading)
        if (direction) {
            double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
            double p_2 = Math.cos(Math.toRadians(heading));
            return (Math.toDegrees(p_1 * p_2 * deltaT)) / 12;
        }else{
            double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
            double p_2 = Math.cos(Math.toRadians(heading));
            return -(Math.toDegrees(p_1 * p_2 * deltaT)) / 12;
        }
    }

    /**
     *
     * @param totalDistanceTraveled Total distance traveled
     * @param heading Heading in degrees
     * @return New Y value
     */
    public static double calculateY(double totalDistanceTraveled, double heading, boolean direction, double deltaT) {
        if (direction) {
            double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
            double p_2 = Math.sin(Math.toRadians(heading));
            return (Math.toDegrees(p_1 * p_2 * deltaT)) / 12;
        } else {
            double p_1 = 0.5 * Math.pow(totalDistanceTraveled, 2);
            double p_2 = Math.sin(Math.toRadians(heading));
            return -(Math.toDegrees(p_1 * p_2 * deltaT)) / 12;
        }
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
 * Class used for calculating the tred line equation
 */
class Equation{

    private double[] c;
    private double[] m;

    private double b;
    private double x;

    public Equation(PolynomialSplineFunction p) {
        c = new double[p.getPolynomials().length];
        m = new double[p.getPolynomials().length];

        for (int C = 0; C <= c.length - 1; C++)
            c[C] = p.getPolynomials()[C].getCoefficients()[0];

        for (int M = 0; M <= m.length - 1; M++) {
            try {
                m[M] = p.getPolynomials()[M].getCoefficients()[1];
            }catch (ArrayIndexOutOfBoundsException ex) {
                m[M] = p.getPolynomials()[M].getCoefficients()[0];
            }
        }
        b = mean(c);
        x = mean(m);

    }

    /**
     * Calculates the value of the equation
     * @param z The X value to evaluate y
     * @return The value
     */
    public double value(double z) {
        return (x * z) + b;
    }

    /**
     * Calculates the mean of a double array
     * @param m Double[] of numbers to find average of
     * @return The average.
     */
    private double mean(double[] m) {
        double sum = 0;
        for (int i = 0; i < m.length; i++) {
            sum += m[i];
        }
        return sum / m.length;
    }

    @Override
    public String toString() {
        if (b > 0)
            return "y = " + x + "x + " + b;
        else
            return "y = " + x + "x - " + b;
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
    private JLabel optionsTrendFutureAnalysis;
    private JLabel trendAnalysisSelectTrend;
    private JLabel trendFilesToBeAnalyzed;
    private JLabel lbCompressorStatus;
    private JLabel lbRobotState;


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

    // ******* Progress Bar ******* \\
    private JProgressBar pgbLoadedTrendFiles;

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

    private XYSeries liftMotorVoltage;
    private XYSeries liftMotorCurrent;
    private XYSeriesCollection liftMotorDataCollection;
    private JFreeChart liftMotorChart;
    private ChartPanel liftMotorPanel;

    private XYSeries leftDriveSpeed;
    private XYSeries rightDriveSpeed;
    private XYSeriesCollection leftRightDriveSpeed;
    private JFreeChart driveSpeedChart;
    private ChartPanel driveSpeedPanel;

    // Playback Options
    private int currentPlaybackPoint = 0;
    private boolean isPaused = false;
    private int frameDelay = 20;

    // Chart Scale Options
    ValueAxis rightMotorScale;
    ValueAxis leftMotorScale;

    // Trend Options
    private int futureAnalysis = 2;

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
        trendAnalysis.setSize(320, 140);

        trendFilesToBeAnalyzed = new JLabel("Files to be analyzed: " + TrendFiles.size());
        trendFilesToBeAnalyzed.setSize(trendFilesToBeAnalyzed.getPreferredSize().getSize());
        trendFilesToBeAnalyzed.setLocation(10, 40);
        trendAnalysis.add(trendFilesToBeAnalyzed);

        trendAnalysisSelectTrend = new JLabel("Select Data To View Trends:");
        trendAnalysisSelectTrend.setSize(trendAnalysisSelectTrend.getPreferredSize().getSize());
        trendAnalysisSelectTrend.setLocation(10, 10);
        trendAnalysis.add(trendAnalysisSelectTrend);

        cbTrendData = new JComboBox(new String[]{"Left Drivetrain Voltage", "Left Drivetrain Current",
                "Right Drivetrain Voltage", "Right Drivetrain Current", "Lift Motor Voltage", "Lift Motor Current"});
        cbTrendData.setSize(cbTrendData.getPreferredSize().getSize());
        cbTrendData.setLocation(15 + trendAnalysisSelectTrend.getWidth(), 7);
        trendAnalysis.add(cbTrendData);

        pgbLoadedTrendFiles = new JProgressBar();
        pgbLoadedTrendFiles.setMinimum(0);
        pgbLoadedTrendFiles.setMaximum(TrendFiles.size());
        pgbLoadedTrendFiles.setSize(280, 15);
        pgbLoadedTrendFiles.setLocation(10, 70);
        trendAnalysis.add(pgbLoadedTrendFiles);

        btnTrendAnalysis = new JButton("Start Trend Analysis");
        btnTrendAnalysis.setSize(btnTrendAnalysis.getPreferredSize().getSize());
        btnTrendAnalysis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread CalculateTrendData = new Thread() {
                    @Override
                    public void run() {
                        CalculateTrendData(TrendFiles, cbTrendData.getSelectedIndex());
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
    public void CalculateTrendData(ArrayList<String> TrendFiles, int TREND_TYPE) {

        System.out.println("Calculating trend data...");

        int count = 1;
        long startTime = System.nanoTime();
        for (String fileName : TrendFiles) {
            pgbLoadedTrendFiles.setValue(count);
            try {
                System.out.print("\t");
                OI.trendLogs.add(new LogFileReader("trends/" + fileName).loadLog());
            } catch (IOException e) {
                e.printStackTrace();
            }
            count ++;
        }

        ArrayList<Double> max = new ArrayList<Double>();
        ArrayList<Double> mode = new ArrayList<Double>();

        // Loop through all trend logs
        for (LogFileData l : OI.trendLogs) {
            //Find max, and average, and curve fit.

            double _max = 0.0;
            double[] _mode = new double[l.GetTotalFramesCaptured()];
            int _modeIndexValue = 0;

            for (DataPoint point : l.PointData) {
                switch (TREND_TYPE) {
                    case 0:
                        // Calculate
                        if (_max < point.getLeftVoltage()[0])
                            _max = point.getLeftVoltage()[0];

                        if (point.getLeftVoltage()[0] > 1) {
                            _mode[_modeIndexValue] = point.getLeftVoltage()[0];
                            _modeIndexValue++;
                        }

                        break;
                    case 1:
                        if (_max < point.getLeftCurrent()[0])
                            _max = point.getLeftCurrent()[0];

                        if (point.getLeftCurrent()[0] > 1) {
                            _mode[_modeIndexValue] = point.getLeftCurrent()[0];
                            _modeIndexValue++;
                        }
                        break;
                    case 2:
                        if (_max < point.getRightVoltage()[0])
                            _max = point.getRightVoltage()[0];

                        if (point.getRightVoltage()[0] > 1) {
                            _mode[_modeIndexValue] = point.getRightVoltage()[0];
                            _modeIndexValue++;
                        }

                        break;
                    case 3:
                        if (_max < point.getRightCurrent()[0])
                            _max = point.getRightCurrent()[0];

                        if (point.getRightCurrent()[0] > 1) {
                            _mode[_modeIndexValue] = point.getRightCurrent()[0];
                            _modeIndexValue++;
                        }
                        break;
                    case 4:
                        if (_max < point.getLiftVoltage())
                            _max = point.getLiftVoltage();

                        if (point.getLiftVoltage() > -1 ) {
                            _mode[_modeIndexValue] = point.getLiftVoltage();
                            _modeIndexValue++;
                        }
                        break;
                    case 5:
                        if (_max < point.getLiftCurrent())
                            _max = point.getLiftCurrent();

                        if (point.getLiftCurrent() > -1) {
                            _mode[_modeIndexValue] = point.getLiftCurrent();
                            _modeIndexValue++;
                        }
                        break;
                }
            }

            mode.add(StatUtils.mode(_mode, 0, _modeIndexValue)[0]);
            max.add(_max);

        }

        // Create xPoints[] to serve as a basis for all xPoints;
        // xPoints[] simply contains an integer from 1-x[].length (ex. {1, 2, 3, 4, 5, 6})
        // to serve as a marker for the X value of the data points.
        double[] xPoints = new double[max.size()];
        for (int xP = 0; xP <= xPoints.length - 1; xP ++)
            xPoints[xP] = xP + 1;

        // Create a double[] for max and mode ArrayLists
        // The array length is based on the length of the xPoints[] to ensure a (x, y) pair.
        double[] _max = new double[xPoints.length];
        for (int _mP = 0; _mP <= max.size() - 1; _mP ++)
            _max[_mP] = max.get(_mP);

        double[] _mode = new double[xPoints.length];
        for (int _mM = 0; _mM <= mode.size() - 1; _mM ++)
            _mode[_mM] = mode.get(_mM);

        System.out.println("\t\tMaxes: " + Arrays.toString(_max));
        System.out.println("\t\tModes: " + Arrays.toString(_mode));

        // Max and mode trend line equation
        PolynomialSplineFunction ApacheMaxEquation = new LinearInterpolator().interpolate(xPoints, _max);
        PolynomialSplineFunction ApacheModeEquation = new LinearInterpolator().interpolate(xPoints, _mode);

        Equation maxEquation = new Equation(ApacheMaxEquation);
        Equation modeEquation = new Equation(ApacheModeEquation);

        System.out.println("\t\t\tMax Equation: " + maxEquation);
        System.out.println("\t\t\tMode Equation: " + modeEquation);

        pgbLoadedTrendFiles.setValue(0);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Loaded and created trend in " + duration + "ms");

        // Create a new JDialog to display the new data
        {
            JDialog trendData = new JDialog();
            trendData.setAlwaysOnTop(true);
            trendData.setSize(500, 380);
            trendData.setLayout(null);

            XYSeries maxTrendKnown = new XYSeries("Max Trend Known", false);
            for (int maxTrendKnownPoint = 0; maxTrendKnownPoint <= _max.length - 1; maxTrendKnownPoint++)
                maxTrendKnown.add(maxTrendKnownPoint + 1, _max[maxTrendKnownPoint]);

            XYSeries modeTrendKnown = new XYSeries("Mode Trend Known", false);
            for (int modeTrendKnownPoint = 0; modeTrendKnownPoint <= _mode.length - 1; modeTrendKnownPoint++)
                modeTrendKnown.add(modeTrendKnownPoint + 1, _mode[modeTrendKnownPoint]);

            XYSeries maxTrendFuture = new XYSeries("Max Trend Calculated", false);
            maxTrendFuture.add(maxTrendKnown.getDataItem(modeTrendKnown.getItemCount() - 1));
            for (int maxFuture = 1; maxFuture <= futureAnalysis; maxFuture++)
                maxTrendFuture.add(_max.length + maxFuture, maxEquation.value(_max.length + maxFuture));

            XYSeries modeTrendFuture = new XYSeries("Mode Trend Calculated", false);
            modeTrendFuture.add(modeTrendKnown.getDataItem(modeTrendKnown.getItemCount() - 1));
            for (int modeFuture = 1; modeFuture <= futureAnalysis; modeFuture++)
                modeTrendFuture.add(_mode.length + modeFuture, modeEquation.value(_max.length + modeFuture));

            XYSeriesCollection trendDataCollection = new XYSeriesCollection();
            trendDataCollection.addSeries(maxTrendKnown);
            trendDataCollection.addSeries(modeTrendKnown);
            trendDataCollection.addSeries(maxTrendFuture);
            trendDataCollection.addSeries(modeTrendFuture);

            JFreeChart trendMotorChart;
            ChartPanel trendMotorPanel;

            trendMotorChart = ChartFactory.createXYLineChart(
                    "",
                    "",
                    "",
                    trendDataCollection,
                    PlotOrientation.VERTICAL,
                    true,
                    false,
                    false
            );
            trendMotorChart.setBackgroundPaint(getBackground());

            trendMotorPanel = new ChartPanel(trendMotorChart);
            trendMotorPanel.setSize(450, 300);
            trendMotorPanel.setLocation(10, 10);
            trendData.add(trendMotorPanel);

            switch (TREND_TYPE) {
                case 0:
                    trendData.setTitle("Left Motor Voltage Trend");
                    break;
            }

            trendData.setVisible(true);
        }

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

        //Lift motor data
        {
            //Set up the charts, Voltage and Current
            liftMotorVoltage = new XYSeries("Voltage");
            liftMotorVoltage.add(0, 0);
            liftMotorCurrent = new XYSeries("Current");
            liftMotorCurrent.add(0, 0);

            liftMotorDataCollection = new XYSeriesCollection();
            liftMotorDataCollection.addSeries(liftMotorVoltage);
            liftMotorDataCollection.addSeries(liftMotorCurrent);

            liftMotorChart = ChartFactory.createXYLineChart(
                    "Lift Motor Data",
                    "",
                    "",
                    liftMotorDataCollection,
                    PlotOrientation.VERTICAL,
                    true,
                    false,
                    false
            );
            liftMotorChart.setBackgroundPaint(getBackground());

            liftMotorPanel = new ChartPanel(liftMotorChart);
            liftMotorPanel.setSize(300, 200);
            liftMotorPanel.setLocation(700, 435);
            add(liftMotorPanel);
        }

        // Drive Speeds
        {
            //Set up the charts, Voltage and Current
            rightDriveSpeed = new XYSeries("Right Drive Speed ft/s");
            rightDriveSpeed.add(0, 0);
            leftDriveSpeed = new XYSeries("Left Drive Speed ft/s");
            leftDriveSpeed.add(0, 0);

            leftRightDriveSpeed = new XYSeriesCollection();
            leftRightDriveSpeed.addSeries(rightDriveSpeed);
            leftRightDriveSpeed.addSeries(leftDriveSpeed);

            driveSpeedChart = ChartFactory.createXYLineChart(
                    "Drive Speed Data",
                    "",
                    "",
                    leftRightDriveSpeed,
                    PlotOrientation.VERTICAL,
                    true,
                    false,
                    false
            );
            driveSpeedChart.setBackgroundPaint(getBackground());

            driveSpeedPanel = new ChartPanel(driveSpeedChart);
            driveSpeedPanel.setSize(300, 200);
            driveSpeedPanel.setLocation(700, 235);
            add(driveSpeedPanel);
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

                // Update right motor current and voltage
                rightMotorSeriesVoltage_1.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getRightVoltage()[0]);
                rightMotorSeriesCurrent_1.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getRightCurrent()[0]);


                // Update lift motor current and voltage
                liftMotorVoltage.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getLiftVoltage());
                liftMotorCurrent.add(OI.mainLog.PointData.get(i).TimeElapsed, p.getLiftCurrent());

                // Gyro heading
                DecimalFormat df = new DecimalFormat("####.00");
                String heading = df.format(OI.mainLog.PointData.get(i).getHeading());
                gyroAngle.setText("Gyro Angle: " +  heading + "\u00B0");
                gyroAngle.setSize(gyroAngle.getPreferredSize().getSize());

                leftDriveSpeed.add(OI.mainLog.PointData.get(i).TimeElapsed, OI.mainLog.PointData.get(i).getLeftSpeed());
                rightDriveSpeed.add(OI.mainLog.PointData.get(i).TimeElapsed, OI.mainLog.PointData.get(i).getRightSpeed());


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

        liftMotorCurrent.clear();
        liftMotorVoltage.clear();

        leftDriveSpeed.clear();
        rightDriveSpeed.clear();

        locationSeries.clear();

    }

}