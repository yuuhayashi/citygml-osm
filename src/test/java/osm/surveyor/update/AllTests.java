package osm.surveyor.update;

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
	//osm.surveyor.update.Test_Fujitv.class,
	//Test_A.class,
	//Test_B.class,
	osm.surveyor.update.Test_haya4.class
	//Test_TokyoSt.class
})
public class AllTests {
}
