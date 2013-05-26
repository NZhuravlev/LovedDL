package api;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public final class Utils {
	
	public static void download(URL urlAddress, String fileName) throws IOException {
    	
    	URLConnection conn = null;
		conn = urlAddress.openConnection();
        
        System.out.println("Started downloading of " + fileName);
        
        String contentType = conn.getContentType();
        int contentLength = conn.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("This is not a binary file.");
        }
        
        File folder = new File("/home/skeeve/Музыка");
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdir();
        }
        
        File binaryFile = new File("/home/skeeve/Музыка" + File.separator + fileName);
        if (!binaryFile.exists()) {

            BufferedInputStream in = new BufferedInputStream(conn.getInputStream());
            byte[] data = new byte[contentLength];
            int bytesRead = 0;
            int offset = 0;
            while (offset < contentLength) {
                bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            in.close();

            if (offset != contentLength) {
                throw new IOException("Only read " + offset + " bytes; Expected " + contentLength + " bytes");
            }
            FileOutputStream out = new FileOutputStream(binaryFile);
            out.write(data);
            out.flush();
            out.close();
        } else System.out.println(binaryFile.getName()+" уже существует.");{
            return;
        }
	}
}
