package cyberdojo.bowlingGame;

public class App 
{
    public static void main( String[] args )
    {
        String b = "5/|5/|5/|5/|5/|5/|5/|5/|5/|5/||5";

        game myGame = new game(b);
        myGame.play();
        if (myGame.gameFinished){
        	myGame.score();
        	System.out.println(myGame.score);
        }
    }
}
