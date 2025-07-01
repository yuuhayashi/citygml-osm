package osm.surveyor.download;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import osm.surveyor.OnlineTests;

/**
 * `mvn test -Dtest=osm.surveyor.download.AllTests`
 * 
 */
@RunWith(Categories.class)
@ExcludeCategory(OnlineTests.class)
@SuiteClasses({
	Test_Fujitv.class,
	Test_A.class,
	Test_B.class,
	Test_haya4.class,
	Test_TokyoSt.class,
	HttpGetTest.class
})
public class AllTests {
}
