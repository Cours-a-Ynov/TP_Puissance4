import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {

    public static final  int BUFFER_LENGTH = 1024;
    SocketChannel socket = null;

    public static void main(String[] args) {
        Server s = new Server();
        s.accept();
        String message = s.read();
        System.out.println("Message received : " + message);
        s.close();
    }

    public void accept(){
        System.out.println("Waiting for connection on port 8000");
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(8000));
            socket = ssc.accept();
            System.out.println("Connection established!");
        }
        catch(IOException e){
            System.err.println("Could not launch server : " + e.getMessage());
        }
        
    }

    public String read(){
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_LENGTH);
        try {
            int bytesRead = socket.read(buffer);
            System.out.println("Read " + bytesRead + " bytes");
            try{;
                String message = new String(buffer.array(), 0, bytesRead, "UTF-8");
                return message;
            }
            catch(UnsupportedEncodingException e){
                System.err.println("Unsupported encoding!");
            }
        }
        catch(IOException e){
            System.err.println("Could not read from socket : " + e.getMessage());;
        }
        return "";
        
    }

    public void close(){
        try{
            socket.close();
        }
        catch(IOException e){
            System.err.println("Could not close socket");
        }
        
    }
}
