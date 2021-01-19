package com.accela.test.dbconsole.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.accela.test.dbconsole.DatabaseManager;

public class Database {

	private static final String addPerson = "INSERT INTO agenda.agenda ( firstName, surname ) " + 
			"VALUES ( '%s', '%s' );";
	private static final String editPerson = "UPDATE agenda.agenda SET firstName='%s', surname='%s' WHERE firstName='%s' AND surname='%s'";
	private static final String findPerson = "SELECT * FROM agenda.agenda WHERE firstName='%s' AND surname='%s'";
	private static final String removePerson = "DELETE FROM agenda.agenda WHERE firstName='%s' AND surname='%s'";
	private static final String listPerson = "SELECT * FROM agenda.agenda";
	private static final String countPerson = "SELECT COUNT(*) FROM agenda.agenda;";
	
	private DatabaseManager dm;
	
	public Database(DatabaseManager dm) {
		this.dm = dm;
	}
	
	  public synchronized boolean addPerson(String firstName, String surname) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      conn = this.dm.getDatabaseConnection();
		      ps = conn.prepareStatement(String.format(findPerson , firstName, surname));
		      rs = ps.executeQuery();
		      if (rs.next()) {
		    	  return false;
		      }
		      else
		      {
		    	  final String addPersonQuery = String.format(addPerson , firstName, surname);
			      ps = conn.prepareStatement(addPersonQuery);
			      int rowsAffected = ps.executeUpdate();
			      if (rowsAffected == 1) {
			    	  return true;
			      } else {
			    	  return false;
				  }
		      }
		    }
		    catch (SQLException e) {
			      e.printStackTrace();
			      return false;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return false;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }
	  
	  public synchronized boolean editPerson(String firstName, String surname) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      conn = this.dm.getDatabaseConnection();
		      ps = conn.prepareStatement(String.format(findPerson , firstName, surname));
		      rs = ps.executeQuery();
		      if (rs.next()) {
		    	  return true;
		      }
		      else
		      {
		    	  return false;
		      }
		    }
		    catch (SQLException e) {
			      e.printStackTrace();
			      return false;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return false;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }
	  
	  public synchronized boolean editPerson2(String firstName, String surname, String newFirstName, String newSurname) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		    	  String editPersonQuery = String.format(editPerson , newFirstName, newSurname, firstName, surname);
		    	  conn = this.dm.getDatabaseConnection();
			      ps = conn.prepareStatement(editPersonQuery);
			      int rowsAffected = ps.executeUpdate();
			      if (rowsAffected == 1) {
			    	  return true;
			      } else {
			    	  return false;
				  }
		    }
		    catch (SQLException e) {
			      return false;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return false;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }

	  public synchronized boolean removePerson(String firstName, String surname) {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      conn = this.dm.getDatabaseConnection();
		      ps = conn.prepareStatement(String.format(findPerson , firstName, surname));
		      rs = ps.executeQuery();
		      if (rs.next()) {
		    	  final String removePersonQuery = String.format(removePerson , firstName, surname);
			      ps = conn.prepareStatement(removePersonQuery);
			      int rowsAffected = ps.executeUpdate();
			      if (rowsAffected == 1) {
			    	  return true;
			      } else {
			    	  return false;
				  }
		      }
		      else
		      {
		    	  return false;
		      }
		    }
		    catch (SQLException e) {
			      e.printStackTrace();
			      return false;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return false;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }
	  
	  public synchronized int countPerson() {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      conn = this.dm.getDatabaseConnection();
		      ps = conn.prepareStatement(countPerson);
		      rs = ps.executeQuery();
		      if (rs.next()) 
		      {
		    	  int number = rs.getInt("count(*)");
		    	  return number;
		      } else {
		    	  return -1;
		      }
		    }
		    catch (SQLException e) {
			      e.printStackTrace();
			      return -1;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return -1;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }	  
	  
	  public synchronized List<String> listPerson() {
		    Connection conn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		    try {
		      conn = this.dm.getDatabaseConnection();
		      ps = conn.prepareStatement(listPerson);
		      rs = ps.executeQuery();
		      List<String> ret = new ArrayList<String>();
		      while (rs.next()) {
		    	  ret.add(rs.getString("id"));
		    	  ret.add(rs.getString("firstName"));
		    	  ret.add(rs.getString("surname"));
		      }
		      return ret;
		    }
		    catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			    } catch (Exception e) {
				  e.printStackTrace();
				  return null;
				}
		    finally {
			      if (ps != null) {
			        try {
			          ps.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			      if (conn != null) {
			        try {
			          conn.close();
			        } catch (SQLException e) {
			          e.printStackTrace();
			        }
			      }
			    }
	  }	  	  
}