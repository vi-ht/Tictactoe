/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package P0024_Tictactoe_150;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 *
 * @author Thanh Vi
 */
public class Thread_of_servers extends Thread{
       Socket socket;
      JList<String>display ;
      String sender;
      String receiver;
      Vector<Socket> listSocket;
      Vector<String> Name;
      DefaultListModel<String> model = new DefaultListModel<>();
      Thread t;
      BufferedWriter os=null;
      BufferedReader bf=null;

      public Thread_of_servers(Socket socket, String sender, String receiver, Vector<Socket> listSocket, Vector<String> Name) {
            this.socket = socket;
            this.sender = sender;
            this.receiver = receiver;
            this.listSocket = listSocket;
            this.Name = Name;
            try {
                  bf=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                  os=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            } catch (Exception e) {
            }
      }
      
      public void Display_in_list(Vector<String> name){
                  System.out.println(listSocket.size());
                  try {
                        for (int i = 0; i <listSocket.size(); i++)
                        {
                              os=new BufferedWriter(new OutputStreamWriter(listSocket.get(i).getOutputStream()));
                              for(String Name: name){
                                    os.write("*"+Name);os.newLine();
                                    os.flush();
                              }
                        }
                  } catch (Exception e) {
                  }
}
      
      @Override
            public void run(){
            while(true){
                  try {
                        if(socket!=null){
                                    String msg="";
                                    if((msg=bf.readLine())!= null && msg.length()>0){   
                                          System.out.println(msg);
                                          switch(msg.charAt(0)){
                                                case '>': 
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break;
                                                case 'y': 
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("yq");
                                                      System.out.println("msggggggggggg");
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(2, 3))).getOutputStream()));
                                                      sendtoCilent("yy");
                                                      System.out.println("msggggggggggg");
                                                      break;
                                                case '.': 
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break;
                                                case '!':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("!"+msg.substring(2));
                                                      break;
                                                case '#':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("#"+msg.substring(2));
                                                      break;
                                                case 'x':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break;
                                                case '&':
                                                      model.removeElementAt(Integer.parseInt(msg.substring(1)));
                                                      display.setModel(model);
                                                      break;
                                                 case '=':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break;
                                                 case '-':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("-");
                                                      break;
                                                 case ')':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(")");
                                                      break;
                                                  case '(':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("(");
                                                      break;
                                                 case 'o':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent("o");
                                                      break;
                                                 case '$':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break;
                                                case '@':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(1, 2))).getOutputStream()));
                                                      //System.out.println("-------------------------------------------------"+msg.substring(1, 2));
                                                      
                                                      sendtoCilent(msg);
                                                      break; 
                                                 case '~':
                                                      os = new BufferedWriter(new OutputStreamWriter(listSocket.get(Integer.parseInt(msg.substring(2, 3))).getOutputStream()));
                                                      sendtoCilent(msg);
                                                      break; 
                                          }    
                                    }
                              }
                  } catch (Exception e) {
                              int index = listSocket.indexOf(socket);
                              System.out.println(index);
                              listSocket.remove(index);
                              Name.remove(index);
                              Exit(Name, index, listSocket);
                              break;
                  }
            }
            }
            public void Exit(Vector<String> name,int index,Vector<Socket> list){

                  System.out.println(list.size());
                  try {
                        for (int i = 0; i <list.size(); i++)
                        {
                         os=new BufferedWriter(new OutputStreamWriter(list.get(i).getOutputStream()));
                                    os.write("&"+index);os.newLine();
                                    os.flush();
                        }
                  } catch (Exception e) {
                  }
            }
            public void sendtoCilent(String message){
                  try {
                        os.write(message);
                        os.newLine();
                        os.flush();
                        os = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                  } catch (Exception e) {
                        System.out.println(e.getMessage());
                  }
            }
}
