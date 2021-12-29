package osm.surveyor.osm.api;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import osm.surveyor.DetailTests;

/**
 * `mvn test -Dtest=osm.surveyor.osm.api.AllTests`
 * 
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_52396075`
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_52396076_1`
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_53375768`
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_53392547`
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_53392588`
 * `mvn test -Dtest=osm.surveyor.osm.api.CitygmlFileTest_53392589`
 * `mvn test -Dtest=osm.surveyor.osm.api.AllTests`
 * `mvn test -Dtest=osm.surveyor.osm.api.AllTests`
 * `mvn test -Dtest=osm.surveyor.osm.api.AllTests`
 * 
 */
@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({
	CitygmlFileTest_52396075.class,
	CitygmlFileTest_52396076_1.class,
	CitygmlFileTest_53375768.class,
	CitygmlFileTest_53392547.class,
	CitygmlFileTest_53392588.class,
	CitygmlFileTest_53392589.class,
	CitygmlFileTest_53395404.class,		// Issue #49
	
	CitygmlFileTest_A.class,
	CitygmlFileTest_AA.class,
	CitygmlFileTest_B.class,
	CitygmlFileTest_C.class,
	CitygmlFileTest_D.class,
	CitygmlFileTest_E.class,
	CitygmlFileTest_Issue28.class,
	CitygmlFileTest_Issue32.class,
	CitygmlFileTest_Issue34.class
})
public class AllTests {

}
