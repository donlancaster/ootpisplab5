package sample.Compression1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class DefaultCompression implements Compression {

    public DefaultCompression() {
        }
        @Override
        public String compress (String text){
            byte[] data = text.getBytes();
            try {
                ByteArrayOutputStream bout = new ByteArrayOutputStream(512);
                DeflaterOutputStream dos = new DeflaterOutputStream(bout, new Deflater(6));
                dos.write(data);
                dos.flush();
                dos.close();
                bout.close();
                return Base64.getEncoder().encodeToString(bout.toByteArray());
            } catch (IOException var5) {
                return null;
            }
        }

        @Override
        public String decompress (String text){
            byte[] data = Base64.getDecoder().decode(text);
            try {
                InputStream in = new ByteArrayInputStream(data);
                InflaterInputStream iin = new InflaterInputStream(in);
                ByteArrayOutputStream bout = new ByteArrayOutputStream(512);

                int b;
                while ((b = iin.read()) != -1) {
                    bout.write(b);
                }
                in.close();
                iin.close();
                bout.close();
                return bout.toString();
            } catch (IOException var7) {
                return null;
            }
        }

    }