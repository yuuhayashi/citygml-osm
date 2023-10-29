package osm.surveyor.osm.camel;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import osm.surveyor.DetailTests;

/**
 * `mvn test -Dtest=osm.surveyor.update.AllTests`
 * 
 */
@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({
	Test_A.class,
	Test_53392547.class,
	Test_B.class,
	Test_C.class,
	Test_D.class,
	Test_70.class,		// Issue#71
	Test_Issue79.class,		// Issue #79
	Test_Issue119.class,	// Issue #119
	osm.surveyor.osm.camel.Test_Fujitv.class,
	osm.surveyor.osm.camel.Test_haya4.class
})
public class AllTests {
}
