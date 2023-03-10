package myServlet.Model2practice;

import java.util.HashMap;
import java.util.Map;

public class Model {
    private Map<String,String> mymap=new HashMap<>();
    public Model(){
        mymap.put("Tom","cat");
        mymap.put("Jerry","mouse");
        mymap.put("Spike","dog");
    }
    public String doAnimals(String user){
        String animal=mymap.get(user);
        return String.format("%s,%s!",animal,user);
    }
}
