import peasy.*;

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

void setup() {
  size(600, 600, P3D);
  colorMode(HSB);
  background(0);

  cam = new PeasyCam(this, 500);
  
  xedge = width/2 - 50;
  yedge = height/2 - 50;
  
  for (int i = -xedge; i < xedge; i = i + stepSize) {
    for (int j = -yedge; j < yedge; j = j + stepSize) {
      grid.add(new PVector(float(i), float(j)));
    }
  }
  image = mapping(grid);
  animation = generateFrame(grid, image);
  
}

void draw() {
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

void mouseClicked() {
  if (status == GRID_DISPLAY) {
    status = ANIMATION_DISPLAY;
  } else if(status == IMG_DISPLAY){
    status = REVERSE_ANIMATION_DISPLAY;
  }
}
