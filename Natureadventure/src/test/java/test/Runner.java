package test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by oscar on 5/11/14.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "html:target/cukes",
    tags = {"@put"}
)
public class Runner {
}