import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.FileNotFoundException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.Invocable;

public class CoffeeScript {

    public static void main(String args[]) {
        // Load JavaScript Script Engine
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");

        // Load CoffeeScript
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("coffee-script.js");
            Reader reader = new InputStreamReader(is);
            jsEngine.eval(reader);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        // Compile the CoffeeScript file to JavaScript and print output
        try {
            Invocable invocableEngine = (Invocable) jsEngine;
            String output = (String) invocableEngine.invokeFunction("CoffeeScript.compile", new FileReader(args[0]));
            System.out.println(output);
        } catch(FileNotFoundException ex) {
            System.out.println("Couldn't load the file "+args[0]);
        } catch(NoSuchMethodException ex) {
            ex.printStackTrace();
        } catch(ScriptException ex) {
            ex.printStackTrace();
        }
    }
}
