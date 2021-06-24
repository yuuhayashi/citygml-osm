package osm.surveyor.osm.api;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import osm.surveyor.DetailTests;
import osm.surveyor.update.HttpGetTest;

/**
 * `mvn test -Dtest=osm.surveyor.osm.api.AllTests`
 * 
 */
@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({
	CitygmlFileTest.class, 
	CitygmlFileTest_A.class, 
	CitygmlFileTest_B.class, 
	CitygmlFileTest_C.class,
	CitygmlFileTest_D.class,
	CitygmlFileTest_E.class,
	CitygmlFileTest_Issue28.class,
	CitygmlFileTest_Issue32.class,
	HttpGetTest.class
})
public class AllTests {

}
