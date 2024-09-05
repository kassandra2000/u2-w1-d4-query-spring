package kassandrafalsitta.u2w1d1.entities;

import kassandrafalsitta.u2w1d1.enums.StateTable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Setter
@ToString
public class Table {
    @Setter(AccessLevel.NONE)
    private int tableNum;
    private int numMaxClients;
    private StateTable state;

    //costruttore
    public Table(int numMaxClients, StateTable state) {
        Random r = new Random();
        this.tableNum = r.nextInt(1, 100);
        this.numMaxClients = numMaxClients;
        this.state = state;
    }


}
