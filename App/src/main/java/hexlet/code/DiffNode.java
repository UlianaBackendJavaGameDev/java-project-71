package hexlet.code;

public class DiffNode {

    private String key;
    private  String status;
    private Object oldValue;
    private Object newValue;

    public DiffNode(String key, String status, Object oldValue, Object newValue){
        this.key = key;
        this.status = status;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }
    public String getKey(){
        return  key;
    }
    public  String getStatus(){
        return  status;
    }
    public Object getOldValue(){
        return oldValue;
    }
    public Object getNewValue(){
        return newValue;
    }


}
