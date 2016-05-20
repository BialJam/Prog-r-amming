package Actors.people;

/**
 * Created by Marcin on 2016-05-20.
 */
public class Badass extends AbstractPerson{


    public void dance(){
        happines += 10;
    }
    
    public void drink(){
        drunk += 20;
        if(drunk >= 100){
            angry = 100;
        }
    }
}
