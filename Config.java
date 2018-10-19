public class Config {
	//public final int viewDistance;//Furthest a ship looks(and travels) when looking for halite; 16-32
	public final int maxShipCount;//Maximum number of ships to produce; 25-150
	public final int maxShipTurn;//Last turn to produce ships on; 50-450
	public final int maxCapacityBeforeDropoff;//Maximum amount of halite a ship can have before returning to dropoff; 0-1000
	public final int minHaliteAmount;//Minumum halite a square needs to have for a ship to stay on it; 50-750

	public Config(/*int vd, */int msc, int mst, int mcbd, int mha) {
		//viewDistance = vd;
		maxShipCount = msc;
		maxShipTurn = mst;
		maxCapacityBeforeDropoff = mcbd;
		minHaliteAmount = mha;
	}
}