package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.Console;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JMSException {
        System.out.println( "Hello World!" );
        ConnectionFactory factory=new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_URL);
        Connection connection= factory.createConnection();
        connection.start();
        Session session= connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination destination= session.createQueue("Interact");

        MessageConsumer consumer= session.createConsumer(destination);

        Console console=System.console();
        Message msg;String response;
        do{
            msg=consumer.receive();
            response=((TextMessage)msg).getText();
            System.out.println("Received "+response);
        }while(!response.equals("bye"));
        connection.close();
    }
}
