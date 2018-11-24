package zk.api.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author KQS
 * @since 2018/11/24.
 */
public class CreateSession implements Watcher {
    private static ZooKeeper zooKeeper;

    @Override
    public void process(WatchedEvent watchedEvent) {

        if(watchedEvent.getState().equals(Event.KeeperState.SyncConnected)) {
            System.out.println("Session create  success!");
        }
    }

    public static void main(String[] args) {
        try {
            zooKeeper = new ZooKeeper("192.168.117.128:2181",5000, new CreateSession());
            System.out.println(zooKeeper.getState());
            Thread.sleep(5000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
