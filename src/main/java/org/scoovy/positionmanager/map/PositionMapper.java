package org.scoovy.positionmanager.map;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.scoovy.positionmanager.model.Point;

public class PositionMapper {
	private Map<String, Point> points;
	private PositionMapper(Map<String, Point> map){
		this.points = map;
	}
	public Point getPoint(String tagId) {
		return this.points.get(tagId);
	}
	public static PositionMapper load(String resourceName) throws IOException{
		URL url = PositionMapper.class.getResource(resourceName);
		Map<String, Point> map = new HashMap<>();
		try (Scanner scanner = new Scanner(url.openStream())){
			//ヘッダ行を読み飛ばす  row, column, id
			scanner.nextLine();
			
			while(scanner.hasNextLine()) {
				String[] line = scanner.nextLine().split(",");
				int y = Integer.parseInt(line[0]);
				int x = Integer.parseInt(line[1]);
				String id = line[2];
				Point point = new Point(x, y);
				map.put(id, point);
			}
		}
		return new PositionMapper(map);
	}
}
