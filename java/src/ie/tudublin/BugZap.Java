package ie.tudublin;

import processing.core.PApplet;

public class BugZap extends PApplet {

    // Create view
    public void settings()
	{
		size(750, 750);
	}
    
    // Initialise score
    int score = 0;
    int highscore = 0;
    int gameMode = 0;

    // Player variables
    float playerX;
    float playerY;
    float playerWidth = 20;
    float playerSpeed = 10;

    
    float bugY = 20;
    float bugWidth = 20;
    float bugX = random(20, width - bugWidth);

    public void setup() 
    {
        textAlign(CENTER, CENTER);
        textSize(50);
        fill(0, 255, 0);
        text("BUGZAP", width/2, height/2);
        textSize(25);
        fill(255);
        text("Highscore: " + score, width/2, (height/2) + 50);
        text("Press space to play", width/2, (height/2) + 100);
        
        if(keyCode == ' ')
        {
            score = 0;
            gameMode = 0;
            reset();
        }
        
	}
	
    public void reset()
    {
        resetBug();
        playerX = width/2;
        playerY = height - 50;
    }

    public void resetBug()
    {
        bugX = random(bugWidth/2 ,width - (bugWidth/2) );
        bugY = 20;
    }

    public void drawPlayer(float x, float y, float w)
    {
        stroke(0, 255, 0);
        strokeWeight(2);

        float h = w;

        // Inside triangle 
        line(x - w, y, x + w, y);
        line(x - w, y, x, y + h);
        line(x + w, y, x, y + h);

        // Outside triangle
        line(x - (w*2), y + h, x, y - h);
        line(x + (w*2), y + h, x, y - h);
    }

    public void drawBug(float x, float y, float w)
    {
        stroke(200, 200, 255);
        strokeWeight(2);

        float h = w;

        //Body
        line(x, y, x + w, y);
        line(x, y, x, y + h);
        line(x, y + h, x + w, y + h);
        line(x + w, y, x + w, y + h);

        // Eyes
        line(x + (w*0.2f), y + (h/2), x + (w*0.4f), y + (h/2));
        line(x + (w*0.6f), y + (h/2), x + (w*0.8f), y + (h/2));

        // Tentacles
        line(x, y + h, x, y + (h*1.5f));
        line(x + (w/2), y + h, x + (w/2), y + (h*1.5f));
        line(x + w, y + h, x + w, y + (h*1.5f));
    }

    public void draw()
	{	
        background(0);
        if(gameMode == 0)
        {
            drawPlayer(playerX, playerY, playerWidth);
            drawBug(bugX, bugY, bugWidth);
            fill(255);
            textSize(20);
            text("Score: " + score, 10, 20);
            moveBug();
        }

        else
        {
            textAlign(CENTER, CENTER);
            textSize(50);
            fill(255, 0 , 0);
            text("GAMEOVER", width/2, height/2);
            textSize(25);
            fill(255);
            text("Final score: " + score, width/2, (height/2) + 50);
            text("Press space to restart", width/2, (height/2) + 100);

            if(keyCode == ' ')
            {
                setup();
            }
        }

        if(bugY > height - 50)
        {
            if(score > highscore)
            {
                highscore = score;
            }

            gameMode = 1;
        }
	}

    public void keyPressed()
    {
        // Left arrow pressed
        if (keyCode == LEFT)
        {
            if(playerX > playerWidth)
            {
                playerX -= playerSpeed;
            }
        }

        // Right arrrow pressed
        if(keyCode == RIGHT)
        {   
            if(playerX < width - playerWidth)
            {
                playerX += playerSpeed;
            }
        }

        // Spacebar pressed
        if(keyCode == ' ')
        {
            if(playerX > bugX - (bugWidth/2) && playerX < bugX + (bugWidth/2))
            {   
                stroke(255, 0, 0); 
                line(playerX, playerY, playerX, bugY);
                score++;
                resetBug();

            }
        }
    }

    public void moveBug()
    {
        if( (frameCount % 1) == 0)
        {
            bugX += random(-5, 5);

            if(bugX < bugWidth/2)
            {
                bugX = bugWidth/2;
            }

            if(bugX > width - bugWidth)
            {
                bugX = width - bugWidth;
            }
            bugY += 2;
        }
    }

   
    
}
