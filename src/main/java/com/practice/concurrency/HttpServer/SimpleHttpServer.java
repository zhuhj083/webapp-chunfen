package com.practice.concurrency.HttpServer;


import com.practice.concurrency.threadPool.DefaultThreadPool;
import com.practice.concurrency.threadPool.MyThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

//这个web服务器用来处理http请求
//目前只能处理简单的文本和JPG图片内容
//这个web服务器使用main线程不断地接受客户端Socket的连接，将连接以请求提交给线程池处理
//这样使得web服务器能够同时处理多个客户端的请求
public class SimpleHttpServer {
    //处理HttpRequest的线程池
    static MyThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);

    //SimpleHttpServer的根路径
    static String basePath;
    static ServerSocket serverSocket;
    //服务监听端口
    static int port = 8080 ;


    public static void setBasePath(String basePath) {
        if (basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }

    public static void setPort(int port) {
        if (port > 0 ) {
            SimpleHttpServer.port = port;
        }
    }

    //启动SimpleHttpServer
    public static void start() throws  Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null ;
        while (( socket = serverSocket.accept()) != null){
            //接受一个客户端Socket，生成一个HttpRequestHandler，放入线程池执行
            threadPool.execute(new HttpRequestHandler(socket));
        }
    }

    static class  HttpRequestHandler implements  Runnable{
        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            String line = null;
            BufferedReader br = null ;
            BufferedReader reader = null ;
            PrintWriter out = null ;
            InputStream in = null ;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                //如果请求资源的后缀为jpg或ico，则读取资源并输出
                if (filePath.endsWith("jpg") || filePath.endsWith("ico")){
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0 ;
                    while((i = in.read())!=-1){
                        baos.write(i);
                    }
                    byte[] array = baos.toByteArray();
                   ; out.println("HTTP/1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Context-Type:image/jpeg");
                    out.println("Context-Length:"+array.length);
                    out.println("");
                    socket.getOutputStream().write(array,0,array.length);
                }else{
                    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server:Molly");
                    out.println("Context-Type:text/html;charset=UTF-8");
                    out.println("");
                    while ( (line = br.readLine() )!= null){
                        out.println(line);
                    }
                }
                out.flush();
            } catch (IOException e) {
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally {
                close(br,in,reader,out,socket);
            }

        }
    }

    private static void  close(Closeable... closeables){
        if (closeables != null){
            for (Closeable closeable: closeables) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
