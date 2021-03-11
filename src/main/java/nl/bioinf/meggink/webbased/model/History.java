package nl.bioinf.meggink.webbased.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class History {
    private static final int MAX_SIZE = 5;
    private Queue<Penguin> historyItems = new LinkedList<>();

    public void addItem(Penguin penguin) {
        if (historyItems.size() == MAX_SIZE){
            historyItems.poll();
        }
        historyItems.offer(penguin);
    }

    public List<Penguin> getHistory(){
        return (List<Penguin>)this.historyItems;
    }
}
