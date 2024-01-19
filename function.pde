ArrayList<PVector> mapping(ArrayList<PVector> points){
    ArrayList<PVector> image = new ArrayList<PVector>();

    for (int i = 0; i < points.size(); ++i) {
        PVector vec = points.get(i);
        Complex z = new Complex(vec.x/divideConst, vec.y/divideConst);
        Complex w = f(z);
        image.add(new PVector(w.getX()*divideConst, w.getY()*divideConst));
    }

    return image;
}

Complex f(Complex z){
    //Squared
    //Complex w = z.power(5.35);

    //Recipocal
    Complex w = z.reciprocal();

    //Multipy by i
    //Complex w = z.timesI();
    return w;
}

ArrayList<ArrayList<PVector>> generateFrame(ArrayList<PVector> start, ArrayList<PVector> end){
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
                cordX = map(float(j),0,no_of_Frames,first.x,last.x);
            }
            if (first.y == last.y){
                cordY = first.y;
            } else {
                cordY = map(float(j),0,no_of_Frames,first.y,last.y);
            }
            
            locations.add(new PVector(cordX,cordY));
        }
        animation.add(locations);
    }
    return animation;
}
