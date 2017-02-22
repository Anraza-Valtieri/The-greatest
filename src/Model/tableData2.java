// This class acts as a model class,holding getters,setters and properties
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tableData2 {

    private final StringProperty qid;
    private final StringProperty qtype;
    private final StringProperty qquestion;

    public tableData2(){
        this.qid = new SimpleStringProperty("");
        this.qtype = new SimpleStringProperty("");
        this.qquestion = new SimpleStringProperty("");
    }
    //Default constructor
    public tableData2(String qid, String qtype, String qquestion) {
        this.qid = new SimpleStringProperty(qid);
        this.qtype = new SimpleStringProperty(qtype);
        this.qquestion = new SimpleStringProperty(qquestion);
    }

    public String getQid() {
        return qid.get();
    }

    public StringProperty qidProperty() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid.set(qid);
    }

    public String getQtype() {
        return qtype.get();
    }

    public StringProperty qtypeProperty() {
        return qtype;
    }

    public void setQtype(String qtype) {
        this.qtype.set(qtype);
    }

    public String getQquestion() {
        return qquestion.get();
    }

    public StringProperty qquestionProperty() {
        return qquestion;
    }

    public void setQquestion(String qquestion) {
        this.qquestion.set(qquestion);
    }

}
