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
})
public class AllTests {
}
