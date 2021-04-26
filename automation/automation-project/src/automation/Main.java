package automation;

import java.util.List;

import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

public class Main {

	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		System.setProperty("webdriver.chrome.driver", "lib\\chromedriver.exe");
		List<String> suites = Lists.newArrayList();
		suites.add("testng.xml");
		testng.setTestSuites(suites);
		testng.addListener((ITestNGListener) tla);
		testng.run();
		tla.getFailedTests().stream().map( p -> p.getThrowable()).forEach( a -> System.out.println(a));;
	}

}
