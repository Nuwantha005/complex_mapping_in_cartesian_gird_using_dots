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

    void setPolar(float r, float theta){
        x = r * cos(theta);
        y = r * sin(theta);
    }

    Complex add(Complex other){
        Complex tot = new Complex(x+other.x, y+other.y);
        return tot;
    }

    Complex substract(Complex other){
        Complex tot = new Complex(x-other.x, y-other.y);
        return tot;
    }

    Complex times(float num){
        return new Complex(x * num, y * num);
    }

    Complex power(float num){
        float r = getR();
        float theta = getAngle();

        r = pow(r,num);
        theta = theta * num;

        Complex ans = new Complex();
        ans.setPolar(r,theta);
        return ans;
    }

    Complex reciprocal(){
        Complex w = new Complex();
        if(getR() != 0){
            w.setPolar(1/getR(), -getAngle());
        }
        return w;
    }

    Complex timesI(){
        return new Complex(y,x);
    }

    void printCartesian(){
        println(x,"+",y,"i");
    }

    float getX(){
        return x;
    }
    float getY(){
        return y;
    }
    float getR(){
        return (sqrt(x*x+y*y));
    }
    float getAngle(){
        return atan2(y,x);
    }
}
