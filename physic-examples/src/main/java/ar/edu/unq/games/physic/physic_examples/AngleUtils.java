package ar.edu.unq.games.physic.physic_examples;

public class AngleUtils {

	
		public static double ajustarAnguloEntrePiYMenosPi(double theta) {
						
			if (theta > Math.PI) {
				return ajustarAnguloEntrePiYMenosPi(theta - (2 * Math.PI));
			}
			if (theta < -Math.PI) {
				return ajustarAnguloEntrePiYMenosPi(theta + (2 * Math.PI));
			}
			return theta;
		}
}
