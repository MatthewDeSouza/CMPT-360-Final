package finalprojectgui;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import org.apache.commons.io.IOUtils;
import java.io.InputStream;
import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;

public class InputHandler {

    private File instructorFile, departmentFile;
    private InputStream instructorStream, departmentStream;
    private final String IOExceptionString = "ERROR! InputStream for object '"
            + this.toString() + "' is null! (Missing " + this.toString()
            + ".initFileInputStream()?)";

    public InputHandler(File instructorFile, File departmentFile) throws IOException {
        this.instructorFile = instructorFile;
        this.departmentFile = departmentFile;
        departmentStream = FileUtils.openInputStream(departmentFile);
        instructorStream = FileUtils.openInputStream(instructorFile);
        if (instructorStream == null) {
            throw new IOException(IOExceptionString);
        }
        if (departmentStream == null) {
            throw new IOException(IOExceptionString);
        }
    }
    

    public String getInputFileAsString() throws IOException {
        instructorStream = FileUtils.openInputStream(instructorFile);
        return IOUtils.toString(instructorStream, Charset.defaultCharset());
    }

    public String getDepartmentFileAsString() throws IOException {
        departmentStream = FileUtils.openInputStream(departmentFile);
        return IOUtils.toString(departmentStream, Charset.defaultCharset());
    }

    public boolean containsID(String searchField) throws IOException {
        instructorStream = FileUtils.openInputStream(instructorFile);
        String[] tempInputArray = format(getInputFileAsString()).split(",");
        for (String x : tempInputArray) {
            if (x.equals(searchField)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDepartment(String searchField) throws IOException {
        departmentStream = FileUtils.openInputStream(departmentFile);
        String searchFieldDepartment = format(getDepartmentFileAsString());
        String[] tempDepartmentArray = searchFieldDepartment.split(",");
        for (String x : tempDepartmentArray) {
            if (x.equals(searchField)) {
                return true;
            }
        }
        return false;
    }

    public String getDepartmentLocation(String searchField) throws IOException {
        departmentStream = FileUtils.openInputStream(departmentFile);
        String searchFieldDepartment = format(getDepartmentFileAsString());
        String[] temp = searchFieldDepartment.split(",");
        for (int i = 0; i < temp.length; i = i + 3) {
            if (temp[i].equals(searchField)) {
                return temp[i + 1];
            }
        }
        return null;
    }

    public String[] getInstructorDetails(String searchField) throws IOException {
        String[] ans = new String[3];
        instructorStream = FileUtils.openInputStream(instructorFile);
        if (containsID(searchField)) {
            instructorStream = FileUtils.openInputStream(instructorFile);
            String[] temp = format(getInputFileAsString()).split(",");
            for (int i = 0; i < temp.length; i = i + 3) {
                if (temp[i].equals(searchField)) {
                    ans[0] = temp[i + 1];
                    ans[1] = temp[i + 2];
                }
            }
            ans[2] = getDepartmentLocation(ans[1]);
            return ans;
        }
        return null;
    }

    public void write(String str) throws IOException {
        FileWriter temp = new FileWriter(instructorFile, true);
        temp.write(str);
        temp.close();
    }

    public static String cleanse(String str) {
        return str.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    public static ClassLoader classLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    private static String format(String str) {
        return str.replaceAll(System.lineSeparator(), ",");
    }
}
