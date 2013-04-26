public class Bonding {
	public static void main(String[] args) {
		Player parent = new Player(null);
		Player child = new Player(parent);
		parent.target = child;
		parent.aim(new Ball());
	}

	public static class Ball extends Throwable{}
	public static class Player {
		Player target;
		Integer throwCount = 0;

		public Player(Player target) {
			this.target = target;
		}
		
		public void aim(Ball ball) {
			try {
				System.out.println("Throw #" + (++throwCount));
				throw ball;
			} catch (Ball returned) {
				target.aim(returned);
			} finally {
				throwCount++;
			}
		}
	}
}
