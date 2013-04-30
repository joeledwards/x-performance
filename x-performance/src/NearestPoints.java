import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class NearestPoints {
	private static final int POINT_COUNT = 1000;
	private static final int M_CLOSEST = 5;

	public static void main (String[] args) {
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < POINT_COUNT; i++) {
			points.add(newRandomPoint());
		}

		Point origin = newRandomPoint();
		List<Point> closestPoints = findMClosest(origin, points, M_CLOSEST);
		List<Point> realClosestPoints = referenceMethod(origin, points, M_CLOSEST);

		assert closestPoints.equals(realClosestPoints);
	}

	private static Point newRandomPoint() {
			Point newPoint = new Point();
			newPoint.x = (int) (Math.random() * 1000.0);
			newPoint.y = (int) (Math.random() * 1000.0);
			return newPoint;
	}

	private static List<Point> findMClosest(Point origin, List<Point> points, int M) {
		return referenceMethod(origin, points, M);
	}

	private static double distance(Point a, Point b) {
		double x_squared = Math.pow(a.x - b.x, 2);
		double y_squared = Math.pow(a.y - b.y, 2);
		double sqrt_diff = Math.sqrt(x_squared + y_squared);
		return sqrt_diff;
	}

	private static List<Point> referenceMethod(Point origin, List<Point> points, int M) {
		List<Point> closestPoints = new ArrayList<Point>();
		return closestPoints;
	}

	private static class PointDistance implements Comparable<PointDistance> {
		Point point;
		double distance;

		public PointDistance(Point point, double distance) {
			this.point = point;
			this.distance = distance;
		}

		@Override
		public int compareTo(PointDistance other) {
			if (distance < other.distance)
				return -1;
			if (distance > other.distance)
				return 1;
			return 0;
		}

	}
}
