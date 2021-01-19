package com.accela.test.dbconsole;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleManager {

	int state;
	String command;
	String[] vars;
	BufferedReader br;
	
	private static final String ADD = "add";
	private static final String REMOVE = "remove";
	private static final String EDIT = "edit";
	private static final String COUNT = "count";
	private static final String LIST = "list";
	private static final String HELP = "help";
	private static final String EXIT = "exit";
	
	public void setDone() {
		state = 0;
		vars = null;
		command = null;
	}
	
	public int checkState() {
		return state;
	}
	
	public String[] getParamaters() {
		return vars;
	}
	
	public boolean read(){
		try {
		state = 0;
		String line = br.readLine();
		String[] tokens = line.split(" ");
		if (tokens.length > 0) {
			if (command == null || !command.equalsIgnoreCase(EDIT)) {
				command = tokens[0];
				vars = new String[tokens.length - 1];
				for (int i = 1; i < tokens.length; i++) {
					vars[i-1] = tokens[i];
				}
				state = 1;
			}
			else if (command.equalsIgnoreCase(EDIT)) {
				vars = new String[tokens.length];
				for (int i = 0; i < tokens.length; i++) {
					vars[i] = tokens[i];
				}
				state = 1;
			}
		}
		return true;
		}
		catch (IOException e) {
			state = 0;
			return false;
		}
	}
	
	public boolean checkInput() {
		if (command.equalsIgnoreCase(ADD)) {
			state = 2;
			if (vars.length != 2) {
				state = 0;
				return false;
			}
			else {
				return true;
			}
		}
		else if (command.equalsIgnoreCase(REMOVE)) {
			state = 3;
			if (vars.length != 1) {
				state = 0;
				return false;
			}
			else {
				return true;
			}
		}
		else if (command.equalsIgnoreCase(EDIT)) {
			state = 4;
			if (vars.length != 2) {
				state = 0;
				return false;
			}
			else {
				return true;
			}
		}
		else if (command.equalsIgnoreCase(COUNT)) {
			state = 5;
			if (vars.length != 0) {
				state = 0;
				return false;
			}
			else {
				return true;
			}
		}
		else if (command.equalsIgnoreCase(LIST)) {
			state = 6;
			if (vars.length != 0) {
				state = 0;
				return false;
			}
			else {
				return true;
			}
		}
		else if (command.equalsIgnoreCase(HELP)) {
			state = 7;
			return true;
		}
		else if (command.equalsIgnoreCase(EXIT)) {
			state = 8;
			return true;
		}
		state = 0;
		return false;
	}
	
	public ConsoleManager(String[] args) {
		state = 0;
		br = new BufferedReader(new InputStreamReader(System.in));
		if (args.length > 0) {
			command = args[0];
			vars = new String[args.length - 1];
			for (int i = 1; i < args.length; i++) {
				vars[i-1] = args[i];
			}
			state = 1;
		}
	}
	
}
