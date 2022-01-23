import Models.User;
import database.BetsInformation;
import database.LoginConfirm;
import database.MatchHistory;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

class test1 {
    @Test
    public void testGetMatch(){

        Vector<String> vector=new Vector<>();
        vector.add("2022-02-24");
        vector.add("Madryt");
        vector.add("Hiszpania-Wlochy");
        assertEquals(vector,BetsInformation.getMatch(2));
        assertNotEquals(vector,BetsInformation.getMatch(1));

    }
    @Test
    public void testLogin(){
        User user1=new User("2143124","user2");
        User user2=new User("234","unknown");
        assertEquals(LoginConfirm.checkUser(user1),user1.getPin());
        assertNotEquals(LoginConfirm.checkUser(user2),user2.getPin());

    }
    @Test
    public void testMatchHistrory(){
        User user1=new User("5555","user43");
        assertEquals(MatchHistory.countBets(user1,4),3);
        assertNotEquals(MatchHistory.countBets(user1,2),3);
    }


}