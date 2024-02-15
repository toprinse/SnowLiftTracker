#include "Publisher.h"


Publisher::Publisher(std::string identity): mPublisher(nullptr){
    this->mIdentity = identity;
}

Publisher::~Publisher(){
    if(mPublisher != nullptr){
        zsock_destroy(&mPublisher);
        mPublisher = nullptr;
    }
}

int Publisher::Bind(std::string address, bool connect){

    if (connect){
        string connect_addr = ">" + address;
        mPublisher = zsock_new_pub(connect_addr.c_str());
        assert(mPublisher != nullptr);
        return 0;
    }

    mPublisher = zsock_new_pub(address.c_str());
    assert(mPublisher != nullptr);

    return 0;
}

int Publisher::Publish(std::string topic, std::string data){
    int rc =0;

    zframe_t* topicFrame = zframe_new(topic.c_str(), strlen(topic.c_str()));
	if (nullptr == topicFrame) {
		printf("error creating frame\n");
		return 1;
		}

	zframe_t* dataFrame = zframe_new(data.c_str(), strlen(data.c_str()));
	if (nullptr == dataFrame) {
		printf("error creating frame 2\n");
		return 1;
	}

	rc = zframe_send(&topicFrame, mPublisher, ZFRAME_MORE);
    if(rc != 0) return zmq_errno();
	rc = zframe_send(&dataFrame, mPublisher, 0);
    if(rc != 0) return zmq_errno();

	zframe_destroy(&topicFrame);
	zframe_destroy(&dataFrame);

    return rc;
}

/*
Example : 

int main(int nargs, char *args[])
{
    Publisher pub("test");
    string address = "ipc://backend";
    string data = "Data my data my data";
    string topic = "topic";
    int rc = 0;

    rc = pub.Bind(address.c_str());

    for(int i = 1; i < 11; i++){
        rc = pub.Publish(topic, data);
        usleep(1000000);
    }

    pub.~Publisher();
    return 0;
}
*/