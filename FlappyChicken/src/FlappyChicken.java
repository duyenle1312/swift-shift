/*
backpic =loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bg.png");
		 backpic01 =loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bg.png");
		 //backpic02 =loadImage("https://raw.githubusercontent.com/Gaspared/FlappyBird/main/img/back.png");
		 //birdpic =loadImage("https://www.clipartkey.com/mpngs/m/94-942387_artwork-flappy-bird-bird-flappy-bird-sprite.png");
		 birdpic =loadImage("https://raw.githubusercontent.com/Gaspared/FlappyBird/main/img/bird.png");
		 wallpic =loadImage("https://raw.githubusercontent.com/Gaspared/FlappyBird/main/img/wall.png");
		 
		 //welcomescreen=loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/welcome.png");
		 welcomescreen=loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/screen.png");
		 welcomescreen.resize(0, displayHeight);
		 game = 1; score = 0; highscore = 0; x = -200; vertical = 0;*/
 
 
 import processing.core.*;
 import processing.data.*;
 import processing.event.*;
 import processing.opengl.*;

 import java.util.HashMap;
 import java.util.ArrayList;
 import java.io.File;
 import java.io.BufferedReader;
 import java.io.PrintWriter;
 import java.io.InputStream;
 import java.io.OutputStream;
 import java.io.IOException;

 public class FlappyChicken extends PApplet {

 PImage backpic, birdpic, birdpic1, birdpic2, birdpic3, wallpic, welcomescreen;
 int game, score, highscore, x, y, vertical, wallx[] = new int[2], wally[] =new int[2];
  public void setup() {
    backpic = loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bg.png");
    backpic.resize(0, displayHeight);
    birdpic = loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bird.png");
    //
    birdpic1 = loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bird1.png");
    birdpic2 = loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bird2.png");
    birdpic3 = loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/bird3.png");
    //
    wallpic = loadImage("https://raw.githubusercontent.com/Gaspared/FlappyBird/main/img/wall.png");
    welcomescreen= loadImage("https://raw.githubusercontent.com/duyenle1312/swift-shift/main/screen.png");
	welcomescreen.resize(0, displayHeight);
    game = 1; score = 0; highscore = 0; x = -200; vertical = 0; 
    fill(0,0,0);
    textSize(20);  
 }
  public void draw() { 
   if(game == 0) {
     imageMode(CORNER);
     image(backpic, x, 0);
     image(backpic, x + backpic.width, 0);
     x -= 5;
     vertical += 1;
     y += vertical;
     if(x == -1800) x = 0;
     for(int i = 0 ; i < 2; i++) {
       imageMode(CENTER);
       image(wallpic, wallx[i], wally[i] - (wallpic.height/2+100));
       image(wallpic, wallx[i], wally[i] + (wallpic.height/2+100));
       if(wallx[i] < 0) {
         wally[i] = (int)random(200,height-200);
         wallx[i] = width;
       }
       if(wallx[i] == width/2) highscore = max(++score, highscore);
       if(y>height||y<0||(abs(width/2-wallx[i])<25 && abs(y-wally[i])>100)) game=1;
       wallx[i] -= 6;
     }
     switch(score % 4) {
     case 0:
    	 image(birdpic, width/2, y);
    	 break;
     case 1:
    	 image(birdpic1, width/2, y);
    	 break;
     case 2:
    	 image(birdpic3, width/2, y);
    	 break;
     case 3:
    	 image(birdpic2, width/2, y);
    	 break;
     }
       
     text("Score: "+score, 280, 60);
   }
   else {
     imageMode(CENTER);
     image(welcomescreen, width/2,height/2);
     text("High Score: "+highscore, 250, 50);
   }
 }
  public void mousePressed() {
   vertical = -15;
   if(game==1) {
     wallx[0] = 600;
     wally[0] = y = height/2;
     wallx[1] = 900;
     wally[1] = 600;
     x = game = score = 0;
   }
 }


   public void settings() { size(600, 800); }

   static public void main(String[] passedArgs) {
     String[] appletArgs = new String[] { "FlappyChicken" };
     if (passedArgs != null) {
       PApplet.main(concat(appletArgs, passedArgs));
     } else {
       PApplet.main(appletArgs);
     }
   }
 }