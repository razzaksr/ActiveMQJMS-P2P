package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JMSException {
        System.out.println( "Hello World!" );
        ActiveMQConnectionFactory factory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection= factory.createConnection();
        connection.start();

        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination= session.createQueue("Interact");

        MessageProducer producer= session.createProducer(destination);

        Scanner scanner=new Scanner(System.in);
        String msg;
        do{
            System.out.println("Enter the message ");
            msg= scanner.nextLine();
            TextMessage data= session.createTextMessage(msg);
            producer.send(data);
        }while(!msg.equals("bye"));

        scanner.close();
        connection.close();
    }
}
