package cyberdojo.bowlingGame;

public class frame{
	int frameScore= 0;
	boolean hasStrike = false;
	boolean hasSpare = false;
	
	public void roll(ball ball,Character hitRecord){
		if (hitRecord.charValue() == 'X'){
			this.hasStrike = true;
			ball.pinsDown = game.pinsPerFrame;
		}
		else if(hitRecord.charValue() == '/'){
			this.hasSpare = true;
		}
		else if(hitRecord.charValue() == '-'){
			ball.pinsDown = 0;
		}
		else{
			ball.pinsDown = Character.getNumericValue(hitRecord.charValue());
		}
	}
}