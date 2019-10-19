package br.com.core.report;

import br.com.core.setup.DriverManager;


public class ExtentReports extends DriverManager {

    private String complementNameEvidence;

    /**
     * Add comment to the cucumber-extent report listener for generating ExtentReports and Klov
     *
     * @param sMsg Text to be added to the report
     */
    public static synchronized void appendToReport(String sMsg) {
        System.out.println("[FrameWork] " + sMsg);
        testScenario.get().write(sMsg);
    }

    public String getComplementNameEvidence() {
        return complementNameEvidence;
    }

    public void setComplementEvidenceName(String complementNameEvidence) {
        this.complementNameEvidence = complementNameEvidence;
    }
}
