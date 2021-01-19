package com.accela.test.dbconsole;

import java.util.List;

import com.accela.test.dbconsole.db.Database;

/**
 * Main class with flow method. DatabaseManager dispatches the sql connection and Database contains the calls
 * ConsoleManager provides emthods to read, check and write from/to the console
 */
public class App 
{
    public static void main( String[] args )
    {
    	DatabaseManager dm = new DatabaseManager(); 
    	Database data = new Database(dm);
        ConsoleManager cm = new ConsoleManager(args);
        if (cm.checkState() == 1) {
        	programFlow(cm, data);
        }
        while (cm.checkState() == 0)
        {
        	printMenu();
        	cm.read();
        	if (cm.checkState() == 1) {
        		programFlow(cm, data);
        	}
        }
    }
        
    private static void programFlow(ConsoleManager cm, Database data) {
		if(cm.checkInput()) {
			if(cm.checkState() == 2) {
				String[] params = cm.getParamaters();
				if (!data.addPerson(params[0], params[1])) {
					printDBError();
				}
				cm.setDone();
				printDone();
			}
			else if(cm.checkState() == 3) {
				String[] params = cm.getParamaters();
				if (!data.removePerson(params[0], params[1])) {
					printDBError();
				}
				cm.setDone();
				printDone();
			}
			else if(cm.checkState() == 4) {
				String[] params = cm.getParamaters();
				if (!data.editPerson(params[0], params[1])) {
					printDBError();
					cm.setDone();
				}
				else {
    				printEditDetails();
    				cm.read();
    				if (cm.checkState() == 1) {
    					if(cm.checkInput()) {
    						if (cm.checkState() == 4) {
    	        				String[] params2 = cm.getParamaters();
    	        				if (!data.editPerson2(params[0], params[1], params2[0], params2[1])) {
    	        					printDBError();
    	        				}
    	        				cm.setDone();
    	        				printDone();
    						}
    					}
    				}
				}
			}
			else if(cm.checkState() == 5) {
				final int count = data.countPerson();
				if (data.countPerson() == -1) {
					printDBError();
				}
				else{
					printOutput(cm.checkState(), String.valueOf(count));
				}
				cm.setDone();
			}
			else if(cm.checkState() == 6) {
				List<String> persons = data.listPerson();
				if (persons == null) {
					printDBError();
				}
				else {
					printOutput(cm.checkState(), persons.toArray(new String[0]));
				}
				cm.setDone();
			}
			else if(cm.checkState() == 7) {
				printHelp();
				cm.setDone();
			}
			else if(cm.checkState() == 8) {

			}
		}
		else {
			printInputError();
		}
	}
    
    private static void printMenu() {
    	System.out.println("> [ADD, REMOVE, EDIT, COUNT, LIST, HELP, EXIT]");
    	System.out.print("> ");
    }
    
    private static void printHelp() {
    	System.out.println("> The list of command and its parameters is as follows:");
    	System.out.println("> ADD firstname surname");
    	System.out.println(">   Adds a new person to the database");
    	System.out.println(">   - firstname: First name of the person");
    	System.out.println(">   - surname: Surname of the person");
    	System.out.println("> REMOVE firstname surname");
    	System.out.println(">   Removes the specified person from the database");
    	System.out.println(">   - firstname: First name of the person");
    	System.out.println(">   - surname: Surname of the person");
    	System.out.println("> EDIT firstname surname newFirstname newSurname");
    	System.out.println(">   Substitutes the first name and surname of a person for the new ones introduced"
    			+ " if the person existed previously in the database");
    	System.out.println(">   - firstname: First name of the person to be edited");
    	System.out.println(">   - surname: Surname of the person to be edited");
    	System.out.println(">   - newFirstname: New first name of the person");
    	System.out.println(">   - newSurname: New surname of the person"); 
    	System.out.println("> COUNT");
    	System.out.println(">   Returns the number of persons in the database");
    	System.out.println("> LIST");
    	System.out.println(">   Returns the full list of persons in the database");
    	System.out.println("> HELP");
    	System.out.println(">   Displays this help menu");
    	System.out.println("> EXIT");
    	System.out.println(">   Quits this application");
    }
    
    private static void printInputError() {
    	System.out.println("> Input error. Run HELP for command and parameter details");
    }
    
    private static void printDBError() {
    	System.out.println("> Database error. Ensure your SQL process or service is running");
    }
    
    private static void printEditDetails() {
    	System.out.println("> Person exists in database. Input the new first name and surname");
    	System.out.print("> ");
    }
    
    private static void printOutput(int state, String... args) {
    	
    	System.out.print(">");
    	int index = 0;
    	for (String val : args) {
    		System.out.print(" ");
    		System.out.print(val);
    		if (index == 2) {
    			System.out.println();
    			System.out.print(">");
    			index = 0;
    		}
    		else {
    			index++;
    		}
    	}
    	System.out.println();
    	System.out.println("> DONE");
    }
    private static void printDone() {
    	System.out.println("> DONE");
    }
}
