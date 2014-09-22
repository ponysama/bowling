package cyberdojo.bowlingGame;

import java.util.LinkedList;
import java.util.Queue;
public class game{
	public int score = 0;
	boolean gameFinished = false;
	public static final int MaxTurns = 10;
	public static final int pinsPerFrame = 10;
	public ball firstBall = null;
	frame[] gameFrames = new frame[MaxTurns];
	Queue<Character> hitRecords;
	int currentFrameNumber = 0;	
	
	game(String playRecord){
		this.hitRecords = new LinkedList<Character>();
		for(int i=0;i<MaxTurns;i++) this.gameFrames[i] = new frame();
		loadRecord(playRecord);
	}
	
	public void loadRecord(String playRecord){
		char[] cleanRecord = playRecord.replace("|", "").toCharArray();
		for (char rec: cleanRecord){
			(this.hitRecords).add(new Character(rec));
		}	
		
	}
	
	
	
	public void play(){
		firstBall = new ball();
		ball thisball = firstBall;
		frame currentFrame = null;
		
		while (this.currentFrameNumber<this.MaxTurns){
			
			currentFrame = this.gameFrames[this.currentFrameNumber];
			currentFrame.roll(thisball, hitRecords.poll());
			
			if (!(currentFrame.hasStrike)) {
				int firstBallScore = thisball.pinsDown;
				thisball = this.drawNextBall(thisball);
				currentFrame.roll(thisball, this.hitRecords.poll());
				if(currentFrame.hasSpare){
					thisball.pinsDown = this.pinsPerFrame - firstBallScore;
				}
			}
			
			thisball = this.drawNextBall(thisball);
			
			currentFrameNumber++;
		}
		
		if (currentFrame.hasStrike){
			this.playBonus(thisball);
			this.playBonus(this.drawNextBall(thisball));
		}
		else if(currentFrame.hasSpare){
			this.playBonus(thisball);
		}
		else{
			
		}
		
		this.gameFinished = true;
	}
	
	public void playBonus(ball bonus){
		if (this.hitRecords.peek()!=null){
			char rec = this.hitRecords.poll().charValue();
			if(rec=='X'){
				bonus.pinsDown = 10;
			}
			else if(Character.isDigit(rec)){
				bonus.pinsDown = Character.getNumericValue(rec);
			}
			else{
				bonus.pinsDown = 0;
			}
		}
		else{
			System.out.println("sth wrong");
		}
	}
	
	public void score(){
		this.currentFrameNumber = 0;
		frame currentFrame;
		ball currentBall = this.firstBall;
		while (currentFrameNumber<this.MaxTurns){
			currentFrame = this.gameFrames[currentFrameNumber];
			this.score += this.frameScore(currentFrame, currentBall);
			currentBall = currentBall.nextBall;
			if(!currentFrame.hasStrike) currentBall = currentBall.nextBall;
			currentFrameNumber++;
		}
	}
	
	public int frameScore(frame currentFrame,ball currentBall){
		ball next = currentBall.nextBall;
		int framescore;
		if(currentFrame.hasStrike){
			framescore = this.pinsPerFrame + next.pinsDown + next.nextBall.pinsDown;
			
		}
		else if(currentFrame.hasSpare){
			framescore = this.pinsPerFrame + next.nextBall.pinsDown;
		}
		else{
			framescore = currentBall.pinsDown + next.pinsDown;
		}
		return framescore;
		
	}
	
	public ball drawNextBall(ball prevBall){
		ball nextball = new ball();
		prevBall.nextBall = nextball;
		return nextball;
	}
	
}
