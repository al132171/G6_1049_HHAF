package test;

import cucumber.api.CucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Created by oscar on 5/11/14.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = "html:target/cukes",
    tags = {"@put"}
)
public class Runner {
}