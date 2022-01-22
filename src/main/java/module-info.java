module finalprojectgui {
    requires transitive javafx.controls;
    requires transitive javafx.base;
    requires transitive javafx.graphics;
    requires transitive org.apache.commons.io;
    
    opens finalprojectgui to javafx.base;
    exports finalprojectgui;
}
