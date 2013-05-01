import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.TreeSet;

public class NearestPoints
{
	private static final int POINT_COUNT = 2000000;
	private static final int M_CLOSEST = 25;
	private static final Random rand = new Random(System.currentTimeMillis());

	public static void main (String[] args)
	{
		List<Point> points = new ArrayList<Point>();
		for (int i = 0; i < POINT_COUNT; i++)
			points.add(newRandomPoint());

		Point origin = newRandomPoint();
		long start = System.currentTimeMillis();
		List<Point> realClosestPoints = referenceMethod(origin, points, M_CLOSEST);
		List<Double> realClosestDistances = new ArrayList<Double>();
		for (Point point : realClosestPoints) {
			realClosestDistances.add(distance(origin, point));
		}

		long twixt = System.currentTimeMillis();

		List<Point> closestPoints = findMClosest(origin, points, M_CLOSEST);
		long end = System.currentTimeMillis();
		List<Double> closestDistances = new ArrayList<Double>();
		for (Point point : realClosestPoints) {
			closestDistances.add(distance(origin, point));
		}

		System.out.println("Origin : " + origin.toString());
		System.out.println("Closest " + M_CLOSEST + " Points");

		System.out.println();
		System.out.println("referenceMethod(), took " + (twixt - start) + " ms:");
		for (Point point : realClosestPoints)
			System.out.println("  " + point + " distance=" + distance(origin, point));

		System.out.println();
		System.out.println("findMClosest(), took " + (end - twixt) + " ms:");
		for (Point point : closestPoints)
			System.out.println("  " + point + " distance=" + distance(origin, point));

		System.out.println();
		System.out.println("Correct count? " + (closestPoints.size() == M_CLOSEST));
		System.out.println("Matches reference? " + realClosestDistances.equals(closestDistances));
		System.out.println("Matches reference point order? " + realClosestPoints.equals(closestPoints));

	}

	private static Point newRandomPoint()
	{
		int x = (int) (rand.nextDouble() * 1000.0 - 500.0);
		int y = (int) (rand.nextDouble() * 1000.0 - 500.0);
		return new Point(x, y);
	}

	private static List<Point> findMClosest(Point origin, List<Point> points, int M)
	{
		// TODO: Custom Implementation


		PriorityQueue<PointDistance> pointQueue = new PriorityQueue<PointDistance>();
		for (Point point : points)
		{
			PointDistance pointDistance = new PointDistance(point, distance(origin, point));
			pointQueue.add(pointDistance);
		}

		List<Point> closestPoints = new ArrayList<Point>();
		for (int i = 0; i < M; i++) {
			closestPoints.add(pointQueue.remove().getPoint());
		}

		return closestPoints;


		// TODO: End Custom Implementation
	}


	private static double distance(Point a, Point b)
	{
		double x_diff = a.getX() - b.getX();
		double y_diff = a.getY() - b.getY();
		double x_squared = x_diff * x_diff;
		double y_squared = y_diff * y_diff;
		return Math.sqrt(x_squared + y_squared);
	}

	private static List<Point> referenceMethod(Point origin, List<Point> points, int M)
	{
		TreeSet<PointDistance> pointTree = new TreeSet<PointDistance>();
		for (Point point : points)
		{
			PointDistance pointDistance = new PointDistance(point, distance(origin, point));
			if (pointTree.size() < M)
			{
				pointTree.add(pointDistance);
			}
			else if (pointTree.last().compareTo(pointDistance) > 0)
			{
				pointTree.pollLast();
				pointTree.add(pointDistance);
			}
		}

		List<Point> closestPoints = new ArrayList<Point>();
		for (PointDistance pointDistance : pointTree)
			closestPoints.add(pointDistance.getPoint());

		return closestPoints;
	}

	private static class PointDistance implements Comparable<PointDistance>
	{
		private Point point;
		private double distance;

		public PointDistance(Point point, double distance)
		{
			this.point = point;
			this.distance = distance;
		}

		public Point getPoint()
		{
			return point;
		}

		@Override
		public int compareTo(PointDistance other)
		{
			if (distance < other.distance)
				return -1;
			if (distance > other.distance)
				return 1;
			if (this != other)
				return 1;
			return 0;
		}

		@Override
		public boolean equals(Object other)
		{
			return this == other;
		}
	}
}
