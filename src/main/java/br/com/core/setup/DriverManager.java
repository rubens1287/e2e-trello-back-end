package br.com.core.setup;

import cucumber.api.Scenario;

public abstract class DriverManager {

    public static ThreadLocal<Scenario> testScenario = new ThreadLocal<Scenario>();
    private static Process process;

    public static Process getProcess() {return process;}

    public static void setProcess(Process process) {DriverManager.process = process;}
}
