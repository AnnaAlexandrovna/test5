package Contract;

import Client.ClientInfo;
import org.junit.Assert;

public class CellularContractTest {
    @org.junit.Test
    public void isTheFieldInSortField(){
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new CellularContract("28.01.2004","28.01.2024", client1, 100,7,100);
        Assert.assertEquals(true, contract1.isTheFieldInSortField("startDate"));
        Assert.assertEquals(false, contract1.isTheFieldInSortField("smsNum"));
        Assert.assertEquals(false, contract1.isTheFieldInSortField("maxSpeed"));
    }
    @org.junit.Test
    public void getAllField(){
        ClientInfo client1 = new ClientInfo("test", "test", "01.05.2000","man", "2048 23423423424");
        AbstractContract contract1 = new CellularContract("28.01.2004","28.01.2024", client1, 100,7,100);
        Assert.assertEquals(13, contract1.getAllField().size());
    }
}
