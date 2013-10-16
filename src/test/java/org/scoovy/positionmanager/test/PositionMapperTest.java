package org.scoovy.positionmanager.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.scoovy.positionmanager.map.PositionMapper;
import org.scoovy.positionmanager.model.Point;

public class PositionMapperTest {
	private PositionMapper mapper;
	@Before
	public void init() throws IOException{
		this.mapper = PositionMapper.load("/positions.csv");
	}
	@Test
	public void testgetPoint() {
		Point point = this.mapper.getPoint("E07C1CE55CE871D");
		//キーが存在する場合
		Point requiredPoint = new Point(3, 1);
		assertEquals(point, requiredPoint);
		//キーが存在しない場合
		Point nullPoint = this.mapper.getPoint("???????????????");
		assertNull(nullPoint);
	}

}
