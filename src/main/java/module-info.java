module finalprojectgui {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.graphics;
    requires org.apache.commons.io;
    
 
    opens finalprojectgui to javafx.base;
    exports finalprojectgui;
}
