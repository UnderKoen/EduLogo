package nl.edulogo.acslogo;

import nl.edulogo.acslogo.handlers.procedures.Procedure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 2019-01-07.
 */
public class Document implements Serializable {
    private static int untitled = 0;

    private transient String documentName;
    private transient File orgin;
    private Content content;

    public Document() {
        this((untitled == 0)? "Untitled" : String.format("Untitled %s", untitled));
        untitled++;
    }

    public Document(String documentName) {
        this.documentName = documentName;
        orgin = null;
        content = new Content("", new ArrayList<>());
    }

    public Document(File file) throws IOException {
        orgin = file;
        documentName = file.getName();

        ObjectInputStream si = new ObjectInputStream(new FileInputStream(file));
        try {
            content = (Content) si.readObject();
            si.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void save() throws IOException {
        saveToFile(orgin);
    }

    public void saveToFile(File file) throws IOException {
        ObjectOutputStream so = new ObjectOutputStream(new FileOutputStream(file));
        so.writeObject(content);
        so.flush();
        so.close();
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getCode() {
        return content.code;
    }

    public void setCode(String code) {
        content.setCode(code);
    }

    public List<Procedure> getProcedures() {
        return content.getProcedures();
    }

    public void setProcedures(List<Procedure> procedures) {
        content.setProcedures(procedures);
    }

    public File getOrgin() {
        return orgin;
    }

    public void setOrgin(File orgin) {
        this.orgin = orgin;
    }

    private class Content implements Serializable {
        private String code;
        private List<Procedure> procedures;

        public Content(String code, List<Procedure> procedures) {
            this.code = code;
            this.procedures = procedures;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public List<Procedure> getProcedures() {
            return procedures;
        }

        public void setProcedures(List<Procedure> procedures) {
            this.procedures = procedures;
        }
    }
}
