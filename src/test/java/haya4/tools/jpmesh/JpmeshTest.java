package haya4.tools.jpmesh;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.geotools.geometry.DirectPosition2D;
import org.junit.Test;

public class JpmeshTest {
	@Test
	public void getMeshTest() {
		assertEquals("5339", Jpmesh.getMesh(139.7d, 35.683333d, 1));
		assertEquals("533945", Jpmesh.getMesh(139.7d, 35.683333d, 2));
		assertEquals("53394526", Jpmesh.getMesh(139.7d, 35.683333d, 3));
	}
    
	@Test
    public void getPositionTest() {
    	DirectPosition2D ret = Jpmesh.getPosition(null);
    	assertNull(ret);
    	
    	ret = Jpmesh.getPosition("5339");
    	assertNotNull(ret);
    	assertEquals(35.333333d, ret.getY(), 0.000001d);
    	assertEquals(139.000000d, ret.getX(), 0.000001d);
    	
    	ret = Jpmesh.getPosition("533945");
    	assertNotNull(ret);
    	assertEquals(35.666666d, ret.getY(), 0.000001d);
    	assertEquals(139.625000d, ret.getX(), 0.000001d);
    	
    	ret = Jpmesh.getPosition("53394526");
    	assertNotNull(ret);
    	assertEquals(35.683333d, ret.getY(), 0.000001d);
    	assertEquals(139.7d, ret.getX(), 0.000001d);
    	
    	ret = Jpmesh.getPosition("5339452600");
    	assertNotNull(ret);
    	assertEquals(35.683333d, ret.getY(), 0.000001d);
    	assertEquals(139.7d, ret.getX(), 0.000001d);
    	
    	ret = Jpmesh.getPosition("53394526 ");
    	assertNull(ret);
    }

    @Test
    public void getCenterTest() {
    	DirectPosition2D center = Jpmesh.getCenterPosition("53394526");
    	assertNotNull(center);
    	assertEquals(35.687500d, center.getY(), 0.000001d);
    	assertEquals(139.70625d, center.getX(), 0.000001d);
    }

    @Test
    public void getRectangleTest() {
    	Rectangle2D rect = Jpmesh.getRectangle("53394526");
    	assertNotNull(rect);
    	assertEquals(35.683333d, rect.getY(), 0.000001d);
    	assertEquals(139.7d, rect.getX(), 0.000001d);
    	assertEquals(0.0083333d, rect.getHeight(), 0.000001d);
    	assertEquals(0.0125, rect.getWidth(), 0.000001d);
    }
}
