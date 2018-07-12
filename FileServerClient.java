import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileServerClient {


    public FileServerClient() {}

    public void makeRequest(String url, String filename) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        InputStream inputStream = con.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(filename);

        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        outputStream.close();
        inputStream.close();
    }

    public void writeToFile(String filename, String buffer) throws IOException {
        BufferedWriter out = null;
        try {
            FileWriter writer = new FileWriter(filename, false);
            out = new BufferedWriter(writer);
            out.write(buffer);
        }
        catch (IOException e) {
            System.out.println("Error writing file.");
        }
        finally {
            if(out != null) {
                out.close();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        FileServerClient client = new FileServerClient();
        String url = "http://localhost:3000/file?file_name=sunrise.jpg";
        client.makeRequest(url, "test.jpg");
    }
}
