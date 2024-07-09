package application;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class TranslateObs {

	private TranslateTransition trans;
	
	public TranslateObs(Node node) {
		
		trans = new TranslateTransition( Duration.millis(50), node);
		trans.setFromX(0f);
		trans.setByX(10f);
		trans.setCycleCount(2);
		trans.setAutoReverse(true);
		
		
		
	}
	
	public void shake() {
		
		trans.playFromStart();
	}
}
