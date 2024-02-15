#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <pthread.h>
#include <time.h>

#include <czmq.h>

const char *PROXY_SUBSCRIBER_ADDRESS = "ipc://frontend";
const char *PROXY_PUBLISHER_ADDRESS = "ipc://backend";

const int POLLER_TIMEOUT_MS = -1; // Wait indefinitely
const int XPUB_VERBOSER = 1; //permit to have multiple sub-unsub notification for the same topic.

const char * const event_action[]={"UnSubscribe","Subscribe"};

void print_message(zmq_msg_t *m)
{
    int i;
    for (i=0;i<zmq_msg_size(m);i++) printf("%c",((char*)zmq_msg_data(m))[i]);
}

int main()
{
    int zmqErrno;
    int status = 0;
    int a = 0;
    long long more;
    size_t more_size = sizeof more;
    int cpt = 1;

    long statTime = 0;
    struct timeval begin;
    struct timeval end;

    long statSize = 0;
    


    //Proxy init
    void *mContext = zmq_ctx_new();
    void *mPublisher = zmq_socket(mContext, ZMQ_XPUB);
    void *mSubscriber = zmq_socket(mContext, ZMQ_SUB);


    zmq_setsockopt(mSubscriber, ZMQ_SUBSCRIBE, "", 0);

    zmq_setsockopt(mPublisher, ZMQ_XPUB_VERBOSER, &XPUB_VERBOSER, sizeof(XPUB_VERBOSER)); 

    //Socket bind / connect + check
    int rc = zmq_bind(mPublisher, PROXY_PUBLISHER_ADDRESS);
    if(rc != 0){
        int zmqErrno = zmq_errno();
        printf("[Proxy] FAILED to bind Publisher, %d\n",zmqErrno);
        status = zmqErrno;
    }

    rc = zmq_bind(mSubscriber, PROXY_SUBSCRIBER_ADDRESS);
    if(rc != 0){
        zmqErrno = zmq_errno();
        printf("[Proxy] FAILED to bind Subscriber, %d\n",zmqErrno);
        status = zmqErrno;
    }

    //Poller Configuration
    zmq_pollitem_t socketPoller[3];
    socketPoller[0].socket = mPublisher;
    socketPoller[0].fd = 0;
    socketPoller[0].events = ZMQ_POLLIN;

    socketPoller[1].socket = mSubscriber;
    socketPoller[1].fd = 0;
    socketPoller[1].events = ZMQ_POLLIN;

    //Blocking function because of POLLER_TIMEOUT_MS -> wait indefinitely
    while (rc >= 0 && !zsys_interrupted) {
        //printf("Waiting...\n");
        rc = zmq_poll(socketPoller, 2, POLLER_TIMEOUT_MS);

        switch (rc) {
            case -1:
                zmqErrno = zmq_errno();
                printf("[Proxy] FAILED to poll, %d\n",zmqErrno);
                status = zmqErrno;
                break;
            case 0:
                printf("[Proxy] Poller TIMEDOUT\n");
                status = -9;
                break;
            default:
                if(socketPoller[1].revents != 0 ) { //Condition could be items[0].revents & ZMQ_POLLIN
                    //printf("[Proxy] New message event\n");

                    gettimeofday(&begin, NULL);

                    zmq_msg_t topic;
                    zmq_msg_init (&topic);
                    zmq_msg_recv (&topic, mSubscriber, 0);
                    // printf("        topic: ");print_message(&topic);
                    //printf("\n");

                    statSize += zmq_msg_size(&topic);

                    zmq_msg_send(&topic, mPublisher, ZMQ_SNDMORE);

                    do {
                        zmq_msg_t message;
                        zmq_msg_init (&message);
                        zmq_msg_recv (&message, mSubscriber, 0);
                        printf(" message: ");print_message(&message);
                        //printf("        message size : %lu", zmq_msg_size(&message));
                        //printf("\n");
                        
                        statSize += zmq_msg_size(&message);

                        zmq_msg_send(&message, mPublisher, 0);
                        zmq_msg_close(&message);

                        zmq_getsockopt (mSubscriber, ZMQ_RCVMORE, &more, &more_size);
                        if (more) printf(" there is more here\n");
                    } while (more);

                    zmq_msg_close(&topic);  

                    gettimeofday(&end, NULL);

                    statTime += (end.tv_sec - begin.tv_sec) * 1000000 + (end.tv_usec - begin.tv_usec);

                    printf("\rn = %i Forward time : %d µs       |           Forward size : %d o", cpt, statTime, statSize);
                    cpt++;
                }

                if(socketPoller[0].revents != 0 ){
                    a++;
                    printf("[Proxy] New subscription event\n");
                    zmq_msg_t notif;
                    zmq_msg_init (&notif);
                    zmq_msg_recv (&notif, mPublisher, 0); //topic == 0AAA not the same as sub 0->bit

                    //printf(" topic #%d, size %lu, action: %s ",a,zmq_msg_size(&topic),event_action[(int)((char*)zmq_msg_data(&topic))[0]]);print_message(&topic);
                    printf("        notif: "); print_message(&notif);
                    printf("\n");          

                    if((int)((char*)zmq_msg_data(&notif))[0] == 1){
                        printf("    Subscribe => Return cached value \n");
                        zmq_msg_t message;
                        zmq_msg_init_size (&message, 256);      //buffer 256 
                        zmq_msg_t topic;
                        zmq_msg_init_size (&topic, zmq_msg_size(&notif)-1);

                        //find message value in cache 
                        memcpy (zmq_msg_data(&message), "Return cached value", strlen("Return cached value"));
                        memcpy (zmq_msg_data(&topic), zmq_msg_data(&notif)+1, zmq_msg_size(&notif)-1);

                        printf("        topic: "); print_message(&topic);
                        printf("\n");
                        printf("        message: "); print_message(&message);
                        printf("\n");

                        //int rc = zmq_msg_send(&topic, mPublisher, ZMQ_SNDMORE); // Send topic otherwise sub will not receive 
                        if(rc < 0 ) printf("Error sending topic");

                        //rc = zmq_msg_send(&message, mPublisher, 0);
                        if(rc < 0 ) printf("Error sending message");

                        zmq_msg_close(&message);
                        zmq_msg_close(&topic);
                    }
                    else{
                        printf("    Unsubscribe => Nothing to do \n");
                    }
                    
                    zmq_msg_close(&notif);
                }
                break;
        }
    }
    return status;
}
