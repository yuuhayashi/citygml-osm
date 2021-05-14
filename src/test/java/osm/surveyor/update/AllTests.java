package osm.surveyor.update;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import osm.surveyor.DetailTests;

@RunWith(Categories.class)
@ExcludeCategory(DetailTests.class)
@SuiteClasses({ OsmUpdaterTest_A.class, OsmUpdaterTest.class, PostgisTest.class })
public class AllTests {

}
