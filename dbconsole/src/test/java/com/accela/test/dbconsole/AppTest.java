package com.accela.test.dbconsole;

import junit.framework.TestCase;

import static org.mockito.Mockito.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.accela.test.dbconsole.db.Database;

@RunWith(MockitoJUnitRunner.class)
public class AppTest extends TestCase {

	@Mock
    private DatabaseManager dm;
    @Mock
    private Connection c;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet rs;

    private Database data;
    

    @Before
    public void setUp() throws Exception {
        assertNotNull(dm);
        when(c.prepareStatement(any(String.class))).thenReturn(stmt);
        when(dm.getDatabaseConnection()).thenReturn(c);

        data = new Database(dm);
    }

    
    @Test
    public void addPerson() throws Exception{
        when(rs.next()).thenReturn(false);
        when(stmt.executeQuery()).thenReturn(rs);
    	when(stmt.executeUpdate()).thenReturn(1);    	
    	assertTrue(data.addPerson("John", "Doe"));
    }
    
    @Test
    public void addPersonPresent() throws Exception{
        when(rs.next()).thenReturn(true);
        when(stmt.executeQuery()).thenReturn(rs);
    	assertFalse(data.addPerson("John", "Doe"));
    }
    
    @Test
    public void removePerson() throws Exception{
        when(rs.next()).thenReturn(true);
        when(stmt.executeQuery()).thenReturn(rs);
    	when(stmt.executeUpdate()).thenReturn(1);    	
    	assertTrue(data.removePerson("John", "Doe"));
    }
    
    @Test
    public void removePersonPresent() throws Exception{
        when(rs.next()).thenReturn(false);
        when(stmt.executeQuery()).thenReturn(rs);
    	assertFalse(data.removePerson("John", "Doe"));
    }
    
    @Test
    public void listPerson() throws Exception{
    	when(stmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(rs.getString("id")).thenReturn("1").thenReturn("2");
        when(rs.getString("firstName")).thenReturn("John").thenReturn("Foo");
        when(rs.getString("surname")).thenReturn("Doe").thenReturn("Bar");
        
        List<String> list = new ArrayList<String>();
        list.add(String.valueOf(1));
        list.add(String.valueOf("John"));
        list.add(String.valueOf("Doe"));
        list.add(String.valueOf(2));
        list.add(String.valueOf("Foo"));
        list.add(String.valueOf("Bar"));
        List<String> result = data.listPerson();
    	assertEquals(result.get(0), list.get(0));
    	assertEquals(result.get(1), list.get(1));
    	assertEquals(result.get(2), list.get(2));
    	assertEquals(result.get(3), list.get(3));
    	assertEquals(result.get(4), list.get(4));
    	assertEquals(result.get(5), list.get(5));
    }

}