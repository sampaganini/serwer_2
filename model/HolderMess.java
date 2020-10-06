package files.project.manager.model;

/**
 * uchwyt na wiadomosc do wyslania/odebrania
 */
public class HolderMess {
    public DataSending dataToSend;
    public ReceiveData dataToReceive;

    public HolderMess(DataSending dataToSend, ReceiveData dataToReceive) {
        this.dataToSend = dataToSend;
        this.dataToReceive = dataToReceive;
    }
}
