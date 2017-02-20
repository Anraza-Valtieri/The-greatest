// This class acts as a model class,holding getters,setters and properties
package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class tableData {

    private final StringProperty qstatus;
    private final StringProperty qname;

    //Default constructor
    public tableData (String qstatus, String qname) {
        this.qstatus = new SimpleStringProperty(qstatus);
        this.qname = new SimpleStringProperty(qname);
    }

    //Getters
    public String getqstatus() {
        return qstatus.get();
    }

    public String getqname() {
        return qname.get();
    }

    //Setters
    public void setqstatus(String value) {
        qstatus.set(value);
    }

    public void setqname(String value) {
        qname.set(value);
    }

    //Property values
    public StringProperty qstatusProperty() {
        return qstatus;
    }

    public StringProperty qnameProperty() {
        return qname;
    }

}
