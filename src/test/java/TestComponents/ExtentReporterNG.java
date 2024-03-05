package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    // Uděláme jí static, abychom k tehle metodě mohli přistupovat aniž bychom museli deklarovat objekt
    public static ExtentReports getReportObject(){

        //ExtentSparkReporter, this class help us to configure basic config. and set path, where should be store
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Web Automation Results");
        extentSparkReporter.config().setDocumentTitle("Test Results");

        // This is main class
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("Tester", "Jiří Kvarda");

        return extentReports;

    }
}
