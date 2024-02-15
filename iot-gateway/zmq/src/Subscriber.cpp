#include "Subscriber.h"

Subscriber::Subscriber(string identity){
    this->mIdentity = identity;
}

Subscriber::~Subscriber(){
    if(mSubscriber != nullptr){
        zsock_destroy(&mSubscriber);
        mSubscriber = nullptr;
    }
}

int Subscriber::Connect(string address, bool bind){

    if (bind){
        string bind_addr = "@" + address;
        mSubscriber = zsock_new_sub(bind_addr.c_str(), NULL);
        assert(mSubscriber != nullptr);
        return 0;
    }

    mSubscriber = zsock_new_sub(address.c_str(), NULL); // here we doesn't subscribe to a topic so we pass `nullptr`
    assert(mSubscriber != nullptr);

    return 0;
}

int Subscriber::Subscribe(string topic){

    zsock_set_subscribe(mSubscriber, topic.c_str()); // function return void so we can't test
    return 0;
}


int Subscriber::ReceiveBlock(string &data){
    zframe_t* topicFrame;

    do{ //We receive 2 frames topic and data; with this we will have just the last reveived frame
        topicFrame = zframe_recv(mSubscriber);
        if (nullptr == topicFrame) {
		    return zmq_errno();
	    }

    }while (zframe_more(topicFrame) != 0);

    for(int i=0; i<zframe_size(topicFrame); i++){
        data.push_back(static_cast<char>(zframe_data(topicFrame)[i]));
    }

    return 0;
}

int Subscriber::ReceiveNoBlock(string &data){
    zpoller_t *poller = zpoller_new (mSubscriber, NULL);

    void* socket = zpoller_wait(poller, POLLER_TIMEOUT);
    if(socket == NULL){ // NULL can be TIMEDOUT or ERROR
        if(zpoller_expired(poller)){
            return EAGAIN;
        } 
        else{
            return zmq_errno();          
        }
    }
    return 0;
}

/*
Example : 

int main(int nargs, char *args[])
{
    Subscriber sub("test");
    string address = "ipc://backend";
    string topic = "topic";
    string data;
    int rc = 0;

    rc = sub.Connect(address);
    if(rc != 0) printf("[ERROR] Connect : %s\n", strerror(rc));

    rc = sub.Subscribe(topic);
    if(rc != 0) printf("[ERROR] Subscribe : %s\n", strerror(rc));

    for(int i = 0; i < 9; i++){ // With PUB we send 10 messages but ZMQ always miss the first so we wait for 9.
        rc = sub.ReceiveBlock(data);
        if(rc != 0) printf("[INFO] ReceiveBlock : %s\n", strerror(rc));
        printf("recv = %s\n", data.c_str());
        data.clear();
    }

    sub.~Subscriber();
    return 0;
}
*/