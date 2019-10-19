package runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = "gherkin",
				 tags = {},
				 plugin = {"json:target/json-cucumber-reports/cukejson.json",
						   "testng:target/testng-cucumber-reports/cuketestng.xml",
						 },
				features = "feature")
public class Runner extends AbstractTestNGCucumberTests {}
