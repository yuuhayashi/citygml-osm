package osm.surveyor.citygml;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import osm.surveyor.PomFile;

public class ConversionTableTest {
	static String version;
	static String name;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Path path = Paths.get("pom.xml");
        PomFile pom = new PomFile(path.toFile());
        pom.parse();
        version = pom.getVersion();
        name = pom.getName();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test148() {
        // resourceフォルダに`conversion.json`が存在すること
		try {
			ConversionTable conversionTable = new ConversionTable();
			assertNotNull(conversionTable);
			assertNotNull(conversionTable.version);
			assertNotNull(conversionTable.name);
			assertNotNull(conversionTable.usageList);
			assertEquals(15, conversionTable.usageList.size());
			assertEquals(version, conversionTable.version);		// pom.xml の <version> と一致すること
			assertEquals(name, conversionTable.name);		// pom.xml の <name> と一致すること
		}
		catch(Exception e) {
			fail();
		}
	}
}
