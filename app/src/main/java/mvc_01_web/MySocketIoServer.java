package mvc_01_web;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class MySocketIoServer implements ModelObserver {

    private ModelObserverSource model;

    private SocketIOServer server ;


    public MySocketIoServer(ModelObserverSource model){
        this.model = model;
        model.addObserver(this);


        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(10001);
        server = new SocketIOServer(config);

        //sed message to client
        server.addEventListener("getState", byte[].class, (client, data, ackSender) -> client.sendEvent("update", model.getState()));
        server.start();
        this.notifyModelUpdated();

    }

    @Override
    public void notifyModelUpdated() {
        server.getBroadcastOperations().sendEvent("update", model.getState());
        System.out.println("[SocketIoServer] model updated => updating the view");
    }
}
