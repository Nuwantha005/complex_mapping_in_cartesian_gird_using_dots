/* autogenerated by Processing revision 1293 on 2024-01-19 */
import processing.core.*;
import processing.data.*;
import processing.event.*;
import processing.opengl.*;

import peasy.*;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class normal_functions_using_dots extends PApplet {



PeasyCam cam;
ArrayList<PVector> image;
ArrayList<PVector> grid = new ArrayList<PVector>();
FloatList colorList = new FloatList();
ArrayList<ArrayList<PVector>> animation;

int status = 0;
int xedge,yedge;
int divideConst = 200;
int stepSize = 10;
int circleSize = 5;
int no_of_Frames = 360;
int currentFrame = 0;

int GRID_DISPLAY = 0;
int IMG_DISPLAY = 1;
int ANIMATION_DISPLAY = 2;
int REVERSE_ANIMATION_DISPLAY = 3;

public void setup() {
  /* size commented out by preprocessor */;
  colorMode(HSB);
  background(0);

  cam = new PeasyCam(this, 500);
  
  xedge = width/2 - 50;
  yedge = height/2 - 50;
  
  for (int i = -xedge; i < xedge; i = i + stepSize) {
    for (int j = -yedge; j < yedge; j = j + stepSize) {
      grid.add(new PVector(PApplet.parseFloat(i), PApplet.parseFloat(j)));
    }
  }
  image = mapping(grid);
  animation = generateFrame(grid, image);
  
}

public void draw() {
  background(0);

  if (status == GRID_DISPLAY) {
    beginShape();
    for(PVector v:grid){
      float col = map(v.x,-xedge,xedge,0,255);
      colorList.append(col);
      fill(col, 255, 255);
      circle(v.x, v.y, circleSize);
    }
    endShape();
  } else if (status == IMG_DISPLAY) {
    fill(255);
    beginShape();
    for (int i = 0; i < image.size(); ++i) {
      PVector v = image.get(i);
      fill(colorList.get(i), 255, 255);
      circle(v.x, v.y, circleSize);
    }
    endShape();
  } else if (status == ANIMATION_DISPLAY){
    beginShape();
    for (int i = 0; i < animation.size(); ++i) {
      ArrayList<PVector> list = animation.get(i);
      PVector v = list.get(currentFrame);
      fill(colorList.get(i), 255, 255);
      circle(v.x, v.y, circleSize);
    }
    endShape();
    currentFrame++;
    if (currentFrame == no_of_Frames-1) {
      status = IMG_DISPLAY;
    }
  } else if (status == REVERSE_ANIMATION_DISPLAY){
    beginShape();
    for (int i = 0; i < animation.size(); ++i) {
      ArrayList<PVector> list = animation.get(i);
      PVector v = list.get(currentFrame);
      fill(colorList.get(i), 255, 255);
      circle(v.x, v.y, circleSize);
    }
    endShape();
    currentFrame--;
    if (currentFrame == 0) {
      status = GRID_DISPLAY;
    }
  }
}

public void mouseClicked() {
  if (status == GRID_DISPLAY) {
    status = ANIMATION_DISPLAY;
  } else if(status == IMG_DISPLAY){
    status = REVERSE_ANIMATION_DISPLAY;
  }
}
class Complex{
    float x, y ;

    Complex(float x_val, float y_val){
        x = x_val;
        y = y_val;
    }

    Complex(){
        x = 0;
        y = 0;
    }

    public void setPolar(float r, float theta){
        x = r * cos(theta);
        y = r * sin(theta);
    }

    public Complex add(Complex other){
        Complex tot = new Complex(x+other.x, y+other.y);
        return tot;
    }

    public Complex substract(Complex other){
        Complex tot = new Complex(x-other.x, y-other.y);
        return tot;
    }

    public Complex times(float num){
        return new Complex(x * num, y * num);
    }

    public Complex power(float num){
        float r = getR();
        float theta = getAngle();

        r = pow(r,num);
        theta = theta * num;

        Complex ans = new Complex();
        ans.setPolar(r,theta);
        return ans;
    }

    public Complex reciprocal(){
        Complex w = new Complex();
        if(getR() != 0){
            w.setPolar(1/getR(), -getAngle());
        }
        return w;
    }

    public Complex timesI(){
        return new Complex(y,x);
    }

    public void printCartesian(){
        println(x,"+",y,"i");
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public float getR(){
        return (sqrt(x*x+y*y));
    }
    public float getAngle(){
        return atan2(y,x);
    }
}
public ArrayList<PVector> mapping(ArrayList<PVector> points){
    ArrayList<PVector> image = new ArrayList<PVector>();

    for (int i = 0; i < points.size(); ++i) {
        PVector vec = points.get(i);
        Complex z = new Complex(vec.x/divideConst, vec.y/divideConst);
        Complex w = f(z);
        image.add(new PVector(w.getX()*divideConst, w.getY()*divideConst));
    }

    return image;
}

public Complex f(Complex z){
    //Squared
    //Complex w = z.power(5.35);

    //Recipocal
    Complex w = z.reciprocal();

    //Multipy by i
    //Complex w = z.timesI();
    return w;
}

public ArrayList<ArrayList<PVector>> generateFrame(ArrayList<PVector> start, ArrayList<PVector> end){
    ArrayList<ArrayList<PVector>> animation = new ArrayList<ArrayList<PVector>>();
    for (int i = 0; i < start.size(); ++i) {
        PVector first = start.get(i);
        PVector last = end.get(i);
        ArrayList<PVector> locations = new ArrayList<PVector>();
        for (int j = 0; j < no_of_Frames; ++j) {
            float cordX ,cordY;
            if (first.x == last.x) {
                cordX = first.x;
            } else {
                cordX = map(PApplet.parseFloat(j),0,no_of_Frames,first.x,last.x);
            }
            if (first.y == last.y){
                cordY = first.y;
            } else {
                cordY = map(PApplet.parseFloat(j),0,no_of_Frames,first.y,last.y);
            }
            
            locations.add(new PVector(cordX,cordY));
        }
        animation.add(locations);
    }
    return animation;
}


  public void settings() { size(600, 600, P3D); }

  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "normal_functions_using_dots" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}