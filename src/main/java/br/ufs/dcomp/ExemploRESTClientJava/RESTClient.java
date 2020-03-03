package br.ufs.dcomp.ExemploRESTClientJava;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RESTClient 
{
    public static void main( String[] args )
    {
        try {
            
            // JAVA 8 como pré-requisito (ver README.md)
            
            String username = "user";
            String password = "pass";
     
            String usernameAndPassword = username + ":" + password;
            String authorizationHeaderName = "Authorization";
            String authorizationHeaderValue = "Basic " + java.util.Base64.getEncoder().encodeToString( usernameAndPassword.getBytes() );
     
            // Perform a request
            String restResource = "http://http-balancer-f260513a2ba99597.elb.us-east-1.amazonaws.com";
            Client client = ClientBuilder.newClient();
            Response resposta = client.target( restResource )
            	//.path("/api/exchanges/%2f/ufs/bindings/source") // lista todos os binds que tem o exchange "ufs" como source	
                .path("/api/exchanges")
            	.request(MediaType.APPLICATION_JSON)
                .header( authorizationHeaderName, authorizationHeaderValue ) // The basic authentication header goes here
                .get();     // Perform a post with the form values
           
            if (resposta.getStatus() == 200) {
            	String json = resposta.readEntity(String.class);
                System.out.println(json);
            } else {
                System.out.println(resposta.getStatus());
            }   
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
