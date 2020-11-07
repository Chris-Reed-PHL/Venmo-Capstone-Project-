package com.techelevator.tenmo;


import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;


import com.techelevator.tenmo.models.Accounts;
import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.TransferTypes;
import com.techelevator.tenmo.models.Transfers;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.AuthenticationServiceException;
import com.techelevator.view.ConsoleService;

public class App {

private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	
	public static String AUTH_TOKEN = "";
    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
    private final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
    	App app = new App(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
    	app.run();
    }

    public App(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		Accounts account = null;
		
		try {
		    
		
		account = restTemplate.exchange(API_BASE_URL + "accounts/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Accounts.class).getBody();
		System.out.println("Your current balance is: " + account.getBalance());
		}catch(Exception ex) {
			System.out.println("WRONG!");
		}
	}  
	
	


	private void viewTransferHistory() {
		Transfers[] transfer = null;
		 User[] user = null;
	
		try {
			user = restTemplate.exchange(API_BASE_URL + "transfers/users/", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
			transfer = restTemplate.exchange(API_BASE_URL + "transfers/history/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Transfers[].class).getBody();
			System.out.println("-------------------------------------------");
			System.out.println("Transfers");
			System.out.println("ID         From/To     Amount");
			System.out.println("-------------------------------------------");
			for(int i = 0; i<transfer.length; i++) {
				
				
			
			System.out.println(transfer[i].getTransferId()+ "        "+ user[i].getUsername() + "              "+ transfer[i].getAmount());
			}
			}catch(Exception ex) {
				System.out.println("No History Found");
		}
		
	}
	
	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	
	
	private void sendBucks() {
		// list all users, ask which user id and amount- 
		Transfers transfer = new Transfers();
		Accounts account = null;
		
		 User[] user = null;
		 
		 
		
		user = restTemplate.exchange(API_BASE_URL + "transfers/users/", HttpMethod.GET, makeAuthEntity(), User[].class).getBody();
		account = restTemplate.exchange(API_BASE_URL + "accounts/" + currentUser.getUser().getId(), HttpMethod.GET, makeAuthEntity(), Accounts.class).getBody();

		
		System.out.println("-------------------------------------------");
		System.out.println("User");
		System.out.println("ID     Name");
		System.out.println("-------------------------------------------");
		for(int i = 0; i< user.length; i++) {
			
			if(!user[i].getId().equals(currentUser.getUser().getId())) {
				
				System.out.println(user[i].getId()+ "     " +user[i].getUsername());
			}
			
			
		}
		System.out.println("-------------------------------------------");
		
		
		transfer.setAccountFrom(currentUser.getUser().getId());
//		transfer.setTransferId(1);
//		transfer.setTransferStatusId();
//		transfer.setTransferTypeId();

		Integer sendToId = console.getUserInputInteger("Enter ID of user you are sending to (0 to cancel)");
		
		boolean balanceChecker = false; 
		
		while( !balanceChecker) {
			
		
		BigDecimal sendAmount = console.getUserInputBigDecimal("Enter amount");
		if(sendAmount.compareTo(account.getBalance()) == 1) {
			System.out.println("Sorry, you don't got that");
			continue;
		
		}
		else{ 
			balanceChecker = true;
		}
		
		transfer.setAccountTo(sendToId);
		transfer.setAmount(sendAmount);
		
		transfer = restTemplate.postForObject(API_BASE_URL + "transfers/send/", makeTransferEntity(transfer), Transfers.class);
		}
		
	}	

	
	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}
	
	private void exitProgram() {
		System.exit(0);
	}

	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
				AUTH_TOKEN = currentUser.getToken();
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
	
	private HttpEntity makeAuthEntity() {
	 HttpHeaders headers = new HttpHeaders();
	 headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	    
	}
	
	private HttpEntity makeTransferEntity(Transfers transfer) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.setContentType(MediaType.APPLICATION_JSON);
		    headers.setBearerAuth(AUTH_TOKEN);
		    HttpEntity<Transfers> entity = new HttpEntity<>(transfer, headers);
			return entity;
		    
		}
}
