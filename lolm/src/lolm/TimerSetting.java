package lolm;

public enum TimerSetting {
	SLOWEST(5000), SLOWER(2000), SLOW(1500), NORMAL(1000), FAST(500), FASTER(100), FASTEST(10);
	final long speed;
	TimerSetting(long speed) {
		this.speed = speed;
	}
	public long getSpeed() {
		return speed;
	}
}
