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
	CitygmlFileTest_52396075.class,		// 箱根町 Issue #13 
	CitygmlFileTest_52396076_1.class,	// 箱根町 Issue #13
	CitygmlFileTest_53375768.class,		// 伊那市 Issue #12 「v1.2.4 単独の建物でもbuilding:part」
	CitygmlFileTest_53392547.class,		// 東京都大田区 多摩川 サンプル地区
	CitygmlFileTest_53392588.class,		// 東京都大田区 「name=都営大森西三丁目第2アパート」 innerあり
	CitygmlFileTest_53392589.class,
	CitygmlFileTest_53395404.class,		// Issue #49
	
	CitygmlFileTest_A.class,
	CitygmlFileTest_AA.class,
	CitygmlFileTest_B.class,
	CitygmlFileTest_C.class,
	CitygmlFileTest_D.class,			// 東京都大田区大森西三丁目 「name=都営大森西三丁目第2アパート」 innerあり Issue #40
	CitygmlFileTest_E.class,			
	CitygmlFileTest_Issue28.class,		// 福岡県飯塚市 #28,#34,#36,#37
	CitygmlFileTest_Issue32.class,		// #32
	CitygmlFileTest_Issue34.class		// 福岡県飯塚市 #34
})
public class AllTests {

}
